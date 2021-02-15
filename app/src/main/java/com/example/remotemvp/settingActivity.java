package com.example.remotemvp;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.util.Pair;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.nightonke.boommenu.BoomButtons.ButtonPlaceEnum;
import com.nightonke.boommenu.BoomButtons.HamButton;
import com.nightonke.boommenu.BoomButtons.OnBMClickListener;
import com.nightonke.boommenu.BoomMenuButton;
import com.nightonke.boommenu.Piece.PiecePlaceEnum;

import java.util.ArrayList;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class settingActivity extends AppCompatActivity {


    private BoomMenuButton bmb;
    private ArrayList<Pair> piecesAndButtons = new ArrayList<>();
    private ImageView imageViewGif;
    TextView txtResponseSettingPAGE;

    String commandMain,whButton;

    public static int seriChandomClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting_items);

        imageViewGif = findViewById(R.id.gifSettingPage);
        txtResponseSettingPAGE = findViewById(R.id.txtResponseSettingPage);

        boomMenuButtonOptions();

        /////////////////// MOVE GIF
        /*from raw folder*/
        Glide.with(this).load(R.raw.setting2).into(imageViewGif);

    }

    private void boomMenuButtonOptions() {
        bmb = (BoomMenuButton) findViewById(R.id.bmb);
        bmb.setPiecePlaceEnum(PiecePlaceEnum.HAM_3);
        bmb.setButtonPlaceEnum(ButtonPlaceEnum.HAM_3);
        for (int i = 0; i < bmb.getPiecePlaceEnum().pieceNumber(); i++) {
            HamButton.Builder builder = new HamButton.Builder();
            builder.listener(new OnBMClickListener() {
                @Override
                public void onBoomButtonClick(int index) {
                    // When the boom-button corresponding this builder is clicked.
//                    Toast.makeText(settingItems.this, "Clicked " + index, Toast.LENGTH_SHORT).show();
                    if(index == 0)          doMangeDoors();
                    else if(index == 1)     doFactory();
                    else if(index == 2)     addAllowList();
                }
            });


            if(i==0) {

                builder.normalImageRes(R.drawable.door3)
                        .normalTextRes(R.string.managedoors)
                        .normalColorRes(R.color.green);


            }
            else if(i==1)
            {
                builder.normalImageRes(R.drawable.factory)
                        .normalTextRes(R.string.factorysetting)
                        .normalColorRes(R.color.yello);

            }
            else if(i==2)
            {
                builder.normalImageRes(R.drawable.report5)
                        .normalTextRes(R.string.accesslist)
                        .normalColorRes(R.color.pink);
            }


            bmb.addBuilder(builder);
        }
    }

    private void addAllowList() {

    }

    private void doMangeDoors() {

            Intent intent = new Intent(settingActivity.this,addActivity.class);
            startActivity(intent);

    }


    private void doFactory() {
    }


}
