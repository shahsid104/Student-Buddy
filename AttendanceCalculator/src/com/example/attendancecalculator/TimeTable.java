package com.example.attendancecalculator;
import java.util.Calendar;

import com.easysolutions.attendancecalculator.R.id;
import com.easysolutions.attendancecalculator.R;

import android.app.Activity;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.res.Configuration;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.inputmethod.EditorInfo;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.Toast;
import android.widget.TimePicker;
import android.app.TimePickerDialog;


public class TimeTable extends Activity {
	LayoutInflater li;
	int i,l,k;
	String array[];
	LinearLayout ll;
	TableRow tr;
	TableLayout tl;
	CheckBox d;
	private int hour;
	private int minute;
	TimePicker timePicker1;
	static final int TIME_DIALOG_ID = 999;
	EditText et_selected;
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.timetable);
		li=this.getLayoutInflater();
		ImageButton b =(ImageButton)findViewById(R.id.button1);
		ImageButton c=(ImageButton)findViewById(R.id.button2);
		ImageButton e=(ImageButton)findViewById(R.id.button4);
		 d=(CheckBox)findViewById(R.id.checkBox1);
		final SharedPreferences sp=PreferenceManager.getDefaultSharedPreferences(this);
		d.setChecked(loadPrefs("cb2"));
		i=sp.getInt("i", 1);
		k=sp.getInt("no_of_subjects",0);	
		array=new String[k];
		final Calendar cal = Calendar.getInstance();
		hour = cal.get(Calendar.HOUR_OF_DAY);
		minute = cal.get(Calendar.MINUTE);
		Log.e(String.valueOf(hour),"HOUR");
		Log.e(String.valueOf(minute),"Minute");

		c.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				activity();
			}	
		});
		d.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if (((CheckBox) v).isChecked()){
    				savePrefs("cb2",true);
					destroy();
				}
				else{
    				savePrefs("cb2",false);
    			}
			}
		});
		b.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				try{
					add(i++);
				}catch(Exception e){
					e.printStackTrace();
				}
				sp.edit().putInt("i", i).commit();
			}
		});
		for( l=0;l<k;l++)
		{ 
			array[l]=sp.getString("sub"+l,"eccf");
			Log.e(array[l],"sub"+l);
		}
		
		for(int j=0;j<i;j++)
		{
			add(j);	
		}
		e.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				try{
					Log.e(String.valueOf(i),"i");
					sp.edit().remove("time"+String.valueOf(i-1)).commit();
					sp.edit().remove("mon"+String.valueOf(i-1)).commit();
					sp.edit().remove("tue"+String.valueOf(i-1)).commit();
					sp.edit().remove("wed"+String.valueOf(i-1)).commit();
					sp.edit().remove("thu"+String.valueOf(i-1)).commit();
					sp.edit().remove("fri"+String.valueOf(i-1)).commit();
					sp.edit().remove("sat"+String.valueOf(i-1)).commit();
					tl.removeViewAt(i--);
					sp.edit().putInt("i", i).commit();
				}
				catch(Exception e){
					e.printStackTrace();
				}
			}
		});
	}
	@Override
	public void onConfigurationChanged(Configuration newConfig) {
		// TODO Auto-generated method stub
		super.onConfigurationChanged(newConfig);
		Log.e("Changing", "Chngin");
	}
	void add(int i)
	{
		final EditText et;
		tl = (TableLayout)findViewById(R.id.table);
		tr = (TableRow)li.inflate(R.layout.tablerow, null);
		ArrayAdapter<String>ar =new ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line,
				array);	
	    et = (EditText)tr.findViewById(id.editText1);
	    et.setInputType(EditorInfo.TYPE_NULL);
		AutoCompleteTextView et1 = (AutoCompleteTextView)tr.findViewById(id.editText2);
		et1.setAdapter(ar);
		et1.setThreshold(0);
		AutoCompleteTextView et2= (AutoCompleteTextView)tr.findViewById(id.editText3);
		et2.setAdapter(ar);
		et2.setThreshold(0);
		AutoCompleteTextView et3 = (AutoCompleteTextView)tr.findViewById(id.editText4);
		et3.setAdapter(ar);
		et3.setThreshold(0);
		AutoCompleteTextView et4 = (AutoCompleteTextView)tr.findViewById(id.editText5);
		et4.setAdapter(ar);
		et4.setThreshold(0);
		AutoCompleteTextView et5 = (AutoCompleteTextView)tr.findViewById(id.editText6);
		et5.setAdapter(ar);
		et5.setThreshold(0);
		AutoCompleteTextView et6=  (AutoCompleteTextView)tr.findViewById(id.editText7);
		et6.setAdapter(ar);
		et6.setThreshold(0);
		final int j=i;
		loadPrefs(et,"time"+String.valueOf(i));
		loadPrefs(et1,"mon"+String.valueOf(i));
		loadPrefs(et2,"tue"+String.valueOf(i));
		loadPrefs(et3,"wed"+String.valueOf(i));
		loadPrefs(et4,"thu"+String.valueOf(i));
		loadPrefs(et5,"fri"+String.valueOf(i));
		loadPrefs(et6,"sat"+String.valueOf(i));
		et.addTextChangedListener(new TextWatcher() {

			@Override
			public void onTextChanged(CharSequence arg0, int arg1, int arg2, int arg3) {
				// TODO Auto-generated method stub


			}

			@Override
			public void beforeTextChanged(CharSequence arg0, int arg1, int arg2,
					int arg3) {
				// TODO Auto-generated method stub


			}

			@Override
			public void afterTextChanged(Editable arg0) {
				
				savePrefs("time"+String.valueOf(j), arg0.toString());

			}
		});
		et.setOnTouchListener(new OnTouchListener() {

			@Override
			public boolean onTouch(View arg0, MotionEvent arg1) {
				// TODO Auto-generated method stub
				et_selected=et;
				showDialog(TIME_DIALOG_ID);
				et_selected.requestFocus();
				return true;
			}
		});
		
		et1.addTextChangedListener(new TextWatcher() {

			@Override
			public void onTextChanged(CharSequence arg0, int arg1, int arg2, int arg3) {
				// TODO Auto-generated method stub

			}

			@Override
			public void beforeTextChanged(CharSequence arg0, int arg1, int arg2,
					int arg3) {
				// TODO Auto-generated method stub

			}

			@Override
			public void afterTextChanged(Editable arg0) {
				savePrefs("mon"+String.valueOf(j), arg0.toString());

			}
		});
		et2.addTextChangedListener(new TextWatcher() {

			@Override
			public void onTextChanged(CharSequence arg0, int arg1, int arg2, int arg3) {
				// TODO Auto-generated method stub

			}

			@Override
			public void beforeTextChanged(CharSequence arg0, int arg1, int arg2,
					int arg3) {
				// TODO Auto-generated method stub

			}

			@Override
			public void afterTextChanged(Editable arg0) {
				savePrefs("tue"+String.valueOf(j), arg0.toString());

			}
		});
		et3.addTextChangedListener(new TextWatcher() {

			@Override
			public void onTextChanged(CharSequence arg0, int arg1, int arg2, int arg3) {
				// TODO Auto-generated method stub

			}

			@Override
			public void beforeTextChanged(CharSequence arg0, int arg1, int arg2,
					int arg3) {
				// TODO Auto-generated method stub

			}

			@Override
			public void afterTextChanged(Editable arg0) {
				savePrefs("wed"+String.valueOf(j), arg0.toString());

			}
		});
		et4.addTextChangedListener(new TextWatcher() {

			@Override
			public void onTextChanged(CharSequence arg0, int arg1, int arg2, int arg3) {
				// TODO Auto-generated method stub

			}

			@Override
			public void beforeTextChanged(CharSequence arg0, int arg1, int arg2,
					int arg3) {
				// TODO Auto-generated method stub

			}

			@Override
			public void afterTextChanged(Editable arg0) {
				savePrefs("thu"+String.valueOf(j), arg0.toString());

			}
		});
		et5.addTextChangedListener(new TextWatcher() {

			@Override
			public void onTextChanged(CharSequence arg0, int arg1, int arg2, int arg3) {
				// TODO Auto-generated method stub

			}

			@Override
			public void beforeTextChanged(CharSequence arg0, int arg1, int arg2,
					int arg3) {
				// TODO Auto-generated method stub

			}

			@Override
			public void afterTextChanged(Editable arg0) {
				savePrefs("fri"+String.valueOf(j), arg0.toString());

			}
		});
		et6.addTextChangedListener(new TextWatcher() {

			@Override
			public void onTextChanged(CharSequence arg0, int arg1, int arg2, int arg3) {
				// TODO Auto-generated method stub

			}

			@Override
			public void beforeTextChanged(CharSequence arg0, int arg1, int arg2,
					int arg3) {
				// TODO Auto-generated method stub

			}

			@Override
			public void afterTextChanged(Editable arg0) {
				savePrefs("sat"+String.valueOf(j), arg0.toString());

			}
		});
		tl.addView(tr);
		tl.requestLayout();

	}
	@Override
	protected Dialog onCreateDialog(int id) {
		switch (id) {
		case TIME_DIALOG_ID:Log.e("","Create Dialog");
			// set time picker as current time
			return new TimePickerDialog(this, 
                                        timePickerListener, hour, minute,false);
 
		}
		return null;
	}
	private TimePickerDialog.OnTimeSetListener timePickerListener = 
            new TimePickerDialog.OnTimeSetListener() {
		public void onTimeSet(TimePicker view, int selectedHour,
				int selectedMinute) {
			Log.e(String.valueOf(selectedHour),"OnTimeSet");
			Log.e(String.valueOf(selectedMinute),"OnTimeSet");
			hour = selectedHour;
			minute = selectedMinute;
			et_selected.setText(new StringBuilder().append(pad(hour))
					.append(":").append(pad(minute)));
 
			
		}
	};
	private static String pad(int c) {
		if (c >= 10)
		   return String.valueOf(c);
		else
		   return "0" + String.valueOf(c);
	}
	private void loadPrefs(EditText e,String key){
		SharedPreferences sp=PreferenceManager.getDefaultSharedPreferences(this);
		//Log.e(String.valueOf(e.getId()), key);
		e.setText(sp.getString(key, ""));

	}
	private void savePrefs(String key,String value){
		Log.e(key, value);
		SharedPreferences sp=PreferenceManager.getDefaultSharedPreferences(this);
		Editor edit=sp.edit();
		edit.putString(key, value);
		edit.commit();

	}
	private void savePrefs(String key,boolean value){
		SharedPreferences sp=PreferenceManager.getDefaultSharedPreferences(this);
		Editor edit=sp.edit();
		edit.putBoolean(key, value);
		edit.commit();
	}
	private boolean loadPrefs(String key){
		SharedPreferences sp=PreferenceManager.getDefaultSharedPreferences(this);
		return sp.getBoolean(key,false);
		
	}
	void activity()
	{
		/*Intent i=new Intent(this,StatusBar.class);
			startActivity(i);*/
		//SharedPreferences sp=PreferenceManager.getDefaultSharedPreferences(this);
        //int c=0;
		Intent service = new Intent(this, Services.class);
		startService(service);
		savePrefs("cb2",false);
		Intent intent=new Intent(this,MainActivity.class);
		startActivity(intent);
		finish();
	}
	void destroy()
	{
		Intent r=new Intent(this,Services.class);
		stopService(r);
	}
	@Override
	public void onSaveInstanceState(Bundle outState) {
		super.onSaveInstanceState(outState);

	}
}