package gaur.himanshu.imagesearchapp.data.pagingSource

import android.util.Log
import androidx.paging.PagingSource
import androidx.paging.PagingState
import gaur.himanshu.imagesearchapp.data.mappers.DelivererDotToDelivererMapper
import gaur.himanshu.imagesearchapp.data.mappers.mapAll
import gaur.himanshu.imagesearchapp.data.model.remote.ApiService
import gaur.himanshu.imagesearchapp.domain.model.Deliverer
import kotlinx.coroutines.delay

class DelivererPagingSource(
    private val apiService: ApiService,
    private  val name : String,
    private  val mapper : DelivererDotToDelivererMapper
): PagingSource<Int, Deliverer>() {
    override fun getRefreshKey(state: PagingState<Int, Deliverer>): Int? {
        return state.anchorPosition?.let {
            state.closestPageToPosition(it)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(it)?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Deliverer> {
        val page = params.key ?: 1
        val pageSize = params.loadSize

        return try {
            delay(3000)

            val response = apiService.getDeliverer(name = name, page = page)

            // Log the response
            Log.d("DelivererPagingSource", "API Response for page $page: $response")

            val delivererList = response.deliverers ?: emptyList() // ✅ avoid null crash

            LoadResult.Page(
                data = mapper.mapAll(delivererList),
                prevKey = if (page == 1) null else page - 1,
                nextKey = if (delivererList.size < pageSize) null else page + 1
            )
        } catch (e: Exception) {
            Log.e("DelivererPagingSource", "Error fetching deliverers on page $page", e)
            LoadResult.Error(e)
        }
    }


}