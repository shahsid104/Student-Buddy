package com.example.attendancecalculator;

import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.GraphViewSeries;
import com.jjoe64.graphview.GraphView.GraphViewData;
import com.jjoe64.graphview.GraphViewSeries.GraphViewSeriesStyle;
import com.jjoe64.graphview.LineGraphView;
import com.jjoe64.*;
import com.jjoe64.graphview.*;
import com.easysolutions.attendancecalculator.R;
import android.app.Activity;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.widget.LinearLayout;
import android.widget.TextView;

public class Result extends Activity {
	double val1;
	TextView textout1;
	LineGraphView lv;
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_result);
		val1=getIntent().getExtras().getDouble("calc");
		textout1=(TextView) findViewById(R.id.tv1);
		textout1.setText("PRESENT ATTENDANCE IS "+val1+"%");
		SharedPreferences sp=PreferenceManager.getDefaultSharedPreferences(this);
        int x=sp.getInt("VALUE",75);

		GraphViewSeries exampleSeries = new GraphViewSeries("",new GraphViewSeriesStyle(Color.RED,3),new GraphViewData[] {  				
				new GraphViewData(0,x),
				new GraphViewData(4,x)				    				 
		});
		GraphViewSeries nexampleSeries = new GraphViewSeries("",new GraphViewSeriesStyle(Color.BLUE,3),new GraphViewData[] {  				
				new GraphViewData(1,0),
				new GraphViewData(1,val1)				    				 
		});
		GraphViewSeries n1exampleSeries = new GraphViewSeries("",new GraphViewSeriesStyle(Color.BLUE,3),new GraphViewData[] {  				
				new GraphViewData(1,val1),
				new GraphViewData(3,val1)				    				 
		});
		GraphViewSeries n2exampleSeries = new GraphViewSeries("",new GraphViewSeriesStyle(Color.BLUE,3),new GraphViewData[] {  				
				new GraphViewData(3,val1),
				new GraphViewData(3,0)				    				 
		});

		GraphView graphView = new LineGraphView(  
				this // context  
				, "Result" // heading  

				); 
		//graphView.getGraphViewStyle().setGridColor(Color.GREEN); 
		graphView.getGraphViewStyle().setHorizontalLabelsColor(Color.YELLOW);
		graphView.setHorizontalLabels(new String[] {"Present","Attendance"});
		graphView.addSeries(exampleSeries); // data 
		graphView.addSeries(nexampleSeries); // data    
		graphView.addSeries(n1exampleSeries);
		graphView.addSeries(n2exampleSeries);
	    //GraphViewSeries line1Series = new GraphViewSeries("LINE ONE", new GraphViewSeriesStyle(Color.GREEN, 3), new GraphViewData[] { new GraphViewData(0, 0) }); // put an complete array
		//graphView.setGraphViewStyle(new GraphViewStyle(Color.DKGRAY, Color.DKGRAY, Color.LTGRAY));
		LinearLayout layout = (LinearLayout) findViewById(R.id.result1);  
		layout.addView(graphView);
	}


}
