package gaur.himanshu.imagesearchapp.data.model.remote.dto

import com.google.gson.annotations.SerializedName

data class DelivererDto(
    @SerializedName("delivererId") val id: String,
    @SerializedName("name") val name: String,

) {

}