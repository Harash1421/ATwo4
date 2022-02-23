package com.mms.asensor

import android.annotation.SuppressLint
import android.content.Context
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), SensorEventListener {
    var sensorManger:SensorManager? = null
    var sensor:Sensor? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        sensorManger = getSystemService(Context.SENSOR_SERVICE) as SensorManager
        sensor = sensorManger!!.getDefaultSensor(Sensor.TYPE_LIGHT)
    }

    @SuppressLint("SetTextI18n")
    override fun onSensorChanged(event: SensorEvent?) {
        if (event!!.values[0] > 5000){
                main_Asensor.text = "Dark Mode"
                main_Light.setImageResource(R.drawable.light_on)
        }else{
            main_Asensor.text = "light Mode"
            main_Light.setImageResource(R.drawable.light_off)
        }
    }

    override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {}

    override fun onResume() {
        super.onResume()
        sensorManger!!.registerListener(this,sensor, SensorManager.SENSOR_DELAY_NORMAL)
    }

    override fun onPause() {
        super.onPause()
        sensorManger!!.unregisterListener(this)
    }
}