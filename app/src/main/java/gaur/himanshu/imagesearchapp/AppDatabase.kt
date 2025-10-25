package gaur.himanshu.imagesearchapp

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import gaur.himanshu.imagesearchapp.data.model.local.DelivererDao
import gaur.himanshu.imagesearchapp.data.model.local.RemoteKeysDao
import gaur.himanshu.imagesearchapp.data.model.local.entities.DelivererEntity
import gaur.himanshu.imagesearchapp.data.model.local.entities.RemoteKey

/**
 * Room database for the deliverer search app
 * Stores deliverer data and pagination keys for offline access
 * Uses singleton pattern to ensure single database instance
 */
@Database(
    entities = [DelivererEntity::class, RemoteKey::class],
    version = 2,
    exportSchema = true
)
abstract class AppDatabase : RoomDatabase() {

    /**
     * Provides access to deliverer data operations
     */
    abstract fun getDelivererDao(): DelivererDao

    /**
     * Provides access to remote keys for pagination state management
     */
    abstract fun getRemoteKeyDao(): RemoteKeysDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        /**
         * Gets database instance using singleton pattern
         * Creates database if it doesn't exist
         */
        fun getInstance(context: Context): AppDatabase =
            INSTANCE ?: synchronized(this) {
                INSTANCE ?: buildDatabase(context).also { INSTANCE = it }
            }

        /**
         * Builds the Room database instance
         */
        private fun buildDatabase(context: Context) =
            Room.databaseBuilder(
                context.applicationContext,
                AppDatabase::class.java, "app_db"
            ).build()
    }
}