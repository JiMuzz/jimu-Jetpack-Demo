package com.panda.jetpackdemo.dataBinding

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ObservableField
import com.panda.jetpackdemo.R
import com.panda.jetpackdemo.databinding.ActivityDatabindingBinding
import com.panda.jetpackdemo.paging_room.User

class DataActivity :AppCompatActivity(){

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val userName = ObservableField<String>()
        userName.set("Bob")

        val binding: ActivityDatabindingBinding = DataBindingUtil.setContentView(
            this, R.layout.activity_databinding)
        binding.user = UserData(userName)
    }
}