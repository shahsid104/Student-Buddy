package com.example.attendancecalculator;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.TextView;
import com.easysolutions.attendancecalculator.R;

public class MainActivity extends Activity implements OnClickListener{
	TextView textout1,textout2;
	EditText text1,text2,text3,text4;
	RadioGroup rgroup1,rgroup2;
	int value1,value2,value3,value4;
	Button b,tt,ass;
	int mode;
	String getvalue1,getvalue2,getvalue3,getvalue4;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		text1=(EditText) findViewById(R.id.editText1);
		text2=(EditText) findViewById(R.id.editText2);
		text3=(EditText) findViewById(R.id.editText3);
		text4=(EditText) findViewById(R.id.editText4);
		b=(Button)findViewById(R.id.button2);
		tt=(Button)findViewById(R.id.button3);
		ass=(Button)findViewById(R.id.button4);
		rgroup1=(RadioGroup) findViewById(R.id.rg1);
		b.setOnClickListener(this);
		loadPrefs();
		tt.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				activity();

			}
		});
		ass.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				activity2();

			}
		});
		rgroup1.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(RadioGroup arg0, int arg1) {
				if (arg1 == R.id.radioButton1) {
					mode=0;
				} else if (arg1 == R.id.radioButton2) {
					mode=1;
				}
			}
		});

		Button gen=(Button) findViewById(R.id.button1);

		gen.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				try{
					// TODO Auto-generated method stub
					 getvalue1=text1.getText().toString();
					 getvalue2=text2.getText().toString(); 
					 getvalue3=text3.getText().toString();
				     getvalue4=text4.getText().toString();

					value1=Integer.parseInt(getvalue1);
					value2=Integer.parseInt(getvalue2);
					value3=Integer.parseInt(getvalue3);
					value4=Integer.parseInt(getvalue4);
					switch(mode)
					{
					case 0:calc(value1,value2);
					break;
					case 1:calc2(value1,value2,value3,value4);
					}
				}catch(Exception e)
				{
					if(mode==0&&getvalue1!="" &&getvalue2!="")
					{
						value1=Integer.parseInt(getvalue1);
						value2=Integer.parseInt(getvalue2);
						calc(value1,value2);
					}
					else{
				
						Toast.makeText(MainActivity.this, "Cannot be empty!", Toast.LENGTH_LONG).show();
				}
					}
			}
		});

	}
	private void loadPrefs(){
		SharedPreferences sp=PreferenceManager.getDefaultSharedPreferences(this);
		int v=sp.getInt("VALUE",0);
		text4.setText(String.valueOf(v));

	}
	private void savePrefs(String key,int value){
		SharedPreferences sp=PreferenceManager.getDefaultSharedPreferences(this);
		Editor edit=sp.edit();
		edit.putInt(key, value);
		edit.commit();

	}
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		String a=text4.getText().toString();
		int value=Integer.parseInt(a);
		savePrefs("VALUE",value);
	}


	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	public void calc(int v1,int v2)
	{
		double calc;
		int v;
		v=v2-v1;
		if(v<0){
			Toast.makeText(MainActivity.this, "Cannot be negative!", Toast.LENGTH_LONG).show();
		}
		else{
		calc=(v*100/v2*1.0);
		Intent resultIntent=new Intent(this,Result.class);
		resultIntent.putExtra("calc", calc);
		startActivity(resultIntent);
		}
	}
	public void calc2(int v1,int v2,int v3,int v4)
	{
		double calc2;
		int calc1,v,v5;
		v=v2-v1;
		if(v<0){
			Toast.makeText(MainActivity.this, "Cannot be negative!", Toast.LENGTH_LONG).show();
		}
		else{
		v5=v3-v2;
		calc2=(v4*v3/100.0);
		calc1=(int)calc2-v;
		Intent resultIntent=new Intent(this,Result2.class);
		resultIntent.putExtra("calc1", calc1);
		resultIntent.putExtra("v",v5);
		startActivity(resultIntent);
		}
	}
	public void activity()
	{
		Intent TableIntent=new Intent(this,TablePageOne.class);
		startActivity(TableIntent);
	}
	public void activity2()
	{
		Intent AssignIntent=new Intent(this,Assign.class);
		startActivity(AssignIntent);
	}
}
