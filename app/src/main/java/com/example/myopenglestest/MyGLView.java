package com.example.myopenglestest;

import android.content.Context;			// コンテキストクラスを利用するためのライブラリ
import android.view.MotionEvent;			// タッチパネルイベント用ライブラリ

import android.opengl.GLSurfaceView;		// OpenGL/ESを利用するためのライブラリ

public class MyGLView extends GLSurfaceView {

	float befoer_x, befoer_y, after_x, after_y;				// 座標値
	MyRenderer myRenderer;

	// コンストラクタ
	public MyGLView(Context context) {
		super(context);
		myRenderer = new MyRenderer();
		setRenderer(myRenderer);
	}

	// タッチスクリーンイベントの定義
	public boolean onTouchEvent(MotionEvent ev) {
		switch(ev.getAction()) {
		case MotionEvent.ACTION_DOWN:
			// x座標、y座標
			befoer_x = ev.getX();
			befoer_y = ev.getY();

			// onDrawを再実行
//			MyGLView.this.invalidate();
			
			break;
		case MotionEvent.ACTION_MOVE:
			// x座標、y座標
			after_x = ev.getX();
			after_y = ev.getY();

			// onDrawを再実行
//			MyGLView.this.invalidate();
			myRenderer.setRotate(after_x - befoer_x, after_y - befoer_y);
			befoer_x = after_x;
			befoer_y = after_y;
			
			break;		
		case MotionEvent.ACTION_UP:
			// x座標、y座標
			after_x = ev.getX();
			after_y = ev.getY();

			// onDrawを再実行
//			MyGLView.this.invalidate();
			myRenderer.setRotate(after_x - befoer_x, after_y - befoer_y);
			
			break;
		}
		return true;
	}

}
