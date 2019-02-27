package com.shereen.foxy.model.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.shereen.foxy.Constants

@Database(entities = arrayOf(ResponseEntity::class), version = 1, exportSchema = false)
abstract class MyDatabase: RoomDatabase() {

    abstract fun responseDao(): ResponseDao

    companion object {
        private var INSTANCE: MyDatabase? = null

        fun getInstance(context: Context): MyDatabase? {
            if (INSTANCE == null) {
                synchronized(MyDatabase::class) {
                    INSTANCE = Room.databaseBuilder(
//                        Room.inMemoryDatabaseBuilder(
                        context.applicationContext,
                        MyDatabase::class.java,
                        Constants.DATABASE_NAME)
                        .build()
                }
            }
            return INSTANCE
        }

        fun destroyInstance(){
            INSTANCE = null
        }
    }
}