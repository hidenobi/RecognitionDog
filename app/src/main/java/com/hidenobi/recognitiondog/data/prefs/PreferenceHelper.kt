package com.hidenobi.recognitiondog.data.prefs

import android.content.SharedPreferences

class PreferenceHelper(
    private val sharedPreferences: SharedPreferences
) : AppPreference {
    companion object {
        const val PREFS_KEY = "PREFS_KEY"
    }

}