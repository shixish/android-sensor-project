package com.ryderdonahue;

import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.widget.ProgressBar;
import android.widget.TextView;

public class CS_430_0Activity extends Activity {
	
	private TextView accText;
    private SensorManager myManager;
    private List<Sensor> sensors;
    private Sensor accSensor;
    private float oldX, oldY, oldZ = 0f;
    private ProgressBar mProgress1;
    private ProgressBar mProgress2;
    private ProgressBar mProgress3;
    private int var1=0;
    

    
   /** Called when the activity is first created. */
   @Override
   public void onCreate(Bundle savedInstanceState) {
       super.onCreate(savedInstanceState);        
            
       
       setContentView(R.layout.main);
       mProgress1 = (ProgressBar) findViewById(R.id.progressBar1);
       mProgress2 = (ProgressBar) findViewById(R.id.progressBar2);
       mProgress3 = (ProgressBar) findViewById(R.id.progressBar3);

       accText = (TextView)findViewById(R.id.textView1);  
      
       // Set Sensor + Manager
       myManager = (SensorManager)getSystemService(Context.SENSOR_SERVICE);
       sensors = myManager.getSensorList(Sensor.TYPE_ACCELEROMETER);
       if(sensors.size() > 0)
       {
         accSensor = sensors.get(0);
       }
   }

   
  
 private Runnable doSomething = new Runnable() {
   public void run() {
	   var1++;
	   accText.setText("var1: "+var1);

   }
   };

   private void runtime(){
	   var1++;
	   accText.setText("var1: "+var1);
}
private void updateTV(float x, float y, float z)
   {
    float thisX = x - oldX * 10;
    float thisY = y - oldY * 10;
    float thisZ = z - oldZ * 10;
    
    accText.setText("x: " + Math.round(thisX) + ";\n y:" + Math.round(thisY) + ";\n z: " + Math.round(thisZ)+";\n var1: "+var1);
    mProgress1.setProgress((Math.round(thisX)/2)+50);
    mProgress2.setProgress((Math.round(thisY)/2)+50);
    mProgress3.setProgress((Math.round(thisZ)/2)+50);
    
    oldX = x;
    oldY = y;
    oldZ = z;
   }
   private final SensorEventListener mySensorListener = new SensorEventListener()
   {
    public void onSensorChanged(SensorEvent event)
    {
         updateTV(event.values[0],
                   event.values[1],
                   event.values[2]);
    }
    
    public void onAccuracyChanged(Sensor sensor, int accuracy) {}
   };
   
   
  
   
   
   @Override
   protected void onResume()
   {
    super.onResume();
    myManager.registerListener(mySensorListener, accSensor, SensorManager.SENSOR_DELAY_GAME);      
   }
  
   @Override
   protected void onStop()
   {     
    myManager.unregisterListener(mySensorListener);
    super.onStop();
   }
   
	/** Called when the activity is first created. */
    /*
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        /*
        EditText et = (EditText) findViewById(R.id.textView1);
        et.setText("FUCKING DYNAMIC TEXT YO!");
    	

        final TextView textViewToChange = (TextView) findViewById(R.id.textView1);
        textViewToChange.setText(
            "This text should not be editable!");
    	}
    */
    
}
