package com.example.enactus.RoomDatabase

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.LocalDate
import java.util.*


@Entity

data class RoomEntity(
    @PrimaryKey val current_date :Int,
    @ColumnInfo(name = "current_day_of_month") val current_day_of_month: Int,
    @ColumnInfo(name = "water_drank") val water_drank: String
)