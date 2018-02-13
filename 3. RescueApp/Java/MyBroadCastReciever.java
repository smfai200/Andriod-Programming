package com.example.syedfaizan.rescue_numbers_set;

import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.util.Log;
import android.widget.Toast;
import com.example.syedfaizan.rescue_numbers_set.Globals;

import static android.content.Context.MODE_PRIVATE;
import static com.example.syedfaizan.rescue_numbers_set.MainActivity.prefname;


public class MyBroadCastReciever extends BroadcastReceiver {
    private boolean screenOff;

    @Override
    public void onReceive(Context context, Intent intent) {
        if (intent.getAction().equals(Intent.ACTION_SCREEN_OFF)) {
            Log.d("RECIEVE: "," SCREEN OFF");
            Globals.screenoff++;
            Log.d("Screen Off: ",Integer.toString(Globals.screenoff));
            screenOff = true;
        } else if (intent.getAction().equals(Intent.ACTION_SCREEN_ON)) {
            Log.d("RECIEVE: "," SCREEN ON");
            Globals.screen++;
            Log.d("Screen On: ",Integer.toString(Globals.screen));
            screenOff = false;
        } else if(intent.getAction().equals(Intent.ACTION_USER_PRESENT)){
            Log.d("Screen Unlocked: ","TRUE");
            Globals.screen = 0;
            Globals.screenoff = 0;
        }
        if(Globals.screen == 2 && Globals.screenoff == 3){
            Log.d("Activated: ", " Time to Send Alert");
            SendMessage obj = new SendMessage();
            obj.Sendsms();
        }
        Intent i = new Intent(context, MyService.class);
        i.putExtra("screen_state", screenOff);
        context.startService(i);
    }
}