package com.example.attendancecalculator;

import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.os.IBinder;
import android.preference.PreferenceManager;
import android.util.Log;
import android.widget.Toast;

public class Receiver extends BroadcastReceiver{
	@Override
	public void onReceive(Context context, Intent intent) {
		// TODO Auto-generated method stub
		//Toast.makeText(context.getApplicationContext(), "Receiver Class!!", Toast.LENGTH_LONG).show();
	   // SharedPreferences sharedPrefs = PreferenceManager.getDefaultSharedPreferences(context);
		SharedPreferences sp=PreferenceManager.getDefaultSharedPreferences(context);
	    boolean x=sp.getBoolean("cb2",false);
	    boolean y=sp.getBoolean("cb1",false);
	    if(x==false)
	    {	
		Intent service = new Intent(context, Services.class);
		context.startService(service);
	    }
	    if(y==false){
		Intent services = new Intent(context, Services2.class);
		context.startService(services);
	    }
	}

}



