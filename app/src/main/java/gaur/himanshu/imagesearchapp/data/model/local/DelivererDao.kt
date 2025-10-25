package gaur.himanshu.imagesearchapp.data.model.local

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import gaur.himanshu.imagesearchapp.data.model.local.entities.DelivererEntity

/**
 * Data Access Object for deliverer database operations
 * Provides methods for CRUD operations and search functionality
 */
@Dao
interface DelivererDao {

    /**
     * Insert multiple deliverers, replacing existing ones with same ID
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(item: List<DelivererEntity>)

    /**
     * Insert single deliverer, replacing existing one with same ID
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(item: DelivererEntity)

    /**
     * Get deliverers by exact query match (used by RemoteMediator)
     * @param name exact search query
     * @return PagingSource for query-specific deliverers
     */
    @Query("SELECT * FROM DelivererEntity WHERE `query`=:name")
    fun getDeliverers(name: String): PagingSource<Int, DelivererEntity>

    /**
     * Get all deliverers from database
     * @return PagingSource for all cached deliverers
     */
    @Query("SELECT * FROM DelivererEntity")
    fun getAllDeliverers(): PagingSource<Int, DelivererEntity>

    /**
     * Get count of deliverers corresponding to a query (used by RemoteMediator)
     * @param name search query
     * @return number of deliverers matching the query
     */
    @Query("SELECT COUNT(*) FROM DelivererEntity WHERE `query`=:name")
    suspend fun getCountCorrespondingToQuery(name: String): Int

    /**
     * Search deliverers by name pattern matching
     * Uses LIKE operator for partial name searches
     * @param searchName partial name to search for
     * @return PagingSource for deliverers matching the name pattern
     */
    @Query("SELECT * FROM DelivererEntity WHERE name LIKE '%' || :searchName || '%' ORDER BY name ASC")
    fun searchDeliverersByName(searchName: String): PagingSource<Int, DelivererEntity>
}