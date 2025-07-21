package com.aventra.calculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    TextView _resultTextView;
    EditText _firstEditText;
    EditText _secondEditText;
    Button _btnAddition;
    Button _btnSubstraction;
    Button _btnMultiplaction;
    Button _btnDivision;
    CalculatorService _calculatorService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        _calculatorService = new CalculatorService();

        _btnAddition = findViewById(R.id.btnAddition);
        _btnSubstraction = findViewById(R.id.btnSubstraction);
        _btnMultiplaction = findViewById(R.id.btnMultiplication);
        _btnDivision = findViewById(R.id.btnDivision);
        _firstEditText = findViewById(R.id.etFirstNumber);
        _secondEditText = findViewById(R.id.etSecondNumber);
        _resultTextView = findViewById(R.id.tvResult);

        _btnAddition.setOnClickListener(operationClickListener);
        _btnSubstraction.setOnClickListener(operationClickListener);
        _btnMultiplaction.setOnClickListener(operationClickListener);
        _btnDivision.setOnClickListener(operationClickListener);



    }
    View.OnClickListener operationClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if (_firstEditText.getText().toString().equals("") || _secondEditText.getText().toString().equals("")){
                Toast.makeText(MainActivity.this, "Deger Giriniz!", Toast.LENGTH_SHORT).show();
                return;
            }

            int a = GetInputA(); // EditText’ten veri alma fonksiyonun olmalı
            int b = GetInputB();
            int result = 0;

            int id = v.getId();

            if (id == R.id.btnAddition) {
                result = _calculatorService.Add(a, b);
            } else if (id == R.id.btnSubstraction) {
                result = _calculatorService.Sub(a, b);
            } else if (id == R.id.btnMultiplication) {
                result = _calculatorService.Mul(a, b);
            } else if (id == R.id.btnDivision) {
                result = _calculatorService.Div(a, b);
            }

            ShowResult(result); // Sonucu TextView’e yazdıran fonksiyon
            Toast.makeText(MainActivity.this, "DENEME", Toast.LENGTH_SHORT).show();
        }
    };
    private int GetInputA()
    {
        return Integer.parseInt(_firstEditText.getText().toString());
    }

    private int GetInputB()
    {
        return  Integer.parseInt(_secondEditText.getText().toString());
    }

    private void ShowResult(int result){
        _resultTextView.setText("Result = " + result);
    }
}