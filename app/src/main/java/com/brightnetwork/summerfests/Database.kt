package com.brightnetwork.summerfests

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Database
import androidx.room.Insert
import androidx.room.Query
import androidx.room.RoomDatabase
import kotlinx.coroutines.flow.Flow

@Database(entities = [FestivalDTO::class], version = 3)
abstract class AppDatabase : RoomDatabase() {

    abstract fun festivalsDao(): FestivalsDao
}

@Dao
interface FestivalsDao {

    @Query("DELETE FROM festivaldto")
    fun delete()

    @Query("SELECT * FROM festivaldto ORDER BY festivaldto.cost DESC")
    fun get(): Flow<List<FestivalDTO>>

    @Insert
    fun insert(festivals: List<FestivalDTO>)
}