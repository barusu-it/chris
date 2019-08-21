package it.barusu.chris.channel.config

import org.springframework.data.jpa.repository.JpaRepository
import java.util.*

interface ChannelDefinitionRepository : JpaRepository<ChannelDefinition, Long> {

    fun findByChannelNo(channelNo: String): Optional<ChannelDefinition>

}
