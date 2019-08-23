package it.barusu.chris.cashier.query

import it.barusu.chris.common.CashierType
import it.barusu.chris.common.DefinitionStatus
import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.LastModifiedDate
import org.springframework.data.jpa.domain.support.AuditingEntityListener
import java.time.LocalDateTime
import javax.persistence.*

@Entity
@EntityListeners(AuditingEntityListener::class)
@Table(name = "cs_cashier_group",
        uniqueConstraints = [UniqueConstraint(columnNames = ["merchant_id", "cashier_type", "group_name"])])
data class CashierGroup(
        @Id
        @GeneratedValue
        var id: Long? = null,

        @Version
        var version: Long? = null,

        @Column(name = "merchant_id", nullable = false)
        var merchantId: Long? = null,

        @Enumerated(EnumType.STRING)
        @Column(name = "cashier_type", length = 64, nullable = false)
        var cashierType: CashierType? = null,

        @Column(name = "group_name", nullable = false)
        var groupName: String? = null,

        @Column(name = "description")
        var description: String? = null,

        @Column(name = "logo")
        var logo: String? = null,

        @CreatedDate
        @Column(name = "created_time", nullable = false)
        var createdTime: LocalDateTime? = null,

        @LastModifiedDate
        @Column(name = "updated_time", nullable = false)
        var updatedTime: LocalDateTime? = null,

        @Enumerated(EnumType.STRING)
        @Column(name = "status", length = 64, nullable = false)
        var status: DefinitionStatus = DefinitionStatus.ACTIVED
)