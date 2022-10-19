package com.example.TP2.presentation.internal.di.components;

import android.content.Context;

import com.example.TP2.domain.executor.PostExecutionThread;
import com.example.TP2.domain.executor.ThreadExecutor;
import com.example.TP2.presentation.internal.di.modules.ApplicationModule;
import com.example.TP2.presentation.view.activity.BaseActivity;

import javax.inject.Singleton;

import dagger.Component;

/**
 * A component whose lifetime is the life of the application.
 */
@Singleton // Constraints this component to one-per-application or unscoped bindings.
@Component(modules = ApplicationModule.class)
public interface ApplicationComponent {
    void inject(BaseActivity baseActivity);

    //Exposed to sub-graphs.
    Context context();
    ThreadExecutor threadExecutor();
    PostExecutionThread postExecutionThread();
}