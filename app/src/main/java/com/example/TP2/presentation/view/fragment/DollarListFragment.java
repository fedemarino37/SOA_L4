package com.example.TP2.presentation.view.fragment;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.TP2.presentation.internal.di.components.DollarComponent;
import com.example.TP2.presentation.model.DollarModel;
import com.example.TP2.presentation.presenter.DollarListPresenter;
import com.example.TP2.presentation.view.DollarListView;

import java.util.Collection;

import javax.inject.Inject;

public class DollarListFragment extends BaseFragment implements DollarListView {

    public interface DollarListListener {
        void onDollarClicked(final DollarModel dollarModel);
    }

    @Inject DollarListPresenter dollarListPresenter;
    //@Inject DollarA usersAdapter;



    private DollarListListener dollarListListener;


    public DollarListFragment() {
        setRetainInstance(true);
    }

    @Override
    public void renderDollarList(Collection<DollarModel> dollarModelCollection) {

    }

    @Override public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.getComponent(DollarComponent.class).inject(this);
    }

    @Override public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        /*final View fragmentView = inflater.inflate(R.layout.fragment_user_list, container, false);
        ButterKnife.bind(this, fragmentView);
        setupRecyclerView();
        return fragmentView;*/
        return null;
    }

    @Override public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        this.dollarListPresenter.setView(this);
        if (savedInstanceState == null) {
            this.loadDollarList();
        }
    }



    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void showRetry() {

    }

    @Override
    public void hideRetry() {

    }

    @Override
    public void showError(String message) {

    }

    @Override
    public Context context() {
        return null;
    }

    /**
     * Loads all users.
     */
    private void loadDollarList() {
        this.dollarListPresenter.initialize();
    }
}
