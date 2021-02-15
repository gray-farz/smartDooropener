package com.example.remotemvp.model;

import android.content.Context;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.util.Log;

import java.util.List;

public class ModelWithViewOpenDoor implements IModelWithViewOpenDoor
{

    public List<Employee> allemployees;
    public static String[] doorNames, wifiNames,keys;
    Context context;

    public ModelWithViewOpenDoor(Context context)
    {
        this.context=context;

    }


    @Override
    public String[] getValuesFromDatabase()
    {
        Log.d("aaa","getvalue");
        allemployees = new DatabaseHelper(context).getListOfEmployees();
        Log.d("aaa","allemployees.size() "+allemployees.size());
        String[] itemsNames = new String[allemployees.size()];
        doorNames = new String[allemployees.size()+1];
        wifiNames=new String[allemployees.size()+1];
        keys=new String[allemployees.size()+1];
        doorNames[0] = "نام درب";
        keys[0]="key";
        wifiNames[0]="wifiName";
        for(int i=0;i<allemployees.size();i++)
        {
            itemsNames[i] = allemployees.get(i).toString();
            String[] separated =itemsNames[i].split("-");
//            Log.d("aaa","separated.length "+separated.length);
            for (int j = 0; j < separated.length; j++)
            {
//                Log.d("aaa","j "+j);
                if(j==0)    wifiNames[i+1]=separated[j];

                if(j==1)    { keys[i+1]=separated[j]; }

                if(j==2)
                {
                    doorNames[i+1] = separated[j];
//                    Log.d("aaa","i+1 "+(i+1));
//                    Log.d("aaa","doorNames[i] "+doorNames[i]);

                }
            }
        }
        return doorNames;

    }

    @Override
    public String[] getdoorNameBasedOnCurrentWifi() {
        String currentWIFI= getWifiName();
//        Log.d("aaa","-----  currentWifi in  getdoorNameBasedOnCurrentWifi   ------ "+currentWIFI);
        String[] doorNameSetted = {"nulll", "nulll"};

        if(currentWIFI != null)
        {
            String currentWIFIsplit = currentWIFI.substring(1, currentWIFI.length() - 1).trim();
            for(int i=0;i<(allemployees.size()+1);i++)
            {
                //Log.d("aaa","i "+i);
                if(wifiNames[i].equals(currentWIFIsplit))
                {

                    doorNameSetted[0]=String.valueOf(i);
                    doorNameSetted[1]=doorNames[i];
                    //Log.d("aaa"," doorNameSetted[1] "+ doorNameSetted[1]);
                    break;
                }
            }
//            }

        }

        return doorNameSetted;
    }

    @Override
    public String getWifiName() {
        WifiManager manager = (WifiManager) context.getSystemService(Context.WIFI_SERVICE);
        if (manager.isWifiEnabled()) {
            //Log.d("aaa","is Enabled");
            WifiInfo wifiInfo = manager.getConnectionInfo();

            if (wifiInfo != null)
            {
                String str=wifiInfo.getSSID();
                return wifiInfo.getSSID();
            }
            else
            {
                //Log.d("aaa","wifiInfo == null ");
            }

        }
        return null;
    }

    @Override
    public void findKeyThenSocket() {

    }

    @Override
    public String getKeyRelateToDoorName(String doorNameSetted, String keySSID) {
        for(int i=0;i<(allemployees.size()+1);i++)
        {
            Log.d("aaa","i "+i);
            if(doorNames[i].equals(doorNameSetted))
            {
                keySSID=keys[i];
//                Log.d("aaa","wifiNames[i] "+wifiNames[i]);
//                Log.d("aaa","!!!!!! keySSID in findKeyThenSocket "+keySSID);

                break;
            }
        }
        return keySSID;
    }

    @Override
    public void unknownSSID(String currentWIFI) {
        String currentWIFIsplit = currentWIFI.substring(1, currentWIFI.length() - 1).trim();
        if (currentWIFIsplit.equals("unknown ssid") ) currentWIFIsplit="FIRSTTIMEERFAN";
    }

    @Override
    public String getKeySSID(String keySSID) {
        String doorNameSetted = getdoorNameBasedOnCurrentWifi()[1];
        keySSID = getKeyRelateToDoorName(doorNameSetted,keySSID);
        return keySSID;
    }


//    public void getValuesFromDatabase() {
//        Log.d("aaa","getvalue");
//        allemployees = new DatabaseHelper(context).getListOfEmployees();
//        Log.d("aaa","allemployees.size() "+allemployees.size());
//        String[] itemsNames = new String[allemployees.size()];
//        doorNames = new String[allemployees.size()+1];
//        wifiNames=new String[allemployees.size()+1];
//        keys=new String[allemployees.size()+1];
//        doorNames[0] = "نام درب";
//        keys[0]="key";
//        wifiNames[0]="wifiName";
//        for(int i=0;i<allemployees.size();i++)
//        {
//            itemsNames[i] = allemployees.get(i).toString();
//            String[] separated =itemsNames[i].split("-");
////            Log.d("aaa","separated.length "+separated.length);
//            for (int j = 0; j < separated.length; j++)
//            {
////                Log.d("aaa","j "+j);
//                if(j==0)    wifiNames[i+1]=separated[j];
//
//                if(j==1)    { keys[i+1]=separated[j]; }
//
//                if(j==2)
//                {
//                    doorNames[i+1] = separated[j];
////                    Log.d("aaa","i+1 "+(i+1));
////                    Log.d("aaa","doorNames[i] "+doorNames[i]);
//
//                }
//            }
//        }
////        Log.d("aaa","doorNames "+doorNames);
//    }

//
//    public String getWifiName() {
//        WifiManager manager = (WifiManager) contextthis.getSystemService(Context.WIFI_SERVICE);
//
//        if (manager.isWifiEnabled()) {
//            //Log.d("aaa","is Enabled");
//            WifiInfo wifiInfo = manager.getConnectionInfo();
//
//            if (wifiInfo != null)
//            {
//                String str=wifiInfo.getSSID();
//                //Log.d("aaa","str "+str);
//                return wifiInfo.getSSID();
//            }
//            else
//            {
//                //Log.d("aaa","wifiInfo == null ");
//            }
//
//        }
//        return null;
//    }
//
//    private String getKeySSID(String keySSID, Context context)
//    {
//
//        String doorNameSetted = getdoorNameBasedOnCurrentWifi()[1];
//        for(int i=0;i<(allemployees.size()+1);i++)
//        {
//            Log.d("aaa","i "+i);
//            if(doorNames[i].equals(doorNameSetted))
//            {
//                keySSID=keys[i];
////                Log.d("aaa","wifiNames[i] "+wifiNames[i]);
////                Log.d("aaa","!!!!!! keySSID in findKeyThenSocket "+keySSID);
//
//                break;
//            }
//        }
//
//        return keySSID;
//    }
//
//    public String encode(String rawStr)
//    {
//        Random random = new Random();
//        int num=random.nextInt(9999);
//        Log.d("aaa","num "+num);
//        rawStr = "V0OUT1"+num+rawStr;
//        return  rawStr;
//    }
//
//    public void creatSocket(String keySSID, Context context, Activity activity) {
////        Log.d("aaa","####   go to client    ####");
//        IMEI imei = new IMEI(context,activity);
//        imei.getIMEINumber();
//        if(imei.IMEINumber.equals(""))
//            Toast.makeText(context, R.string.nullIMEI, Toast.LENGTH_SHORT).show();
//
//        String commandMain,whButton;
//        commandMain="ACTIONkeyDETAILS"+keySSID+"IMEI"+imei.IMEINumber;
//        commandMain = encode(commandMain);
//        whButton="GAMENUMBER";
//
//        Client myClient = new Client("192.168.4.1",80, textViewthis, textViewthis, commandMain,whButton, activity);
//        myClient.execute();
//    }
//
//    public void findKeyThenSocket(String wifiNAME, Context context, Activity activity) {
//
////        Log.d("aaa","currentWIFI "+wifiNAME);
//        String keySSID="";
//        String currentWIFIsplit = wifiNAME.substring(1, wifiNAME.length() - 1).trim();
//        if (currentWIFIsplit.equals("unknown ssid") ) currentWIFIsplit="FIRSTTIMEERFAN";
//
//        keySSID = getKeySSID(keySSID,context);
//
//        if(keySSID!="")
//        {
//            Log.d("aaa","=====  keySSID ====== "+keySSID);
//            creatSocket(keySSID,context,activity);
//        }
//
//        else
//        {
////            Toast.makeText(this, "کلید یافت نشد", Toast.LENGTH_SHORT).show();
//
//        }
//    }
//
//    public void openDoor(Context context, Activity activity) {
//        Log.d("aaa","openDoor");
//        String currentWIFI= getWifiName();
////        Log.d("aaa","currentWifi "+currentWIFI);
////                Toast.makeText(openDoorActivity.this, "currentWifi in butON "+currentWIFI, Toast.LENGTH_SHORT).show();
//        if(currentWIFI == null)
//        {
//            Intent intent = new Intent(Settings.ACTION_WIFI_SETTINGS);
//            activity.startActivityForResult(intent,8);
//        }
//        else
//        {
//            findKeyThenSocket(currentWIFI,context,activity);
//        }
//    }

}

