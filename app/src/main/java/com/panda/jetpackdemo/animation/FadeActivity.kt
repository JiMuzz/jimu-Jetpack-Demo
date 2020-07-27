package com.panda.jetpackdemo.animation

import android.app.ActivityOptions
import android.os.Bundle
import android.transition.Fade
import android.transition.Scene
import android.transition.Transition
import android.transition.TransitionManager
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import com.panda.jetpackdemo.R
import kotlinx.android.synthetic.main.activity_fade.*

class FadeActivity : AppCompatActivity(){

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fade)

        val sceneRoot: ViewGroup = findViewById(R.id.scene_root)
        val aScene: Scene = Scene.getSceneForLayout(sceneRoot, R.layout.a_scene, this)
        val anotherScene: Scene = Scene.getSceneForLayout(sceneRoot, R.layout.another_scene, this)

        titletv.setOnClickListener {
            TransitionManager.go(anotherScene)
        }




    }
}