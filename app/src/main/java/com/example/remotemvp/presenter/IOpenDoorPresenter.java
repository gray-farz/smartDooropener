package com.example.remotemvp.presenter;

import android.app.Activity;
import android.content.Context;

public interface IOpenDoorPresenter {

    public void getLocationPermission();
    public void turnONlocation();
    public void getSpinnerValuesAndSet();
    public void getPreferences();
    public void openDoor();
}
