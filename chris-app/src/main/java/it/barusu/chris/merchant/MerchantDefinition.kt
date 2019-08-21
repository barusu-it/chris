package it.barusu.chris.merchant

import it.barusu.chris.channel.config.ChannelDefinition
import it.barusu.chris.common.DefinitionStatus
import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.LastModifiedDate
import org.springframework.data.jpa.domain.support.AuditingEntityListener
import java.time.LocalDateTime
import javax.persistence.*

@Entity
@EntityListeners(AuditingEntityListener::class)
@Table(name = "cs_merchant_definition", uniqueConstraints = [UniqueConstraint(columnNames = ["merchant_no"])])
data class MerchantDefinition(
        @Id
        @GeneratedValue
        var id: Long? = null,

        @Version
        var version: Long? = null,

        @Column(name = "merchant_no", length = 64, nullable = false)
        var merchantNo: String? = null,

        @Column(name = "name", nullable = false)
        var name: String? = null,

        @Column(name = "description")
        var description: String? = null,

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
    // 1. this field define in class body, so this field will not be used in equal().
    // to fix jpa stack overflow problem.
    // reference:
    // https://stackoverflow.com/questions/3987799/jpa-with-hibernate-3-manytomany-stack-overflow-and-multiple-bag-errors
    // https://kotlinlang.org/docs/reference/data-classes.html
    // 2. you must use this class to add relation data if you want to add relation record for merchant and channel
    // reference:
    // https://www.iteye.com/blog/317722960-2173137
    @ManyToMany(cascade = [CascadeType.ALL], fetch = FetchType.EAGER)
    @JoinTable(name = "cs_merchant_channel",
            joinColumns = [JoinColumn(name = "merchant_id", referencedColumnName = "id")],
            inverseJoinColumns = [JoinColumn(name = "channel_id", referencedColumnName = "id")])
    var channels: MutableSet<ChannelDefinition> = mutableSetOf()
}