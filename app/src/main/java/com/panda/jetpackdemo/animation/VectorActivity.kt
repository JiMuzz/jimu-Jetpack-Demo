package com.panda.jetpackdemo.animation

import android.graphics.drawable.Animatable
import android.os.Bundle
import android.view.VelocityTracker
import androidx.appcompat.app.AppCompatActivity
import androidx.dynamicanimation.animation.DynamicAnimation
import androidx.dynamicanimation.animation.SpringAnimation
import androidx.dynamicanimation.animation.SpringForce
import com.panda.jetpackdemo.R
import kotlinx.android.synthetic.main.activity_vector.*


class VectorActivity : AppCompatActivity() {

    //滑动速度跟踪器
    private val velocityTracker: VelocityTracker = VelocityTracker.obtain()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_vector)
        imageView.setOnClickListener {
            (imageView.drawable as Animatable).start()
        }

        val springForce = SpringForce(0.0f)
            .setDampingRatio(0f)  //设置阻尼
            .setStiffness(0.5f)  //设置刚度

        imageView2.setOnClickListener {
            SpringAnimation(imageView2, DynamicAnimation.TRANSLATION_Y).apply {
                spring = springForce
                setStartVelocity(500f) //设置速度
                start()
            }
        }

    }
}