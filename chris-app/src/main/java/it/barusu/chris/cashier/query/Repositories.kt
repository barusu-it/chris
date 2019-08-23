package it.barusu.chris.cashier.query

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.mongodb.repository.MongoRepository

interface CashierGroupRepository : JpaRepository<CashierGroup, Long>

interface CashierPaymentRepository : JpaRepository<CashierPayment, Long>

interface CashierPaymentGlobalConfigRepository : MongoRepository<CashierPaymentGlobalConfig, String>

interface CashierPaymentRuleRepository : MongoRepository<CashierPaymentRule, String>