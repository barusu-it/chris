package it.barusu.chris

import org.springframework.context.annotation.Configuration
import org.springframework.data.jpa.repository.config.EnableJpaAuditing

// if you want to use @CreatedDate in JPA, you should add @EnableJpaAuditing
// and add @EntityListeners(AuditingEntityListener::class) on you entity class
@EnableJpaAuditing
@Configuration
class JpaAuditingConfiguration