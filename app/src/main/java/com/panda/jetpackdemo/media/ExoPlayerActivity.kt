package com.panda.jetpackdemo.media

import android.net.Uri
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.android.exoplayer2.SimpleExoPlayer
import com.google.android.exoplayer2.source.MediaSource
import com.google.android.exoplayer2.source.ProgressiveMediaSource
import com.google.android.exoplayer2.upstream.DataSource
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory
import com.google.android.exoplayer2.util.Util
import com.panda.jetpackdemo.R
import kotlinx.android.synthetic.main.activity_exoplayer.*


class ExoPlayerActivity : AppCompatActivity() {
    var player: SimpleExoPlayer ?= null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_exoplayer)

        //初始化
        player = SimpleExoPlayer.Builder(this).build()
        video_view.player = player
        player?.playWhenReady = true

        //设置播放资源
        val dataSourceFactory: DataSource.Factory = DefaultDataSourceFactory(
            this,
            Util.getUserAgent(this, "yourApplicationName")
        )
        val uri: Uri = Uri.EMPTY
        val videoSource: MediaSource = ProgressiveMediaSource.Factory(dataSourceFactory)
            .createMediaSource(uri)
        player?.prepare(videoSource)
    }

    private fun releasePlayer() {
        //释放
        player?.release()
        player = null
    }
}