package com.example.enactus.WaterDatabase

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update


@Dao
interface WaterDAO {

    @Insert
    fun insert_to_water_entity(water_drink_data : WaterEntity )

    @Query("Select * from WaterEntity order by date")
    fun retrieve_data_from_Water_entity() : List<WaterEntity>

    @Update
    fun update_in_water_entity(water_drink_data : WaterEntity )


}