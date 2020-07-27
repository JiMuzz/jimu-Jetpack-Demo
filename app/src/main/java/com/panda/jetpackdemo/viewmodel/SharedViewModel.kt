package com.panda.jetpackdemo.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.panda.jetpackdemo.paging_room.User


class SharedViewModel : ViewModel() {
    var userData = MutableLiveData<User>()

    fun select(item: User) {
        userData.value = item
    }

    override fun onCleared() {
        super.onCleared()
    }
}