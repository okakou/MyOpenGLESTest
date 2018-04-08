package com.example.myopenglestest;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;

// センサイベント取得のためのクラス定義
public class MySensor implements SensorEventListener {
	
	MyGLView myGLView;
	
	// センサー値
	private float[] acceleration	= new float[3];	// 加速度センサー
	private float[] geomagnetic		= new float[3];	// 地磁気センサー
	private float[] attitude		= new float[3];	// 傾きセンサー
	
	// 回転行列
	private float[] inR 				= new float[16];
	private float[] outR 			= new float[16];
	private float[] I 				= new float[16];
	
	public MySensor(MyGLView gview) {
		myGLView = gview;
	}

	public void stopSensor() {
	}

	@Override
	public void onAccuracyChanged(Sensor sensor, int accuracy) {
	}

	@Override
	public void onSensorChanged(SensorEvent event) {

		
		switch(event.sensor.getType()) {
		case Sensor.TYPE_ACCELEROMETER:
			acceleration = event.values.clone();
			break;		
		case Sensor.TYPE_MAGNETIC_FIELD:
			geomagnetic = event.values.clone();
			break;
		}
		
		if(acceleration != null && geomagnetic != null) {
			// 回転行列を計算
			SensorManager.getRotationMatrix(inR, I, acceleration, geomagnetic);
			// 端末の画面設定に合わせる(以下は, 縦表示で画面を上にした場合)
			SensorManager.remapCoordinateSystem(inR, SensorManager.AXIS_X, SensorManager.AXIS_Y, outR);
			// 横表示の場合
			//SensorManager.remapCoordinateSystem(inR, SensorManager.AXIS_X, SensorManager.AXIS_Z, outR);
			// 方位角/傾きを取得
			SensorManager.getOrientation(outR, attitude);

			myGLView.setRotate((attitude[2]*4), -(attitude[1]*4));
//			va.setText("方位角：" + String.format("%3.1f", Math.toDegrees(attitude[0])));
//			vb.setText("傾斜角：" + String.format("%3.1f", Math.toDegrees(attitude[1])));
//			vc.setText("回転角：" + String.format("%3.1f", Math.toDegrees(attitude[2])));
		}	
	}
}
