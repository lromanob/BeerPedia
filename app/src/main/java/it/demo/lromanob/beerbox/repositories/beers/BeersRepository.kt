package it.demo.lromanob.beerbox.repositories.beers

import androidx.paging.PagingData
import it.demo.lromanob.beerbox.models.BeerItem
import kotlinx.coroutines.flow.Flow

interface BeersRepository {
    fun getBeers(name: String, pageSize: Int): Flow<PagingData<BeerItem>>
}