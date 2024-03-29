package it.barusu.chris.cashier.query

import com.fasterxml.jackson.annotation.JsonFormat
import io.vertx.codegen.annotations.DataObject
import io.vertx.core.json.JsonObject
import it.barusu.chris.common.CashierType
import it.barusu.chris.common.ChannelType
import it.barusu.chris.common.DefinitionStatus
import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.LastModifiedDate
import org.springframework.data.jpa.domain.support.AuditingEntityListener
import java.time.LocalDateTime
import javax.persistence.*

@Entity
@EntityListeners(AuditingEntityListener::class)
@Table(name = "cs_cashier_payment",
        uniqueConstraints = [UniqueConstraint(columnNames = ["merchant_id", "cashier_type", "channel_type"])],
        indexes = [Index(columnList = "status"), Index(columnList = "name")])
// 此处需要添加DataObject注解，由于vertx-service-proxy生成代码时默认只对原生类型和JsonObject做转换，其他类型需要添加该注解
@DataObject(generateConverter = true)
data class CashierPayment(

        @Id
        @GeneratedValue
        var id: Long? = null,

        @Version
        var version: Long? = null,

        @Column(name = "merchant_id", nullable = false, updatable = false)
        var merchantId: Long? = null,

        @Enumerated(EnumType.STRING)
        @Column(name = "cashier_type", length = 64, nullable = false, updatable = false)
        var cashierType: CashierType? = null,

        @Enumerated(EnumType.STRING)
        @Column(name = "channel_type", length = 64, nullable = false, updatable = false)
        var channelType: ChannelType? = null,

        @Column(name = "group_id")
        var groupId: Long? = null,

        @Column(name = "name")
        var name: String? = null,

        @Column(name = "description")
        var description: String? = null,

        @Column(name = "tips")
        var tips: String? = null,

        @Column(name = "logo")
        var logo: String? = null,

        @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
        @CreatedDate
        @Column(name = "created_time", nullable = false)
        var createdTime: LocalDateTime? = null,

        @LastModifiedDate
        @Column(name = "updated_time", nullable = false)
        var updatedTime: LocalDateTime? = null,

        @Enumerated(EnumType.STRING)
        @Column(name = "status", length = 64, nullable = false)
        var status: DefinitionStatus = DefinitionStatus.ACTIVED
) {
    // @DataObject生成代码时需要JsonObject参数的构造器和toJson方法
    constructor(jsonObject: JsonObject) : this() {
        CashierPaymentConverter.fromJson(jsonObject, this)
    }

    fun toJson(): JsonObject {
        val json = JsonObject()
        CashierPaymentConverter.toJson(this, json)
        return json
    }
}