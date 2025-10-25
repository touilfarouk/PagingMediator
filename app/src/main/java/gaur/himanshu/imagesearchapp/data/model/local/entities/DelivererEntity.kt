package gaur.himanshu.imagesearchapp.data.model.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Room database entity for storing deliverer data locally
 * Used for caching API responses and offline access
 */
@Entity
data class DelivererEntity(
    @PrimaryKey(autoGenerate = false)
    val id: String,     // Unique identifier from API
    val name: String,   // Deliverer name
    val query: String   // Search query that fetched this deliverer (for RemoteMediator)
)
