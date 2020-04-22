package by.nrstudio.shambambukli.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import by.nrstudio.shambambukli.db.dao.CellDao
import by.nrstudio.shambambukli.model.Cell

@Database(entities = [Cell::class], version = 1)
abstract class Db : RoomDatabase() {

    abstract fun cellDao(): CellDao

    companion object {

        @Volatile
        private var INSTANCE: Db? = null

        fun getInstance(context: Context): Db = INSTANCE ?: synchronized(this) {
            INSTANCE ?: createDatabase(context).also { INSTANCE = it }
        }

        private fun createDatabase(context: Context) =
            Room.databaseBuilder(context.applicationContext, Db::class.java, "app.db")
                .fallbackToDestructiveMigration()
                .build()
    }
}
