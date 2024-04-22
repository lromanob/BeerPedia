package it.demo.lromanob.beerbox.repositories.filterword

import it.demo.lromanob.beerbox.models.db.Word
import kotlinx.coroutines.flow.Flow

interface FilterWordRepository {

    suspend fun insert(word: Word)
    suspend fun delete(word: Word)
    suspend fun getAllWords(): Flow<List<Word>>
}
