package com.hidenobi.recognitiondog.worker

import android.app.Notification
import android.content.Context
import android.util.Log
import androidx.hilt.work.HiltWorker
import androidx.work.Constraints
import androidx.work.CoroutineWorker
import androidx.work.NetworkType
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkerParameters
import com.google.firebase.crashlytics.FirebaseCrashlytics
import com.google.firebase.remoteconfig.FirebaseRemoteConfig
import com.hidenobi.base.remoteconfig.extension.remoteLogic
import com.hidenobi.base.remoteconfig.initializer.SampleRemoteInitializer
import com.hidenobi.recognitiondog.di.DispatcherDefault
import com.hidenobi.recognitiondog.di.DispatcherIO
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext
import kotlinx.coroutines.withTimeoutOrNull
import javax.inject.Inject


/**
 * @author: Vo Huu Tuan
 * @since:  9:48 AM 3/19/2025
 * */

@HiltWorker
class RemoteConfigSyncWorker @AssistedInject constructor(
    @Assisted appContext: Context,
    @Assisted workerParams: WorkerParameters,
) : CoroutineWorker(appContext, workerParams) {

    private val remoteConfig: FirebaseRemoteConfig by lazy {
        FirebaseRemoteConfig.getInstance()
    }
    private val ioDispatcher: CoroutineDispatcher by lazy {
        Dispatchers.IO
    }
    private val crashlytics: FirebaseCrashlytics by lazy {
        FirebaseCrashlytics.getInstance()
    }

    override suspend fun doWork(): Result {
        return kotlin.runCatching {
            withContext(ioDispatcher) {
                val success = withTimeoutOrNull(SYNC_TIMEOUT_MILLIS) {
                    remoteConfig.fetchAndActivate().await()
                } ?: return@withContext Result.retry()
                if (success) {
                    SampleRemoteInitializer.syncWithRemoteConfig(remoteConfig)
                }
                return@withContext Result.success()
            }
        }.getOrElse {
            crashlytics.recordException(it)
            return Result.failure()
        }
    }

    companion object {
        private val constraints = Constraints.Builder()
            .setRequiredNetworkType(NetworkType.CONNECTED)
            .build()
        val Request = OneTimeWorkRequestBuilder<RemoteConfigSyncWorker>()
            .setConstraints(constraints)
            .build()

        const val UNIQUE_NAME = "RemoteConfigSyncWorker"

        const val SYNC_TIMEOUT_MILLIS = 30_000L
    }
}