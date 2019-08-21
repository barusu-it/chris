<#-- @ftlvariable name="request" type="it.barusu.chris.channel.process.bean.TransactionRequest" -->
<#-- @ftlvariable name="IdUtils" type="it.barusu.chris.util.IdUtils" -->
<#-- @ftlvariable name="DateUtils" type="it.barusu.chris.util.DateUtils" -->
<#setting number_format="######0" />
<#import "WECHAT_Macro.ftl" as macro>
<@macro.compress_single_line>
<?xml version="1.0" encoding="UTF-8"?>
<xml>
    <appid>${request.secret.appId}</appid>
    <mch_id>${request.secret.merchantNo}</mch_id>
    <nonce_str>${IdUtils.uuidWithoutDash()}</nonce_str>
    <out_trade_no>${request.transaction.transactionNo}</out_trade_no>
    <product_id>${request.transaction.transactionNo}</product_id>
    <sign_type>${request.secret.signatureAlgorithm}</sign_type>
    <body>${request.transaction.description}</body>
    <detail>${request.transaction.description}</detail>
    <notify_url>${request.secret.callbackUrl}</notify_url>
    <fee_type>CNY</fee_type>
    <total_fee>${request.transaction.amount * 100}</total_fee>
    <time_start>${request.createdTime.format(DateUtils.of("yyyyMMddHHmmss"))}</time_start>
    <time_expire>${request.transaction.expiredTime.format(DateUtils.of("yyyyMMddHHmmss"))}</time_expire>
    <trade_type>NATIVE</trade_type>
    <sign></sign>
</xml>
</@macro.compress_single_line>