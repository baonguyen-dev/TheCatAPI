package com.example.the_cat_api.di

import com.example.the_cat_api.data.repository.cat_repository.CatRepository
import com.example.the_cat_api.data.repository.cat_repository.CatRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object RepositoryModule {
    @Provides
    @Singleton
    fun provideCatRepositoryModule(catRepositoryImpl: CatRepositoryImpl) : CatRepository {
        return catRepositoryImpl
    }
}