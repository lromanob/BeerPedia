package it.demo.satispay.beerbox.repositories.beers

import androidx.paging.PagingSource
import androidx.paging.PagingSource.LoadResult.Page
import androidx.paging.PagingState
import it.demo.satispay.beerbox.models.BeerItem
import it.demo.satispay.beerbox.networking.ApiService
import retrofit2.HttpException
import java.io.IOException


private const val STARTING_PAGE_INDEX = 1
class PageBeersPagingSource(
    private val apiService: ApiService,
    private val name: String
) : PagingSource<Int, BeerItem>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, BeerItem> {
        return try {
            val nextPageNumber = params.key ?: STARTING_PAGE_INDEX
            val data = apiService.fetchBeers(per_page = params.loadSize, page = nextPageNumber, beer_name = if(name.isNullOrEmpty())null else name)

            Page(
                data = data.body().orEmpty(),
                prevKey = null,
                nextKey = nextPageNumber+1
            )
        } catch (e: IOException) {
            LoadResult.Error(e)
        } catch (e: HttpException) {
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, BeerItem>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }

    }
}
