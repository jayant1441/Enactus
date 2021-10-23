package com.example.enactus.PeriodFragmentFunction.PeriodFragmentRoomDB

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class SexDriveEntity(
    @PrimaryKey @NonNull val Current_date: String,
    @ColumnInfo(name = "current_month") val current_month: Int,
    @ColumnInfo(name = "current_day") val current_day: Int,
    @ColumnInfo(name = "SexDriveColumn") val SexDriveColumn: String
)