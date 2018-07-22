package myyl.com.myyl.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.text.format.DateUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ScrollView;

import com.handmark.pulltorefresh.library.ILoadingLayout;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshScrollView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import myyl.com.myyl.R;
import myyl.com.myyl.activity.base.BaseActivity;
import myyl.com.myyl.adapter.AdapterFmMoreList;
import myyl.com.myyl.adapter.GirdDropDownAdapter;
import myyl.com.myyl.adapter.ListDropDownAdapter;
import myyl.com.myyl.model.MyFmInfo;
import myyl.com.myyl.utils.MyActivityManager;
import myyl.com.myyl.utils.views.MyListView;
import myyl.com.myyl.utils.views.drawdownMenu.DropDownMenu;
import myyl.com.myyl.utils.views.drawdownMenu.PopNoListener;

public class ActivityFmMore extends BaseActivity {


    private static final String TAG = "ActivityFmMore";
    @BindView(R.id.titleback_btn_back)
    LinearLayout titlebackBtnBack;
    @BindView(R.id.dropDownMenu)
    DropDownMenu dropDownMenu;


    private String citys[] = {"不限", "武汉", "北京", "上海", "成都", "广州", "深圳", "重庆", "天津", "西安", "南京", "杭州"};
    private String ages[] = {"不限", "18岁以下", "18-22岁", "23-26岁", "27-35岁", "35岁以上"};

    private String headers[] = {"推荐", "评价最高", "癌肿", "价格"}; //
    private List<View> popupViews = new ArrayList<>();

    private GirdDropDownAdapter cityAdapter;
    private ListDropDownAdapter ageAdapter;
    private List<MyFmInfo> myFmInfos = new ArrayList<MyFmInfo>();
    private AdapterFmMoreList adapterFmMoreList;

    private PullToRefreshScrollView mPullRefreshScrollView;
    private MyListView myListView;
    private LinearLayout id_no_data;

    private int page = 1;

    private Handler handler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = this;
        setContentView(R.layout.activity_fm_more);
        ButterKnife.bind(this);
        MyActivityManager.getInstance().addActivity(this);
        initView();
        testData();
    }

    private void testData() {

        myListView.setVisibility(View.VISIBLE);
        id_no_data.setVisibility(View.GONE);
        for (int i = 0; i < 3; i++) {
            MyFmInfo fmInfo = new MyFmInfo();
            fmInfo.setUserUrl("http://d.5857.com/xgs_150428/001.jpg");
            fmInfo.setUserName("测试" + i);
            fmInfo.setContent("等级是否就开始福建省了福建省了房间乱收费上课了的飞机上课的房间看电视放假了的书法家了第三方吉林省福建省的否打开司法局的书法家");
            fmInfo.setNum(12);
            fmInfo.setSecond(122);
            fmInfo.setAmount(1000);
            fmInfo.setTime("4天前");
            myFmInfos.add(fmInfo);
        }
        adapterFmMoreList.notifyDataSetChanged();


    }

    private void initView() {
        showView(R.id.titleback_btn_back, true);
        setTvText(R.id.tv_title, "我的FM");
        showView(R.id.next_sure, false);


        //init city menu
        final ListView cityView = new ListView(this);
        cityAdapter = new GirdDropDownAdapter(this, Arrays.asList(citys));
        cityView.setDividerHeight(0);
        cityView.setAdapter(cityAdapter);

        //init age menu
        final ListView ageView = new ListView(this);
        ageView.setDividerHeight(0);
        ageAdapter = new ListDropDownAdapter(this, Arrays.asList(ages));
        ageView.setAdapter(ageAdapter);


        //add item click event
        cityView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                cityAdapter.setCheckItem(position);
                dropDownMenu.setTabText(position == 0 ? headers[0] : citys[position]);
                dropDownMenu.closeMenu();
            }
        });

        ageView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ageAdapter.setCheckItem(position);
                dropDownMenu.setTabText(position == 0 ? headers[1] : ages[position]);
                dropDownMenu.closeMenu();
            }
        });


        //init popupViews
        popupViews.add(cityView);
        popupViews.add(ageView);


        //init context view
        LayoutInflater inflater=getLayoutInflater();
        View view=inflater.inflate(R.layout.activity_fm_more_list,null);
        mPullRefreshScrollView = view.findViewById(R.id.pull_refresh_scrollview);
        myListView = view.findViewById(R.id.listview);
        id_no_data = view.findViewById(R.id.id_no_data);

        //init dropdownview
        dropDownMenu.setDropDownMenu(Arrays.asList(headers), popupViews, view, new PopNoListener(){

            @Override
            public void noPopCallback(int position) {
                toast("当前选项："+position);
            }
        });




        //刷新操作
        myListView = findViewById(R.id.listview);
        adapterFmMoreList = new AdapterFmMoreList(mContext, myFmInfos);
        myListView.setAdapter(adapterFmMoreList);
        myListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                startActivity(new Intent(mContext, ActivityFmDetail.class));
            }
        });


        mPullRefreshScrollView = findViewById(R.id.pull_refresh_scrollview);
        // 下拉刷新、上拉加载更多
        mPullRefreshScrollView.setMode(PullToRefreshBase.Mode.BOTH);
        // TODO:必须先设置Mode后再设置刷新文本
        ILoadingLayout startLabels = mPullRefreshScrollView.getLoadingLayoutProxy(true, false);
        startLabels.setPullLabel("下拉刷新...");// 刚下拉时，显示的提示
        startLabels.setRefreshingLabel("正在载入...");// 刷新时
        startLabels.setReleaseLabel("释放立即刷新...");// 下来达到一定距离时，显示的提示
        // TODO:必须先设置Mode后再设置刷新文本
        ILoadingLayout endLabels = mPullRefreshScrollView.getLoadingLayoutProxy(false, true);
        endLabels.setPullLabel("上拉加载更多...");// 刚下拉时，显示的提示
        endLabels.setRefreshingLabel("正在载入...");// 刷新时
        endLabels.setReleaseLabel("释放立即加载...");// 下来达到一定距离时，显示的提示
        mPullRefreshScrollView.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<ScrollView>() {

            @Override
            public void onPullDownToRefresh(PullToRefreshBase<ScrollView> refreshView) {
                // 下拉刷新
                String label = DateUtils.formatDateTime(mContext, System.currentTimeMillis(),
                        DateUtils.FORMAT_SHOW_TIME | DateUtils.FORMAT_SHOW_DATE | DateUtils.FORMAT_ABBREV_ALL);
                refreshView.getLoadingLayoutProxy(true, false).setLastUpdatedLabel("更新于：" + label);
//                reloadData();
//                if (myFmInfos.size() == 0) {
                    handler.postDelayed(new Runnable() {

                        @Override
                        public void run() {
                            showErrorMsg("没有更多了");
                            mPullRefreshScrollView.onRefreshComplete();
                        }
                    }, 1000);
//                }else{
//                    page++;
//                    loadData();
//                }
            }

            @Override
            public void onPullUpToRefresh(PullToRefreshBase<ScrollView> refreshView) {
                // 上拉加载更多
                String label = DateUtils.formatDateTime(mContext, System.currentTimeMillis(),
                        DateUtils.FORMAT_SHOW_TIME | DateUtils.FORMAT_SHOW_DATE | DateUtils.FORMAT_ABBREV_ALL);
                refreshView.getLoadingLayoutProxy(false, true).setLastUpdatedLabel("更新于：" + label);


                if (myFmInfos.size() == 0) {
                    handler.postDelayed(new Runnable() {

                        @Override
                        public void run() {
                            showErrorMsg("没有更多了");
                            mPullRefreshScrollView.onRefreshComplete();
                        }
                    }, 1000);
                }else{
                    page++;
                    loadData();
                }


            }
        });



    }

    private void loadData() {

    }


    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    public void onBackPressed() {
        //退出activity前关闭菜单
        if (dropDownMenu.isShowing()) {
            dropDownMenu.closeMenu();
        } else {
            super.onBackPressed();
        }
    }


    @OnClick({R.id.titleback_btn_back})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.titleback_btn_back:
                finish();
                break;

        }
    }
}
