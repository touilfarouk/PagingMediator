package gaur.himanshu.imagesearchapp.domain.model

import java.util.UUID

data class Deliverer(
    val name: String,
    val id: String =  UUID.randomUUID().toString()
)