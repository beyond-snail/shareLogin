package myyl.com.myyl.activity;

import android.os.Bundle;
import android.os.Handler;
import android.text.format.DateUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ScrollView;

import com.handmark.pulltorefresh.library.ILoadingLayout;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshScrollView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import myyl.com.myyl.R;
import myyl.com.myyl.activity.base.BaseActivity;
import myyl.com.myyl.adapter.AdapterCollect;
import myyl.com.myyl.model.MyCollect;
import myyl.com.myyl.utils.MyActivityManager;
import myyl.com.myyl.utils.views.MyListView;

public class ActivityCollect extends BaseActivity {


    private static final String TAG = "ActivityConsultation";
    @BindView(R.id.titleback_btn_back)
    LinearLayout titlebackBtnBack;
    @BindView(R.id.listview)
    MyListView listview;
    @BindView(R.id.id_no_data)
    LinearLayout idNoData;
    @BindView(R.id.pullToRefreshScrollView)
    PullToRefreshScrollView pullToRefreshScrollView;


    private List<MyCollect> myCollects = new ArrayList<MyCollect>();
    private AdapterCollect adapterCollect;


    private int page = 1;

    private Handler handler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = this;
        setContentView(R.layout.activity_collect);
        ButterKnife.bind(this);
        MyActivityManager.getInstance().addActivity(this);
        initView();
        testData();
    }

    private void testData() {

        for (int i = 0; i < 3; i++) {
            MyCollect fmInfo = new MyCollect();
            fmInfo.setUserUrl("http://d.5857.com/xgs_150428/001.jpg");
            fmInfo.setUserName("测试" + i);
            fmInfo.setPersonCount(111);
            fmInfo.setDrugStr("PD1");
            myCollects.add(fmInfo);
        }
        adapterCollect.notifyDataSetChanged();


    }

    private void initView() {
        showView(R.id.titleback_btn_back, true);
        setTvText(R.id.tv_title, "找药");
        showView(R.id.next_sure, false);

        //刷新操作
        adapterCollect = new AdapterCollect(mContext, myCollects);
        listview.setAdapter(adapterCollect);
        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
//                startActivity(new Intent(mContext, ActivityConsultationDetail.class));
            }
        });

        // 下拉刷新、上拉加载更多
        pullToRefreshScrollView.setMode(PullToRefreshBase.Mode.BOTH);
        // TODO:必须先设置Mode后再设置刷新文本
        ILoadingLayout startLabels = pullToRefreshScrollView.getLoadingLayoutProxy(true, false);
        startLabels.setPullLabel("下拉刷新...");// 刚下拉时，显示的提示
        startLabels.setRefreshingLabel("正在载入...");// 刷新时
        startLabels.setReleaseLabel("释放立即刷新...");// 下来达到一定距离时，显示的提示
        // TODO:必须先设置Mode后再设置刷新文本
        ILoadingLayout endLabels = pullToRefreshScrollView.getLoadingLayoutProxy(false, true);
        endLabels.setPullLabel("上拉加载更多...");// 刚下拉时，显示的提示
        endLabels.setRefreshingLabel("正在载入...");// 刷新时
        endLabels.setReleaseLabel("释放立即加载...");// 下来达到一定距离时，显示的提示
        pullToRefreshScrollView.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<ScrollView>() {

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
                        pullToRefreshScrollView.onRefreshComplete();
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


                if (myCollects.size() == 0) {
                    handler.postDelayed(new Runnable() {

                        @Override
                        public void run() {
                            showErrorMsg("没有更多了");
                            pullToRefreshScrollView.onRefreshComplete();
                        }
                    }, 1000);
                } else {
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


    @OnClick({R.id.titleback_btn_back})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.titleback_btn_back:
                finish();
                break;
        }
    }

}
