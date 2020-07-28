package com.panda.jetpackdemo.dataBinding

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ObservableField
import com.panda.jetpackdemo.R
import com.panda.jetpackdemo.databinding.ActivityDatabindingBinding
import com.panda.jetpackdemo.databinding.ActivityDatabindingMvvmBinding
import com.panda.jetpackdemo.paging_room.User
import com.panda.jetpackdemo.paging_room.UserModel

class MvvmActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Obtain the ViewModel component.
        val userModel: UserViewModel by viewModels()

        // Inflate view and obtain an instance of the binding class.
        val binding: ActivityDatabindingMvvmBinding =
            DataBindingUtil.setContentView(this, R.layout.activity_databinding_mvvm)

        // Assign the component to a property in the binding class.
        binding.viewmodel = userModel
    }
}