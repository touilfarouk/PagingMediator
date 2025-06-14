package gaur.himanshu.imagesearchapp

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import gaur.himanshu.imagesearchapp.data.model.local.DelivererDao
import gaur.himanshu.imagesearchapp.data.model.local.RemoteKeysDao
import gaur.himanshu.imagesearchapp.data.model.local.entities.RemoteKey
import gaur.himanshu.imagesearchapp.data.model.local.entities.DelivererEntity

@Database(
    entities = [DelivererEntity::class, RemoteKey::class],
    version = 1,
    exportSchema = true
)
abstract class AppDatabase : RoomDatabase() {

    companion object {
        fun getInstance(context: Context) =
            Room.databaseBuilder(context, AppDatabase::class.java, "app_db")
                .build()
    }

    abstract fun getDelivererDao(): DelivererDao
    abstract  fun getRemoteKeyDao(): RemoteKeysDao

}