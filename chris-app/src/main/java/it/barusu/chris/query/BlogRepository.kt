package it.barusu.chris.query

import it.barusu.chris.query.Blog
import org.springframework.data.jpa.repository.JpaRepository

interface BlogRepository : JpaRepository<Blog, Long>