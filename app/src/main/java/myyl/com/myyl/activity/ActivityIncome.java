package myyl.com.myyl.activity;

import android.os.Bundle;
import android.os.Handler;
import android.text.format.DateUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.handmark.pulltorefresh.library.ILoadingLayout;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshScrollView;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import myyl.com.myyl.R;
import myyl.com.myyl.activity.base.BaseActivity;
import myyl.com.myyl.adapter.AdapterIncome;
import myyl.com.myyl.model.IncomeItemInfo;
import myyl.com.myyl.model.IncomeRecord;
import myyl.com.myyl.utils.MyActivityManager;
import myyl.com.myyl.utils.TimeUtil;
import myyl.com.myyl.utils.ToastUtils;
import myyl.com.myyl.utils.views.MyListView;
import myyl.com.myyl.utils.views.pickTime.widget.DatePickDialog;
import myyl.com.myyl.utils.views.pickTime.widget.OnSureLisener;
import myyl.com.myyl.utils.views.pickTime.widget.bean.DateType;

public class ActivityIncome extends BaseActivity {


    private static final String TAG = "ActivityIncome";
    @BindView(R.id.titleback_btn_back)
    LinearLayout titlebackBtnBack;
    @BindView(R.id.listview)
    MyListView listview;
    @BindView(R.id.id_no_data)
    LinearLayout idNoData;
    @BindView(R.id.pullToRefreshScrollView)
    PullToRefreshScrollView pullToRefreshScrollView;
    @BindView(R.id.tv_time)
    TextView tvTime;
    @BindView(R.id.tv_total)
    TextView tvTotal;


    private IncomeRecord incomeRecord;
    private AdapterIncome adapterIncome;


    private int page = 1;

    private Handler handler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = this;
        setContentView(R.layout.activity_income);
        ButterKnife.bind(this);
        MyActivityManager.getInstance().addActivity(this);
        testData();
        initView();
    }

    private void testData() {
        incomeRecord = new IncomeRecord();
        incomeRecord.setTotalAmout(1111111);
        incomeRecord.setDate("2018年7月");
        List<IncomeItemInfo> incomeItemInfos = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            IncomeItemInfo itemInfo = new IncomeItemInfo();
            itemInfo.setAmount(222);
            itemInfo.setTime("2018-12-21 11:11:11");
            itemInfo.setName("测试");
            incomeItemInfos.add(itemInfo);
        }
        incomeRecord.setIncomeItemInfo(incomeItemInfos);
    }

    private void initView() {
        showView(R.id.titleback_btn_back, true);
        setTvText(R.id.tv_title, "收入记录");
        showView(R.id.next_sure, true);
        setTvText(R.id.next_sure, "筛选");
        textView(R.id.next_sure).setTextSize(13);


        //刷新操作
        adapterIncome = new AdapterIncome(mContext, incomeRecord.getIncomeItemInfo());
        listview.setAdapter(adapterIncome);
        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
//                startActivity(new Intent(mContext, ActivityConsultationDetail.class));
            }
        });


//        pullToRefreshScrollView = findViewById(R.id.pull_refresh_scrollview);
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


                if (incomeRecord.getIncomeItemInfo().size() == 0) {
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


    @OnClick({R.id.titleback_btn_back, R.id.next_sure})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.titleback_btn_back:
                finish();
                break;
            case R.id.next_sure:
                DatePickDialog dialog = new DatePickDialog(this);
                //设置上下年分限制
                dialog.setYearLimt(5);
                //设置标题
                dialog.setTitle("选择时间");
                //设置类型
                dialog.setType(DateType.TYPE_YM);
                //设置消息体的显示格式，日期格式
                dialog.setMessageFormat("yyyy-MM");
                //设置选择回调
                dialog.setOnChangeLisener(null);
                //设置点击确定按钮回调
                dialog.setOnSureLisener(new OnSureLisener() {
                    @Override
                    public void onSure(Date date) {
//                        tvDateChoose.setText(TimeUtil.getStringFromDate(date, "yyyy-MM-dd"));
                        ToastUtils.showShort(mContext, TimeUtil.getStringFromDate(date, "yyyy-MM"));
                    }
                });
                dialog.show();
                break;
        }
    }


}
