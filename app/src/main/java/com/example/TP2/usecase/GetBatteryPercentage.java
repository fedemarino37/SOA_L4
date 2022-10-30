package com.example.TP2.usecase;

import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.BatteryManager;

import com.example.TP2.repository.BatteryRepository;

public class GetBatteryPercentage {
    BatteryRepository batteryRepository;

    public GetBatteryPercentage() {
        batteryRepository = new BatteryRepository();
    }

    public String getPercentage(Context ctx) {
        return batteryRepository.getPercentage(ctx);
    }
}
