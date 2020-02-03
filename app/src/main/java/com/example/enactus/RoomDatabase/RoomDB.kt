package com.example.enactus.RoomDatabase

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = arrayOf(RoomEntity::class),version = 1,exportSchema = false)
abstract class RoomDB :RoomDatabase() {

    abstract fun RoomDao() : RoomDAO
}