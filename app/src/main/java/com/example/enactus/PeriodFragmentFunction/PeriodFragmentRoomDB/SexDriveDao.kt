package com.example.enactus.PeriodFragmentFunction.PeriodFragmentRoomDB

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface SexDriveDao {
    @Insert
    fun insert_in_SexDriveEntity(users: SexDriveEntity)

    @Query("Select * from SexDriveEntity order by `Current_date`")
    fun retrieveData_from_SexDrive_entity(): List<SexDriveEntity>
}

