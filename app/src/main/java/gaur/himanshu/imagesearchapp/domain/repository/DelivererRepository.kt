package gaur.himanshu.imagesearchapp.domain.repository

import androidx.paging.PagingData
import androidx.paging.PagingSource
import gaur.himanshu.imagesearchapp.data.model.local.entities.DelivererEntity
import gaur.himanshu.imagesearchapp.domain.model.Deliverer
import kotlinx.coroutines.flow.Flow

/**
 * Repository interface for deliverer data operations
 * Provides different ways to fetch deliverer data
 */
interface DelivererRepository {

    /**
     * Gets deliverers using RemoteMediator pattern
     * Searches API and caches results in local database
     * @param q search query for deliverer names
     * @return Flow of paged deliverer data with offline support
     */
    fun getRemoteMediatorDeliverers(q: String): Flow<PagingData<Deliverer>>

    /**
     * Gets all deliverers from local database
     * @return PagingSource for all cached deliverer entities
     */
    fun getAllDeliverers(): PagingSource<Int, DelivererEntity>

    /**
     * Searches deliverers only in local database using pattern matching
     * @param searchName partial name to search for
     * @return Flow of paged deliverer data matching the search pattern
     */
    fun searchLocalDeliverers(searchName: String): Flow<PagingData<Deliverer>>
}