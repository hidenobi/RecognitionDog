package com.hidenobi.recognitiondog.application

import androidx.hilt.work.HiltWorkerFactory
import androidx.work.Configuration
import androidx.work.WorkManager
import com.hidenobi.base.BaseApplication
import dagger.hilt.android.HiltAndroidApp
import javax.inject.Inject


/**
 * @author: Vo Huu Tuan
 * @since:  11:12 AM 3/18/2025
 * */
@HiltAndroidApp
class RecognitionDogApplication : BaseApplication(), Configuration.Provider {
    companion object {
        private lateinit var _instance: RecognitionDogApplication
        fun getInstance(): RecognitionDogApplication {
            return _instance
        }
    }

    @Inject
    lateinit var workerFactory: HiltWorkerFactory

    @Inject
    lateinit var workManager: WorkManager

    override fun onCreate() {
        super.onCreate()
        _instance = this
    }

    override val workManagerConfiguration: Configuration
        get() = Configuration.Builder().setWorkerFactory(workerFactory).build()
}