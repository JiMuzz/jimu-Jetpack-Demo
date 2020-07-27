package com.panda.jetpackdemo.viewmodel

import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.panda.jetpackdemo.paging_room.User
import java.nio.channels.Selector


class MyFragment1 : Fragment() {
    private lateinit var btn: Button

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val model=activity?.let { ViewModelProvider(it).get(SharedViewModel::class.java) }
        btn.setOnClickListener{
            model?.select(User(0,"bob"))
        }
    }
}


