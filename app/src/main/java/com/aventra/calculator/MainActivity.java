package com.aventra.calculator;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private final static String FIRST_NUMBER_SAVE_KEY = "firstNumber";
    private final static String SECOND_NUMBER_SAVE_KEY = "secondNumber";

    TextView _resultTextView;
    EditText _firstEditText;
    EditText _secondEditText;
    Button _btnAddition;
    Button _btnSubstraction;
    Button _btnMultiplaction;
    Button _btnDivision;
    CalculatorService _calculatorService;

    StorageManager storageManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        _calculatorService = new CalculatorService();

        _btnAddition = findViewById(R.id.btnAddition);
        _btnSubstraction = findViewById(R.id.btnSubtraction);
        _btnMultiplaction = findViewById(R.id.btnMultiplication);
        _btnDivision = findViewById(R.id.btnDivision);
        _firstEditText = findViewById(R.id.etFirstNumber);
        _secondEditText = findViewById(R.id.etSecondNumber);
        _resultTextView = findViewById(R.id.tvResult);

        _btnAddition.setOnClickListener(operationClickListener);
        _btnSubstraction.setOnClickListener(operationClickListener);
        _btnMultiplaction.setOnClickListener(operationClickListener);
        _btnDivision.setOnClickListener(operationClickListener);

        // Mode private -> Bu veriye sadece benim uygulamamdan ulasilabilsin.

        storageManager = new StorageManager(MainActivity.this);

        load();
    }

    @Override
    protected void onStop() {
        super.onStop();
        save();
    }

    View.OnClickListener operationClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            if (!InputHandler.hasValidInputs(_firstEditText, _secondEditText)){
                UIHelper.showToast(MainActivity.this, "Deger Giriniz!");
                return;
            }

            int a = InputHandler.parseInput(_firstEditText); // EditText’ten veri alma fonksiyonun olmalı
            int b = InputHandler.parseInput(_secondEditText);
            int result = 0;

            int id = v.getId();

            if (id == R.id.btnAddition) {
                result = _calculatorService.add(a, b);
            } else if (id == R.id.btnSubtraction) {
                result = _calculatorService.sub(a, b);
            } else if (id == R.id.btnMultiplication) {
                result = _calculatorService.mul(a, b);
            } else if (id == R.id.btnDivision) {
                result = _calculatorService.div(a, b);
            }

            showResult(result); // Sonucu TextView’e yazdıran fonksiyon
        }
    };

    private void save()
    {
        if (InputHandler.hasValidInput(_firstEditText)){
            // SAVE Data
            storageManager.save(FIRST_NUMBER_SAVE_KEY, InputHandler.parseInput(_firstEditText));
        }
        else if (storageManager.has(FIRST_NUMBER_SAVE_KEY)){
            // Delete Data
            storageManager.remove(FIRST_NUMBER_SAVE_KEY);
        }

        if (InputHandler.hasValidInput(_secondEditText)){
            // SAVE Data
            storageManager.save(SECOND_NUMBER_SAVE_KEY, InputHandler.parseInput(_secondEditText));
        }
        else if (storageManager.has(SECOND_NUMBER_SAVE_KEY)){
            // Delete Data
            storageManager.remove(SECOND_NUMBER_SAVE_KEY);
        }
    }

    private void load(){
        int aInputLoad = storageManager.load(FIRST_NUMBER_SAVE_KEY);
        int bInputLoad = storageManager.load(SECOND_NUMBER_SAVE_KEY);

        if (aInputLoad != 0){
            _firstEditText.setText(String.valueOf(aInputLoad));
        }

        if (bInputLoad != 0){
            _secondEditText.setText(String.valueOf(bInputLoad));
        }
    }

    private void showResult(int result){
        _resultTextView.setText("Result = " + result);
    }
}