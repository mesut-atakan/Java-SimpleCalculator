package com.aventra.calculator;

public class CalculatorService {
    public int Add(int a, int b) { return a + b; }
    public int Sub(int a, int b) { return  a - b; }
    public int Mul(int a, int b) { return a * b; }
    public int Div(int a, int b) { return b != 0 ? a / b : 0; }
}
