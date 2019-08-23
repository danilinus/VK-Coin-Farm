package com.jobs.vkcoinfarm;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.appodeal.ads.Appodeal;
import com.appodeal.ads.InterstitialCallbacks;

public class MainActivity extends AppCompatActivity {

    String appKey = "62608292964873c54191dd4ca24d6ae5e6045fc0a8fac820";

    int bestN = 0;

    public TextView[] txtV = new TextView[7];

    public void BestBay() {
        float bestvalue = Memory.Pice(0);
        int bestn = 0;

        for (int i = 1; i < Memory.Values.length; i++)
            if (Memory.Pice(i) / Memory.Consts[i] < bestvalue / Memory.Consts[bestn]) {
                bestn = i;
                bestvalue = Memory.Pice(i);
            }
        bestN = bestn;
        UpdateValues();
        saveText();
    }

    public void UpdateValues() {
        for (int i = 0; i < txtV.length; i++)
            txtV[i].setText(String.valueOf(Memory.Values[i]));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Appodeal.initialize(this, appKey, Appodeal.BANNER | Appodeal.NON_SKIPPABLE_VIDEO, Memory.ads_sup);
        Appodeal.disableLocationPermissionCheck();
        //Appodeal.setTesting(true);
        Appodeal.setAutoCache(Appodeal.NON_SKIPPABLE_VIDEO | Appodeal.BANNER, false);
        Appodeal.cache(this, Appodeal.NON_SKIPPABLE_VIDEO);
        Appodeal.cache(this, Appodeal.BANNER);
        setContentView(R.layout.activity_main);
        loadText();
        if (Memory.first_login) {
            AlertDialog f = new AlertDialog.Builder(MainActivity.this)
                    .setTitle("Добро пожаловать!")
                    .setMessage("   Данное приложение ориентировано на пользователей сервиса VK Coin.\nОно поможет вам выбрать оптимальный ускоритель. Таким образом вы сможите добиться максимальной скорости добычи.\n\n    Внимание, приложение никак не связано с оффициальным сервисом VK Coin, к сожалению, на данный момент это реализовать не возможно, по этому все данные синхронизируются в ручную.")
                    .setCancelable(false)
                    .setPositiveButton("Я понял", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            Memory.first_login = false;
                            saveText();
                        }
                    })
                    .create();
            f.show();
            f.getButton(AlertDialog.BUTTON_POSITIVE).setTextColor(getResources().getColor(R.color.colorPrimary));
        }
        if (Memory.first_ads) {
            AlertDialog k = new AlertDialog.Builder(MainActivity.this)
                    .setTitle("Разрешение!")
                    .setMessage("Согласны на использование персональных данных для улучшения качества рекламы?")
                    .setCancelable(false)
                    .setPositiveButton("Да", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            Memory.ads_sup = true;
                            Memory.first_ads = false;
                            saveText();
                        }
                    }).setNegativeButton("Нет", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            Memory.ads_sup = false;
                            Memory.first_ads = false;
                            saveText();
                        }
                    })
                    .create();
            k.show();
            k.getButton(AlertDialog.BUTTON_POSITIVE).setTextColor(getResources().getColor(R.color.colorPrimary));
            k.getButton(AlertDialog.BUTTON_NEGATIVE).setTextColor(getResources().getColor(R.color.colorPrimary));
        }
        txtV[0] = findViewById(R.id.cursor_value_txt);
        txtV[1] = findViewById(R.id.videocard_value_txt);
        txtV[2] = findViewById(R.id.videocards_value_txt);
        txtV[3] = findViewById(R.id.super_computer_value_txt);
        txtV[4] = findViewById(R.id.server_value_txt);
        txtV[5] = findViewById(R.id.quantum_computer_value_txt);
        txtV[6] = findViewById(R.id.datacenter_value_txt);
        BestBay();
        ((ImageView) findViewById(R.id.best_bay_img)).setImageResource(Memory.Images[bestN]);
        ((TextView) findViewById(R.id.best_bay_txt)).setText(Memory.Names[bestN]);
        ((TextView) findViewById(R.id.best_bay_txt)).setTextColor(ContextCompat.getColor(this, Memory.Colors[bestN]));
        findViewById(R.id.pay_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Memory.Values[bestN] > 900)
                    Toast.makeText(v.getContext(), "Слишком много", Toast.LENGTH_SHORT).show();
                else {
                    Memory.Values[bestN]++;
                    BestBay();
                    ((ImageView) findViewById(R.id.best_bay_img)).setImageResource(Memory.Images[bestN]);
                    ((TextView) findViewById(R.id.best_bay_txt)).setText(Memory.Names[bestN]);
                    ((TextView) findViewById(R.id.best_bay_txt)).setTextColor(ContextCompat.getColor(getBaseContext(), Memory.Colors[bestN]));
                    Toast.makeText(v.getContext(), "Куплено", Toast.LENGTH_SHORT).show();
                }
            }
        });
        findViewById(R.id.edit_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getBaseContext(), EditActivity.class));
            }
        });
        findViewById(R.id.calculator_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getBaseContext(), CalculatorActivity.class));
            }
        });
        findViewById(R.id.more_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getBaseContext(), MoreActivity.class));
            }
        });
        findViewById(R.id.help_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Appodeal.show(MainActivity.this, Appodeal.NON_SKIPPABLE_VIDEO);
            }
        });
        Appodeal.show(this, Appodeal.BANNER_BOTTOM);
    }

    @Override
    protected void onResume() {
        super.onResume();
        Appodeal.onResume(this, Appodeal.BANNER);
        Appodeal.onResume(this, Appodeal.NON_SKIPPABLE_VIDEO);
        BestBay();
        ((ImageView) findViewById(R.id.best_bay_img)).setImageResource(Memory.Images[bestN]);
        ((TextView) findViewById(R.id.best_bay_txt)).setText(Memory.Names[bestN]);
        ((TextView) findViewById(R.id.best_bay_txt)).setTextColor(ContextCompat.getColor(this, Memory.Colors[bestN]));
    }

    SharedPreferences sPref;

    void saveText() {
        sPref = getPreferences(MODE_PRIVATE);
        SharedPreferences.Editor ed = sPref.edit();
        for (int i = 0; i < Memory.Values.length; i++)
            ed.putInt(String.valueOf(i), Memory.Values[i]);
        ed.putBoolean("vkpay", Memory.vkpay);
        ed.putBoolean("ads", Memory.ads_sup);
        ed.putBoolean("fads", Memory.first_ads);
        ed.putBoolean("preview", Memory.first_login);
        ed.putBoolean("acc", Memory.acc);
        ed.putBoolean("music", Memory.music);
        ed.apply();
    }

    void loadText() {
        sPref = getPreferences(MODE_PRIVATE);
        for (int i = 0; i < Memory.Values.length; i++) {
            int o = sPref.getInt(String.valueOf(i), 0);
            Memory.Values[i] = sPref.getInt(String.valueOf(i), 0);
        }
        Memory.acc = sPref.getBoolean("acc", false);
        Memory.music = sPref.getBoolean("music", false);
        Memory.vkpay = sPref.getBoolean("vkpay", false);
        Memory.ads_sup = sPref.getBoolean("ads", false);
        Memory.first_ads = sPref.getBoolean("fads", true);
        Memory.first_login = sPref.getBoolean("preview", true);
    }
}