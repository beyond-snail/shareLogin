package myyl.com.myyl.activity.base;

import android.Manifest;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.TabActivity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TabHost;
import android.widget.TabHost.OnTabChangeListener;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.ArrayList;
import java.util.List;

import me.weyye.hipermission.HiPermission;
import me.weyye.hipermission.PermissionCallback;
import me.weyye.hipermission.PermissionItem;
import myyl.com.myyl.R;
import myyl.com.myyl.enums.TypeBottomTab;
import myyl.com.myyl.utils.MyActivityManager;
import myyl.com.myyl.utils.StatusBarUtil;
import myyl.com.myyl.utils.ToastUtils;

@SuppressWarnings("deprecation")
public class MainTabActivity extends TabActivity implements OnTabChangeListener
{

	private static final String TAG = "MainTabActivity";

	
	private static final String BOTTOM_TAG = "bottom_menu_";
	
	TypeBottomTab[] tabs = TypeBottomTab.values();
	private TabHost mTabHost;
	private int mCurrentTabIndext = 0;



	@Override
	protected void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);

		setContentView(R.layout.basic_tab);
		MyActivityManager.getInstance().addActivity(this);
		StatusBarUtil.setColor(this, getResources().getColor(R.color.statusBar), 0);

		
		initView();
		setNotify();
		requestPermissions();
	}



	private void requestPermissions() {


		List<PermissionItem> permissionItems = new ArrayList<PermissionItem>();
		permissionItems.add(new PermissionItem(Manifest.permission.WRITE_EXTERNAL_STORAGE, "存储", R.drawable.permission_ic_storage));
		permissionItems.add(new PermissionItem(Manifest.permission.ACCESS_FINE_LOCATION, "定位", R.drawable.permission_ic_location));
		permissionItems.add(new PermissionItem(Manifest.permission.READ_PHONE_STATE, "电话", R.drawable.permission_ic_phone));
		permissionItems.add(new PermissionItem(Manifest.permission.CAMERA, "相机", R.drawable.permission_ic_camera));
		permissionItems.add(new PermissionItem(Manifest.permission.RECORD_AUDIO, "录音", R.drawable.permission_ic_storage));
//		permissionItems.add(new PermissionItem(Manifest.permission.WRITE_SETTINGS, "设置", R.drawable.permission_ic_storage));
		HiPermission.create(this)
				.permissions(permissionItems)
				.checkMutiPermission(new PermissionCallback() {
					@Override
					public void onClose() {
						Log.i(TAG, "onClose");
						ToastUtils.showShort(MainTabActivity.this, "用户关闭权限申请");
//						versionUpdate();
					}

					@Override
					public void onFinish() {
//						versionUpdate();
					}

					@Override
					public void onDeny(String permisson, int position) {
						Log.i(TAG, "onDeny");
					}

					@Override
					public void onGuarantee(String permisson, int position) {
						Log.i(TAG, "onGuarantee");
					}
				});
	}

	private void setNotify(){
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
			NotificationManager mNotificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
			// 通知渠道的id
			String id = "1";
			// 用户可以看到的通知渠道的名字.
			CharSequence name = "notification channel";
			// 用户可以看到的通知渠道的描述
			String description = "notification description";
//			int importance = NotificationManager.IMPORTANCE_HIGH;
			NotificationChannel mChannel = new NotificationChannel(id, name, NotificationManager.IMPORTANCE_HIGH);
			// 配置通知渠道的属性
			mChannel.setDescription(description);
			// 设置通知出现时的闪灯（如果 android 设备支持的话）
			mChannel.enableLights(false);
			mChannel.setLightColor(Color.RED);
			// 设置通知出现时的震动（如果 android 设备支持的话）
			mChannel.enableVibration(true);
			mChannel.setVibrationPattern(new long[]{100, 200, 300, 400, 500, 400, 300, 200, 400});
			//最后在notificationmanager中创建该通知渠道
			mNotificationManager.createNotificationChannel(mChannel);
		}
	}



	
	private void initView()
	{
		mTabHost = getTabHost();
		mTabHost.setOnTabChangedListener(this);
		if(mTabHost.getChildCount()!=0){mTabHost.clearAllTabs();}
		int tabnums = tabs.length;
		for (int i = 0; i < tabnums; i++) {
			RelativeLayout mLayoutTab = (RelativeLayout) getLayoutInflater().inflate(R.layout.basic_tab_view, null);
			ImageView tabIcon1 = (ImageView) mLayoutTab.findViewById(R.id.tab_img);
			tabIcon1.setBackgroundResource(tabs[i].getTabIcon());
			TextView tabTv1 = (TextView) mLayoutTab.findViewById(R.id.tab_txt);
			tabTv1.setText(tabs[i].getTabName());
			mTabHost.addTab(mTabHost.newTabSpec(BOTTOM_TAG+i).setIndicator(mLayoutTab).setContent(new Intent(MainTabActivity.this, tabs[i].getTargetClass())));
		}
		mTabHost.setCurrentTab(mCurrentTabIndext);
	}
	
	@Override
	public void onTabChanged(String arg0){}
	
	@Override
	protected void onNewIntent(Intent intent)
	{
		super.onNewIntent(intent);
	}
	
	@Override
	protected void onResume() {
		super.onResume();
	}
	
	@Override
	protected void onDestroy() {
		ImageLoader.getInstance().clearMemoryCache();
		super.onDestroy();
	}
	
}
