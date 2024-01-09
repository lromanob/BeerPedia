package it.demo.satispay.beerbox.repositories.module

import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import it.demo.satispay.beerbox.repositories.db.AppDB
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DBModule {
    @Singleton
    @Provides
    fun provideDatabase(@ApplicationContext appContext: Context) = AppDB.getDatabase(appContext)
}