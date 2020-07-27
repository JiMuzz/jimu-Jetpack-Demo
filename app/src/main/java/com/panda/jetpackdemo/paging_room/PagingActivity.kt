package com.panda.jetpackdemo.paging_room

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.panda.jetpackdemo.R
import kotlinx.android.synthetic.main.activity_main.*

class PagingActivity : AppCompatActivity() {

    private val viewModel by lazy(LazyThreadSafetyMode.NONE) {
        ViewModelProvider(this, object : ViewModelProvider.Factory {
            override fun <T : ViewModel?> create(modelClass: Class<T>): T =
                UserModel(application) as T
        }).get(UserModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //初始化数据
//        viewModel.insert()

        val adapter = UserAdapter()
        rcyview.layoutManager = LinearLayoutManager(this)
        rcyview.adapter = adapter

        // 监听users数据，数据改变调用submitList方法
        viewModel.users.observe(this, Observer(adapter::submitList))

        addbtn.setOnClickListener {
            viewModel.insert()
        }

    }

}