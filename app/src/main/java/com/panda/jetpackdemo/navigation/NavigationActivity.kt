package com.panda.jetpackdemo.navigation

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.os.bundleOf
import androidx.navigation.findNavController
import com.panda.jetpackdemo.R

class NavigationActivity :AppCompatActivity(){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_navigation)



    }

    fun onClick(v: View) {
        //不带参数
        v.findNavController().navigate(R.id.action_blankFragment_to_blankFragment2)

        //带参数
        var bundle = bundleOf("amount" to "amount")
        v.findNavController().navigate(R.id.action_blankFragment_to_blankFragment2, bundle)

    }
}