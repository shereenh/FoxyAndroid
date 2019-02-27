package com.shereen.foxy.model.room

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query
import com.shereen.foxy.Constants

@Dao
interface ResponseDao {

    @Query("SELECT * FROM " + Constants.RESPONSE_TABLE)
    fun getAll(): LiveData<List<ResponseEntity>>

    @Query("SELECT * FROM " + Constants.RESPONSE_TABLE + " LIMIT 1")
    fun getOne(): LiveData<ResponseEntity>

    @Insert(onConflict = REPLACE)
    fun insert(response: ResponseEntity)

    @Query("DELETE from " + Constants.RESPONSE_TABLE)
    fun deleteAll()
}