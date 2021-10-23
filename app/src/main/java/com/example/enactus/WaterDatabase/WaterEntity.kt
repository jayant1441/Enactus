package com.example.enactus.WaterDatabase

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity
data class WaterEntity(
    @PrimaryKey var date: String,
    @ColumnInfo(name = "water_drink_today") val water_drink_today: String,
    @ColumnInfo(name = "day_of_month") var day_of_month: Int,
    @ColumnInfo(name = "month_name") var month_name: Int
)