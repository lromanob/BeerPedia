package it.demo.lromanob.beerbox.repositories.filterword

import androidx.annotation.WorkerThread
import it.demo.lromanob.beerbox.models.db.Word
import it.demo.lromanob.beerbox.repositories.db.AppDB
import javax.inject.Inject

class FilterWordRepositoryImpl @Inject constructor(private val db: AppDB): FilterWordRepository {

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    override suspend fun insert(word: Word) {
        db.wordDao().insert(word)
    }

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    override suspend fun delete(word: Word) {
        db.wordDao().delete(word)
    }

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    override suspend fun getAllWords() =
        db.wordDao().getAllWords()
}
