package com.panda.jetpackdemo.camerax

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.annotation.NonNull
import androidx.appcompat.app.AppCompatActivity
import androidx.camera.core.*
import androidx.camera.core.ImageCapture.OutputFileOptions
import androidx.camera.core.ImageCapture.OutputFileResults
import androidx.camera.lifecycle.ProcessCameraProvider
import androidx.core.app.ActivityCompat
import androidx.core.content.ContentProviderCompat
import androidx.core.content.ContextCompat
import com.google.common.util.concurrent.ListenableFuture
import com.panda.jetpackdemo.R
import kotlinx.android.synthetic.main.activity_camera.*
import java.io.File
import java.util.concurrent.Executors


class CameraActivity : AppCompatActivity() {
    private val TAG = "CameraActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_camera)

        checkPermission()
    }


    private var mImageCapture: ImageCapture? = null

    private fun initCamera() {
        val cameraProviderFuture = ProcessCameraProvider.getInstance(this)
        cameraProviderFuture.addListener(Runnable {
            try {
                val cameraProvider = cameraProviderFuture.get()
                val preview = Preview.Builder().build()


                //图片拍摄用例
                mImageCapture = ImageCapture.Builder()
                    .setFlashMode(ImageCapture.FLASH_MODE_AUTO)
                    .build()

                //配置参数（后置摄像头等）
                // Choose the camera by requiring a lens facing
                val cameraSelector =
                    CameraSelector.Builder().requireLensFacing(CameraSelector.LENS_FACING_FRONT)
                        .build()

                //指定要与相机关联的生命周期，该生命周期会告知 CameraX 何时配置相机拍摄会话并确保相机状态随生命周期的转换相应地更改。
                val camera: Camera = cameraProvider.bindToLifecycle(
                    this,
                    cameraSelector,
                    preview,
                    mImageCapture
                )

                //相机预览
                preview.setSurfaceProvider(view_finder.createSurfaceProvider())

            } catch (e: java.lang.Exception) {
                e.printStackTrace()
            }
        }, ContextCompat.getMainExecutor(this))
    }

    fun takePhoto(view: View?) {
        if (mImageCapture != null) {
            val outputFileOptions: OutputFileOptions = OutputFileOptions.Builder(cretaeFile()).build()

            //拍照
            mImageCapture?.takePicture(
                outputFileOptions,
                ContextCompat.getMainExecutor(this),
                object : ImageCapture.OnImageSavedCallback {
                    override fun onImageSaved(@NonNull outputFileResults: OutputFileResults) {
                        //保存成功
                        Log.e(TAG, "success")
                    }

                    override fun onError(@NonNull exception: ImageCaptureException) {
                        //保存失败
                        Log.e(TAG, "fail")
                    }
                })
        }
    }

    //创建文件
    private fun cretaeFile(): File {
        val file = File(externalMediaDirs.firstOrNull()?.path + "/xphoto", "xtest.jpg")
        when {
            file.exists() -> {

            }
            file.parentFile.exists() -> {
                file.createNewFile()
            }
            else -> {
                file.parentFile.mkdirs()
                file.createNewFile()
            }
        }
        return file
    }

    //检查权限
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
            initCamera()
        }
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