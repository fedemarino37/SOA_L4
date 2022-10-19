package com.example.TP2.presentation.internal.di.components;

import com.example.TP2.presentation.internal.di.PerActivity;
import com.example.TP2.presentation.internal.di.modules.ActivityModule;
import com.example.TP2.presentation.internal.di.modules.DollarModule;
import com.example.TP2.presentation.view.fragment.DollarListFragment;

import dagger.Component;

@PerActivity
@Component(dependencies = ApplicationComponent.class, modules = {ActivityModule.class, DollarModule.class})
public interface DollarComponent extends ActivityComponent {
    void inject(DollarListFragment dollarListFragment);
}
