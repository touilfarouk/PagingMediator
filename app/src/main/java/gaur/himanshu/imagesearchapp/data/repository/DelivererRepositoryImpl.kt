package gaur.himanshu.imagesearchapp.data.repository

import android.util.Log
import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.map
import gaur.himanshu.imagesearchapp.data.mappers.DelivererDotToDelivererMapper
import gaur.himanshu.imagesearchapp.data.mappers.DelivererEntityToDelivererMapper
import gaur.himanshu.imagesearchapp.data.model.local.DelivererDao
import gaur.himanshu.imagesearchapp.data.model.local.RemoteKeysDao
import gaur.himanshu.imagesearchapp.data.model.remote.ApiService
import gaur.himanshu.imagesearchapp.data.pagingSource.DelivererPagingSource
import gaur.himanshu.imagesearchapp.data.pagingSource.DelivererRemoteMediator
import gaur.himanshu.imagesearchapp.domain.model.Deliverer
import gaur.himanshu.imagesearchapp.domain.repository.DelivererRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class DelivererRepositoryImpl @Inject constructor(
    private val apiService: ApiService,
    private val mapper: DelivererDotToDelivererMapper,
    private val delivererDao: DelivererDao,
    private val remoteKeyDao: RemoteKeysDao,
    private val delivererEntityToDelivererMapper: DelivererEntityToDelivererMapper

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

    @OptIn(ExperimentalPagingApi::class)
    override fun getRemoteMediatorDeliverers(name: String): Flow<PagingData<Deliverer>> {
        return Pager(
            config = PagingConfig(
                pageSize = 10,
                prefetchDistance = 1,
                enablePlaceholders = true,
                initialLoadSize = 10
            ),

            pagingSourceFactory = {
                delivererDao.getDeliverers(name)
            },
            remoteMediator = DelivererRemoteMediator(
                query = name,
                delivererDao = delivererDao,
                remoteKeyDao = remoteKeyDao,
                apiService = apiService
            ),

        ).flow
            .map { pagingData ->
                pagingData.map { entity ->
                    delivererEntityToDelivererMapper.map(entity)
                }
            }
    }

}