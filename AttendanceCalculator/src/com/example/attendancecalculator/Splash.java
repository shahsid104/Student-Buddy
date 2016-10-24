package com.example.attendancecalculator;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.AnimationUtils;
import android.widget.TextView;
import android.widget.Toast;
import com.easysolutions.attendancecalculator.R;

public class Splash extends Activity implements AnimationListener {
	Animation animFadein;
	TextView tv;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.splash);
		animFadein = AnimationUtils.loadAnimation(getApplicationContext(),
                R.anim.fadein);     
		animFadein.setAnimationListener(this);
		tv=(TextView)findViewById(R.id.text);
		tv.startAnimation(animFadein);
		Thread logotimer=new Thread(){
			public void run()
			{
			try{
				sleep(3000);
				Intent intent=new Intent(getApplicationContext(),MainActivity.class);
				startActivity(intent);
			}
			catch(Exception e){
				e.printStackTrace();
			}
			finally{
				finish();
			}
			}
		};
		logotimer.start();
		// animation listeners

}
	@Override
	public void onAnimationEnd(Animation animation) {
		// TODO Auto-generated method stub
		
		
	}
	@Override
	public void onAnimationRepeat(Animation arg0) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void onAnimationStart(Animation arg0) {
		// TODO Auto-generated method stub
		
	}
}
