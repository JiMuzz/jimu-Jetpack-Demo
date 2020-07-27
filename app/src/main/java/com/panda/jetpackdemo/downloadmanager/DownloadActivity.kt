package com.panda.jetpackdemo.downloadmanager

import android.app.DownloadManager
import android.content.Context
import android.database.ContentObserver
import android.database.Cursor
import android.net.Uri
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import androidx.appcompat.app.AppCompatActivity


class DownloadActivity : AppCompatActivity() {

    private var mDownId: Long = 0
    private var mDownloadManager: DownloadManager? = null
    private val observer: DownloadContentObserver = DownloadContentObserver()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    //配置下载参数，enqueue开始下载
    fun download(url: String) {
        mDownloadManager =
            this.getSystemService(Context.DOWNLOAD_SERVICE) as DownloadManager
        val request = DownloadManager.Request(Uri.parse(url))
        // 设置文件夹文件名
        request.setDestinationInExternalPublicDir("lz_download", "test.apk")
        // 设置允许的网络类型
        request.setAllowedNetworkTypes(DownloadManager.Request.NETWORK_WIFI)
        // 文件类型
        request.setMimeType("application/zip")
        // 设置通知是否显示
        request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED)
        //设置通知栏标题
        request.setTitle("apk download")
        //设置通知栏内容
        request.setDescription("*** apk")

        mDownId = mDownloadManager!!.enqueue(request)


        contentResolver.registerContentObserver(mDownloadManager!!.getUriForDownloadedFile(mDownId), true, observer)
    }

    //通过ContentProvider查询下载情况
    fun queryDownloadStatus(){
        val query = DownloadManager.Query()
        //通过下载的id查找
        //通过下载的id查找
        query.setFilterById(mDownId)
        val cursor: Cursor = mDownloadManager!!.query(query)
        if (cursor.moveToFirst()) {
            // 已下载字节数
            val downloadBytes = cursor.getInt(cursor.getColumnIndexOrThrow(DownloadManager.COLUMN_BYTES_DOWNLOADED_SO_FAR))
            // 总字节数
            val allBytes= cursor.getInt(cursor.getColumnIndexOrThrow(DownloadManager.COLUMN_TOTAL_SIZE_BYTES))
            // 状态
            when (cursor.getInt(cursor.getColumnIndex(DownloadManager.COLUMN_STATUS))) {
                DownloadManager.STATUS_PAUSED -> {
                }
                DownloadManager.STATUS_PENDING -> {
                }
                DownloadManager.STATUS_RUNNING -> {
                }
                DownloadManager.STATUS_SUCCESSFUL -> {
                    cursor.close()
                }
                DownloadManager.STATUS_FAILED -> {
                    cursor.close()
                }
            }

        }
    }

    //取消下载，删除文件
    fun unDownLoad(view: View?) {
        mDownloadManager!!.remove(mDownId)
    }


    override fun onDestroy() {
        super.onDestroy()
        contentResolver.unregisterContentObserver(observer)
    }


    //监听下载情况
    inner class DownloadContentObserver : ContentObserver(Handler(Looper.getMainLooper())) {
        override fun onChange(selfChange: Boolean) {
            queryDownloadStatus()
        }
    }

}