package com.example.enactus.WaterDatabase

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = arrayOf(WaterEntity::class),version = 1)
abstract class WaterDB : RoomDatabase() {

    abstract fun WaterDAO(): WaterDAO
}