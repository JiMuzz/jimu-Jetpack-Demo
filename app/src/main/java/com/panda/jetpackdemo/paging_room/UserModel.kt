package com.panda.jetpackdemo.paging_room

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList

class UserModel(app: Application) : AndroidViewModel(app) {
    val dao = UserDb.get(app).userDao()
    var idNum = 1

    companion object {
        private const val PAGE_SIZE = 10
    }

    //初始化PageList
    val users = LivePagedListBuilder(
        dao.getAllUser(), PagedList.Config.Builder()
            .setPageSize(PAGE_SIZE)
            .setEnablePlaceholders(true)
            .build()
    ).build()

    //插入用户
    fun insert() = ioThread {
        dao.insert(newTenUser())
    }


    //获取新的10个用户
    fun newTenUser(): ArrayList<User> {
        var newUsers = ArrayList<User>()
        for (index in 1..10) {
            newUsers.add(User(0, "bob${++idNum}"))
        }
        return newUsers
    }

}