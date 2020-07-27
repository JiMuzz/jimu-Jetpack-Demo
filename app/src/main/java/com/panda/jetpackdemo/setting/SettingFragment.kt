package com.panda.jetpackdemo.setting

import android.os.Bundle
import android.widget.Toast
import androidx.preference.Preference
import androidx.preference.PreferenceFragmentCompat
import com.panda.jetpackdemo.R

class SettingFragment : PreferenceFragmentCompat() {
    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        setPreferencesFromResource(R.xml.setting, rootKey)
        val feedbackPreference: Preference? = findPreference("feedback")

        feedbackPreference?.setOnPreferenceClickListener {
            Toast.makeText(context,"hello Setting",Toast.LENGTH_SHORT).show()
            true
        }
    }
}