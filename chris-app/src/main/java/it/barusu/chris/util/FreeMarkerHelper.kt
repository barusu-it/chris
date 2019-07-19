package it.barusu.chris.util

import freemarker.cache.ClassTemplateLoader
import freemarker.cache.MultiTemplateLoader
import freemarker.template.Configuration
import freemarker.template.Template
import freemarker.template.TemplateExceptionHandler
import org.slf4j.LoggerFactory
import java.io.StringWriter
import java.util.*
import java.util.stream.Collectors

class FreeMarkerHelper(templatePaths: Array<String>) {
    companion object {
        @JvmStatic
        private val log = LoggerFactory.getLogger(this::class.java)
    }

    var configuration: Configuration = Configuration(Configuration.DEFAULT_INCOMPATIBLE_IMPROVEMENTS)

    init {
        configuration.defaultEncoding = StringUtils.UTF_8
        configuration.templateLoader = MultiTemplateLoader(
                Arrays.stream(templatePaths)
                        .map { ClassTemplateLoader(this.javaClass, it) }
                        .collect(Collectors.toList())
                        .toTypedArray())
        configuration.setSetting(Configuration.TEMPLATE_UPDATE_DELAY_KEY_SNAKE_CASE, Integer.MAX_VALUE.toString())
        configuration.templateExceptionHandler = TemplateExceptionHandler.RETHROW_HANDLER
    }

    fun render(template: Template, data: Map<String, Any>): String {
        try {
            val writer = StringWriter()
            template.process(data, writer)
            return writer.toString()
        } catch (ex: Exception) {
            log.error("Failed to render the template - ${template.name}")
            throw RuntimeException("Error rendering the template - ${template.name}", ex)
        }
    }

}