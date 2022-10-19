package com.example.TP2.presentation.internal.di.modules;

import android.content.Context;

import com.example.TP2.data.executor.JobExecutor;
import com.example.TP2.data.repository.DollarRepository;
import com.example.TP2.domain.executor.PostExecutionThread;
import com.example.TP2.domain.executor.ThreadExecutor;
import com.example.TP2.presentation.AndroidApplication;
import com.example.TP2.presentation.UIThread;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Dagger module that provides objects which will live during the application lifecycle.
 */
@Module
public class ApplicationModule {
    private final AndroidApplication application;

    public ApplicationModule(AndroidApplication application) {
        this.application = application;
    }

    @Provides @Singleton Context provideApplicationContext() {
        return this.application;
    }

    @Provides @Singleton ThreadExecutor provideThreadExecutor(JobExecutor jobExecutor) {
        return jobExecutor;
    }

    @Provides @Singleton PostExecutionThread providePostExecutionThread(UIThread uiThread) {
        return uiThread;
    }

    @Provides @Singleton DollarRepository provideUserRepository(DollarRepository dollarRepository) {
        return dollarRepository;
    }
}