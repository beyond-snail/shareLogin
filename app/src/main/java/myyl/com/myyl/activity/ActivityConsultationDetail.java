package myyl.com.myyl.activity;

import android.os.Bundle;
import android.text.format.DateUtils;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshScrollView;
import com.makeramen.roundedimageview.RoundedImageView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import myyl.com.myyl.R;
import myyl.com.myyl.activity.base.BaseActivity;
import myyl.com.myyl.utils.MyActivityManager;
import myyl.com.myyl.utils.views.MyListView;
import myyl.com.myyl.utils.views.SegmentView;

public class ActivityConsultationDetail extends BaseActivity implements SegmentView.onSegmentViewClickListener {


    private static final String TAG = "ActivityConsultationDetail";
    @BindView(R.id.titleback_btn_back)
    LinearLayout titlebackBtnBack;
    @BindView(R.id.iv_img)
    RoundedImageView ivImg;
    @BindView(R.id.tv_name)
    TextView tvName;
    @BindView(R.id.tv_zc)
    TextView tvZc;
    @BindView(R.id.tv_ks)
    TextView tvKs;
    @BindView(R.id.tv_hospital)
    TextView tvHospital;
    @BindView(R.id.tv_yz_count)
    TextView tvYzCount;
    @BindView(R.id.tv_zx_count)
    TextView tvZxCount;
    @BindView(R.id.tv_tw_des)
    TextView tvTwDes;
    @BindView(R.id.tv_by_des)
    TextView tvByDes;
    @BindView(R.id.tv_zz_des)
    TextView tvZzDes;
    @BindView(R.id.tv_sc_des)
    TextView tvScDes;
    @BindView(R.id.tv_gr_des)
    TextView tvGrDes;
    @BindView(R.id.segView)
    SegmentView segView;
    @BindView(R.id.ll_fm)
    LinearLayout llFm;
    @BindView(R.id.listview)
    MyListView listview;
    @BindView(R.id.id_no_data)
    LinearLayout idNoData;
    @BindView(R.id.ll_pj)
    LinearLayout llPj;
    @BindView(R.id.pullToRefreshScrollView)
    PullToRefreshScrollView pullToRefreshScrollView;

    private int page = 1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = this;
        setContentView(R.layout.activity_consultation_detail);
        ButterKnife.bind(this);
        MyActivityManager.getInstance().addActivity(this);
        initView();
    }

    private void initView() {
        showView(R.id.titleback_btn_back, true);
        setTvText(R.id.tv_title, "医生详情");
        showView(R.id.next_sure, false);

        segView.setOnSegmentViewClickListener(this);
        segView.setTextValues(new String[]{"我的FM", "全部评论"});


        pullToRefreshScrollView = (PullToRefreshScrollView) findViewById(R.id.pullToRefreshScrollView);
        pullToRefreshScrollView.setOnRefreshListener(new MyOnRefreshListener());
        pullToRefreshScrollView.setMode(PullToRefreshBase.Mode.BOTH);


        testData();

    }

    private void testData() {
        Glide.with(mContext)
                .load("http://d.5857.com/xgs_150428/001.jpg").asBitmap()
                .placeholder(R.drawable.default_image).error(R.drawable.default_image)
                .into(ivImg);

        tvName.setText("测试");
        tvZc.setText("职称");
        tvKs.setText("肿瘤科");
        tvHospital.setText("上海长征医院");
        tvYzCount.setText("预诊次数: " + "5");
        tvZxCount.setText("咨询次数: " + "5");
        tvTwDes.setText("独守空房计算机房设计费拉上姐夫司法局的酸辣粉附近的酸辣粉加水电费健康");
        tvByDes.setText("等级是否教科书的房间看电视积分抵扣司法局的快速路附近鲁大师");
        tvZzDes.setText("健康的房间里将发生的解放军是肯德基是福建省的房间的书法家");
        tvScDes.setText("风刀霜剑风口浪尖上课了房间看电视了减肥卡兰蒂斯姐夫脸开店时间弗兰克斯的");
        tvGrDes.setText("减肥的设计费看电视剧付款了的设计费抗裂砂浆发牢骚的科技弗兰克斯");
    }

    class MyOnRefreshListener implements PullToRefreshBase.OnRefreshListener2<ScrollView> {
        @Override
        public void onPullDownToRefresh(PullToRefreshBase<ScrollView> refreshView) {
            pullToRefreshScrollView.setMode(PullToRefreshBase.Mode.BOTH);
            String label = DateUtils.formatDateTime(mContext, System.currentTimeMillis(), DateUtils.FORMAT_SHOW_TIME | DateUtils.FORMAT_SHOW_DATE | DateUtils.FORMAT_ABBREV_ALL);
            refreshView.getLoadingLayoutProxy().setLastUpdatedLabel(label);
            refreshData();
        }
        @Override
        public void onPullUpToRefresh(PullToRefreshBase<ScrollView> refreshView) {
            page++;
            getdata();
        }
    }

    private void getdata() {
        pullToRefreshScrollView.onRefreshComplete();
    }

    private void refreshData(){
        pullToRefreshScrollView.onRefreshComplete();
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

    @Override
    public void onSegmentViewClick(int index) {
        if (index == 0) {
            llFm.setVisibility(View.VISIBLE);
            llPj.setVisibility(View.GONE);
        } else {
            llPj.setVisibility(View.VISIBLE);
            llFm.setVisibility(View.GONE);
        }
    }
}
