package by.nrstudio.shambambukli.db.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import by.nrstudio.shambambukli.model.Cell

@Dao
interface CellDao {

    companion object {
        const val TABLE_NAME = "cells"
    }

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(it: Cell): Long

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(it: List<Cell>): MutableList<Long>

    @Query(value = "SELECT * FROM $TABLE_NAME ORDER BY id ASC")
    suspend fun getAll(): MutableList<Cell>

    @Query(value = "SELECT * FROM $TABLE_NAME WHERE id = :id")
    suspend fun getItem(id: String): Cell?

    @Query(value = "SELECT * FROM $TABLE_NAME ORDER BY id ASC")
    suspend fun getItems(): MutableList<Cell>

    @Query(value = "SELECT * FROM $TABLE_NAME ORDER BY id ASC LIMIT :limit OFFSET :offset")
    suspend fun getItems(offset: Int, limit: Int): MutableList<Cell>

    @Update
    suspend fun update(it: Cell)

    @Delete
    suspend fun delete(it: Cell)

    @Query("DELETE FROM $TABLE_NAME")
    suspend fun deleteAll()
}
