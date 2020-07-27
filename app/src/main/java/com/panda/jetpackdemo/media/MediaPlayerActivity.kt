package com.panda.jetpackdemo.media

import android.media.AudioManager
import android.media.MediaPlayer
import android.net.Uri
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.panda.jetpackdemo.R

class MediaPlayerActivity : AppCompatActivity() {

    private  var mediaPlayer: MediaPlayer ?= null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        //播放本地文件
        mediaPlayer = MediaPlayer.create(this, R.raw.test_media)
        mediaPlayer?.start()

        //设置播放不息屏 配合权限WAKE_LOCK使用
        mediaPlayer?.setScreenOnWhilePlaying(true)


        //播放本地本地可用的 URI
        val myUri: Uri = Uri.EMPTY
        val mediaPlayer2: MediaPlayer? = MediaPlayer().apply {
            setAudioStreamType(AudioManager.STREAM_MUSIC)
            setDataSource(applicationContext, myUri)
            prepare()
            start()
        }

        //播放网络文件
        val url = "http://........"
        val mediaPlayer3: MediaPlayer? = MediaPlayer().apply {
            setAudioStreamType(AudioManager.STREAM_MUSIC)
            setDataSource(url)
            prepare()
            start()
        }

    }

    override fun onDestroy() {
        super.onDestroy()

        //释放
        mediaPlayer?.release()
        mediaPlayer = null
    }

}