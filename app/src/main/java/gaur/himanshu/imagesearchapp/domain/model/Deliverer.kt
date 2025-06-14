package gaur.himanshu.imagesearchapp.domain.model

import java.util.UUID

data class Deliverer(
    val id: String,
    val name: String,
    val uuid: String = UUID.randomUUID().toString()
)