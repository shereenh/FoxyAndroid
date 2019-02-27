package com.shereen.foxy

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.shereen.foxy.model.MyRepository
import com.shereen.foxy.model.room.MyDatabase
import com.shereen.foxy.model.room.ResponseEntity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import okhttp3.Dispatcher
import kotlin.coroutines.CoroutineContext

class MyViewModel(application: Application) : AndroidViewModel(application) {

    private var responseEntity : ResponseEntity? = null

    private var parentJob = Job()
    private val coroutineContext: CoroutineContext
    get() = parentJob + Dispatchers.Main
    private val scope = CoroutineScope(coroutineContext)

    private val repository: MyRepository
    val oneFox: LiveData<ResponseEntity>

    init{
        val responseDao = MyDatabase.getInstance(application)!!.responseDao()
        repository = MyRepository(responseDao)
        oneFox = repository.oneImage
        initialize()
    }

    fun insertFox(fox: ResponseEntity) = scope.launch(Dispatchers.IO){
        repository.insertOneImage(fox)
    }

    fun getAFox(){
        repository.requestFox()
    }

    override fun onCleared(){
        super.onCleared()
        parentJob.cancel()
    }

    fun initialize(){
        //check if db has an image and if not then pull an image
        responseEntity = oneFox.value
        Log.d(Constants.LOGGER, "getting this: " + responseEntity.toString())
        if (responseEntity == null) {
//            getAFox()
        }
    }
}