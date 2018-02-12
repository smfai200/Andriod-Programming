package com.example.syedfaizan.rescueapp;

import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import com.example.syedfaizan.rescueapp.Globals.*;

public class Rescue_Numbers_Set extends AppCompatActivity {
    EditText num1,num2,num3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rescue__numbers__set);
        num1 = (EditText) findViewById(R.id.contact1);
        num2 = (EditText) findViewById(R.id.contact2);
        num3 = (EditText) findViewById(R.id.contact3);

        //Setup on TOUCH listener
        num1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        num2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent contacts = new Intent(Intent.ACTION_GET_CONTENT);
                contacts.setType(ContactsContract.CommonDataKinds.Phone.CONTENT_ITEM_TYPE);
                startActivityForResult(contacts, 2);
            }
        });

        num3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent contacts = new Intent(Intent.ACTION_GET_CONTENT);
                contacts.setType(ContactsContract.CommonDataKinds.Phone.CONTENT_ITEM_TYPE);
                startActivityForResult(contacts, 3);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(data != null){
            Uri contactUri = data.getData();
            String[] projection = {ContactsContract.CommonDataKinds.Phone.NUMBER, ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME};
            Cursor c = null;
            try {
                c = getContentResolver().query(contactUri, new String[]{
                                ContactsContract.CommonDataKinds.Phone.NUMBER,
                                ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME },
                        null, null, null);

                if (c != null && c.moveToFirst()) {
                    if(requestCode == 1){
                        Globals.num1 = c.getString(0);
                        Globals.num1name = c.getString(1);
                        num1.setText(Globals.num1name);
                    }else if(requestCode == 2){
                        Globals.num2 = c.getString(0);
                        Globals.num2name = c.getString(1);
                        num2.setText(Globals.num1name);
                    }else{
                        Globals.num3 = c.getString(0);
                        Globals.num3name = c.getString(1);
                        num3.setText(Globals.num1name);
                    }
                    Toast.makeText(Rescue_Numbers_Set.this,"Contact Successfully Selected",Toast.LENGTH_SHORT);
                }
            } finally {
                if (c != null)
                    c.close();
            }
        }else{
            Toast.makeText(Rescue_Numbers_Set.this,"Please Select a Phone Number",Toast.LENGTH_LONG);
        }
    }

    public void onClick1(View w){
        Intent contacts = new Intent(Intent.ACTION_GET_CONTENT);
        contacts.setType(ContactsContract.CommonDataKinds.Phone.CONTENT_ITEM_TYPE);
        startActivityForResult(contacts, 1);
    }
    public void confirm(View w){
            Toast.makeText(Rescue_Numbers_Set.this,Globals.num1name,Toast.LENGTH_LONG);
            Toast.makeText(Rescue_Numbers_Set.this,Globals.num2name,Toast.LENGTH_LONG);
            Toast.makeText(Rescue_Numbers_Set.this,Globals.num3name,Toast.LENGTH_LONG);
    }


}
