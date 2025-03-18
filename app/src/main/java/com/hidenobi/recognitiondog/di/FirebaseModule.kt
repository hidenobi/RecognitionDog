package com.hidenobi.recognitiondog.di

import android.content.Context
import com.google.firebase.FirebaseApp
import com.google.firebase.analytics.FirebaseAnalytics
import com.google.firebase.analytics.ktx.analytics
import com.google.firebase.crashlytics.FirebaseCrashlytics
import com.google.firebase.crashlytics.ktx.crashlytics
import com.google.firebase.ktx.Firebase
import com.google.firebase.remoteconfig.FirebaseRemoteConfig
import com.google.firebase.remoteconfig.ktx.remoteConfig
import com.google.firebase.remoteconfig.ktx.remoteConfigSettings
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class FirebaseModule {
    @Provides
    fun provideFirebaseApp(context: Context): FirebaseApp = FirebaseApp.initializeApp(context)!!

    @Provides
    fun provideAnalytics(): FirebaseAnalytics = Firebase.analytics

    @Provides
    fun provideCrashlytics(): FirebaseCrashlytics = Firebase.crashlytics

    @Provides
    fun provideRemoteConfig(): FirebaseRemoteConfig {
        val configSettings = remoteConfigSettings {
            minimumFetchIntervalInSeconds = ONE_MIN_IN_SEC
        }

        return Firebase.remoteConfig.apply {
            setConfigSettingsAsync(configSettings)
        }
    }

    companion object {
        private const val ONE_MIN_IN_SEC = 60L
    }
}
