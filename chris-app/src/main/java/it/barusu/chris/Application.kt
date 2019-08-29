package it.barusu.chris

import io.vertx.core.DeploymentOptions
import io.vertx.core.Handler
import io.vertx.core.Vertx
import io.vertx.core.VertxOptions
import it.barusu.chris.common.ApiException
import it.barusu.chris.infra.vertx.SpringVerticleFactory
import it.barusu.chris.util.StringUtils
import org.slf4j.LoggerFactory
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.event.ApplicationReadyEvent
import org.springframework.context.event.EventListener
import java.util.concurrent.CountDownLatch
import java.util.concurrent.TimeUnit
import java.util.concurrent.atomic.AtomicBoolean
import javax.annotation.Resource

@SpringBootApplication
class Application {

    companion object {
        @JvmStatic
        private val log = LoggerFactory.getLogger(Application::class.java)
    }

    @Resource
    lateinit var verticleFactory: SpringVerticleFactory

    @EventListener
    fun deployVerticles(event: ApplicationReadyEvent) {
        val vertx = Vertx.vertx(VertxOptions().setWorkerPoolSize(60).setMaxEventLoopExecuteTime(Long.MAX_VALUE))
        vertx.registerVerticleFactory(verticleFactory)

        val deployLatch = CountDownLatch(2)
        val failed = AtomicBoolean(false)
        val routeVerticalName = verticleFactory.prefix() + StringUtils.COLON + RouteVerticle::class.java.name

        vertx.deployVerticle(routeVerticalName) {
            if (it.failed()) {
                log.error("Failed to deploy route verticle.", it.cause())
                failed.compareAndSet(false, true)
            }

            deployLatch.countDown()
        }

        val options = DeploymentOptions().setWorker(true).setInstances(30)
        val springVerticalName = verticleFactory.prefix() + StringUtils.COLON + SpringWorker::class.java.name
        vertx.deployVerticle(springVerticalName, options) {
            if(it.failed()) {
                log.error("Failed to deploy verticle", it.cause())
                failed.compareAndSet(false, true)
            }
            deployLatch.countDown()
        }

        if (!deployLatch.await(10, TimeUnit.SECONDS)) {
            throw ApiException(msg = "Timeout waiting for verticle deployments.")
        } else if (failed.get()) {
            throw ApiException(msg = "Failure while deploying verticles.")
        }

    }
}

fun main(args: Array<String>) {
    SpringApplication.run(Application::class.java, *args)
}