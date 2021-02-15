package com.example.remotemvp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.remotemvp.presenter.IOpenDoorPresenter;
import com.example.remotemvp.presenter.OpenDoorPresenterCompl;
import com.example.remotemvp.view.IOpenDoorView;

public class openDoorActivity extends AppCompatActivity implements IOpenDoorView, View.OnClickListener {
    private IOpenDoorPresenter openDoorPresenter;
    ImageView butON1;
    public TextView txtRespond;
    public Spinner name_Spinner;
    TextView txtBUTTON;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_open_door);

        defineWidgets();

        Log.d("aaa","defineWidgets()");
        openDoorPresenter = new OpenDoorPresenterCompl(this,openDoorActivity.this);
        Log.d("aaa","openDoorPresenter");
        openDoorPresenter.getLocationPermission();
        openDoorPresenter.turnONlocation();
        Log.d("aaa","turnONlocation()");
        openDoorPresenter.getSpinnerValuesAndSet();
        Log.d("aaa","getSpinnerValuesAndSet()");

        openDoorPresenter.getPreferences();

        butON1.setOnClickListener(this);
    }



    private void defineWidgets() {

        butON1 = (ImageView) findViewById(R.id.butTurnOn);
        txtRespond = findViewById(R.id.txtResponse);
        txtBUTTON =findViewById(R.id.txtbutton);
        name_Spinner =findViewById(R.id.txtString);

    }


    @Override
    public void openLocationPage() {
        Intent settingsIntent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
        startActivity(settingsIntent);
    }

    @Override
    public void openWifiPage() {
        Intent intent = new Intent(Settings.ACTION_WIFI_SETTINGS);
        startActivity(intent);
    }

    @Override
    public void putValuesInSpinner(String[] names) {
        ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<String>(
                this,R.layout.spinner_items,names
        );
        spinnerArrayAdapter.setDropDownViewResource(R.layout.spinner_items);
        name_Spinner.setAdapter(spinnerArrayAdapter);
    }

    @Override
    public void setSpinner(int idd) {
        name_Spinner.setSelection(idd);
    }

    @Override
    public void showToast(int resIdd) {
        Toast.makeText(openDoorActivity.this, resIdd, Toast.LENGTH_SHORT).show();
    }


    @Override
    public void onClick(View v) {
        if(v.getId()==R.id.butTurnOn)
            openDoorPresenter.openDoor();

    }

}