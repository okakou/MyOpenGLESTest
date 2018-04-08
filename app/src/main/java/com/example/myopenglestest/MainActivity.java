/**
 * メインとなるクラスの定義
 * Activityクラスを継承する
 */
package com.example.myopenglestest;

import java.util.List;
import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;

import android.hardware.Sensor;
import android.hardware.SensorManager;

//public class OpenGL0 extends Activity {
public class MainActivity extends Activity {

	MyGLView myGLView;
	private SensorManager manager;
	private MySensor mysensor;
	List<Sensor> sensors;

	// 初期化処理
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		// OpenGLViewの登録
		myGLView = new MyGLView(this);
		setContentView(myGLView);

		// SensorManagerの取得
		manager = (SensorManager)getSystemService(SENSOR_SERVICE);
 
		// MySensorの作成
		mysensor = new MySensor(myGLView);
		
		// 加速度センサの登録
		sensors = manager.getSensorList(Sensor.TYPE_ACCELEROMETER);
		if (sensors.size() > 0) {
			Sensor s = sensors.get(0);
			manager.registerListener(mysensor, s, SensorManager.SENSOR_DELAY_UI);
		}
		
		// 地磁気センサの登録
		sensors = manager.getSensorList(Sensor.TYPE_MAGNETIC_FIELD);
		if (sensors.size() > 0) {
			Sensor s = sensors.get(0);
			manager.registerListener(mysensor, s, SensorManager.SENSOR_DELAY_UI);
		}
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();

		// Listenerの登録解除
		manager.unregisterListener(mysensor);

		//
		mysensor.stopSensor();
	}

	@Override
	protected void onStop() {
		super.onStop();
		
		// Listenerの登録解除
		manager.unregisterListener(mysensor);
		
		//
       mysensor.stopSensor();
	}
	
	//
	@Override
	protected void onResume(){
		super.onResume();
		myGLView.onResume();
		
		/*
		 *  Listenerの再登録
		 */
		// 加速度センサの登録
		sensors = manager.getSensorList(Sensor.TYPE_ACCELEROMETER);
		if(sensors.size() > 0) {
			Sensor s = sensors.get(0);
			manager.registerListener(mysensor, s, SensorManager.SENSOR_DELAY_UI);
		}
		
		// 地磁気センサの登録 
		sensors = manager.getSensorList(Sensor.TYPE_MAGNETIC_FIELD);
		if (sensors.size() > 0) {
			Sensor s = sensors.get(0);
			manager.registerListener(mysensor, s, SensorManager.SENSOR_DELAY_UI);
		}
	}

	//
	@Override
	protected void onPause(){
		super.onPause();
		myGLView.onPause();
	}

	// メニューボタンを押した際の動作
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// res/menu/main.xmlの設定を反映
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}

