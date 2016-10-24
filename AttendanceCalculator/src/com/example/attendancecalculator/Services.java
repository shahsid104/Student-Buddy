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
import android.text.style.ForegroundColorSpan;
import android.util.Log;
import android.widget.Toast;
import com.easysolutions.attendancecalculator.R;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.StringTokenizer;

public class Services extends Service{
	int hour1,min1, hour,min;
	String hours,mins,days;
	int day;
	private static final String tag=Receiver.class.getSimpleName();
	private IntentFilter filter=new IntentFilter(Intent.ACTION_TIME_TICK);
	long time;
	NotificationManager nm;
	final static int myID = 4321;
	Notification notification;
	static final int UniqueId=1234;
	static int a=0,c,b;
	BroadcastReceiver receivers=new BroadcastReceiver(){
		@Override
		public void onReceive(Context context, Intent intent) {
			// TODO Auto-generated method stub
			// c++;
			//if(c==60)
			//assign();
			start();

		}
	};

	@Override
	public IBinder onBind(Intent intent) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		// TODO Auto-generated method stub
		this.getApplicationContext().registerReceiver(receivers,filter);
		//Toast.makeText(getBaseContext(), "StartCommand!!", Toast.LENGTH_LONG).show();
		/*android.support.v4.app.NotificationCompat.Builder builder=new android.support.v4.app.NotificationCompat.Builder(this.getApplicationContext());
		Intent intent1=new Intent(this,TimeTable.class);
		PendingIntent pi=PendingIntent.getActivity(this,0, intent1,Intent.FLAG_ACTIVITY_CLEAR_TASK);
		builder.setContentTitle("STUDENT BUDDY");
		builder.setContentText("Touch To Start Application");
		builder.setContentIntent(pi);
		builder.setAutoCancel(true);
		builder.setOngoing(true);
		notification=builder.build();
		startForeground( NOTIFICATION_ID,notification);*/
		Intent intent1=new Intent(this,TimeTable.class);
		PendingIntent pi=PendingIntent.getActivity(this,0, intent1,0);
		String body="Touch To Start Application";
		String title="STUDENT BUDDY";
		Notification notice=new Notification(R.drawable.logo2,body,System.currentTimeMillis());
		notice.setLatestEventInfo(this, title,body,pi);
		notice.flags |= Notification.FLAG_NO_CLEAR;
		startForeground(myID, notice);
		final SharedPreferences sp=PreferenceManager.getDefaultSharedPreferences(this);
        a=sp.getInt("a",0);
		return START_STICKY;
	}
	
	public void start()
	{    
		//Toast.makeText(this, "Ticked", Toast.LENGTH_LONG).show();
		SharedPreferences sp=PreferenceManager.getDefaultSharedPreferences(this);
		int x=sp.getInt("timing",10);
		Log.e(String.valueOf(x),"Timing");
		Calendar cal1 = Calendar.getInstance();
		try{
			day=(cal1.get(Calendar.DAY_OF_WEEK)-2);
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		switch(day)
		{
		case 0:days="mon";
		break;
		case 1:days="tue";
		break;
		case 2:days="wed";
		break;
		case 3:days="thu";
		break;
		case 4:days="fri";         
		break;
		case 5:days="sat";
		break;
		}
		if(a<sp.getInt("i", 1))
		{
			String str=sp.getString(days+a,"");
			String str2=sp.getString("time"+a,"0");
			if(str!="")
			{	
				StringTokenizer st = new StringTokenizer(str2,":");
				while(st.hasMoreElements())
				{
					hours=(String) st.nextElement();
					mins=(String) st.nextElement();
				}
				//Toast.makeText(this	, "user:"+hours+":"+mins, Toast.LENGTH_LONG).show();
				hour1=Integer.parseInt(hours);
				min1=Integer.parseInt(mins);
				hour=cal1.get(Calendar.HOUR_OF_DAY);
				min=cal1.get(Calendar.MINUTE);
				//Toast.makeText(this	, "current:"+hour+":"+min, Toast.LENGTH_LONG).show();
				//calcDate(System.currentTimeMillis());
				if(hour1-hour==1)
				{
					min1=min1+60;
					if(min1-min==x)
					{
						nm=(NotificationManager) getSystemService(NOTIFICATION_SERVICE);
						Intent intent1=new Intent(this,TimeTable.class);
						PendingIntent pi=PendingIntent.getActivity(this,0, intent1,0);
						str=str.toUpperCase();
						String body=str+" lecture in "+x+"Minutes!!";
						String title="STUDENT BUDDY";
						Notification n=new Notification(R.drawable.logo2,body,System.currentTimeMillis());
						n.setLatestEventInfo(this, title,body,pi);
		        		n.flags=Notification.FLAG_AUTO_CANCEL;
						n.defaults=Notification.DEFAULT_ALL;
						PowerManager pm = (PowerManager) getSystemService(Context.POWER_SERVICE);
		        		PowerManager.WakeLock wakeLock = pm.newWakeLock( PowerManager.FULL_WAKE_LOCK |PowerManager.ACQUIRE_CAUSES_WAKEUP, "My wakelook");
		        		wakeLock.acquire(5000);
						nm.notify(UniqueId,n);
						a++;	
						 sp.edit().putInt("a",a).commit();

					}
				}
				else if(hour1-hour==0)
				{
					if(min1-min==x)
					{
						nm=(NotificationManager) getSystemService(NOTIFICATION_SERVICE);
						Intent intent1=new Intent(this,TimeTable.class);
						PendingIntent pi=PendingIntent.getActivity(this,0, intent1,0);
						str=str.toUpperCase();
						String body=str+" lecture in "+x+"Minutes!!";
						String title="STUDENT BUDDY";
						Notification n=new Notification(R.drawable.logo2,body,System.currentTimeMillis());
						n.setLatestEventInfo(this, title,body,pi);
		        		n.flags=Notification.FLAG_AUTO_CANCEL;
						n.defaults=Notification.DEFAULT_ALL;
						PowerManager pm = (PowerManager) getSystemService(Context.POWER_SERVICE);
		        		PowerManager.WakeLock wakeLock = pm.newWakeLock( PowerManager.FULL_WAKE_LOCK |PowerManager.ACQUIRE_CAUSES_WAKEUP, "My wakelook");
		        		wakeLock.acquire(5000);
						nm.notify(UniqueId,n);
						a++;	
						 sp.edit().putInt("a",a).commit();

					}
				}
				else if(hour1-hour<0 || min1-min<0)
				{
					a++;
					 sp.edit().putInt("a",a).commit();

				}
			}
			else
			{
				a++;
				 sp.edit().putInt("a",a).commit();

			}
		}
		else
			a=0;
		 sp.edit().putInt("a",a).commit();

	}
	public void calcDate(long millisecs) {
		SimpleDateFormat date_format = new SimpleDateFormat("HH");
		Date resultdate = new Date(millisecs);
		SimpleDateFormat date_format2 = new SimpleDateFormat("mm");
		try{
			hour=Integer.parseInt(date_format.format(resultdate));
			min=Integer.parseInt(date_format2.format(resultdate));
		}catch(Exception e)
		{
			e.printStackTrace();
		}

	}
	/*void assign()
{
	SharedPreferences sp=PreferenceManager.getDefaultSharedPreferences(this);
   if(b<sp.getInt("a",0))
	try{
		 year= cal1.get(Calendar.YEAR);
		 month=(cal1.get(Calendar.MONTH))+1;
		 date=cal1.get(Calendar.DAY_OF_MONTH);
		 }
		 catch(Exception e){
			 e.printStackTrace();
		 }
	if(b!=0)
	{
		String str1=sp.getString("date"+b,"");
		StringTokenizer st = new StringTokenizer(str1,"/");
		while(st.hasMoreElements())
		{
			d=(String) st.nextElement();
			m=(String) st.nextElement();
			y=(String) st.nextElement();
		}

	}
}	*/
	@Override
	public void onDestroy() {
		// TODO Auto-generated method stub
		this.getApplicationContext().unregisterReceiver(receivers);
		super.onDestroy();
	      stopForeground(true);
		//Toast.makeText(getBaseContext(), "Service Destroyed!!", Toast.LENGTH_LONG).show();
	}

}
