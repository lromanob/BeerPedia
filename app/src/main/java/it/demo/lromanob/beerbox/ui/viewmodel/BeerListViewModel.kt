package it.demo.lromanob.beerbox.ui.viewmodel

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.asFlow
import androidx.lifecycle.viewModelScope
import androidx.paging.*
import dagger.hilt.android.lifecycle.HiltViewModel
import it.demo.lromanob.beerbox.models.db.Word
import it.demo.lromanob.beerbox.repositories.beers.BeersRepositoryImpl
import it.demo.lromanob.beerbox.repositories.filterword.FilterWordRepository
import it.demo.lromanob.beerbox.ui.base.BaseViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BeerListViewModel @Inject constructor(private val filterWordRepository: FilterWordRepository,
                                            private val beersRepository: BeersRepositoryImpl,
                                            handle: SavedStateHandle) : BaseViewModel(handle) {



    private lateinit var _wordsFlow: Flow<List<Word>>
    val wordsFlow: Flow<List<Word>>
        get() = _wordsFlow

    companion object {
        const val KEY_FILTER = "beerfilter"
        const val DEFAULT_FILTER = ""
    }

    init {
        getAllWords()
        if (!handle.contains(KEY_FILTER)) {
            handle.set(KEY_FILTER, DEFAULT_FILTER)
        }
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    val beers = handle.getLiveData<String>(KEY_FILTER)
        .asFlow()
        .flatMapLatest { beersRepository.getBeers(it,25) }
        .cachedIn(viewModelScope)

    fun setFilter(filter: String?) {
        handle.set(KEY_FILTER, filter)
    }

    fun insert(word: Word) = viewModelScope.launch {
        filterWordRepository.insert(word)
    }

    fun delete(word: Word) = viewModelScope.launch {
        filterWordRepository.delete(word)
    }

    private fun getAllWords() = launchPagingAsync({
        filterWordRepository.getAllWords()
    }, {
        _wordsFlow = it
    })

}