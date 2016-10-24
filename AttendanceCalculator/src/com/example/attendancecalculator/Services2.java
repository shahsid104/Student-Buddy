package com.example.attendancecalculator;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.os.IBinder;
import android.os.PowerManager;
import android.preference.PreferenceManager;
import android.util.Log;
import android.widget.Toast;
import com.easysolutions.attendancecalculator.R;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.StringTokenizer;

public class Services2 extends Service{
	NotificationManager nm;
	static final int UniqueId=4444;
	private static final int NOTIFICATION_ID = 7392749; //value doen't matter
	private IntentFilter filter=new IntentFilter(Intent.ACTION_TIME_TICK);
	static int b,c=2,count=0;
	int year,month,date,y1,m1,d1,hour,x;
	String d,m,y;
	BroadcastReceiver  receivers=new BroadcastReceiver(){
		@Override
		public void onReceive(Context context, Intent intent) {
			// TODO Auto-generated method stub
			//if(c==2)
			start();
			//c++;

		}
	};

	@Override
	public IBinder onBind(Intent arg0) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public void onCreate() {
		// TODO Auto-generated method stub
		super.onCreate();
		//Toast.makeText(getBaseContext(), "OnCreate!!", Toast.LENGTH_LONG).show();
		final SharedPreferences sp=PreferenceManager.getDefaultSharedPreferences(this);
        count=sp.getInt("count", 0);		
		//Toast.makeText(getBaseContext(), "b"+b, Toast.LENGTH_LONG).show();
		Log.e(String.valueOf(count),"Oncreate");

	}
	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		// TODO Auto-generated method stub
		this.registerReceiver(receivers,filter);
		//Toast.makeText(getBaseContext(), "StartCommand!!", Toast.LENGTH_LONG).show();
		return super.onStartCommand(intent, flags, startId);	
	}
	void start()
	{
		Calendar cal1 = new GregorianCalendar();
		SharedPreferences sp=PreferenceManager.getDefaultSharedPreferences(this);
		try{
			year= cal1.get(Calendar.YEAR);
			month=(cal1.get(Calendar.MONTH))+1;
			date=cal1.get(Calendar.DAY_OF_MONTH);
		}
		catch(Exception e){
			e.printStackTrace();
		}
		//Toast.makeText(getBaseContext(), "b1"+b, Toast.LENGTH_LONG).show();
		x=sp.getInt("no_of_assignments",1);
		Log.e(String.valueOf(x),"Start");
		for (int j = 0; j <x; j++) {
		String str1=sp.getString("date"+j,"");
		Log.e("Str1", str1);
		if(str1!="")
		{
			Log.e(String.valueOf(j),"Main if");
			String str=sp.getString("sub2"+j,"");
			if(str=="")
			{
				Log.e(String.valueOf(j),"str=null");		
				stopSelf();
			}
			else{
				StringTokenizer st = new StringTokenizer(str1,"/");
				while(st.hasMoreElements())
				{
					d=(String) st.nextElement();
					m=(String) st.nextElement();
					y=(String) st.nextElement();
				}
				d1=Integer.parseInt(d);
				m1=Integer.parseInt(m);
				y1=Integer.parseInt(y)%100;
				str=str.toUpperCase();
				int r=year%100;
				hour=cal1.get(Calendar.HOUR_OF_DAY);
				Log.e(String.valueOf(d1),"Main if");
				Log.e(String.valueOf(date),"Main if");
				boolean f=sp.getBoolean("BOOLEAN"+j,false);
				Log.e(String.valueOf(f),"boolean");
				Log.e(String.valueOf(count),"COUNT");
				Log.e(String.valueOf(hour),"hour");
				Log.e(String.valueOf(y1),"y1");
				Log.e(String.valueOf(year),"year");
				Log.e(String.valueOf(r),"r");

				if( (d1-date==1||d1-date==-29||d1-date==-30) && m1-month==0 && (y1-r==0) && f!=true &&count!=x&&hour>10)
				{

					nm=(NotificationManager) getSystemService(NOTIFICATION_SERVICE);
					Intent intent1=new Intent(this,Assign.class);
					PendingIntent pi=PendingIntent.getActivity(this,0, intent1,0);
					String body=str+" Assignment Submission Tomorrow";
					String title="STUDENT BUDDY";
					Notification n=new Notification(R.drawable.logo2,body,System.currentTimeMillis());
					//startForeground(NOTIFICATION_ID, n);
					n.setLatestEventInfo(this, title,body,pi);
					n.flags=Notification.FLAG_AUTO_CANCEL;
					n.defaults=Notification.DEFAULT_ALL;
					PowerManager pm = (PowerManager) getSystemService(Context.POWER_SERVICE);
					PowerManager.WakeLock wakeLock = pm.newWakeLock( PowerManager.FULL_WAKE_LOCK |PowerManager.ACQUIRE_CAUSES_WAKEUP, "My wakelook");
					wakeLock.acquire(5000);
					nm.notify(UniqueId,n);
					sp.edit().putBoolean("BOOLEAN"+j,true).commit();
					count++;
					sp.edit().putInt("count",count).commit();
					break;
				}
				else if(((d1-date<=0&&m1-month==0)||m1-month<0||(y1-r<0))&&f!=true)
				{
					sp.edit().putBoolean("BOOLEAN"+j,true).commit();
					count++;
					sp.edit().putInt("count",count).commit();

				}
				else if(count==x)
				{
					Log.e(String.valueOf(count),"else if");
					sp.edit().putInt("count",0).commit();
					stopSelf();
				}
			}
			c=0;
		}
		else
		{
			Log.e(String.valueOf(count),"last else");
			stopSelf();
		}
		}
	}
	public void calcDate(long millisecs) {
		SimpleDateFormat date_format = new SimpleDateFormat("HH");
		Date resultdate = new Date(millisecs);
		try{
			hour=Integer.parseInt(date_format.format(resultdate));
		}catch(Exception e)
		{
			e.printStackTrace();
		}

	}
	public void onDestroy() {
		// TODO Auto-generated method stub
		this.unregisterReceiver(receivers);
		super.onDestroy();
		//stopForeground(false);
		//nm.cancel(UniqueId);
		//Toast.makeText(getBaseContext(), "Service Destroyed!!", Toast.LENGTH_LONG).show();
	}

}






