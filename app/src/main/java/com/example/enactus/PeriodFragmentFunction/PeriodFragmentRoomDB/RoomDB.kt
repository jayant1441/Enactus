package com.example.enactus.PeriodFragmentFunction.PeriodFragmentRoomDB

import androidx.room.Database
import androidx.room.RoomDatabase


@Database(entities = arrayOf(PainEntity::class, FluidEntity::class , SexDriveEntity::class), version = 4)
abstract class RoomDB : RoomDatabase() {
    abstract fun painDao(): DAO

    abstract fun FluidDao() : FluidDao

    abstract fun SexDriveDao(): SexDriveDao

}