package com.example.TP2.presentation.presenter;


import androidx.annotation.NonNull;

import com.example.TP2.domain.Dollar;
import com.example.TP2.domain.exception.DefaultErrorBundle;
import com.example.TP2.domain.exception.ErrorBundleInterface;
import com.example.TP2.domain.interactor.DefaultObserver;
import com.example.TP2.domain.interactor.GetDollarList;
import com.example.TP2.presentation.exception.ErrorMessageFactory;
import com.example.TP2.presentation.internal.di.PerActivity;
import com.example.TP2.presentation.mapper.DollarModelDataMapper;
import com.example.TP2.presentation.model.DollarModel;
import com.example.TP2.presentation.view.DollarListView;

import java.util.Collection;
import java.util.List;

import javax.inject.Inject;

@PerActivity
public class DollarListPresenter implements Presenter {
    private DollarListView dollarListView;

    private final GetDollarList getDollarListUseCase;
    private final DollarModelDataMapper dollarModelDataMapper;

    @Inject
    public DollarListPresenter(GetDollarList getDollarListUseCase, DollarModelDataMapper dollarModelDataMapper) {
        this.getDollarListUseCase = getDollarListUseCase;
        this.dollarModelDataMapper = dollarModelDataMapper;
    }

    public void setView(@NonNull DollarListView view) {
        this.dollarListView = view;
    }

    @Override
    public void resume() {

    }

    @Override
    public void pause() {

    }

    @Override
    public void destroy() {
        this.getDollarListUseCase.dispose();
        this.dollarListView = null;
    }

    /**
     * Initializes the presenter by start retrieving the dollar list.
     */
    public void initialize() {
        this.loadUserList();
    }

    /**
     * Loads all users.
     */
    private void loadUserList() {
        this.hideViewRetry();
        this.showViewLoading();
        this.getUserList();
    }

    private void showViewLoading() {
        //this.viewListView.showLoading();
    }

    private void hideViewLoading() {
        //this.viewListView.hideLoading();
    }

    private void showViewRetry() {
        //this.viewListView.showRetry();
    }

    private void hideViewRetry() {
        //this.viewListView.hideRetry();
    }

    private void showErrorMessage(ErrorBundleInterface errorBundle) {
        String errorMessage = ErrorMessageFactory.create(this.dollarListView.context(),
                errorBundle.getException());
        this.dollarListView.showError(errorMessage);
    }

    private void showUsersCollectionInView(Collection<Dollar> usersCollection) {
        final Collection<DollarModel> userModelsCollection = this.dollarModelDataMapper.transform(usersCollection);
        //this.dollarListView.renderUserList(userModelsCollection);
    }

    private void getUserList() {
        this.getDollarListUseCase.execute(new DollarListObserver(), null);
    }

    private final class DollarListObserver extends DefaultObserver<List<Dollar>> {

        @Override public void onComplete() {
            DollarListPresenter.this.hideViewLoading();
        }

        @Override public void onError(Throwable e) {
            DollarListPresenter.this.hideViewLoading();
            DollarListPresenter.this.showErrorMessage(new DefaultErrorBundle((Exception) e));
            DollarListPresenter.this.showViewRetry();
        }

        @Override public void onNext(List<Dollar> dollarList) {
            DollarListPresenter.this.showUsersCollectionInView(dollarList);
        }
    }
}
