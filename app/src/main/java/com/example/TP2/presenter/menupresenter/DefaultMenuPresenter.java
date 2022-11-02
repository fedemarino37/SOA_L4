package com.example.TP2.presenter.menupresenter;

import android.content.Context;


import com.example.TP2.view.menuview.MenuActivity;

public class DefaultMenuPresenter implements MenuPresenter {

    private MenuActivity menuView;
    //private final MainModel model; // TODO: CAMBIAR

    public DefaultMenuPresenter(MenuActivity menuView){
        this.menuView = menuView;
        //this.model = new DefaultMainModel(); // TODO: CAMBIAR
    }

    @Override
    public void onButtonClick(Context ctx) {
        //this.model.sendMessage(ctx, this);
    }

    @Override
    public void onDestroy() {
        this.menuView = null;
    }
}
