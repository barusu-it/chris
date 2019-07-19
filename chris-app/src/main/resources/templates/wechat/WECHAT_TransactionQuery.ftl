<#-- @ftlvariable name="request" type="it.barusu.chris.channel.process.bean.TransactionRequest" -->
<#-- @ftlvariable name="IdUtils" type="it.barusu.chris.util.IdUtils" -->
<#setting number_format="######0" />
<#import "WECHAT_Macro.ftl" as macro>
<@macro.compress_single_line>
<?xml version="1.0" encoding="UTF-8"?>
<xml>
    <appid>${request.config.appId}</appid>
    <mch_id>${request.config.merchantNo}</mch_id>
    <nonce_str>${IdUtils.uuidWithoutDash()}</nonce_str>
    <out_trade_no>${request.transaction.transactionNo}</out_trade_no>
    <sign></sign>
</xml>
</@macro.compress_single_line>