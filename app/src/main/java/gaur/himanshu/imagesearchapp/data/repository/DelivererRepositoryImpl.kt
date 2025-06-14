package gaur.himanshu.imagesearchapp.data.repository

import android.util.Log
import androidx.paging.Pager
import androidx.paging.PagingConfig
import gaur.himanshu.imagesearchapp.data.mappers.DelivererDotToDelivererMapper
import gaur.himanshu.imagesearchapp.data.model.remote.ApiService
import gaur.himanshu.imagesearchapp.data.pagingSource.DelivererPagingSource
import gaur.himanshu.imagesearchapp.domain.model.Deliverer
import gaur.himanshu.imagesearchapp.domain.repository.DelivererRepository
import javax.inject.Inject

class DelivererRepositoryImpl @Inject constructor(
    private val apiService: ApiService,
    private val mapper: DelivererDotToDelivererMapper,

): DelivererRepository  {
    override fun getDeliverers(name: String): Pager<Int, Deliverer> {
        Log.d("DelivererRepository", "Fetching deliverers for query: $name")
        return Pager(
            config = PagingConfig(
                pageSize = 10,
                prefetchDistance = 1,
                enablePlaceholders = false,
                initialLoadSize = 10
            ),
            pagingSourceFactory = {
                DelivererPagingSource(
                    apiService = apiService,
                    name = name,
                    mapper = mapper
                )
            }
        )
    }
}