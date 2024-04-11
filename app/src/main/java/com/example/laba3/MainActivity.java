package com.example.laba3;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.app.AlertDialog;
import android.app.Dialog;
import androidx.fragment.app.DialogFragment;
import androidx.annotation.NonNull;

public class MainActivity extends AppCompatActivity {

    EditText number;
    EditText precent;
    Button mGo;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        number = (EditText) findViewById(R.id.et1);
        precent = (EditText) findViewById(R.id.et2);
        mGo = (Button) findViewById(R.id.b1);
        mGo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Send(v);
            }
        });
    }
    public void showDialog(View v) {

        CustomDialogFragment dialog = new CustomDialogFragment();
        dialog.show(getSupportFragmentManager(), "custom");
    }
    public void Send(View view) {
        String numberr = number.getText().toString();
        String precentt = precent.getText().toString();

        try {
            // Пытаемся преобразовать строки в числа
            int num = Integer.parseInt(numberr);
            int perc = Integer.parseInt(precentt);
            if(perc<0)
                showErrorDialog();
            else {
                // Если преобразование прошло успешно, передаем данные в следующую активность
                Intent intent = new Intent(MainActivity.this, ActivitySecond.class);
                intent.putExtra("number", numberr);
                intent.putExtra("precent", precentt);
                startActivity(intent);
            }
        } catch (NumberFormatException e) {
            // Если возникло исключение, показываем диалог с ошибкой
            showErrorDialog();
        }
    }

    // Метод для показа диалога с ошибкой
    private void showErrorDialog() {
        new AlertDialog.Builder(this)
                .setTitle("Ошибка")
                .setMessage("Введите корректные числа.")
                .setPositiveButton(android.R.string.ok, null)
                .show();
    }
}