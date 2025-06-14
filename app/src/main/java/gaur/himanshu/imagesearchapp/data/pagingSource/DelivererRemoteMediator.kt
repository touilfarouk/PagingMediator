package gaur.himanshu.imagesearchapp.data.pagingSource

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import gaur.himanshu.imagesearchapp.data.mappers.DelivererDtoToDelivererEntityMapper
import gaur.himanshu.imagesearchapp.data.mappers.mapAll
import gaur.himanshu.imagesearchapp.data.model.local.DelivererDao
import gaur.himanshu.imagesearchapp.data.model.local.RemoteKeysDao
import gaur.himanshu.imagesearchapp.data.model.local.entities.DelivererEntity
import gaur.himanshu.imagesearchapp.data.model.local.entities.RemoteKey
import gaur.himanshu.imagesearchapp.data.model.remote.ApiService

@OptIn(ExperimentalPagingApi::class)
class DelivererRemoteMediator(
    private val query: String,
    private val delivererDao: DelivererDao,
    private val remoteKeyDao: RemoteKeysDao,
    private val apiService: ApiService
) : RemoteMediator<Int, DelivererEntity>() {

    override suspend fun initialize(): InitializeAction {
        val hasLocalData = delivererDao.getCountCorrespondingToQuery(query) > 0
        return if (hasLocalData) {
            InitializeAction.SKIP_INITIAL_REFRESH
        } else {
            InitializeAction.LAUNCH_INITIAL_REFRESH
        }

    }
    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, DelivererEntity>
    ): MediatorResult {
        val mapper = DelivererDtoToDelivererEntityMapper(query)
        val page = when (loadType) {
            LoadType.REFRESH -> {
                val remoteKey = state.anchorPosition?.let {
                    state.closestItemToPosition(it)?.let {
                        remoteKeyDao.getRemoteKeys(it.id)
                    }
                }
                remoteKey?.nextKey?.minus(1) ?: 1
            }

            LoadType.PREPEND -> {
                val remoteKey = state.pages?.firstOrNull {
                    it.data.isNotEmpty()
                }?.data?.firstOrNull()?.let {
                    remoteKeyDao.getRemoteKeys(it.id)
                }
                remoteKey?.prevKey ?: return MediatorResult.Success(remoteKey != null)
            }

            LoadType.APPEND -> {
                val remoteKey = state.pages?.lastOrNull {
                    it.data.isNotEmpty()
                }?.data?.lastOrNull()?.let {
                    remoteKeyDao.getRemoteKeys(it.id)
                }
                remoteKey?.nextKey ?: return MediatorResult.Success(remoteKey != null)
            }
        }

        if (delivererDao.getCountCorrespondingToQuery(query) > page.times(state.config.pageSize)) {
            return MediatorResult.Success(false)
        }

        return try {
            val response = apiService.getDeliverer(name = query, page = page)
            val remoteDeliverers = response.deliverers?.distinctBy { it.id }
            val endOfPaginationReached = remoteDeliverers?.size!! < state.config.pageSize
            val prevPage = if (page > 1) page - 1 else null
            val nextPage = if (endOfPaginationReached) null else page + 1

            val remoteKeys = remoteDeliverers.map {
                RemoteKey(
                    id = it.id.toString(),
                    prevKey = prevPage,
                    nextKey = nextPage,
                    query = query,

                )
            }
            remoteKeyDao.insertAll(remoteKeys)
            delivererDao.insertAll(mapper.mapAll(remoteDeliverers))
            MediatorResult.Success(endOfPaginationReached)
        } catch (e: Exception) {
            MediatorResult.Error(e)
        }
    }
}