package it.barusu.chris.query

import javax.persistence.*

@Entity
@Table(name = "blog")
data class Blog (
        @Id
        @GeneratedValue
        var id: Long = 0,

        @Column
        var title: String? = null
)

