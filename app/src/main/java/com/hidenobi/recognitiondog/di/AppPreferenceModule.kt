package com.hidenobi.recognitiondog.di

import android.content.Context
import android.content.SharedPreferences
import com.hidenobi.recognitiondog.data.prefs.AppPreference
import com.hidenobi.recognitiondog.data.prefs.PreferenceHelper
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class AppPreferenceModule {
    @Provides
    fun provideSharePreference(@ApplicationContext context: Context): SharedPreferences {
        val preferences =
            context.getSharedPreferences(PreferenceHelper.PREFS_KEY, Context.MODE_PRIVATE)
        return preferences
    }

    @Provides
    fun provideAppPreference(preferences: SharedPreferences): AppPreference {
        return PreferenceHelper(preferences)
    }
}