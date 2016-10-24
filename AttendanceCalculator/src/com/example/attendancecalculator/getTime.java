package com.example.attendancecalculator;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;
import android.widget.RadioGroup.OnCheckedChangeListener;
import com.easysolutions.attendancecalculator.R;

public class getTime extends Activity {
	RadioGroup rgroup1;
	final String KEY_SAVED_RADIO_BUTTON_INDEX = "SAVED_RADIO_BUTTON_INDEX";
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.timing);
		rgroup1=(RadioGroup) findViewById(R.id.rg1);
		Button a=(Button)findViewById(R.id.button1);
		LoadPreferences();
		final SharedPreferences sp=PreferenceManager.getDefaultSharedPreferences(this);
		rgroup1.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			@Override
			public void onCheckedChanged(RadioGroup arg0, int arg1) {
				
				RadioButton checkedRadioButton = (RadioButton)rgroup1.findViewById(arg1);
			    int checkedIndex = rgroup1.indexOfChild(checkedRadioButton);
			    saveprefs(KEY_SAVED_RADIO_BUTTON_INDEX,checkedIndex);
                if(arg1==R.id.radioButton1)
                {
               // Toast.makeText(getTime.this, "Value Stored", Toast.LENGTH_LONG).show();
				saveprefs("timing",5);
			    //saveprefs(KEY_SAVED_RADIO_BUTTON_INDEX,0);

                }
				 if(arg1==R.id.radioButton2)
				 {		
					// Toast.makeText(getTime.this, "Value Stored", Toast.LENGTH_LONG).show();
				saveprefs("timing",10);
			    //saveprefs(KEY_SAVED_RADIO_BUTTON_INDEX,1);

				 }
			   if(arg1==R.id.radioButton3)
			   {
				  // Toast.makeText(getTime.this, "Value Stored", Toast.LENGTH_LONG).show();
				saveprefs("timing",20);
			    //saveprefs(KEY_SAVED_RADIO_BUTTON_INDEX,2);

			   }
				if(arg1==R.id.radioButton4)  
				{
					//Toast.makeText(getTime.this, "Value Stored", Toast.LENGTH_LONG).show();
				saveprefs("timing",30);
			    //saveprefs(KEY_SAVED_RADIO_BUTTON_INDEX,3);

				}
			}
		});	
		a.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				activity();
				
			}
		});
}
	private void saveprefs(String key,int value){
		SharedPreferences sp=PreferenceManager.getDefaultSharedPreferences(this);
		Editor edit=sp.edit();
		edit.putInt(key, value);
		edit.commit();
}
	void activity()
	{
		 Intent intent=new Intent(this,TimeTable.class);
			startActivity(intent);

	}
	private void LoadPreferences(){
		SharedPreferences sp=PreferenceManager.getDefaultSharedPreferences(this);
		  int savedRadioIndex = sp.getInt(KEY_SAVED_RADIO_BUTTON_INDEX, 0);
		  RadioButton savedCheckedRadioButton = (RadioButton)rgroup1.getChildAt(savedRadioIndex);
		  savedCheckedRadioButton.setChecked(true);
		 }
	
}
