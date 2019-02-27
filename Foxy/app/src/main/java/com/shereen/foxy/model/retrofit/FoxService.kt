package com.shereen.foxy.model.retrofit

import com.shereen.foxy.model.room.ResponseEntity;
import kotlinx.coroutines.Deferred;
import retrofit2.Response;
import retrofit2.http.GET;

interface FoxService {

    @GET("floof/")
    fun makeFoxCall(): Deferred<Response<ResponseEntity>>

}
