package com.example.enactus.PeriodFragmentFunction.PeriodFragmentRoomDB

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface FluidDao {

    @Insert
    fun insert_in_FluidEntity(users: FluidEntity)

    @Query("Select * from FluidEntity order by `Current_date` desc")
    fun retrieveData_from_fluid_entity(): List<FluidEntity>
}