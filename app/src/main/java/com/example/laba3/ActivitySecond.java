package com.example.laba3;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.net.Uri;
import android.util.Log;

import android.widget.Toast;


public class ActivitySecond extends Activity {
    TextView mT1;
    TextView mT2;
    TextView mT3;
    Button mBack;
    int number;
    int precent;
    double otvet;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.second);
        //Объявляем объекты, которые будем использовать:
        mT1 = (TextView) findViewById(R.id.tv1);
        mT2 = (TextView) findViewById(R.id.tv2);
        mT3 = (TextView) findViewById(R.id.tv3);
        mBack = (Button) findViewById(R.id.b2);
        // Получаем текстовые данные с первого Activity:
        Intent intent = getIntent();
        String num = getIntent().getStringExtra("number");
        String prec = getIntent().getStringExtra("precent");


// Проверяем, что строка является числом
        int number = Integer.parseInt(num);

        int precent = Integer.parseInt(prec);
// Рассчитываем ответ
        task(number, precent);

// Присваиваем созданным элементам TextView значение полученных текстовых данных:
        mT1.setText(mT1.getText().toString() + "" + number);
        mT2.setText(mT2.getText().toString() + "" + precent);
        mT3.setText(mT3.getText().toString() + "" + otvet);
        Button startBtn = (Button) findViewById(R.id.sendEmail);
        startBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                sendEmail();
            }
        });

    }
    public double task(int number, int precent){
        otvet = number / 100.0 * precent;
        return  otvet;
    }
    public void Back(View view) {
//Создаем обратный переход по нажатию на кнопку "Назад":
        Intent intent = new Intent(ActivitySecond.this, MainActivity.class);
        startActivity(intent);
    }

    protected void sendEmail() {
        Log.i("Send email", "");
        String[] TO = {""};
        String[] CC = {""};
        Intent emailIntent = new Intent(Intent.ACTION_SEND);

        emailIntent.setData(Uri.parse("mailto:"));
        emailIntent.setType("text/plain");
        emailIntent.putExtra(Intent.EXTRA_EMAIL, TO);
        emailIntent.putExtra(Intent.EXTRA_CC, CC);
        emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Your subject");
        emailIntent.putExtra(Intent.EXTRA_TEXT, String.valueOf(otvet));

        try {
            startActivity(Intent.createChooser(emailIntent, "Send mail..."));
            finish();
            Log.i("Finished sending email...", "");
        } catch (android.content.ActivityNotFoundException ex) {
            Toast.makeText(ActivitySecond.this, "There is no email client installed.", Toast.LENGTH_SHORT).show();
        }
    }
}
