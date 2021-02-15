package com.example.remotemvp.model;

import android.content.Context;

public interface IModelWithViewOpenDoor
{
    public String[] getValuesFromDatabase();
    public String[] getdoorNameBasedOnCurrentWifi();
    public String getWifiName();
    public void findKeyThenSocket();
    public String getKeyRelateToDoorName(String doorNameSetted, String keySSID);
    public void unknownSSID(String currentWIFI);
    public String getKeySSID(String keySSID);

}
