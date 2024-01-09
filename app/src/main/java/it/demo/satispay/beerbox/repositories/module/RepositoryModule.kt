package it.demo.satispay.beerbox.repositories.module

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import it.demo.satispay.beerbox.repositories.beers.BeersRepository
import it.demo.satispay.beerbox.repositories.beers.BeersRepositoryImpl
import it.demo.satispay.beerbox.repositories.filterword.FilterWordRepository
import it.demo.satispay.beerbox.repositories.filterword.FilterWordRepositoryImpl

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {
    @Binds
    abstract fun bindWordRepository(
        wordRepositoryImpl: FilterWordRepositoryImpl
    ): FilterWordRepository

    @Binds
    abstract fun bindBeersRepository(
        beersRepositoryImpl: BeersRepositoryImpl
    ): BeersRepository

}