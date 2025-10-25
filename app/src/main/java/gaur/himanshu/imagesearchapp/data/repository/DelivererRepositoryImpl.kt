package gaur.himanshu.imagesearchapp.data.repository

import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.PagingSource
import androidx.paging.map
import gaur.himanshu.imagesearchapp.data.mappers.DelivererEntityToDelivererMapper
import gaur.himanshu.imagesearchapp.data.model.local.DelivererDao
import gaur.himanshu.imagesearchapp.data.model.local.RemoteKeysDao
import gaur.himanshu.imagesearchapp.data.model.local.entities.DelivererEntity
import gaur.himanshu.imagesearchapp.data.model.remote.ApiService
import gaur.himanshu.imagesearchapp.data.pagingSource.DelivererRemoteMediator
import gaur.himanshu.imagesearchapp.domain.model.Deliverer
import gaur.himanshu.imagesearchapp.domain.repository.DelivererRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class DelivererRepositoryImpl @Inject constructor(
    private val apiService: ApiService,
    private val delivererDao: DelivererDao,
    private val remoteKeyDao: RemoteKeysDao,
    private val delivererEntityToDelivererMapper: DelivererEntityToDelivererMapper

): DelivererRepository  {
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

    override fun getAllDeliverers(): PagingSource<Int, DelivererEntity> = delivererDao.getAllDeliverers()

    override fun searchLocalDeliverers(searchName: String): Flow<PagingData<Deliverer>> {
        return Pager(
            config = PagingConfig(
                pageSize = 10,
                prefetchDistance = 1,
                enablePlaceholders = false,
                initialLoadSize = 10
            ),
            pagingSourceFactory = {
                delivererDao.searchDeliverersByName(searchName)
            }
        ).flow
            .map { pagingData ->
                pagingData.map { entity ->
                    delivererEntityToDelivererMapper.map(entity)
                }
            }
    }

}