package it.barusu.chris.sample.query

import org.springframework.data.jpa.repository.JpaRepository

interface BlogRepository : JpaRepository<Blog, Long>