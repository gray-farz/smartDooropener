package com.example.remotemvp.presenter;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.location.LocationManager;
import android.util.Log;
import android.widget.Toast;

import com.example.remotemvp.MainActivity;
import com.example.remotemvp.R;
import com.example.remotemvp.model.Client;
import com.example.remotemvp.model.IClient;
import com.example.remotemvp.model.IMEI;
import com.example.remotemvp.model.IModelWithViewOpenDoor;
import com.example.remotemvp.model.ModelWithViewOpenDoor;
import com.example.remotemvp.view.IOpenDoorView;

import java.util.Random;

import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

public class OpenDoorPresenterCompl implements IOpenDoorPresenter,IClient {

    IOpenDoorView iOpenDoorView;
    Context context;
    final int MY_PERMISSIONS_REQUEST_READ_CONTACTS=10;
    IModelWithViewOpenDoor iModelWithViewOpenDoor;
    OpenDoorPresenterCompl openDoorPresenterCompl;

    SharedPreferences shPref;
    public static final String MyPref = "MyPrefers";

    public OpenDoorPresenterCompl(IOpenDoorView iOpenDoorView, Context context)
    {
        this.iOpenDoorView = iOpenDoorView;
        this.context = context;
        iModelWithViewOpenDoor = new ModelWithViewOpenDoor(context);
        //openDoorPresenterCompl = new OpenDoorPresenterCompl(iOpenDoorView,context);
    }

    public static Activity getActivity(Context context)
    {
        if (context == null)
        {
            return null;
        }
        else if (context instanceof ContextWrapper)
        {
            if (context instanceof Activity)
            {
                return (Activity) context;
            }
            else
            {
                return getActivity(((ContextWrapper) context).getBaseContext());
            }
        }

        return null;
    }

    public String encode(String rawStr)
    {
        Random random = new Random();
        int num=random.nextInt(9999);
        Log.d("aaa","num "+num);
        rawStr = "V0OUT1"+num+rawStr;
        return  rawStr;
    }


    @Override
    public void getLocationPermission() {
        Log.d("aaa","((((   getLocationPermission");
        if (ContextCompat.checkSelfPermission(context,
                Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            Log.d("aaa","decline");

            // نمایش توضیحات در صورت نیاز
            if (ActivityCompat.shouldShowRequestPermissionRationale(getActivity(context),
                    Manifest.permission.ACCESS_FINE_LOCATION)) {
                Log.d("aaa","with explanation");
                ActivityCompat.requestPermissions(getActivity(context),
                        new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                        MY_PERMISSIONS_REQUEST_READ_CONTACTS);
                // محلی که می توان برای کاربر توضیحات فرستاد
            } else {

                // No explanation needed, we can request the permission.
                Log.d("aaa","no explanation");
                ActivityCompat.requestPermissions(getActivity(context),
                        new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                        MY_PERMISSIONS_REQUEST_READ_CONTACTS);
            }
        }

        else
        {
            Log.d("aaa","accepted before");

        }
    }

    @Override
    public void turnONlocation() {
        LocationManager locationManager = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);
        final boolean gpsEnabled = locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);

        if (!gpsEnabled) {
            iOpenDoorView.openLocationPage();


        }
    }

    @Override
    public void getSpinnerValuesAndSet()
    {

        iOpenDoorView.putValuesInSpinner(iModelWithViewOpenDoor.getValuesFromDatabase());
        String[] valuesForFixInSpinner = iModelWithViewOpenDoor.getdoorNameBasedOnCurrentWifi();

        if(valuesForFixInSpinner[0] != "nulll")
        {
            int indexSpinnerAccordingToDoorName = Integer.valueOf(valuesForFixInSpinner[0]);
            iOpenDoorView.setSpinner(indexSpinnerAccordingToDoorName);
        }

    }

    @Override
    public void getPreferences() {
        shPref = context.getSharedPreferences(MyPref, Context.MODE_PRIVATE);
    }

    @Override
    public void openDoor() {
        String currentWIFI= iModelWithViewOpenDoor.getWifiName();
        if(currentWIFI == null)
        {
            iOpenDoorView.openWifiPage();
        }
        else
        {
            iModelWithViewOpenDoor.unknownSSID(currentWIFI);

            String keySSID="";
            keySSID = iModelWithViewOpenDoor.getKeySSID(keySSID);

            if(keySSID!="")
            {
                Log.d("aaa","=====  keySSID ====== "+keySSID);

                IMEI imei = new IMEI(context,getActivity(context));
                if(imei.IMEINumber.equals(""))
                    iOpenDoorView.showToast(R.string.nullIMEI);

                String commandMain,whButton;
                commandMain="ACTIONkeyDETAILS"+keySSID+"IMEI"+imei.IMEINumber;
                commandMain = encode(commandMain);
                whButton="GAMENUMBER";

                Client myClient = new Client("192.168.4.1",80, commandMain,whButton, openDoorPresenterCompl);
                myClient.execute();
            }

            else
            {
//            Toast.makeText(this, "کلید یافت نشد", Toast.LENGTH_SHORT).show();

            }
        }
    }


    @Override
    public void applyResultOfClient(String result) {
        Log.d("aaa","result in applyResultOfClient "+result);
    }
}
