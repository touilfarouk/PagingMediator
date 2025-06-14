package gaur.himanshu.imagesearchapp.data.di

import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import gaur.himanshu.imagesearchapp.AppDatabase
import gaur.himanshu.imagesearchapp.data.mappers.DelivererDotToDelivererMapper
import gaur.himanshu.imagesearchapp.data.mappers.DelivererEntityToDelivererMapper
import gaur.himanshu.imagesearchapp.data.model.local.DelivererDao
import gaur.himanshu.imagesearchapp.data.model.local.RemoteKeysDao
import gaur.himanshu.imagesearchapp.data.model.remote.ApiService
import gaur.himanshu.imagesearchapp.data.repository.DelivererRepositoryImpl
import gaur.himanshu.imagesearchapp.domain.repository.DelivererRepository
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@InstallIn(SingletonComponent::class)
@Module
object DataModule {
    @Provides
    @Singleton
    fun provideRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://onta.dz/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    fun provideApiService(retrofit: Retrofit): ApiService {
        return retrofit.create(ApiService::class.java)
    }

    @Provides
    fun provideImageRepository(
        apiService: ApiService,
        mapper: DelivererDotToDelivererMapper,
        delivererDao: DelivererDao,
        remoteKeyDao: RemoteKeysDao,
        delivererEntityToDelivererMapper: DelivererEntityToDelivererMapper

    ): DelivererRepository {
        return DelivererRepositoryImpl(
            apiService, mapper,
            delivererDao = delivererDao,
            remoteKeyDao = remoteKeyDao,
            delivererEntityToDelivererMapper = delivererEntityToDelivererMapper,
        )
    }

    @Provides
    @Singleton
    fun provideAppDatabase(@ApplicationContext context: Context): AppDatabase {
        return AppDatabase.getInstance(context)
    }

    @Singleton
    @Provides
    fun provideImageDao(appDatabase: AppDatabase): DelivererDao {
        return appDatabase.getDelivererDao()
    }

    @Singleton
    @Provides
    fun provideRemoteKeyDao(appDatabase: AppDatabase): RemoteKeysDao {
        return appDatabase.getRemoteKeyDao()
    }




}