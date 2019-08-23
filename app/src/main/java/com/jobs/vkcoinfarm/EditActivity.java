package com.jobs.vkcoinfarm;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

public class EditActivity extends AppCompatActivity {

    public TextView[] txtV = new TextView[7];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);
        txtV[0] = findViewById(R.id.cursor_edit_txt);
        txtV[1] = findViewById(R.id.videocard_edit_txt);
        txtV[2] = findViewById(R.id.videocards_edit_txt);
        txtV[3] = findViewById(R.id.super_computer_edit_txt);
        txtV[4] = findViewById(R.id.server_edit_txt);
        txtV[5] = findViewById(R.id.quantum_computer_edit_txt);
        txtV[6] = findViewById(R.id.datacenter_edit_txt);
        for(int i = 0; i < txtV.length; i++)
            txtV[i].setText(String.valueOf(Memory.Values[i]));
        ((CheckBox)findViewById(R.id.vkpay_chk)).setChecked(Memory.vkpay);
        ((CheckBox)findViewById(R.id.much_account_chk)).setChecked(Memory.acc);
        ((CheckBox)findViewById(R.id.music_chk)).setChecked(Memory.music);
        findViewById(R.id.back_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        findViewById(R.id.apply_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                for(int i = 0; i < txtV.length; i++)
                    Memory.Values[i] = txtV[i].getText().toString().equals("") ? 0 : Integer.parseInt(txtV[i].getText().toString());
                Memory.vkpay = ((CheckBox)findViewById(R.id.vkpay_chk)).isChecked();
                Memory.acc = ((CheckBox)findViewById(R.id.much_account_chk)).isChecked();
                Memory.music = ((CheckBox)findViewById(R.id.music_chk)).isChecked();
                finish();
            }
        });
        findViewById(R.id.clean_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                for (TextView txt: txtV) txt.setText("0");
                ((CheckBox)findViewById(R.id.vkpay_chk)).setChecked(false);
                ((CheckBox)findViewById(R.id.much_account_chk)).setChecked(false);
                ((CheckBox)findViewById(R.id.music_chk)).setChecked(false);
            }
        });
    }
}