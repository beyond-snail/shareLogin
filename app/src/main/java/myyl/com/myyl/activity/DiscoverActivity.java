package myyl.com.myyl.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import myyl.com.myyl.R;
import myyl.com.myyl.activity.base.TabBasicActivity;
import myyl.com.myyl.utils.MyActivityManager;

public class DiscoverActivity extends TabBasicActivity implements View.OnClickListener{


	private static final String TAG = "DiscoverActivity";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		mContext = this;
		setContentView(R.layout.activity_discover);
		MyActivityManager.getInstance().addActivity(this);
		initView();
	}
	
	private void initView(){


	}


	@Override
	protected void onResume() {
		super.onResume();
	}



	
}
