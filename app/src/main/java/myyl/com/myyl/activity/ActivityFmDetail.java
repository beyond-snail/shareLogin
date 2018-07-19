package myyl.com.myyl.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import myyl.com.myyl.R;
import myyl.com.myyl.activity.base.BaseActivity;
import myyl.com.myyl.activity.base.TabBasicActivity;
import myyl.com.myyl.utils.MyActivityManager;

public class ActivityFmDetail extends BaseActivity {


	private static final String TAG = "ActivityFmDetail";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		mContext = this;
		setContentView(R.layout.activity_fm_detail);
		MyActivityManager.getInstance().addActivity(this);
		initView();
	}
	
	private void initView(){
		showView(R.id.titleback_btn_back, true);
		setTvText(R.id.tv_title, "我的FM");
		showView(R.id.next_sure, false);


	}


	@Override
	protected void onResume() {
		super.onResume();
	}



	
}
