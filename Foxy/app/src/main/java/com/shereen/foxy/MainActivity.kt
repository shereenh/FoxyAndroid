package com.shereen.foxy

import android.content.Context
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProviders
import com.shereen.foxy.model.MyRepository
import com.shereen.foxy.model.retrofit.RetrofitFactory
import com.shereen.foxy.model.room.ResponseEntity
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.lang.Exception
import java.util.concurrent.TimeUnit

class MainActivity : AppCompatActivity() {

    lateinit var mViewModel : MyViewModel
    lateinit var picasso : Picasso

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        init()
    }

    fun init(){

        picasso = getPicasso(this)

        mViewModel = ViewModelProviders.of(this).get(MyViewModel::class.java)

        mViewModel.oneFox.observe(this, Observer<ResponseEntity> {
                imgResponse ->
             setImage(imgResponse.image)
        })

        changeFoxButton.setOnClickListener{
            mViewModel.getAFox()
        }
    }


    fun setImage(img: String){

        Log.d(Constants.LOGGER, "Setting image to $img")
        picasso
            .load(img)
            .fit()
            .centerCrop()
            .into(foxImageView)
    }



    fun getPicasso(con: Context):Picasso{

        return Picasso.Builder(con)
            .loggingEnabled(true)
            .listener { picasso, uri, e -> e.printStackTrace() }
            .build()
    }

    fun toast(mes: String){
        Toast.makeText(this, mes, Toast.LENGTH_SHORT).show()
    }

}
