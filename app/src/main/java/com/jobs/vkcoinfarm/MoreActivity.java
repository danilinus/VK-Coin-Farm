package com.jobs.vkcoinfarm;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MoreActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_more);
        ((TextView)findViewById(R.id.cursor_value_txt)).setText('x' + String.valueOf(Memory.Values[0]));
        ((TextView)findViewById(R.id.videocard_value_txt)).setText('x' + String.valueOf(Memory.Values[1]));
        ((TextView)findViewById(R.id.videocards_value_txt)).setText('x' + String.valueOf(Memory.Values[2]));
        ((TextView)findViewById(R.id.super_computer_value_txt)).setText('x' + String.valueOf(Memory.Values[3]));
        ((TextView)findViewById(R.id.server_value_txt)).setText('x' + String.valueOf(Memory.Values[4]));
        ((TextView)findViewById(R.id.quantum_computer_value_txt)).setText('x' + String.valueOf(Memory.Values[5]));
        ((TextView)findViewById(R.id.datacenter_value_txt)).setText('x' + String.valueOf(Memory.Values[6]));
        ((TextView)findViewById(R.id.cursor_txt)).setText("КУРСОР\n"+ Float.toString(((int)(Memory.Consts[0] * Memory.Values[0] * 1000)) / 1000f) + " в сек");
        ((TextView)findViewById(R.id.videocard_txt)).setText("ВИДЕОКАРТА\n"+ Float.toString(((int)(Memory.Consts[1] * Memory.Values[1] * 1000)) / 1000f) + " в сек");
        ((TextView)findViewById(R.id.videocards_txt)).setText("СТОЙКА ВИДЕОКАРТ\n"+ Float.toString(((int)(Memory.Consts[2] * Memory.Values[2] * 1000)) / 1000f) + " в сек");
        ((TextView)findViewById(R.id.super_computer_txt)).setText("СУПЕРКОМПЬЮТЕР\n"+ Float.toString(((int)(Memory.Consts[3] * Memory.Values[3] * 1000)) / 1000f) + " в сек");
        ((TextView)findViewById(R.id.server_txt)).setText("СЕРВЕР ВК\n"+ Float.toString(((int)(Memory.Consts[4] * Memory.Values[4] * 1000)) / 1000f) + " в сек");
        ((TextView)findViewById(R.id.quantum_computer_txt)).setText("КВАНТОВЫЙ КОМПЬЮТЕР\n"+ Float.toString(((int)(Memory.Consts[5] * Memory.Values[5] * 1000)) / 1000f) + " в сек");
        ((TextView)findViewById(R.id.datacenter_txt)).setText("ДАТАЦЕНТР\n"+ Float.toString(((int)(Memory.Consts[6] * Memory.Values[6] * 1000)) / 1000f) + " в сек");
        float res = 0;
        if(Memory.acc) res += 10;
        if(Memory.vkpay) res += 2;
        if(Memory.music) res += 4;
        for(int i = 0; i < Memory.Values.length; i++)
            res += Memory.Consts[i] * Memory.Values[i];
        ((TextView)findViewById(R.id.about_d_txt)).setText("Ваша скорость добычи: "+ Float.toString(((int)(res * 1000)) / 1000f) + " в сек");
        findViewById(R.id.back_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
