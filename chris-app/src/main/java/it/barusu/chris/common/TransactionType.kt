package it.barusu.chris.common

enum class TransactionType (val description: String) {
    WITHHOLD("扣款"),
    PAYMENT("付款"),
    RECHARGE("充值"),
    WITHDRAW("提现"),
    TRANSFER("转账"),
    REFUND("退款"),
    ;
}