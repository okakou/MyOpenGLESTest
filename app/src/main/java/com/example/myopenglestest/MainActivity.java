/**
 * メインとなるクラスの定義
 * Activityクラスを継承する
 */
package com.example.myopenglestest;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;

//public class OpenGL0 extends Activity {
public class MainActivity extends Activity {

	MyGLView myGLView;

	// 初期化処理
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		myGLView = new MyGLView(this);
		setContentView(myGLView);
	}

	//
	@Override
	protected void onResume(){
		super.onResume();
		myGLView.onResume();
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

