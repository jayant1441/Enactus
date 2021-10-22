package com.example.enactus.PeriodFragmentFunction.PeriodFragmentRoomDB

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query


@Dao
interface DAO {
    @Insert
    fun insert_in_painEntity(users: PainEntity)

    @Query("Select * from PainEntity order by `Current_date` ")
    fun retrieveData_from_pain_entity(): List<PainEntity>


}