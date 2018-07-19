package myyl.com.myyl.activity.base;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.LayoutRes;
import android.text.TextUtils;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;


import java.util.ArrayList;
import java.util.List;

import myyl.com.myyl.R;
import myyl.com.myyl.utils.MyActivityManager;
import myyl.com.myyl.utils.StatusBarUtil;


public abstract class BaseActivity extends Activity {


    private static final String TAG = "BaseActivity";


    protected final static int DATA_LOAD_ING = 0x001;
    protected final static int DATA_LOAD_COMPLETE = 0x002;
    protected final static int DATA_LOAD_FAIL = 0x003;

    public static final Handler handler = new Handler();

    protected CallbackLister callbacklister;

    public interface CallbackLister{
        void show();
    }

    /**
     * 上下文 当进入activity时必须 mContext = this 方可使用，否则会报空指针
     */
    public Context mContext;

    /**
     * 加载等待效果
     */
    public ProgressDialog progress;
    // public AlertDialog progress;


    /**
     * 初始化创建
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mContext = this;
    }

    @Override
    public void setContentView(@LayoutRes int layoutResID) {
        super.setContentView(layoutResID);
        setStatusBar();
    }

    protected void setStatusBar() {
        StatusBarUtil.setColor(this, getResources().getColor(R.color.translucent_60), 112);
    }

    /**
     * 重回前台显示调用
     */
    @Override
    protected void onResume() {
        super.onResume();
    }

    /**
     * Activity销毁，关闭加载效果
     */
    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (progress != null) {
            progress.dismiss();
        }
    }

    /**
     * Activity暂停，关闭加载效果
     */
    @Override
    protected void onPause() {
        super.onPause();
        if (progress != null) {
            progress.dismiss();
        }
    }

    /**
     * Activity停止，关闭加载效果
     */
    @Override
    protected void onStop() {
        super.onStop();
        if (progress != null) {
            progress.dismiss();
        }
    }

//    /**
//     * 初始化标题和回退
//     */
//    public void initTitle(String title) {
//        if (textView(R.id.tv_header) != null) {
//            textView(R.id.tv_header).setText(title);
//        }
//        if (findViewById(R.id.backBtn) != null) {
//            findViewById(R.id.backBtn).setOnClickListener(new View.OnClickListener() {
//
//                @Override
//                public void onClick(View v) {
//                    onBackPressed();
//                }
//            });
//        }
//    }

    /**
     * 触发手机返回按钮
     */
    @Override
    public void onBackPressed() {
        MyActivityManager.getInstance().removeActivity(BaseActivity.this);
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
                BaseActivity.this.disProgressHander.sendMessage(message1);
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
            progress = new ProgressDialog(BaseActivity.this);
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
        progress = new ProgressDialog(BaseActivity.this);
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


    public void toast(String msg) {
        Toast toast = Toast.makeText(mContext, msg, Toast.LENGTH_SHORT);
        toast.setGravity(Gravity.CENTER, 0, 0);
        toast.show();
    }

    /**
     * 提示信息
     *
     */
    public void showErrorMsg(String message) {
        final String str = message;
        BaseActivity.handler.post(new Runnable() {
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
        BaseActivity.handler.post(new Runnable() {
            @Override
            public void run() {
                Toast toast = Toast.makeText(getApplicationContext(), "手机信号差或服务器维护，请稍候重试。谢谢！", Toast.LENGTH_SHORT);
                toast.setGravity(Gravity.CENTER, 0, 0);
                toast.show();
            }
        });
    }

    /**
     * 提示信息
     *
     */
    public void showMsgAndDisProgress(String message) {
        final String str = message;
        BaseActivity.handler.post(new Runnable() {
            @Override
            public void run() {
                Toast toast = Toast.makeText(getApplicationContext(), str, Toast.LENGTH_SHORT);
                toast.setGravity(Gravity.CENTER, 0, 0);
                toast.show();
                disShowProgress();
            }
        });
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

    /**
     * 显示数据加载状态
     *
     * @param loading
     * @param
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





}
