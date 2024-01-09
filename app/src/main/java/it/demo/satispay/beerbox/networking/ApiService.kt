package it.demo.satispay.beerbox.networking

import it.demo.satispay.beerbox.models.BeerItem
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("beers")
    suspend fun fetchBeers(
        @Query("beer_name") beer_name: String? = null,
        @Query("page") page: Int = 1,
        @Query("per_page") per_page: Int = 10
    ): Response<List<BeerItem>>
}