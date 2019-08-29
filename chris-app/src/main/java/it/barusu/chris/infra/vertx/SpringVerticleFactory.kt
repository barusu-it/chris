package it.barusu.chris.infra.vertx

import io.vertx.core.Verticle
import io.vertx.core.spi.VerticleFactory
import org.springframework.context.ApplicationContext
import org.springframework.context.ApplicationContextAware
import org.springframework.stereotype.Component

@Component
class SpringVerticleFactory :VerticleFactory, ApplicationContextAware {

    private lateinit var applicationContext: ApplicationContext

    override fun blockingCreate(): Boolean {
        // Usually verticle instantiation is fast but since our verticles are Spring Beans,
        // they might depend on other beans/resources which are slow to build/lookup.
        return true
    }

    override fun createVerticle(verticleName: String?, classLoader: ClassLoader?): Verticle {
        // Our convention in this example is to give the class name as verticle name
        val clazz = VerticleFactory.removePrefix(verticleName)
        return applicationContext.getBean(Class.forName(clazz)) as Verticle
    }

    override fun prefix(): String {
        // Just an arbitrary string which must uniquely identify the verticle factory
        return "barusu"
    }

    override fun setApplicationContext(applicationContext: ApplicationContext) {
        this.applicationContext = applicationContext
    }

}