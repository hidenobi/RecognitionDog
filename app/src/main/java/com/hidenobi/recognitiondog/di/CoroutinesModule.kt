package com.hidenobi.recognitiondog.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import javax.inject.Qualifier

@Module
@InstallIn(SingletonComponent::class)
class CoroutinesModule {

    @Provides
    @DispatcherIO
    fun provideCoroutinesDispatcherIO(): CoroutineDispatcher {
        return Dispatchers.IO
    }

    @Provides
    @DispatcherMain
    fun provideCoroutinesDispatcherMain(): CoroutineDispatcher {
        return Dispatchers.Main
    }

    @Provides
    @DispatcherDefault
    fun provideCoroutinesDispatcherDefault(): CoroutineDispatcher {
        return Dispatchers.Default
    }

    @Provides
    @DispatcherIO
    fun provideCoroutinesScopeIO(@DispatcherIO dispatcher: CoroutineDispatcher): CoroutineScope {
        return CoroutineScope(dispatcher)
    }

    @Provides
    @DispatcherMain
    fun provideCoroutinesScopeMain(@DispatcherMain dispatcher: CoroutineDispatcher): CoroutineScope {
        return CoroutineScope(dispatcher)
    }

    @Provides
    @DispatcherDefault
    fun provideCoroutinesScopeDefault(@DispatcherDefault dispatcher: CoroutineDispatcher): CoroutineScope {
        return CoroutineScope(dispatcher)
    }
}

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class DispatcherIO

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class DispatcherMain

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class DispatcherDefault