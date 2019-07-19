package it.barusu.chris.channel.process.bean

import it.barusu.chris.common.BankCardType
import it.barusu.chris.common.IdType

data class BankAccount(var bankAccountNo: String,
                       var bankAcronym: String,
                       var channelBankCode: String,
                       var bankAccountName: String,
                       var bankCardType: BankCardType = BankCardType.UNKNOWN,
                       var bankReservedPhone: String,
                       var cvv2: String,
                       var validThru: String,
                       var pinCode: String,
                       var idNo: String,
                       var idType: IdType = IdType.ID_CARD
)