package com.panda.jetpackdemo.paging_room

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class User(@PrimaryKey(autoGenerate = true) val id: Int,
                val name: String)