package com.jobs.vkcoinfarm;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class CalculatorActivity extends AppCompatActivity {
    public static int[] Values = {0, 0, 0, 0, 0, 0, 0};

    public int BestBay()
    {
        float bestvalue = Pice(0);
        int bestn = 0;

        for(int i = 1; i < Values.length; i++)
            if(Pice(i) / Memory.Consts[i] < bestvalue / Memory.Consts[bestn])
            {
                bestn = i;
                bestvalue = Pice(i);
            }
        return bestn;
    }

    public static float Pice(int n) { return Memory.Cents[n] * (float)Math.pow(1.3, Values[n]); }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculator);
        findViewById(R.id.back_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        findViewById(R.id.rent_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (((EditText) findViewById(R.id.money_txt)).getText().toString().equals(""))
                    Toast.makeText(getBaseContext(), "Не число", Toast.LENGTH_SHORT).show();
                else {
                    long o = Long.parseLong(((EditText) findViewById(R.id.money_txt)).getText().toString());
                    float m = 0;
                    Values = Memory.Values.clone();
                    while (Pice(BestBay()) < o) {
                        int best = BestBay();
                        Values[best]++;
                        o -= Pice(best);
                        m += Memory.Consts[best];
                    }

                    for (int i = 0; i < Values.length; i++)
                        Values[i] -= Memory.Values[i];

                    ((TextView) findViewById(R.id.cursor_want_value_txt)).setText(String.valueOf(Values[0]));
                    ((TextView) findViewById(R.id.videocard_want_value_txt)).setText(String.valueOf(Values[1]));
                    ((TextView) findViewById(R.id.videocards_want_value_txt)).setText(String.valueOf(Values[2]));
                    ((TextView) findViewById(R.id.super_computer_want_value_txt)).setText(String.valueOf(Values[3]));
                    ((TextView) findViewById(R.id.server_want_value_txt)).setText(String.valueOf(Values[4]));
                    ((TextView) findViewById(R.id.quantum_computer_want_value_txt)).setText(String.valueOf(Values[5]));
                    ((TextView) findViewById(R.id.datacenter_want_value_txt)).setText(String.valueOf(Values[6]));
                    ((TextView) findViewById(R.id.itog_txt)).setText(m != 0 ? "Итого: +" + String.valueOf(Math.round(m * 1000) / 1000f) + " в сек" : "Недостаточно денег для выгодной покупки :(");
                }
            }
        });
        findViewById(R.id.pay_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                for(int i = 0; i < Values.length; i++) {
                    Memory.Values[i] += Values[i];
                    Values[i] = 0;
                }
                ((TextView)findViewById(R.id.cursor_want_value_txt)).setText(String.valueOf(Values[0]));
                ((TextView)findViewById(R.id.videocard_want_value_txt)).setText(String.valueOf(Values[1]));
                ((TextView)findViewById(R.id.videocards_want_value_txt)).setText(String.valueOf(Values[2]));
                ((TextView)findViewById(R.id.super_computer_want_value_txt)).setText(String.valueOf(Values[3]));
                ((TextView)findViewById(R.id.server_want_value_txt)).setText(String.valueOf(Values[4]));
                ((TextView)findViewById(R.id.quantum_computer_want_value_txt)).setText(String.valueOf(Values[5]));
                ((TextView)findViewById(R.id.datacenter_want_value_txt)).setText(String.valueOf(Values[6]));
                ((TextView)findViewById(R.id.itog_txt)).setText("");
                Toast.makeText(v.getContext(), "Куплено", Toast.LENGTH_SHORT).show();
            }
        });
    }
}