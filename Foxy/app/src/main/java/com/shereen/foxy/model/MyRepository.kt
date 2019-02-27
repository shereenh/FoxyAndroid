package com.shereen.foxy.model

import android.util.Log
import androidx.annotation.WorkerThread
import androidx.lifecycle.LiveData
import com.shereen.foxy.Constants
import com.shereen.foxy.model.retrofit.RetrofitFactory
import com.shereen.foxy.model.room.ResponseDao
import com.shereen.foxy.model.room.ResponseEntity
import kotlinx.coroutines.*
import java.lang.Exception

class MyRepository(private val responseDao: ResponseDao) {

    val retrofitService = RetrofitFactory.makeRetrofitService()

    val oneImage: LiveData<ResponseEntity> = responseDao.getOne()

    val allImages: LiveData<List<ResponseEntity>> = responseDao.getAll()

    fun requestFox(){
        GlobalScope.launch(Dispatchers.Main) {
            val request = retrofitService.makeFoxCall()
            try{
                val response = request.await()
                Log.d(Constants.LOGGER, response.body().toString())
                //save to db
                if (response.body() != null) {
                    insertAfterDelete(response.body()!!)
                }
            } catch(e: Exception){
                Log.d(Constants.LOGGER, e.toString())
            }
        }
    }

    @WorkerThread
    suspend fun insertAfterDelete(response: ResponseEntity){
        GlobalScope.launch {
            val query = async(Dispatchers.IO){
                responseDao.deleteAll()
                responseDao.insert(response)
            }
            query.await()
        }
    }

    @WorkerThread
    suspend fun insertOneImage(response: ResponseEntity){
       GlobalScope.launch {
           val query = async(Dispatchers.IO){
               responseDao.insert(response)
           }
           query.await()
       }
    }

    @WorkerThread
    suspend fun deleteAllImages(){
        GlobalScope.launch {
            val query = async(Dispatchers.IO) {
                responseDao.deleteAll()
            }
            query.await()
        }
    }

}