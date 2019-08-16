package it.barusu.chris.channel.process

import it.barusu.chris.common.ApiException
import org.apache.http.HttpEntity
import org.apache.http.client.HttpClient
import org.apache.http.client.methods.HttpRequestBase

abstract class AbstractProcessor(var httpClient: HttpClient) : Processor {

    override fun <T : Response> execute(request: Request): T {
        throw ApiException(msg = "Request generation is not expected by this processor.")
    }

    override fun <T : Response> handle(content: String, request: Request): T {
        throw ApiException(msg = "Notification is not expected by this processor.")
    }

    fun doExecute(requestBase: HttpRequestBase): HttpEntity = doExecute(httpClient, requestBase)

    fun doExecute(httpClient: HttpClient, requestBase: HttpRequestBase): HttpEntity {
        return httpClient.execute(requestBase).entity
    }
}