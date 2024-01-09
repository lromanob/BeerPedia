
package it.demo.satispay.beerbox.repositories.beers

import androidx.paging.Pager
import androidx.paging.PagingConfig
import it.demo.satispay.beerbox.networking.ApiClient
import it.demo.satispay.beerbox.networking.ApiService
import javax.inject.Inject

class BeersRepositoryImpl @Inject constructor(): BeersRepository {

    private val apiService = ApiClient.getClient().create(ApiService::class.java)


    override fun getBeers(name: String, pageSize: Int) = Pager(
        PagingConfig(pageSize)
    ) {
        PageBeersPagingSource(
            apiService = apiService,
            name = name
        )
    }.flow
}
