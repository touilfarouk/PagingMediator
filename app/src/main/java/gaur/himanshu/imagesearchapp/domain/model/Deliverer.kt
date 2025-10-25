package gaur.himanshu.imagesearchapp.domain.model

/**
 * Domain model representing a deliverer
 * This is the clean architecture representation used throughout the app
 */
data class Deliverer(
    val id: String,        // Unique identifier for the deliverer
    val name: String,      // Display name of the deliverer
    val productCount: Int = 0  // Number of products this deliverer has
)