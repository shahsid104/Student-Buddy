package com.example.attendancecalculator;
import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.GraphViewSeries;
import com.jjoe64.graphview.LineGraphView;
import com.jjoe64.graphview.GraphView.GraphViewData;
import com.jjoe64.graphview.GraphViewSeries.GraphViewSeriesStyle;
import com.easysolutions.attendancecalculator.R;
import java.util.ArrayList;
import android.os.Bundle;
import android.app.Activity;
import android.graphics.Color;
import android.view.Menu;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class Result2 extends Activity {
TextView textout;
int val1,v;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_result2);
		 val1=getIntent().getExtras().getInt("calc1");
		 v=getIntent().getExtras().getInt("v");
		  textout=(TextView) findViewById(R.id.tv2);
		  if(val1<0)
		  {
			  textout.setText("LECTURES REQUIRED TO BE ATTENDED ARE 0");
			  GraphViewSeries exampleSeries = new GraphViewSeries("",new GraphViewSeriesStyle(Color.RED,3),new GraphViewData[] {
				  new GraphViewData(0,v),
					new GraphViewData(4,v)				    				 
			});
			  GraphViewSeries nexampleSeries = new GraphViewSeries("",new GraphViewSeriesStyle(Color.BLUE,3),new GraphViewData[] {  					
						new GraphViewData(0,0),
						new GraphViewData(4,0)				    				 

						//new GraphViewData(1,val1)				    				 
			  });
			 // GraphViewSeries n1exampleSeries = new GraphViewSeries("",new GraphViewSeriesStyle(Color.BLUE,3), new GraphViewData[] {  					
						//new GraphViewData(1,val1),
						//new GraphViewData(3,val1)				    				 
			 // });
			 /* GraphViewSeries n2exampleSeries = new GraphViewSeries("",new GraphViewSeriesStyle(Color.BLUE,3),new GraphViewData[] {  					
						//new GraphViewData(3,val1),
						new GraphViewData(3,0)				    				 
			  });*/  
			  GraphView graphView = new LineGraphView(this,"LEFT");
			  graphView.setScrollable(true); 
		      graphView.addSeries(exampleSeries);
		      graphView.addSeries(nexampleSeries);
		     // graphView.addSeries(n1exampleSeries);
		      //graphView.addSeries(n2exampleSeries);
		     // graphView.setVerticalLabels(new String[]{"Required","Left"});

			 // graphView.getGraphViewStyle().setGridColor(Color.GREEN); 
			  LinearLayout layout = (LinearLayout) findViewById(R.id.result2);  
				layout.addView(graphView);

			  
		  }
		  else{
		  textout.setText("LECTURES REQUIRED TO BE ATTENDED ARE "+val1);
		// ArrayList<Bar> points = new ArrayList<Bar>();
		  //Bar d = new Bar();
		  //d.setColor(Color.parseColor("#FFBB33"));//
		  /*d.setColor(Color.parseColor("#ff0000"));//Red
		  d.setName("Present Attendance");
		  d.setColor(Color.parseColor("#bf8fec"));//purple
		  d.setValue(val1);	*/	  
		 /* Bar d2 = new Bar();
		  d2.setColor(Color.parseColor("#FFBB33"));
		  d2.setName("Test2");
		  d2.setValue(20);
		  points.add(d2);*/
		/*  points.add(d);
		  BarGraph g = (BarGraph)findViewById(R.id.graph);
		  g.setBars(points);	
		  Line l = new Line();
		  LinePoint p = new LinePoint();
		  p.setX(0);
		  p.setY(75);
		  l.addPoint(p);
		/*  p = new LinePoint();
		  p.setX(8);
		  p.setY(8);
		  l.addPoint(p);
		  p = new LinePoint();
		  p.setX(10);
		  p.setY(4);
		  l.addPoint(p);*/
		 // l.setColor(Color.parseColor("#ff0000"));

		 // LineGraph li = (LineGraph)findViewById(R.id.lineGraph1);
		 // li.addLine(l);
		//  li.setRangeY(0, 10);
		 // li.setLineToFill(0);
		  GraphViewSeries exampleSeries = new GraphViewSeries("",new GraphViewSeriesStyle(Color.RED,3),new GraphViewData[] {
			  new GraphViewData(0,v),
				new GraphViewData(4,v)				    				 
		});
		  GraphViewSeries nexampleSeries = new GraphViewSeries("",new GraphViewSeriesStyle(Color.BLUE,3),new GraphViewData[] {  					
					new GraphViewData(1,0),
					new GraphViewData(1,val1)				    				 
		  });
		  GraphViewSeries n1exampleSeries = new GraphViewSeries("",new GraphViewSeriesStyle(Color.BLUE,3), new GraphViewData[] {  					
					new GraphViewData(1,val1),
					new GraphViewData(3,val1)				    				 
		  });
		  GraphViewSeries n2exampleSeries = new GraphViewSeries("",new GraphViewSeriesStyle(Color.BLUE,3),new GraphViewData[] {  					
					new GraphViewData(3,val1),
					new GraphViewData(3,0)				    				 
		  });  
		  GraphView graphView = new LineGraphView(this,"LEFT");
		  graphView.setScrollable(true); 
	      graphView.addSeries(exampleSeries);
	      graphView.addSeries(nexampleSeries);
	      graphView.addSeries(n1exampleSeries);
	      graphView.addSeries(n2exampleSeries);
	     // graphView.setVerticalLabels(new String[]{"Required","Left"});

		 // graphView.getGraphViewStyle().setGridColor(Color.GREEN); 
		  LinearLayout layout = (LinearLayout) findViewById(R.id.result2);  
			layout.addView(graphView);

	}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.result2, menu);
		return true;
	}

}
