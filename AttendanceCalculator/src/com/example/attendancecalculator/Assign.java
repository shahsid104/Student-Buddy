package com.example.attendancecalculator;

import java.util.Calendar;

import com.easysolutions.attendancecalculator.R.id;
import com.easysolutions.attendancecalculator.R;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
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
import android.widget.DatePicker;
import android.widget.TimePicker;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TableLayout;
import android.widget.TableRow;

public class Assign extends Activity{
	LayoutInflater li;
	String array[];
	int k,l,no_of_assignments,year,month,day;
	TableLayout tl;
	TableRow tr;
	ImageButton add,del,done;
	CheckBox stop_notif;
	DatePicker datePicker1;
	static final int DATE_DIALOG_ID = 999;
	EditText et_selected;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.assign);
		li=this.getLayoutInflater();
		final SharedPreferences sp=PreferenceManager.getDefaultSharedPreferences(this);
		add=(ImageButton)findViewById(R.id.button100);
		done=(ImageButton)findViewById(R.id.button101);
		del=(ImageButton)findViewById(R.id.button104);
        stop_notif=(CheckBox)findViewById(R.id.checkBox1);
		no_of_assignments=sp.getInt("no_of_assignments",1);
		k=sp.getInt("no_of_subjects",0);	
		stop_notif.setChecked(loadPrefs("cb1"));
        array=new String[k];
        final Calendar c = Calendar.getInstance();
		year = c.get(Calendar.YEAR);
		month = c.get(Calendar.MONTH);
		day = c.get(Calendar.DAY_OF_MONTH);
        add.setOnClickListener(new OnClickListener() {
		@Override
		public void onClick(View arg0) {
			try{
			add(no_of_assignments++);
			sp.edit().putInt("no_of_assignments", no_of_assignments).commit();
			}catch(Exception e){
				e.printStackTrace();
			}
		}
        });
        done.setOnClickListener(new OnClickListener() {
    		@Override
    		public void onClick(View arg0) {
    			activity();
    			
    		}
        });
        stop_notif.setOnClickListener(new OnClickListener() {
    		@Override
    		public void onClick(View v) {
    			if (((CheckBox) v).isChecked())
    			{
    			   savePrefs("cb1",true);	
    			   destroy();
    			}  	
    			else{
    				savePrefs("cb1",false);
    			}
    		}
        });
        del.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				try{
					sp.edit().remove("date"+String.valueOf(no_of_assignments-1)).commit();
					sp.edit().remove("sub2"+String.valueOf(no_of_assignments-1)).commit();
					tl.removeViewAt(no_of_assignments--);
					sp.edit().putInt("no_of_assignments", no_of_assignments).commit();
					sp.edit().putBoolean("BOOLEAN"+(no_of_assignments-1),false).commit();
				}
				catch(Exception e){
					e.printStackTrace();
				}
			}
		});
        for( l=0;l<k;l++)
		{ 
		  array[l]=sp.getString("sub"+l,"");
		}
        for(int j=0;j<no_of_assignments;j++)
		{
		 add(j);	
		}
        Log.e(String.valueOf(no_of_assignments),"no_of_assignments");
	}
	void add(int i){
		final EditText et;
		ArrayAdapter<String>ar =new ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line,
				array);	
			     tl = (TableLayout)findViewById(R.id.table2);
				 tr = (TableRow)li.inflate(R.layout.assignrow, null);
				 et = (EditText)tr.findViewById(id.editText1);
				 et.setInputType(EditorInfo.TYPE_NULL);
				AutoCompleteTextView et1 = (AutoCompleteTextView)tr.findViewById(id.editText2);
				et1.setAdapter(ar);
				et1.setThreshold(0);
				final int j=i;
				loadPrefs(et,"date"+String.valueOf(i));
				loadPrefs(et1,"sub2"+String.valueOf(i));
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
						savePrefs("date"+String.valueOf(j), arg0.toString());
						
					}
				});

				et.setOnTouchListener(new OnTouchListener() {

					@Override
					public boolean onTouch(View arg0, MotionEvent arg1) {
						// TODO Auto-generated method stub
						et_selected=et;
						showDialog(DATE_DIALOG_ID);
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
						savePrefs("sub2"+String.valueOf(j), arg0.toString());
						
					}
				});
				tl.addView(tr);
				tl.requestLayout();
	}
	@Override
	protected Dialog onCreateDialog(int id) {
		switch (id) {
		case DATE_DIALOG_ID:Log.e("","Create Dialog");
			// set time picker as current time
		return new DatePickerDialog(this, datePickerListener, 
                year, month,day);
		}
		return null;
	}
	private DatePickerDialog.OnDateSetListener datePickerListener 
    = new DatePickerDialog.OnDateSetListener() {

// when dialog box is closed, below method will be called.
public void onDateSet(DatePicker view, int selectedYear,
	int selectedMonth, int selectedDay) {
year = selectedYear;
month = selectedMonth;
day = selectedDay;

// set selected date into textview
et_selected.setText(new StringBuilder().append(day)
   .append("/").append(month+1).append("/").append(year));
}
};
	 private void loadPrefs(EditText e,String key){
			SharedPreferences sp=PreferenceManager.getDefaultSharedPreferences(this);
	       e.setText(sp.getString(key, ""));
			
		}
		private void savePrefs(String key,String value){
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
		public void activity()
		{
			SharedPreferences sp=PreferenceManager.getDefaultSharedPreferences(this);
			for(int i=0;i<sp.getInt("no_of_assignments",1);i++){
				sp.edit().putBoolean("BOOLEAN"+i,false).commit();
			}
			sp.edit().putInt("count",0).commit();
			savePrefs("cb1",false);
			Intent service = new Intent(this, Services2.class);
		    startService(service);
		    finish();
		}
		public void destroy()
		{
			SharedPreferences sp=PreferenceManager.getDefaultSharedPreferences(this);
			for(int i=0;i<sp.getInt("no_of_assignments",1);i++){
				sp.edit().putBoolean("BOOLEAN"+i,false).commit();
			}
			sp.edit().putInt("count",0).commit();
			Intent r=new Intent(this,Services2.class);
			stopService(r);
		}
}
