/**
 * 
 */
package com.example.myopenglestest;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

import android.opengl.GLU;
import android.opengl.GLSurfaceView.Renderer;

public class MyRenderer implements Renderer {

	MyCube cube = new MyCube();
	float x = 0, y = 0;
	float scale = 1;
	
	//　毎フレーム呼ばれる
	@Override
	public void onDrawFrame(GL10 gl) {

		// 背景を塗り潰すバッファを指定
		//  GL_COLOR_BUFFER_BIT		カラーバッファ
		//  GL_DEPTH_BUFFER_BIT		デプスバッファ
		//  GL_STENCIL_BUFFER_BIT	ステンシルバッファ
		gl.glClear(GL10.GL_COLOR_BUFFER_BIT | GL10.GL_DEPTH_BUFFER_BIT);

		// クォリティを設定
		// targetに対象、modeにクォリティを指定
		//  target
		//   GL_FOG_HINT							ファグの計算精度
		//   GL_LINE_SMOOTH_HINT				線のサンプリング精度
		//   GL_PERSPECTIVE_CORRECTION_HINT	カラーとテクスチャ座標の補間精度
		//   GL_POINT_SMOOTH_HINT				点のサンプリング精度
		//   GL_POLYGON_SMOOTH_HINT				ポリゴンのサンプリング精度
		//   GL_FASTEST							効率的
		//   GL_NICEST							高品質
		//   GL_DONT_CARE							なし
		//  mode定数
		//   GL_FASTEST							効率的
		//   GL_NICEST							高品質
		//   GL_DONT_CARE							なし
		gl.glHint(GL10.GL_NICEST, GL10.GL_NICEST);
		
		// 行列演算ターゲットの指定
		//  GL_MODELVIEW		モデルビュー行列
		//  GL_PROJECTION		射影行列
		//  GL_TEXTURE		テクスチャ行列
		gl.glMatrixMode(GL10.GL_MODELVIEW);
		
		// 行列の初期化
		gl.glLoadIdentity();
		
		// モデルの平行移動
		gl.glTranslatef(0, 0, -3f);

		// モデルのスケーリング
		gl.glScalef(1f, 1f, 1f);
		
		// モデルの回転
		gl.glRotatef(x, 1, 0, 0);
		gl.glRotatef(y, 0, 1, 0);

		// モデルの拡大縮小
		gl.glScalef(scale, scale, scale);

		cube.draw(gl);
	}

	// 縦・横を変更した場合に呼ばれる関数
	@Override
	public void onSurfaceChanged(GL10 gl, int width, int height) {
		gl.glViewport(0, 0, width, height);

		gl.glMatrixMode(GL10.GL_PROJECTION);
		gl.glLoadIdentity();
		GLU.gluPerspective(gl, 45f,(float) width / height, 1f, 50f);
	}

	// 初期化時に呼ばれる関数
	@Override
	public void onSurfaceCreated(GL10 gl, EGLConfig config) {
		gl.glEnable(GL10.GL_DEPTH_TEST);
		gl.glDepthFunc(GL10.GL_LEQUAL);
		gl.glEnable(GL10.GL_LIGHTING);
		gl.glEnable(GL10.GL_LIGHT0);
	}

	// 回転度の設定
	public void setRotate(float a, float b) {
		x += b/2;
		y += a/2;
	}
	
	// 拡大縮小度の設定
	public void setScale(float a) {
		if (scale * a < 0.1) {
			scale = 0.1f;
		} else if (scale * a > 2) {
			scale = 2;
		} else {
			scale *= a;
		}
	}
	
}
