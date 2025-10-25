package gaur.himanshu.imagesearchapp.data.model.remote.dto

import com.google.gson.annotations.SerializedName

/**
 * Data Transfer Object for deliverer data from API
 * Maps API response fields to application data structure
 */
data class DelivererDto(
    @SerializedName("delivererId") val id: String,         // API field: delivererId
    @SerializedName("name") val name: String,              // API field: name
    @SerializedName("products") val products: List<ProductDto>? = null // API field: products
)