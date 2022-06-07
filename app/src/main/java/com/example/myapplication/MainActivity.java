package com.example.myapplication;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import org.mariuszgromada.math.mxparser.*;
import android.os.Build;
import android.os.Bundle;
import android.text.SpannableStringBuilder;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
    private EditText display;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        display = findViewById(R.id.input);
        display.setShowSoftInputOnFocus(false);

        if(getString(R.string.mostrar).equals(display.getText().toString())){
            display.setText("");
        }

        display.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });


    }

    private void updateText(String strToAdd){
        String oldStr = display.getText().toString();

        int cursorPos = display.getSelectionStart();
        String leftStr = oldStr.substring(0, cursorPos);
        String rightStr = oldStr.substring(cursorPos);

        if (getString(R.string.mostrar).equals(display.getText().toString())) {
            display.setText(strToAdd);
            display.setSelection(cursorPos +1);
        } else {
            display.setText(String.format("%s%s%s", leftStr, strToAdd,rightStr));
            display.setSelection(cursorPos +1);
        }

    }

    // NUMBERS
    public void zeroBTN(View view) { updateText("0"); }

    public void umBTN(View view) { updateText("1"); }

    public void doisBTN(View view) { updateText("2"); }

    public void tresBTN(View view) { updateText("3"); }

    public void quatroBTN(View view) { updateText("4"); }

    public void cincoBTN(View view) { updateText("5"); }

    public void seisBTN(View view) { updateText("6"); }

    public void seteBTN(View view) {updateText("7"); }

    public void oitoBTN(View view) { updateText("8"); }

    public void noveBTN(View view) { updateText("9"); }

    // SIMBOLS
    public void addBTN(View view) { updateText("+"); }

    public void subBTN(View view) { updateText("-"); }

    public void multiBTN(View view) { updateText("x"); }

    public void dividirBTN(View view) { updateText("/"); }

    public void exponenteBTN(View view) { updateText("^"); }

    public void maisOmenosBTN(View view) { updateText("-"); }

    public void limparBTN(View view) { display.setText(""); }

    public void pontoBTN(View view) { updateText("."); }

    public void igualBTN(View view) {
        String userExp = display.getText().toString();

        userExp = userExp.replaceAll("x", "*");

        Expression exp = new Expression(userExp);

        String result = String.valueOf(exp.calculate());

        display.setText(result);
        display.setSelection(result.length());
    }

    public void parentesesBTN(View view) {
        int cursorPos = display.getSelectionStart();

        int openPar = 0;
        int closePar = 0;
        int textLen = display.getText().length();

        for (int i=0; i < cursorPos; i++){
            if (display.getText().toString().substring(i, i+1).equals("(")){
                openPar += 1;
            }

            if (display.getText().toString().substring(i, i+1).equals(")")){
                closePar += 1;
            }
        }

        if ((openPar == closePar) || display.getText().toString().substring(textLen-1, textLen).equals("(")){
            updateText("(");
            display.setSelection(cursorPos+1);
        }
        else if ((closePar < openPar) && !display.getText().toString().substring(textLen-1, textLen).equals("(")){
            updateText(")");
        }
        display.setSelection(cursorPos+1);
    }

    public void backspaceBTN(View view) {
        int cursorPos = display.getSelectionStart();
        int textLen = display.getText().length();

        if (cursorPos != 0 && textLen !=0) {
            SpannableStringBuilder selection = (SpannableStringBuilder) display.getText();

            selection.replace(cursorPos -1, cursorPos, "");
            display.setSelection(cursorPos -1);

        }
    }


}