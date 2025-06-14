package gaur.himanshu.imagesearchapp.data.model.remote.dto

import com.google.gson.annotations.SerializedName

data class DelivererResponseDto(
    val page: Int,
    @SerializedName("per_page") val perPage: Int,
    @SerializedName("total_pages") val totalPages: Int,
    @SerializedName("total_results") val totalResults: String,
    @SerializedName("deliverers") val deliverers: List<DelivererDto>?
)
