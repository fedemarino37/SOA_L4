package com.example.TP2;
public class Model implements Contract.ModelMVP{

    @Override
    public void sendMessage(OnSendToPresenter presenter) {
        presenter.onFinished("MENSAJE AL PRESENTADOR");
    }
}
