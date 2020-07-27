package com.panda.jetpackdemo.workmanager

import android.content.Context
import androidx.work.Worker
import androidx.work.WorkerParameters


class UploadLogcatWork(appContext: Context, workerParams: WorkerParameters) :
    Worker(appContext, workerParams) {

    override fun doWork(): Result {

        if (isUploadLogcatSuc()) {
            return Result.success()
        } else if (isNeedRetry()){
            return Result.retry()
        }

        return Result.failure()
    }

    fun isUploadLogcatSuc(): Boolean {
        var isSuc: Boolean = false
        return isSuc
    }

    fun isNeedRetry(): Boolean {
        var isSuc: Boolean = false
        return isSuc
    }
}
