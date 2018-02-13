package com.example.syedfaizan.rescue_numbers_set;

import com.example.syedfaizan.rescue_numbers_set.Globals;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.provider.ContactsContract;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    EditText num1, num2, num3;
    Button btn;
    SharedPreferences.Editor storage;
    SharedPreferences storageret;
    public static String prefname = "Details";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        num1 = (EditText) findViewById(R.id.contact0);
        num2 = (EditText) findViewById(R.id.contact2);
        num3 = (EditText) findViewById(R.id.contact3);
        storage = getSharedPreferences(prefname,MODE_PRIVATE).edit();

        //Setup on TOUCH listener
        num1.setOnClickListener(new View.OnClickListener() {
          @Override
            public void onClick(View v) {
                Intent i = new Intent(Intent.ACTION_PICK, ContactsContract.Contacts.CONTENT_URI);
                startActivityForResult(i, 1);
               }
            }
        );
        num2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Intent.ACTION_PICK, ContactsContract.Contacts.CONTENT_URI);
                startActivityForResult(i, 2);
            }
        });

        num3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Intent.ACTION_PICK, ContactsContract.Contacts.CONTENT_URI);
                startActivityForResult(i, 3);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        storageret = getSharedPreferences(prefname,MODE_PRIVATE);
        Globals.num1name = storageret.getString("name1","");
        Globals.num1 = storageret.getString("number1","");
        Globals.num2name = storageret.getString("name2","");
        Globals.num2 = storageret.getString("number2","");
        Globals.num3name = storageret.getString("name3","");
        Globals.num3 = storageret.getString("number3","");
        num1.setText(Globals.num1name);
        num2.setText(Globals.num2name);
        num3.setText(Globals.num3name);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        startService(new Intent(this, MyService.class));
    }

    @Override
    protected void onPause() {
        super.onPause();
        startService(new Intent(this, MyService.class));
        num1.setText(Globals.num1name);
        num2.setText(Globals.num2name);
        num3.setText(Globals.num3name);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        String cNumber = "";
        String name = "";
        //GET PHONE NUMBER AND CONTACT NAME SELECTED BY USER
            if (resultCode == Activity.RESULT_OK) {
                Uri contactData = data.getData();
                Cursor c =  managedQuery(contactData, null, null, null, null);
                if (c.moveToFirst()) {
                    String id =c.getString(c.getColumnIndexOrThrow(ContactsContract.Contacts._ID));
                    String hasPhone =c.getString(c.getColumnIndex(ContactsContract.Contacts.HAS_PHONE_NUMBER));

                    if (hasPhone.equalsIgnoreCase("1")) {
                        Cursor phones = getContentResolver().query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
                                null,ContactsContract.CommonDataKinds.Phone.CONTACT_ID +" = "+ id,
                                null, null);
                        phones.moveToFirst();
                        cNumber = phones.getString(phones.getColumnIndex("data1"));
                        name = c.getString(c.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME));
                    }
                }
            }
            switch (requestCode){
                case 1:
                    //SET GLOBALS FOR NUMBER
                    Globals.num1 = cNumber;
                    Globals.num1name = name;
                    num1.setText(Globals.num1name);
                    //SHAREDPREFERENCE TO STORE DATA LOCALLY
                    storage.putString("name1",Globals.num1name);
                    storage.putString("number1",Globals.num1);
                    break;
                case 2:
                    //SET GLOBALS FOR NUMBER
                    Globals.num2 = cNumber;
                    Globals.num2name = name;
                    num2.setText(Globals.num2name);
                    //SHAREDPREFERENCE TO STORE DATA LOCALLY
                    storage.putString("name2",Globals.num2name);
                    storage.putString("number2",Globals.num2);
                    break;
                case 3:
                    //SET GLOBALS FOR NUMBER
                    Globals.num3 = cNumber;
                    Globals.num3name = name;
                    num3.setText(Globals.num3name);
                    //SHAREDPREFERENCE TO STORE DATA LOCALLY
                    storage.putString("name3",Globals.num3name);
                    storage.putString("number3",Globals.num3);
                    break;
            }
            storage.commit();
        }

        public void confirmbtn(View w){
            Toast.makeText(MainActivity.this,"Confirmed",Toast.LENGTH_LONG).show();
        //    startService(new Intent(getBaseContext(), MyService.class));
             startService(new Intent(this, MyService.class));
        }
        public void stopService(View view) {
            stopService(new Intent(getBaseContext(), MyService.class));
        }
    }