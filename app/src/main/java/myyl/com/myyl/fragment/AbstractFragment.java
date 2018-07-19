package myyl.com.myyl.fragment;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;


import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

import myyl.com.myyl.R;


public abstract class AbstractFragment extends Fragment {

	protected  final String TAG1 = "FragmentTest--";

	protected final String TAG = "AbstractFragment";

	public static final Handler handler = new Handler();

	public final static int DATA_LOAD_ING = 0x001;
	public final static int DATA_LOAD_COMPLETE = 0x002;
	public final static int DATA_LOAD_FAIL = 0x003;

	/**
	 * 主activity
	 */
	public Context mContext;


	/**
	 * 加载等待效果
	 */
	public ProgressDialog progress;


	/**
	 * 视图是否已经初初始化
	 */
	protected boolean isInit = false;
	protected boolean isLoad = false;

	private View view;


	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
//		LogUtils.e(TAG1+TAG, " onCreate() --> isVisibleToUser = "+ getUserVisibleHint());
		super.onCreate(savedInstanceState);
		mContext = getActivity();
//		PushAgent.getInstance(mContext).onAppStart();
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
//		LogUtils.e(TAG1+TAG, "onCreateView");

		view = inflater.inflate(setContentView(), container, false);
		//初始化元素
		initView();
		isInit = true;
		/**初始化的时候去加载数据**/
		isCanLoadData();

		return view;
	}



	@Override
	public void onHiddenChanged(boolean hidden) {
//		LogUtils.e(TAG1+TAG, " onHiddenChanged = "+hidden);
		super.onHiddenChanged(hidden);

		if (!hidden) {
			isCanLoadData();
		}
	}

	/**
	 * 视图是否已经对用户可见，系统的方法
	 */
	@Override
	public void setUserVisibleHint(boolean isVisibleToUser) {
//		LogUtils.e(TAG1+TAG, " setUserVisibleHint() --> isVisibleToUser = " + isVisibleToUser);
		super.setUserVisibleHint(isVisibleToUser);
		isCanLoadData();
	}

	/**
	 * 是否可以加载数据
	 * 可以加载数据的条件：
	 * 1.视图已经初始化
	 * 2.视图对用户可见
	 */
	private void isCanLoadData() {
		if (!isInit) {
//			LogUtils.e(TAG1+TAG, " isInit=" + isInit);
			return;
		}
		if (getUserVisibleHint()) {
			lazyLoad();
			isLoad = true;
		} else {
			if (isLoad) {
//				LogUtils.e(TAG1+TAG, " stopLoad()");
				stopLoad();
			}
//			LogUtils.e(TAG1+TAG, " isCanLoadData = "+ getUserVisibleHint());
		}
	}

	protected abstract void initView();


	/**
	 * 当视图初始化并且对用户可见的时候去真正的加载数据
	 */
	protected abstract void lazyLoad();

	/**
	 * 当视图已经对用户不可见并且加载过数据，如果需要在切换到其他页面时停止加载数据，可以调用此方法
	 */
	protected void stopLoad() {
	}


	/**
	 * 设置Fragment要显示的布局
	 *
	 * @return 布局的layoutId
	 */
	protected abstract int setContentView();

	/**
	 * 获取设置的布局
	 *
	 * @return
	 */
	protected View getContentView() {
		return view;
	}

	/**
	 * 找出对应的控件
	 *
	 * @param id
	 * @param <T>
	 * @return
	 */
	protected <T extends View> T findViewById(int id) {

		return (T) getContentView().findViewById(id);
	}

	protected void showToast(String message) {
		if (!TextUtils.isEmpty(message)) {
			Toast.makeText(getContext(), message, Toast.LENGTH_SHORT).show();
		}

	}




	@Override
	public void onDestroy() {
		super.onDestroy();
		isInit = false;
		isLoad = false;
		if (progress != null) {
			progress.dismiss();
		}

		if(EventBus.getDefault().isRegistered(this)) {
			EventBus.getDefault().unregister(this);
		}
	}

	@Override
	public void onPause() {
		super.onPause();
		if (progress != null) {
			progress.dismiss();
		}
	}

	@Override
	public void onStop() {
		super.onStop();
		if (progress != null) {
			progress.dismiss();
		}
	}

	/**
	 * 显示字符串消息
	 * 
	 * @param message
	 */
	public void showProgress(final String message) {
		AbstractFragment.handler.post(new Runnable() {

			@Override
			public void run() {
				if (progress != null) {
					progress.dismiss();
				}
				progress = new ProgressDialog(getActivity());
				progress.setProgressStyle(ProgressDialog.STYLE_SPINNER);
				progress.setMessage(message);
//				progress.setCancelable(false);
				progress.show();
				View layout = View.inflate(mContext, R.layout.layout_dialog, null);
				TextView tv_msg = (TextView) layout.findViewById(R.id.tv_msg);
				if (!"".equals(message)) {
					tv_msg.setText("  " + message);
				}
				progress.getWindow().setContentView(layout);// show()代码后
			}
		});
	}
	
	public void setProgressText(String msg) {
		if (progress != null && progress.isShowing()) {
			progress.setMessage(msg);
		}
	}

	/**
	 * 隐藏字符串消息
	 */
	public void disShowProgress() {
		AbstractFragment.handler.post(new Runnable() {

			@Override
			public void run() {
				if (progress != null) {
					progress.dismiss();
				}
			}
		});

	}

	/**
	 * 提示信息
	 * 
	 * @param message
	 */
	public void showErrorMsg(String message) {
		final String str = message;
		AbstractFragment.handler.post(new Runnable() {
			@Override
			public void run() {
				Toast toast = Toast.makeText(mContext, str, Toast.LENGTH_SHORT);
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
		AbstractFragment.handler.post(new Runnable() {
			@Override
			public void run() {
				Toast toast = Toast.makeText(mContext, "手机信号差或服务器维护，请稍候重试。谢谢！", Toast.LENGTH_SHORT);
				toast.setGravity(Gravity.CENTER, 0, 0);
				toast.show();
			}
		});
	}

	/**
	 * 显示数据加载状态
	 * 
	 * @param loading
	 * @param datas
	 * @param type
	 */
	public void viewSwitch(View loading, View datas, int type) {
		switch (type) {
		case DATA_LOAD_ING:
			datas.setVisibility(View.GONE);
			loading.setVisibility(View.VISIBLE);
			break;
		case DATA_LOAD_COMPLETE:
			datas.setVisibility(View.VISIBLE);
			loading.setVisibility(View.GONE);
			break;
		case DATA_LOAD_FAIL:
			datas.setVisibility(View.GONE);
			loading.setVisibility(View.GONE);
			break;
		}
	}




	/**
	 * 获取TextView
	 */
	public TextView findTextViewById(int id) {
		return (TextView) view.findViewById(id);
	}

	/**
	 * 获取Button
	 * 
	 * @param id
	 * @return
	 */
	public Button findButtonById(int id) {
		return (Button) view.findViewById(id);
	}

	/**
	 * 获取ImageButton
	 * 
	 * @param id
	 * @return
	 */
	public ImageButton findImageButtonById(int id) {
		return (ImageButton) view.findViewById(id);
	}

	/**
	 * 获取ImageView
	 * 
	 * @param id
	 * @return
	 */
	public ImageView findImageViewById(int id) {
		return (ImageView) view.findViewById(id);
	}

	/**
	 * 获取LinearLayout
	 * 
	 * @param id
	 * @return
	 */
	public LinearLayout findLayoutById(int id) {
		return (LinearLayout) view.findViewById(id);
	}

	/**
	 * 获取LinearLayout
	 * 
	 * @param id
	 * @return
	 */
	public EditText findEditTextById(int id) {
		return (EditText) view.findViewById(id);
	}

	/**
	 * 获取TableRow
	 * 
	 * @param id
	 * @return
	 */
	public TableRow findTableRowById(int id) {
		return (TableRow) view.findViewById(id);
	}

	/**
	 * 进入Activity
	 */
	public void goActivity(Class<?> inClass) {
		Intent intent = new Intent(mContext, inClass);
		startActivity(intent);
	}

	/**
	 * 进入Activity
	 */
	public void goActivityAddBundle(Class<?> inClass, Bundle bundle) {
		Intent intent = new Intent(mContext, inClass);
		intent.putExtras(bundle);
		startActivity(intent);
	}

	/**
	 * 文本View
	 */
	public TextView textView(int textview) {
		return (TextView) view.findViewById(textview);
	}

	/**
	 * 文本button
	 */
	public Button button(int id) {
		return (Button) view.findViewById(id);
	}

	/**
	 * 文本button
	 */
	public ImageView imageView(int id) {
		return (ImageView) view.findViewById(id);
	}

	/**
	 * 文本editText
	 */
	public EditText editText(int id) {
		return (EditText) view.findViewById(id);
	}

	public LinearLayout linearLayout(int id) {
		return (LinearLayout) view.findViewById(id);
	}



<<<<<<< HEAD
=======

>>>>>>> origin/master
}
