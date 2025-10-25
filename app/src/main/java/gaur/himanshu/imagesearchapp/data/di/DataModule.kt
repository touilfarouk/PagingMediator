package gaur.himanshu.imagesearchapp.data.di

import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import gaur.himanshu.imagesearchapp.AppDatabase
import gaur.himanshu.imagesearchapp.data.mappers.DelivererEntityToDelivererMapper
import gaur.himanshu.imagesearchapp.data.model.local.DelivererDao
import gaur.himanshu.imagesearchapp.data.model.local.RemoteKeysDao
import gaur.himanshu.imagesearchapp.data.model.remote.ApiService
import gaur.himanshu.imagesearchapp.data.repository.DelivererRepositoryImpl
import gaur.himanshu.imagesearchapp.domain.repository.DelivererRepository
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

/**
 * Dagger Hilt module for providing data layer dependencies
 * Includes network, database, and repository dependencies
 */
@InstallIn(SingletonComponent::class)
@Module
object DataModule {

    /**
     * Provides Retrofit instance for API calls
     */
    @Provides
    @Singleton
    fun provideRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://onta.dz/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    /**
     * Provides API service interface
     */
    @Provides
    fun provideApiService(retrofit: Retrofit): ApiService {
        return retrofit.create(ApiService::class.java)
    }

    /**
     * Provides deliverer repository implementation
     */
    @Provides
    fun provideImageRepository(
        apiService: ApiService,
        delivererDao: DelivererDao,
        remoteKeyDao: RemoteKeysDao,
        delivererEntityToDelivererMapper: DelivererEntityToDelivererMapper
    ): DelivererRepository {
        return DelivererRepositoryImpl(
            apiService = apiService,
            delivererDao = delivererDao,
            remoteKeyDao = remoteKeyDao,
            delivererEntityToDelivererMapper = delivererEntityToDelivererMapper,
        )
    }

    /**
     * Provides Room database instance
     */
    @Provides
    @Singleton
    fun provideAppDatabase(@ApplicationContext context: Context): AppDatabase {
        return AppDatabase.getInstance(context)
    }

    /**
     * Provides deliverer DAO for database operations
     */
    @Singleton
    @Provides
    fun provideImageDao(appDatabase: AppDatabase): DelivererDao {
        return appDatabase.getDelivererDao()
    }

    /**
     * Provides remote keys DAO for pagination state management
     */
    @Singleton
    @Provides
    fun provideRemoteKeyDao(appDatabase: AppDatabase): RemoteKeysDao {
        return appDatabase.getRemoteKeyDao()
    }




}