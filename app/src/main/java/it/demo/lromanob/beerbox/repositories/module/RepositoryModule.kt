package it.demo.lromanob.beerbox.repositories.module

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import it.demo.lromanob.beerbox.repositories.beers.BeersRepository
import it.demo.lromanob.beerbox.repositories.beers.BeersRepositoryImpl
import it.demo.lromanob.beerbox.repositories.filterword.FilterWordRepository
import it.demo.lromanob.beerbox.repositories.filterword.FilterWordRepositoryImpl

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