package com.panda.jetpackdemo.paging_room

import java.util.concurrent.Executors


private val IO_EXECUTOR = Executors.newSingleThreadExecutor()

//切换线程
fun ioThread(f : () -> Unit) {
    IO_EXECUTOR.execute(f)
}