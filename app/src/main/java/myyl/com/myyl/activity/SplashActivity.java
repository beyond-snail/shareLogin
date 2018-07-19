package myyl.com.myyl.activity;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;


import com.allenliu.versionchecklib.callback.OnCancelListener;
import com.allenliu.versionchecklib.core.http.HttpParams;
import com.allenliu.versionchecklib.core.http.HttpRequestMethod;
import com.allenliu.versionchecklib.v2.AllenVersionChecker;
import com.allenliu.versionchecklib.v2.builder.UIData;
import com.allenliu.versionchecklib.v2.callback.RequestVersionListener;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import me.weyye.hipermission.HiPermission;
import me.weyye.hipermission.PermissionCallback;
import me.weyye.hipermission.PermissionItem;
import myyl.com.myyl.R;
import myyl.com.myyl.activity.base.MainTabActivity;
import myyl.com.myyl.model.Update;
import myyl.com.myyl.myapplication.MyApplication;
import myyl.com.myyl.utils.GsonUtil;
import myyl.com.myyl.utils.MyActivityManager;
import myyl.com.myyl.utils.SharedUtil;
import myyl.com.myyl.utils.StringUtils;
import myyl.com.myyl.utils.SystemUtils;
import myyl.com.myyl.utils.ToastUtils;
import myyl.com.myyl.utils.UtilPreference;

public class SplashActivity extends Activity {


	private Context mContext;
	private static final String TAG = "SplashActivity";
	private static final int RC_SETTINGS_SCREEN = 125;

	private int isMustUpdate = 0;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		mContext = this;
		requestWindowFeature(Window.FEATURE_NO_TITLE);  //无title
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);  //全屏
		setContentView(R.layout.activity_splash);

		MyActivityManager.getInstance().addActivity(this);

		requestPermissions();
	}

	private void requestPermissions() {


		List<PermissionItem> permissionItems = new ArrayList<PermissionItem>();
		permissionItems.add(new PermissionItem(Manifest.permission.WRITE_EXTERNAL_STORAGE, "存储", R.drawable.permission_ic_storage));
//		permissionItems.add(new PermissionItem(Manifest.permission.ACCESS_FINE_LOCATION, "定位", R.drawable.permission_ic_location));
		permissionItems.add(new PermissionItem(Manifest.permission.READ_PHONE_STATE, "电话", R.drawable.permission_ic_phone));
		permissionItems.add(new PermissionItem(Manifest.permission.CAMERA, "相机", R.drawable.permission_ic_camera));
		permissionItems.add(new PermissionItem(Manifest.permission.RECORD_AUDIO, "录音", R.drawable.permission_ic_micro_phone));
		HiPermission.create(mContext)
                .permissions(permissionItems)
				.checkMutiPermission(new PermissionCallback() {
					@Override
					public void onClose() {
						Log.e(TAG, "onClose");
						ToastUtils.showShort(mContext, "用户关闭权限申请");
						versionUpdate();
					}

					@Override
					public void onFinish() {
//						versionUpdate();
                        gotoMainPage();
					}

					@Override
					public void onDeny(String permisson, int position) {
						Log.e(TAG, "onDeny");
					}

					@Override
					public void onGuarantee(String permisson, int position) {
						Log.e(TAG, "onGuarantee");
					}
				});
	}



	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);

		if (requestCode == RC_SETTINGS_SCREEN) {
			// Do something after user returned from app settings screen, like showing a Toast.
			Toast.makeText(this, R.string.returned_from_app_settings_to_activity, Toast.LENGTH_SHORT)
					.show();
		}
	}




	/**
	 * 版本检测
	 */
	private void versionUpdate() {
        String url = "";
		HttpParams params = new HttpParams();
//		params.put("types", Config.UPGRADE_CODE+"");
		AllenVersionChecker
				.getInstance()
				.requestVersion()
				.setRequestMethod(HttpRequestMethod.POSTJSON)
				.setRequestParams( params)
				.setRequestUrl(url)
				.request(new RequestVersionListener() {
					@Nullable
					@Override
					public UIData onRequestVersionSuccess(String result) {
						//拿到服务器返回的数据，解析，拿到downloadUrl和一些其他的UI数据
						Log.e(TAG, result);

						try {
							JSONObject message = new JSONObject(result);

							String data = message.optString("result");

							if (StringUtils.isBlank(data)){
								return null;
							}

							Update update = (Update) GsonUtil.getInstance().convertJsonStringToObject(data, Update.class);

							if (update.getVersions() > SystemUtils.getVerCode(mContext)) {
								UIData uiData = UIData
										.create()
										.setDownloadUrl(update.getDown_url())
										.setTitle("提示")
										.setContent(update.getMessage());
								isMustUpdate = update.getMustUpdate();

								return uiData;
							}else{
								autoLogin();
							}

						} catch (JSONException e) {
							e.printStackTrace();
						}

						return null;

					}

					@Override
					public void onRequestVersionFailure(String message) {
						Log.e(TAG, message);
                        ToastUtils.showLong(mContext, message);
					}
				})
				.setOnCancelListener(new OnCancelListener() {
                    @Override
                    public void onCancel() {
                        if (isMustUpdate == 1){
                        	finish();
						}else {
							autoLogin();
						}
                    }
                })
				.excuteMission(mContext);



	}




	private void autoLogin() {

		new Handler().postDelayed(new Runnable() {
			@Override
			public void run() {
				boolean isFirstTime = MyApplication.getmInstance().shareUtil.getShareBooleanPara(SharedUtil.SHARE_OPEN_FIRSTTIME, true);
				if (isFirstTime) {
//					startActivity(new Intent(SplashActivity.this, GuideActivity.class));
					finish();
				} else {
					String username = UtilPreference.getStringValue(mContext, "username");
					String password =  UtilPreference.getStringValue(mContext, "password");
					if (!StringUtils.isBlank(username) && !StringUtils.isBlank(password)) {
						gotoMainPage();
						finish();
					} else {
//						startActivity(new Intent(SplashActivity.this, LoginActivity.class));
						finish();
					}
				}
			}
		}, 1000);

	}

	
	private void gotoMainPage(){
		startActivity(new Intent(SplashActivity.this, MainTabActivity.class));
		finish();
	}

}
