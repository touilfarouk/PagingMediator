package gaur.himanshu.imagesearchapp.data.model.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Room database entity for storing product data locally
 * Used for caching product information from API responses
 */
@Entity
data class ProductEntity(
    @PrimaryKey(autoGenerate = false)
    val id: String,           // Unique identifier (delivererId + productName hash)
    val name: String,         // Product name
    val pricePerAmount: String, // Product price as string
    val delivererId: String,  // Associated deliverer ID
    val query: String         // Search query that fetched this product (for RemoteMediator)
)