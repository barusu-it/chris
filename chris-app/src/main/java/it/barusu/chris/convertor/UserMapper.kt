package it.barusu.chris.convertor

import it.barusu.chris.query.Blog
import it.barusu.chris.query.User
import org.mapstruct.Mapper
import org.mapstruct.Mapping

@Mapper
interface UserMapper {

    @Mapping(source = "title", target = "firstName")
    fun from(blog: Blog): User
}