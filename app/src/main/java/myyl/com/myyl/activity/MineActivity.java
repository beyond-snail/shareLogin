package myyl.com.myyl.activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;


import org.json.JSONException;
import org.json.JSONObject;

import myyl.com.myyl.R;
import myyl.com.myyl.activity.base.TabBasicActivity;
import myyl.com.myyl.utils.MyActivityManager;

public class MineActivity extends TabBasicActivity implements View.OnClickListener{


	private static final String TAG = "MineActivity";
	private ImageView img_user_icon;
	private TextView tv_name;
	private TextView tv_time;
	private TextView tv_balance_amt;
	private TextView tv_point_amt;
	private TextView tv_version;

	private long groupId;
	private long userId;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		mContext = this;
		setContentView(R.layout.activity_mine);
		MyActivityManager.getInstance().addActivity(this);
		initView();
	}
	
	private void initView(){
//		showView(R.id.titleback_btn_back, false);
//		setTvText(R.id.tv_title, "我的");
//		showView(R.id.next_sure, false);


	}


	@Override
	protected void onResume() {
		super.onResume();
	}



	
}
