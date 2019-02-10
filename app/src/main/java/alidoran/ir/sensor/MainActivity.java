package alidoran.ir.sensor;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.WindowManager;
import android.widget.SeekBar;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements SensorEventListener {

    public SensorManager sensorManager;
    public Sensor lightsensor;
    public Sensor accelerometer;
    SeekBar seekBar;


    @Override
    protected void onCreate ( Bundle savedInstanceState ) {
        super.onCreate ( savedInstanceState );
        setContentView ( R.layout.activity_main );

        sensorManager = ( SensorManager ) getSystemService ( Context.SENSOR_SERVICE );
        lightsensor = sensorManager.getDefaultSensor ( Sensor.TYPE_LIGHT );
        accelerometer = sensorManager.getDefaultSensor ( Sensor.TYPE_ACCELEROMETER );
        seekBar = findViewById ( R.id.seekBar );
        seekBar.setOnSeekBarChangeListener ( new SeekBar.OnSeekBarChangeListener ( ) {
            @Override
            public void onProgressChanged ( SeekBar seekBar , int progress , boolean fromUser ) {
                WindowManager.LayoutParams layoutParams = getWindow ( ).getAttributes ( );
                getWindow ( ).setAttributes ( layoutParams );

                float percent = progress / 100f;
                layoutParams.screenBrightness = percent;
            }

            @Override
            public void onStartTrackingTouch ( SeekBar seekBar ) {

            }

            @Override
            public void onStopTrackingTouch ( SeekBar seekBar ) {

            }
        } );
    }

    @Override
    protected void onResume ( ) {
        super.onResume ( );
        sensorManager.registerListener ( this , lightsensor , SensorManager.SENSOR_DELAY_NORMAL );
        sensorManager.registerListener ( this , accelerometer , SensorManager.SENSOR_DELAY_NORMAL );
    }

    @Override
    protected void onPause ( ) {
        super.onPause ( );
        sensorManager.unregisterListener ( this );
    }

    @Override
    public void onSensorChanged ( SensorEvent event ) {
        //float light=event.values [0];                      //lightsensor
        //Log.i ( "log",light+"");                           //lightsensor
        float x = event.values[0];                           //accelerometer
        float y = event.values[1];                           //accelerometer
        float z = event.values[2];                           //accelerometer
        Log.i ( "log" , x + "/" + y + "/" + z );  //accelerometer
    }


    @Override
    public void onAccuracyChanged ( Sensor sensor , int accuracy ) {

    }
}
