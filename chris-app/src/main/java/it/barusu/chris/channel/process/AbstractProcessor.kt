package it.barusu.chris.channel.process

abstract class AbstractProcessor : Processor {

    override fun <T : Response> execute(request: Request): T {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}