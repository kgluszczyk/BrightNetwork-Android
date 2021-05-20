package com.brightnetwork.summerfests

import androidx.room.Dao
import androidx.room.Database
import androidx.room.Insert
import androidx.room.Query
import androidx.room.RoomDatabase

@Database(entities = [FestivalDTO::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun festivalsDao() : FestivalsDao
}

@Dao
interface FestivalsDao {

    @Query("DELETE FROM festivaldto")
    fun delete()

    @Query("SELECT * FROM festivaldto ORDER BY festivaldto.cost DESC")
    fun get() :List<FestivalDTO>

    @Insert
    fun insert(festivals: List<FestivalDTO>)
}