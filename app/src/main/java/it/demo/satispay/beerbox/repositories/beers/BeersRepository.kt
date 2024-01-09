package it.demo.satispay.beerbox.repositories.beers

import androidx.paging.PagingData
import it.demo.satispay.beerbox.models.BeerItem
import kotlinx.coroutines.flow.Flow

interface BeersRepository {
    fun getBeers(name: String, pageSize: Int): Flow<PagingData<BeerItem>>
}