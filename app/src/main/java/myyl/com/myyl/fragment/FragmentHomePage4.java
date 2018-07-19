package myyl.com.myyl.fragment;


import java.util.ArrayList;
import java.util.List;

import myyl.com.myyl.R;
import myyl.com.myyl.adapter.AdapterZx;
import myyl.com.myyl.model.MyZxInfo;
import myyl.com.myyl.utils.LogUtils;
import myyl.com.myyl.utils.views.MyListView;

public class FragmentHomePage4 extends AbstractFragment{
    private static final String TAG = "FragmentHomePage4";


    private List<MyZxInfo> myZxInfos = new ArrayList<>();
    private AdapterZx adapter;
    private MyListView myListView;


    @Override
    protected void initView() {
        myListView = findViewById(R.id.listview);
        adapter = new AdapterZx(mContext, myZxInfos);
        myListView.setAdapter(adapter);
    }



    private void testData() {
        myZxInfos.clear();
        for (int i = 0; i < 3; i++) {
            MyZxInfo zxInfo = new MyZxInfo();
            zxInfo.setUserUrl("http://d.5857.com/xgs_150428/001.jpg");
            zxInfo.setUserName("测试" + i);
            zxInfo.setTime("4:10 PM");
            zxInfo.setContent("等级是否就开始福建省了福建省了房间乱收费上课了的飞机上课的房间看电视放假了的书法家了第三方吉林省福建省的否打开司法局的书法家");
            myZxInfos.add(zxInfo);
        }
        adapter.notifyDataSetChanged();
    }







    protected void lazyLoad() {
        LogUtils.e(TAG1+TAG, "lazyLoad()");
//        reloadData();
        testData();
    }

    @Override
    protected int setContentView() {
        return R.layout.fragment_home_page4;
    }

    @Override
    public void onResume() {
        super.onResume();
        LogUtils.e(TAG1+TAG, "onResume");
//        reloadData();
    }




}
