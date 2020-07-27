package com.panda.jetpackdemo.setting

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.panda.jetpackdemo.R

class SettingActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_setting)

        supportFragmentManager
            .beginTransaction()
            .replace(R.id.settings_container, SettingFragment())
            .commit()
    }

}