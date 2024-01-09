package it.demo.satispay.beerbox.models.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "word_table")
data class Word(@PrimaryKey @ColumnInfo(name = "word") val word: String){
    companion object {
        fun populateData(): Array<Word> {
            return arrayOf(
                Word("Blonde"),
                Word("Lager"),
                Word("Malts"),
                Word("Stouts & Porters")
            )
        }
    }

}
