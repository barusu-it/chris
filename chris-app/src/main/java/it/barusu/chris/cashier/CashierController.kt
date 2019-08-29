package it.barusu.chris.cashier

import it.barusu.chris.cashier.query.CashierPayment
import it.barusu.chris.cashier.query.CashierPaymentManager
import it.barusu.chris.util.json.JsonProviderHolder
import org.slf4j.LoggerFactory
import org.springframework.http.MediaType
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.*
import javax.annotation.Resource

@Controller
@RequestMapping("/cashier")
class CashierController {
    companion object {
        @JvmStatic
        private val log = LoggerFactory.getLogger(this::class.java)
    }

    @Resource
    lateinit var cashierPaymentManager: CashierPaymentManager


    @RequestMapping(
            value = ["/payment"],
            method = [RequestMethod.POST],
            produces = [MediaType.APPLICATION_JSON_VALUE])
    @ResponseBody
    fun save(@RequestBody request: CashierPayment): String {
        log.info("processing /cashier/payment.")

        val cashierPayment = cashierPaymentManager.save(request)
        return JsonProviderHolder.JACKSON.convert(cashierPayment)
    }

    @RequestMapping(
            value = ["/payment/{id}"],
            method = [RequestMethod.GET],
            produces = [MediaType.APPLICATION_JSON_VALUE])
    @ResponseBody
    fun findOne(@PathVariable("id") idString: String) :String{
        val id = idString.toLong()
        val optional = cashierPaymentManager.fundById(id)
        @Suppress("UsePropertyAccessSyntax")
        val record: Any = if (optional.isPresent()) optional.get() else mutableMapOf<String, Any>()
        return JsonProviderHolder.JACKSON.convert(record)
    }

}