package com.example.yummy2.di

import android.content.Context
import com.example.yummy2.data.DataStoreRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object MainModule {

    @Provides
    @Singleton
    fun ProvideDataStoreRepository ( @ApplicationContext context: Context ) = DataStoreRepository(context = context)
}