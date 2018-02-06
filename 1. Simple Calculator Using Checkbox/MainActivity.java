package com.example.syedfaizan.firstapplication;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private EditText number1;
    private EditText number2;
    private Button result;
    private TextView resultdis;
    private CheckBox checkadd;
    private CheckBox checksub;
    private CheckBox checkmul;
    private CheckBox checkdiv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    //CONFIGURING ALL
        number1 = (EditText) findViewById(R.id.num1);
        number2 = (EditText) findViewById(R.id.num2);
        result = (Button) findViewById(R.id.resultbtn);
        resultdis = (TextView) findViewById(R.id.resultshow);
        checkadd = (CheckBox) findViewById(R.id.addcheck);
        checksub = (CheckBox) findViewById(R.id.subcheck);
        checkmul = (CheckBox) findViewById(R.id.mulcheck);
        checkdiv = (CheckBox) findViewById(R.id.divcheck);
    }


    public void add(View w){
        int num1 = Integer.parseInt(number1.getText().toString());
        int num2 = Integer.parseInt(number2.getText().toString());
        int result = 0 ;
        boolean flag = true;

        if(checkadd.isChecked()){
            result = num1+num2;
            checkadd.setChecked(false);
        }else if(checksub.isChecked()){
            result = num1-num2;
            checksub.setChecked(false);
        }else if(checkmul.isChecked()){
            result = num1*num2;
            checkmul.setChecked(false);
        }else if(checkdiv.isChecked()){
            result = num1/num2;
            checkdiv.setChecked(false);
        }else{
            flag=false;
            Toast.makeText(MainActivity.this,"Select an Option",Toast.LENGTH_LONG).show();
        }
        if(flag){
            resultdis.setText(Integer.toString(result));
        }

    }

}
