package myyl.com.myyl.activity.base;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import myyl.com.myyl.myapplication.MyApplication;


public abstract class RootActivity extends Activity implements OnClickListener
{
	/**
	 * 上下文 当进入activity时必须 mContext = this 方可使用，否则会报空指针
	 */
	public Context mContext;
	protected Activity mActivity;
	protected MyApplication mApp;
	private Dialog dialog;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		mContext = this;
		mApp = MyApplication.getmInstance();
	}
	
	@Override
	protected void onDestroy() {
		super.onDestroy();
	}
	

	
	protected String getTvText(int vid){
		TextView view = (TextView) findViewById(vid);
		CharSequence cs = view.getText();
		if(cs!=null)return cs.toString();
		else return "";
	}
	
	protected void setTvEnable(int vid, boolean boo){
		View view = findViewById(vid);
		if(view!=null){view.setEnabled(boo);}
	}
	



	/**
	 * 文本View
	 */
	public TextView textView(int textview) {
		return (TextView) findViewById(textview);
	}

	protected void setTvText(int vid, String text) {
		TextView view = (TextView) findViewById(vid);
		if(view!=null){view.setText(TextUtils.isEmpty(text) ? "": text);}
	}

	protected void showView(int vid, boolean isShow){
		View view = (View) findViewById(vid);
		if(view!=null){
			view.setVisibility(isShow? View.VISIBLE: View.GONE);
		}
	}

	protected void showLayoutView(int vid, boolean isShow){
		LinearLayout view = (LinearLayout) findViewById(vid);
		if(view!=null){
			view.setVisibility(isShow? View.VISIBLE: View.GONE);
		}
	}

	/**
	 * 文本button
	 */
	public Button button(int id) {
		return (Button) findViewById(id);
	}

	/**
	 * 文本button
	 */
	public ImageView imageView(int id) {
		return (ImageView) findViewById(id);
	}

	/**
	 * 文本editText
	 */
	public EditText editText(int id) {
		return (EditText) findViewById(id);
	}

	public LinearLayout linearLayout(int id) {
		return (LinearLayout) findViewById(id);
	}
	public RelativeLayout relativeLayout(int id) {
		return (RelativeLayout) findViewById(id);
	}

    

	
}
