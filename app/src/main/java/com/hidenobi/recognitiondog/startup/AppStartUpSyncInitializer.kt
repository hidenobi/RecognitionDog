package com.hidenobi.recognitiondog.startup

import android.content.Context
import android.util.Log
import androidx.startup.Initializer
import androidx.work.ExistingWorkPolicy
import androidx.work.WorkManager
import androidx.work.WorkManagerInitializer
import com.hidenobi.recognitiondog.worker.RemoteConfigSyncWorker

class AppStartUpSyncInitializer : Initializer<Unit> {
    override fun create(context: Context) {
        val worker = WorkManager.getInstance(context)
        worker.enqueueUniqueWork(
            RemoteConfigSyncWorker.UNIQUE_NAME,
            ExistingWorkPolicy.KEEP,
            RemoteConfigSyncWorker.Request,
        )
    }

    override fun dependencies(): List<Class<out Initializer<*>>> {
        return listOf(WorkManagerInitializer::class.java)
    }
}
