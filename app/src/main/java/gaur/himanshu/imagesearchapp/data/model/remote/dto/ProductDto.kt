package gaur.himanshu.imagesearchapp.data.model.remote.dto

import com.google.gson.annotations.SerializedName

/**
 * Data Transfer Object for product data from API
 * Represents individual products within a deliverer
 */
data class ProductDto(
    @SerializedName("name") val name: String,                    // Product name
    @SerializedName("pricePerAmount") val pricePerAmount: String // Product price
)