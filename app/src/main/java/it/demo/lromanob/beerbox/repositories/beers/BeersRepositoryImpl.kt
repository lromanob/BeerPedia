
package it.demo.lromanob.beerbox.repositories.beers

import androidx.paging.Pager
import androidx.paging.PagingConfig
import it.demo.lromanob.beerbox.networking.ApiClient
import it.demo.lromanob.beerbox.networking.ApiService
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
