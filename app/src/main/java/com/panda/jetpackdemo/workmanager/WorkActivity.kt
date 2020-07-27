package com.panda.jetpackdemo.workmanager


import android.os.Build
import android.os.Bundle
import android.os.PersistableBundle
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.work.*
import java.util.concurrent.TimeUnit

class WorkActivity :AppCompatActivity(){


    @RequiresApi(Build.VERSION_CODES.N)
    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState, persistentState)

        //设定约束
        val constraints =
            Constraints.Builder()
                //网络链接的时候使用
                .setRequiredNetworkType(NetworkType.CONNECTED)
                //是否在设备空闲的时候执行
                .setRequiresDeviceIdle(false)
                //是否在低电量的时候执行
                .setRequiresBatteryNotLow(true)
                //是否在内存不足的时候执行
                .setRequiresStorageNotLow(true)
                //是否时充电的时候执行
                .setRequiresCharging(true)
                //延迟执行
                .setTriggerContentMaxDelay(1000 * 1, TimeUnit.MILLISECONDS)
                .build()

        //设定循环任务
        val uploadRequest =
            PeriodicWorkRequestBuilder<UploadLogcatWork>(1, TimeUnit.HOURS)
                .setConstraints(constraints)
                .addTag("uploadTag")
                .build()

        //执行
        WorkManager.getInstance(applicationContext).enqueue(uploadRequest)


        //监听执行结果
        WorkManager.getInstance(this)
//            .getWorkInfosByTagLiveData("uploadTag") //通过tag拿到work
            .getWorkInfoByIdLiveData(uploadRequest.id) //通过id拿到work
            .observe(this, Observer {
                it?.apply {
                    when (this.state) {
                        WorkInfo.State.BLOCKED -> println("BLOCKED")
                        WorkInfo.State.CANCELLED -> println("CANCELLED")
                        WorkInfo.State.RUNNING -> println("RUNNING")
                        WorkInfo.State.ENQUEUED -> println("ENQUEUED")
                        WorkInfo.State.FAILED -> println("FAILED")
                        WorkInfo.State.SUCCEEDED -> println("SUCCEEDED")
                        else -> println("else status ${this.state}")
                    }
                }

            })

    }


    //取消
    fun cancelWork(){
        WorkManager.getInstance(applicationContext).cancelAllWorkByTag("uploadTag")
    }

    fun startLineWork(){
        //图片滤镜1
        val filter1 = OneTimeWorkRequestBuilder<UploadLogcatWork>()
            .build()
        //图片滤镜2
        val filter2 = OneTimeWorkRequestBuilder<UploadLogcatWork>()
            .build()
        //图片滤镜3
        val filter3 = OneTimeWorkRequestBuilder<UploadLogcatWork>()
            .build()
        //图片压缩
        val compress = OneTimeWorkRequestBuilder<UploadLogcatWork>()
            .build()
        //图片上传
        val upload = OneTimeWorkRequestBuilder<UploadLogcatWork>()
            .build()

        WorkManager.getInstance(applicationContext)
            .beginWith(listOf(filter1, filter2, filter3))
            .then(compress)
            .then(upload)
            .enqueue()
    }
}