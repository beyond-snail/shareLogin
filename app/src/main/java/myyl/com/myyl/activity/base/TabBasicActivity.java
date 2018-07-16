package myyl.com.myyl.activity.base;

import android.content.Intent;
import android.view.KeyEvent;
import android.widget.Toast;

public class TabBasicActivity extends BaseActivity1 {

	private static int PAGE_EXIT = 999; //退出
	private long exitTime = 0;
	
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
	    if(keyCode == KeyEvent.KEYCODE_BACK&& event.getAction() == KeyEvent.ACTION_DOWN){
	        if((System.currentTimeMillis()-exitTime) > 1000){
	            Toast.makeText(this, "再按一次返回键退出", Toast.LENGTH_SHORT).show();
	            exitTime = System.currentTimeMillis();
	        }
	        else {
//	        	mApp.cleanUserInfoEntity();
	        	finish();
	        }
	        return true;
	    }
	    return super.onKeyDown(keyCode, event);
	}

	@Override
	protected void onActivityResult(int arg0, int arg1, Intent arg2) {
		if(arg0==PAGE_EXIT&&arg1==RESULT_OK){
			finish();
		}
		super.onActivityResult(arg0, arg1, arg2);
	}
	
}
