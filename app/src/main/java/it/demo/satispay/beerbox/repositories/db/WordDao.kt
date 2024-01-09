package it.demo.satispay.beerbox.repositories.db

import androidx.room.*
import it.demo.satispay.beerbox.models.db.Word
import kotlinx.coroutines.flow.Flow

@Dao
interface WordDao {

    @Query("SELECT * FROM word_table")
    fun getAllWords(): Flow<List<Word>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(word: Word)

    @Query("DELETE FROM word_table")
    suspend fun deleteAll()

    @Delete
    suspend fun delete(word: Word)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(vararg dataEntities: Word)

}
