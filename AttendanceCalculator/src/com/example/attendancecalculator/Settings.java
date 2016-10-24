package com.example.attendancecalculator;


import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.preference.PreferenceActivity;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import com.easysolutions.attendancecalculator.R;

public class Settings extends PreferenceActivity implements OnClickListener {
	Button b;
	EditText e;
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		b=(Button)findViewById(R.id.button2);
		e=(EditText)findViewById(R.id.editText4);
		b.setOnClickListener(this);
		loadPrefs();
		
	}
	 private void loadPrefs(){
		SharedPreferences sp=PreferenceManager.getDefaultSharedPreferences(this);
       int v=sp.getInt("VALUE",0);
		Log.e("Restored val",String.valueOf(v));
       e.setText(String.valueOf(v));
		
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
		String a=e.getText().toString();
		int value=Integer.parseInt(a);
		Log.e("val",String.valueOf(value));
		savePrefs("VALUE",value);
	}

}
