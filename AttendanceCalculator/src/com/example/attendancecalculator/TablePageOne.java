package com.example.attendancecalculator;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import com.easysolutions.attendancecalculator.R;

public  class TablePageOne extends Activity implements OnClickListener {
	ImageButton b; 
	int i,r,j,s;
	EditText et1,et;
	LinearLayout ll;
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.tablepg1);
		final SharedPreferences sp=PreferenceManager.getDefaultSharedPreferences(this);
		et1 = (EditText)findViewById(R.id.editText1);
		ImageButton b=(ImageButton)findViewById(R.id.button11);
		loadPrefs();
		 r=sp.getInt("no_of_subjects",0);
		add(r);
		et1.addTextChangedListener(new TextWatcher(){
			@Override
			public void onTextChanged(CharSequence arg0, int arg1, int arg2,
					int arg3) {
				// TODO Auto-generated method stub
				
				String getvalue1=et1.getText().toString();
				if(getvalue1=="")
				{
					add(0);
				}
				else
				{
					try{
				s=Integer.parseInt(getvalue1);
					}catch(Exception e)
					{
						e.printStackTrace();
					}
				add(s);
				}
				
			}
			 @Override
			public void beforeTextChanged(CharSequence arg0, int arg1,
					int arg2, int arg3) {
				// TODO Auto-generated method stub
				 int ar=sp.getInt("no_of_subjects",0);
				 try{
					 ll.removeViews(2,ar);
				 }
				 catch(Exception e)
				 {
					 e.printStackTrace();
				 }
				
			}
			 @Override
			public void afterTextChanged(Editable arg0) {
				// TODO Auto-generated method stub
				 try{
					savePrefs("no_of_subjects",Integer.parseInt(arg0.toString()));
			}catch(Exception e){
				e.printStackTrace();
			}
			 }
			
		});
		b.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				activity();

			}
		});
		
	}
        void add(int k)
        {
    	for(int j=0;j<k;j++)
    	{
    		 ll=(LinearLayout)findViewById(R.id.edit);
    	    et=new EditText(this); 
            loadPrefs(et,"sub"+String.valueOf(j));
            final int l=j;
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
    				// TODO Auto-generated method stub
    				savePrefs("sub"+String.valueOf(l),arg0.toString());
    				
    			}
    		});
    		ll.addView(et);
    		ll.requestLayout();
    		}
    	}    
        private void loadPrefs(){
    		SharedPreferences sp=PreferenceManager.getDefaultSharedPreferences(this);
    		int v=sp.getInt("no_of_subjects",0);
    		et1.setText(String.valueOf(v));

    	}
    	private void savePrefs(String key,int value){
    		SharedPreferences sp=PreferenceManager.getDefaultSharedPreferences(this);
    		Editor edit=sp.edit();
    		edit.putInt(key, value);
    		edit.commit();

    	}
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
			public void activity(){
				Intent TimeIntent=new Intent(this,getTime.class);
				startActivity(TimeIntent);	
				
			}
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				
			}
			
			}

