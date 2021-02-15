package com.example.remotemvp.view;

public interface IOpenDoorView {
    public void openLocationPage();
    public void openWifiPage();
    public void putValuesInSpinner(String[] names);
    public void setSpinner(int idd);
    public void showToast(int resIdd);
}
