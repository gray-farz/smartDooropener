package com.example.remotemvp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.remotemvp.presenter.ImainPresenter;
import com.example.remotemvp.presenter.mainPresenterCompl;
import com.example.remotemvp.view.ImainView;

public class MainActivity extends AppCompatActivity implements ImainView {
    private ImainPresenter mainPresenter;

    ImageView butOPEN,butSETTING,butREPORT,butTEST;
    TextView txtSHOWIMEI;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Log.d("aaa","main");
        defineWidgets();
        defineClickAction();

        mainPresenter = new mainPresenterCompl(this,MainActivity.this);
        mainPresenter.initImei();

    }

    private void defineWidgets() {
        butOPEN = findViewById(R.id.butOpen);
        butSETTING = findViewById(R.id.butSetting);
        butREPORT =findViewById(R.id.butReport);
        butTEST = findViewById(R.id.butTest);
        txtSHOWIMEI = findViewById(R.id.txtShowIMEI);

    }
    private void defineClickAction()
    {
//        butTEST.setOnClickListener(doTest);
        butOPEN.setOnClickListener(openDoor);
        butSETTING.setOnClickListener(goSetting);
//        butREPORT.setOnClickListener(getReport);

    }

    private View.OnClickListener openDoor = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent(MainActivity.this,openDoorActivity.class);
            startActivity(intent);
        }
    };

    private View.OnClickListener goSetting = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent(MainActivity.this, settingActivity.class);
            Log.d("aaa","go setting");
            startActivity(intent);

        }
    };

    @Override
    public void setTextt(String str)
    {
        txtSHOWIMEI.setText(str);
    }


}