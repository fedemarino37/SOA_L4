package com.example.TP2.model.dollarmodel;

import android.content.Context;
import android.os.BatteryManager;

import com.example.TP2.entity.DollarEntity;
import com.example.TP2.usecase.DefaultGetDollarList;
import com.example.TP2.usecase.GetDollarList;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class DefaultDollarModel implements DollarModel {

    private final GetDollarList getDollarList;
    private final OnSendToPresenter presenter;

    public DefaultDollarModel(OnSendToPresenter presenter) {
        this.getDollarList = new DefaultGetDollarList();
        this.presenter = presenter;
    }

    @Override
    public void getUSBCableStatus(int plugged) {
        if (plugged == BatteryManager.BATTERY_PLUGGED_AC)
            presenter.showUSBCableStatus("AC CONECTADO!");
        else if (plugged == BatteryManager.BATTERY_PLUGGED_USB)
            presenter.showUSBCableStatus("USB CONECTADO!");
        else if(plugged == 0)
            presenter.showUSBCableStatus("CABLE DESCONECTADO!");
    }

    @Override
    public void getDollarList(Context ctx) {
        Observable<Object> dollarEntity = getDollarList.executeWithObservable(ctx);
        dollarEntity.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Object>() {
                    @Override
                    public void onSubscribe(Disposable d) {}

                    @Override
                    public void onNext(Object object) {
                        List<DollarEntity> dollarList = (List<DollarEntity>)object;
                        presenter.setDollarList(dollarList);
                    }

                    @Override
                    public void onError(Throwable e) {
                        presenter.onError(e.getMessage());
                    }

                    @Override
                    public void onComplete() {}
                });
    }
}
