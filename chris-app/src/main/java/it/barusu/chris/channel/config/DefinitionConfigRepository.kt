package it.barusu.chris.channel.config

import org.springframework.data.jpa.repository.JpaRepository
import java.util.*

interface DefinitionConfigRepository : JpaRepository<DefinitionConfig, Long> {

    fun findByChannelNo(channelNo: String): Optional<DefinitionConfig>

}
