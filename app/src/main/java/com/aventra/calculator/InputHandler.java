package com.aventra.calculator;

import android.widget.EditText;

public class InputHandler {
    public static  boolean hasValidInputs(EditText a, EditText b){
        return !a.getText().toString().trim().isEmpty() && !b.getText().toString().trim().isEmpty();
    }

    public static  boolean hasValidInput(EditText editText){
        return !editText.getText().toString().trim().isEmpty();
    }

    public static int parseInput(EditText editText){
        try {
            return Integer.parseInt(editText.getText().toString());
        } catch (NumberFormatException e){
            return 0;
        }
    }
}
