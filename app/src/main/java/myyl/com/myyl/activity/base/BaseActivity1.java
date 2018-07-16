package myyl.com.myyl.activity.base;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import myyl.com.myyl.R;
import myyl.com.myyl.utils.StatusBarUtil;


public class BaseActivity1 extends RootActivity {

	/**
	 * 加载等待效果
	 */
	public ProgressDialog progress;

	public static final Handler handler = new Handler();
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		StatusBarUtil.setColor(this, getResources().getColor(R.color.statusBar), 0);
	}


	@Override
	public void onClick(View arg0) {}
	

	
	@Override
	protected void onDestroy() {
		super.onDestroy();
	}

	@Override
	protected void onPause() {
		super.onPause();
	}

	@Override
	protected void onResume() {
		super.onResume();
	}

	/**
	 * 滚动条超时
	 *
	 * @author mo
	 *
	 */
	@SuppressWarnings("unused")
	private class ProgressTimeOut extends Thread {
		@Override
		public void run() {
			try {
				Thread.sleep(30000);
				Message message1 = new Message();
				BaseActivity1.this.disProgressHander.sendMessage(message1);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

		}
	}

	/**
	 * 消息队列方式显示进度
	 */
	@SuppressLint("HandlerLeak")
	public Handler progressHander = new Handler() {
		@Override
		public void handleMessage(Message msg) {
			if (progress != null) {
				progress.dismiss();
			}
			progress = new ProgressDialog(BaseActivity1.this);
			progress.setProgressStyle(ProgressDialog.STYLE_SPINNER);
			progress.setMessage(msg.obj.toString());
			progress.setCancelable(false);
			progress.show();
			View layout = View.inflate(mContext, R.layout.layout_dialog, null);
			TextView tv_msg = (TextView) layout.findViewById(R.id.tv_msg);
			if (!"".equals(msg.obj.toString())) {
				tv_msg.setText("  " + msg.obj.toString());
			}
			progress.getWindow().setContentView(layout);// show()代码后
		}
	};

	/**
	 * 隐藏消息队列进度的显示
	 */
	public Handler disProgressHander = new Handler() {
		@Override
		public void handleMessage(Message msg) {
			if (progress != null) {
				progress.dismiss();
			}
		}
	};


	/**
	 * 显示字符串消息
	 *
	 * @param message
	 */
	public void showProgress(String message) {
		if (progress != null) {
			progress.dismiss();
		}
		progress = new ProgressDialog(BaseActivity1.this);
		progress.setProgressStyle(ProgressDialog.STYLE_SPINNER);
		progress.setMessage(message);
		progress.setCancelable(false);
		progress.show();
		View layout = View.inflate(mContext, R.layout.layout_dialog, null);
		TextView tv_msg = (TextView) layout.findViewById(R.id.tv_msg);
		if (!"".equals(message)) {
			tv_msg.setText("  " + message);
		}
		progress.getWindow().setContentView(layout);// show()代码后

	}

	/**
	 * 隐藏字符串消息
	 */
	public void disShowProgress() {
		if (progress != null) {
			progress.dismiss();
		}
	}


	/**
	 * 提示信息
	 *
	 */
	public void showErrorMsg(String message) {
		final String str = message;
		BaseActivity1.handler.post(new Runnable() {
			@Override
			public void run() {
				Toast toast = Toast.makeText(getApplicationContext(), str, Toast.LENGTH_SHORT);
				toast.setGravity(Gravity.CENTER, 0, 0);
				toast.show();
			}
		});
	}

	/**
	 * 提示信息号或请求失败信息
	 *
	 * showErrorRequestFair
	 *
	 */
	public void showE404() {
		BaseActivity1.handler.post(new Runnable() {
			@Override
			public void run() {
				Toast toast = Toast.makeText(getApplicationContext(), "手机信号差或服务器维护，请稍候重试。谢谢！", Toast.LENGTH_SHORT);
				toast.setGravity(Gravity.CENTER, 0, 0);
				toast.show();
			}
		});
	}


}
