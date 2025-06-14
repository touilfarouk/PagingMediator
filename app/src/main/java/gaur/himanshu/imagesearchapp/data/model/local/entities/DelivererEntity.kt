package gaur.himanshu.imagesearchapp.data.model.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class DelivererEntity(
    @PrimaryKey(autoGenerate = false)
    val id : String,
    val name : String,
    val query: String
)
