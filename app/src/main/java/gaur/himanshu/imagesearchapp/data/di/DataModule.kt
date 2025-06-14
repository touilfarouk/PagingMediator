package gaur.himanshu.imagesearchapp.data.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import gaur.himanshu.imagesearchapp.data.mappers.DelivererDotToDelivererMapper
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

    ): DelivererRepository {
        return DelivererRepositoryImpl(
            apiService, mapper,

        )
    }




}