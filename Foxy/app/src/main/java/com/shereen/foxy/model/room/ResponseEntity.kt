package com.shereen.foxy.model.room

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.shereen.foxy.Constants

@Entity(tableName = Constants.RESPONSE_TABLE)
data class ResponseEntity(@PrimaryKey(autoGenerate = true) var id: Long?,
                          var image: String,
                          var link: String)
{
    constructor():this(null,"","")
}