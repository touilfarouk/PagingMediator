package gaur.himanshu.imagesearchapp.data.model.local

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import gaur.himanshu.imagesearchapp.data.model.local.entities.DelivererEntity

@Dao
interface DelivererDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(item: List<DelivererEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(item: DelivererEntity)

    @Query("SELECT * FROM DelivererEntity WHERE `query`=:name")
    fun getDeliverers(name: String): PagingSource<Int, DelivererEntity>

    @Query("SELECT COUNT(*) FROM DelivererEntity WHERE `query` =:name")
    suspend fun countBasedOnQuery(name: String): Int

    @Query("SELECT COUNT(*) FROM DelivererEntity WHERE `query`=:name")
    suspend fun getCountCorrespondingToQuery(name: String): Int

}