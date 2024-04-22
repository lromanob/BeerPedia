package it.demo.lromanob.beerbox.repositories.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import it.demo.lromanob.beerbox.models.db.Word
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

@Database(entities = [Word::class], version = 1, exportSchema = false)
abstract class AppDB : RoomDatabase() {
    abstract fun wordDao(): WordDao


    companion object {
        @Volatile private var instance: AppDB? = null

        fun getDatabase(context: Context): AppDB =
            instance ?: synchronized(this) { instance ?: buildDatabase(context).also { instance = it } }

        private fun buildDatabase(appContext: Context) =
            Room.databaseBuilder(appContext, AppDB::class.java, "beerbox")
                .addCallback(object : Callback() {
                    override fun onCreate(db: SupportSQLiteDatabase) {
                        GlobalScope.launch(Dispatchers.IO){
                            instance!!.wordDao().insertAll(dataEntities = *Word.populateData())
                        }

                    }
                })
                .fallbackToDestructiveMigration()
                .build()

    }
}