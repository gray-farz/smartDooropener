package com.example.remotemvp.presenter;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.LocationManager;
import android.os.Handler;
import android.os.Looper;
import android.provider.Settings;
import android.util.Log;

import com.example.remotemvp.model.IMEI;
import com.example.remotemvp.view.ImainView;

import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

public class mainPresenterCompl implements ImainPresenter
{
    ImainView imainView;
    Context context;
    IMEI imei;

    public mainPresenterCompl(ImainView imainView,Context context)
    {
        this.imainView = imainView;
        this.context = context;

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


    @Override
    public void initImei() {
        Activity activity = getActivity(context);
        imei = new IMEI(context,activity);
        imei.getIMEINumber();
        Log.d("aaa","imei "+imei.IMEINumber);
        imainView.setTextt(imei.IMEINumber);
    }

}
