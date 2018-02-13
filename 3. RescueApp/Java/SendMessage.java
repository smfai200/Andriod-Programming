package com.example.syedfaizan.rescue_numbers_set;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.telephony.SmsManager;
import android.util.Log;

import static android.content.Context.MODE_PRIVATE;
import static com.example.syedfaizan.rescue_numbers_set.MainActivity.prefname;

public class SendMessage extends AppCompatActivity {
    SharedPreferences storageret;
    private SmsManager message;
    MainActivity obj;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
 //       Intent inte = new Intent(SendMessage.this,MainActivity.class);
   //     startActivity(inte);
    }

    SendMessage(){

    }

    public void Sendsms(){
     //   Intent inte = new Intent(SendMessage.this,MainActivity.class);
       // startActivity(inte);
        message = SmsManager.getDefault();
        try {
            message.sendTextMessage(Globals.num1, null, "Emergency Alert", null, null);
            message.sendTextMessage(Globals.num2, null, "Emergency Alert", null, null);
            message.sendTextMessage(Globals.num3, null, "Emergency Alert", null, null);
            Log.d("Screen: Message Send: ", "True");
        }catch (Exception e){
            Log.d("Exception: ","Message Sending Exception");
        }
    }

    public void getvalue(){
        storageret = getSharedPreferences(prefname, Context.MODE_PRIVATE);
        Globals.num1 = storageret.getString("number1","");
        Globals.num2 = storageret.getString("number2","");
        Globals.num3 = storageret.getString("number3","");
        Log.d("Screen Test: ",Globals.num1);
        Log.d("Screen Test: ",Globals.num2);
    }
}
