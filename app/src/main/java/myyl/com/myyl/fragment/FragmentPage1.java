package myyl.com.myyl.fragment;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Handler;
import android.text.format.DateUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ScrollView;

import com.google.gson.Gson;
import com.handmark.pulltorefresh.library.ILoadingLayout;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshScrollView;
import com.loopj.android.http.RequestParams;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import myyl.com.myyl.R;
import myyl.com.myyl.myapplication.MyApplication;
import myyl.com.myyl.utils.LogUtils;
import myyl.com.myyl.utils.StringUtils;
import myyl.com.myyl.utils.views.MyListView;


public class FragmentPage1 extends AbstractFragment{
    private static final String TAG = "FragmentOrderManagerAll";




    @Override
    protected void initView() {

    }





    protected void lazyLoad() {
        LogUtils.e(TAG1+TAG, "lazyLoad()");
//        reloadData();
    }

    @Override
    protected int setContentView() {
        return R.layout.fragment1;
    }

    @Override
    public void onResume() {
        super.onResume();
        LogUtils.e(TAG1+TAG, "onResume");
//        reloadData();
    }









}
