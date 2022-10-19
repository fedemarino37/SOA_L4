package com.example.TP2.presentation.internal.di.components;


import android.app.Activity;

import com.example.TP2.presentation.internal.di.PerActivity;
import com.example.TP2.presentation.internal.di.modules.ActivityModule;

import dagger.Component;

/**
 * A base component upon which fragment's components may depend.
 * Activity-level components should extend this component.
 */
@PerActivity
@Component(dependencies = ApplicationComponent.class, modules = ActivityModule.class)
interface ActivityComponent {
    //Exposed to sub-graphs.
    Activity activity();
}