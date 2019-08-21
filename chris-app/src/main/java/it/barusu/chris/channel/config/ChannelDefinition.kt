package it.barusu.chris.channel.config

import it.barusu.chris.common.ChannelType
import it.barusu.chris.common.DefinitionStatus
import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.LastModifiedDate
import org.springframework.data.jpa.domain.support.AuditingEntityListener
import java.time.LocalDateTime
import javax.persistence.*

@Entity
@EntityListeners(AuditingEntityListener::class)
@Table(name = "cs_channel_definition", uniqueConstraints = [UniqueConstraint(columnNames = ["channel_no"])])
data class ChannelDefinition(
        @Id
        @GeneratedValue
        var id: Long? = null,

        @Version
        var version: Long? = null,

        @Column(name = "channel_no", length = 64, nullable = false)
        var channelNo: String? = null,

        @Enumerated(EnumType.STRING)
        @Column(name = "channel_type", length = 64, nullable = false)
        var channelType: ChannelType? = null,

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
)