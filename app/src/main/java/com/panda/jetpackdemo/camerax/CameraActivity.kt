package com.panda.jetpackdemo.camerax

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import android.os.PersistableBundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.camera.core.ImageCapture
import androidx.core.app.ActivityCompat
import com.panda.jetpackdemo.R
import kotlinx.android.synthetic.main.activity_camera.*
import java.io.File
import java.util.concurrent.Executor
import java.util.concurrent.Executors

class CameraActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_camera)

        checkPermission()

        btn_view.setOnClickListener { startCamera() }
    }


    private fun checkPermission() {
        if (ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.CAMERA
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            ActivityCompat.requestPermissions(
                this,
                arrayOf(Manifest.permission.CAMERA), 1000
            )
            return
        } else {
            camera_view.bindToLifecycle(this@CameraActivity)
        }
    }

    fun startCamera() {
        camera_view.takePicture(Executors.newSingleThreadExecutor(),
            object : ImageCapture.OnImageCapturedCallback() {

            })
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        //super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == 1000) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED
            ) {
                //todo:permission granted
                checkPermission()
            } else {
                //todo:permission denied

            }
        }
    }
}