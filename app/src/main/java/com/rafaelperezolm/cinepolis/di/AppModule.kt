package com.rafaelperezolm.cinepolis.di

import android.content.Context
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.rafaelperezolm.cinepolis.common.Const
import com.rafaelperezolm.cinepolis.data.local.AppDatabase
import com.rafaelperezolm.cinepolis.data.local.CinepolisDAO
import com.rafaelperezolm.cinepolis.data.remote.CinepolisClient
import com.rafaelperezolm.cinepolis.data.remote.CinepolisRemoteDataSource
import com.rafaelperezolm.cinepolis.data.remote.CinepolisService
import com.rafaelperezolm.cinepolis.data.repository.CinepolisRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideRetrofit(gson: Gson) : Retrofit = Retrofit.Builder()
        .baseUrl(Const.API_BASE_URL)
        .addConverterFactory(GsonConverterFactory.create(gson))
        .client(CinepolisClient().getClient())
        .build()

    @Provides
    fun provideGson(): Gson = GsonBuilder().create()

    @Provides
    fun provideCinepolisService(retrofit: Retrofit): CinepolisService = retrofit.create(CinepolisService::class.java)

    @Singleton
    @Provides
    fun provideCinepolisRemoteDataSource(cinepolisService: CinepolisService) = CinepolisRemoteDataSource(cinepolisService)

    @Singleton
    @Provides
    fun provideDatabase(@ApplicationContext appContext: Context) = AppDatabase.getDatabase(appContext)

    @Singleton
    @Provides
    fun provideCinepolisDao(db: AppDatabase) = db.characterDao()

    @Singleton
    @Provides
    fun provideRepository(remoteDataSource: CinepolisRemoteDataSource, localDataSource: CinepolisDAO) =
        CinepolisRepository(remoteDataSource, localDataSource)

}