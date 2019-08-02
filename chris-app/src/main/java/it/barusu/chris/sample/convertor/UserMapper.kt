package it.barusu.chris.sample.convertor

import it.barusu.chris.sample.query.Blog
import it.barusu.chris.sample.query.User
import org.mapstruct.Mapper
import org.mapstruct.Mapping

@Mapper
interface UserMapper {

    @Mapping(source = "title", target = "firstName")
    fun from(blog: Blog): User
}