package com.panda.jetpackdemo.lifecycle

import android.content.Context
import android.location.Location
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.OnLifecycleEvent

class LifecycleActivity : AppCompatActivity() {
    private lateinit var myLocationListener: MyLocationListener

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        myLocationListener = MyLocationListener(this) { location ->
            // update UI
        }
        lifecycle.addObserver(myLocationListener)
    }



    internal class MyLocationListener (
        private val context: Context,
        private val callback: (Location) -> Unit
    ): LifecycleObserver {

        @OnLifecycleEvent(Lifecycle.Event.ON_START)
        fun start() {

        }

        @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
        fun stop() {
            // disconnect if connected
        }
    }
}