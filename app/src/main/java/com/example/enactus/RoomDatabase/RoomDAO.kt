package com.example.enactus.RoomDatabase

import androidx.room.Dao
import androidx.room.Insert

@Dao
interface RoomDAO {

    @Insert
    fun insertAll(users: RoomEntity)
}