package gaur.himanshu.imagesearchapp.domain.model

/**
 * Domain model representing a product
 * Clean architecture representation used throughout the app
 */
data class Product(
    val id: String,           // Unique identifier (generated from delivererId + name)
    val name: String,         // Product name
    val pricePerAmount: String, // Product price as string
    val delivererId: String   // Associated deliverer ID
)