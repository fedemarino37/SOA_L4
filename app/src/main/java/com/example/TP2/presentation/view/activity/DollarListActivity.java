package com.example.TP2.presentation.view.activity;

import android.content.Context;
import android.content.Intent;

import com.example.TP2.presentation.internal.di.HasComponent;
import com.example.TP2.presentation.internal.di.components.DaggerDollarComponent;
import com.example.TP2.presentation.internal.di.components.DollarComponent;
import com.example.TP2.presentation.model.DollarModel;
import com.example.TP2.presentation.view.fragment.DollarListFragment;

public class DollarListActivity extends BaseActivity implements HasComponent<DollarComponent>, DollarListFragment.DollarListListener {

    public static Intent getCallingIntent(Context context) {
        return new Intent(context, DollarListActivity.class);
    }

    public DollarComponent dollarComponent;

    private void initializeInjector() {
        this.dollarComponent = DaggerDollarComponent.builder()
                .applicationComponent(getApplicationComponent())
                .activityModule(getActivityModule())
                .build();
    }

    @Override
    public DollarComponent getComponent() {
        return null;
    }

    @Override
    public void onDollarClicked(DollarModel dollarModel) {
        //this.navigator.navigateToUserDetails(this, userModel.getUserId());
    }
}
