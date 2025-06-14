package gaur.himanshu.imagesearchapp.data.model.remote

import gaur.himanshu.imagesearchapp.data.model.remote.dto.DelivererResponseDto
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {
    @GET("/api/deliverers/delivererapi.php")
    suspend fun getDeliverer(
        @Query("name") name: String,
        @Query("page") page: Int,
        @Query("per_page") perPage: Int = 10
    ): DelivererResponseDto
}