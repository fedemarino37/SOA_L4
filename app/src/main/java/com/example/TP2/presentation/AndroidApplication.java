package com.example.TP2.presentation;

import android.app.Application;

import com.example.TP2.presentation.internal.di.components.ApplicationComponent;
import com.example.TP2.presentation.internal.di.components.DaggerApplicationComponent;
import com.example.TP2.presentation.internal.di.modules.ApplicationModule;

/**
 * Android Main Application
 */
public class AndroidApplication extends Application {

    public ApplicationComponent applicationComponent;

    @Override public void onCreate() {
        super.onCreate();
        this.initializeInjector();
    }

    private void initializeInjector() {
        this.applicationComponent = DaggerApplicationComponent.builder()
                .applicationModule(new ApplicationModule(this))
                .build();
    }

    public ApplicationComponent getApplicationComponent() {
        return this.applicationComponent;
    }
}