package it.barusu.chris.channel.config

import it.barusu.chris.common.ChannelType
import it.barusu.chris.common.DefinitionStatus
import javax.persistence.*

@Entity
@Table(name = "cs_channel_definition_config", uniqueConstraints = [UniqueConstraint(columnNames = ["channel_no"])])
data class DefinitionConfig(
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

        @Enumerated(EnumType.STRING)
        @Column(name = "status", length = 64, nullable = false)
        var status: DefinitionStatus = DefinitionStatus.ACTIVED
)