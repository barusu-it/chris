package it.barusu.chris.channel.process.wechat.mapper

import com.thoughtworks.xstream.annotations.XStreamAlias

@XStreamAlias("xml")
data class ResponseMapper(
        @XStreamAlias("return_code")
        var code: String? = null,

        @XStreamAlias("return_msg")
        var message: String? = null,

        @Suppress("SpellCheckingInspection")
        @XStreamAlias("appid")
        var appId: String? = null,

        @XStreamAlias("mch_id")
        var merchantNo: String? = null,

        @XStreamAlias("device_info")
        var deviceInfo: String? = null,

        @XStreamAlias("nonce_str")
        var nonceString: String? = null,

        @XStreamAlias("sign")
        var signature: String? = null,

        @XStreamAlias("out_trade_no")
        var outTradeNo: String? = null,

        @XStreamAlias("attach")
        var attach: String? = null,

        @XStreamAlias("result_code")
        var resultCode: String? = null,

        @XStreamAlias("trade_state")
        var tradeState: String? = null,

        @XStreamAlias("err_code")
        var errorCode: String? = null,

        @XStreamAlias("err_code_des")
        var errorMessage: String? = null,

        @XStreamAlias("trade_type")
        var tradeType: String? = null,

        @XStreamAlias("prepay_id")
        var prepayId: String? = null,

        @XStreamAlias("code_url")
        var codeUrl: String? = null,

        @XStreamAlias("time_end")
        var finishedTime: String? = null,

        @XStreamAlias("trade_state_desc")
        var tradeStatusDescription: String? = null)