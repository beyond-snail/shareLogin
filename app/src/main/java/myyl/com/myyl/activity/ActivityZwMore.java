package myyl.com.myyl.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.text.format.DateUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;

import com.bumptech.glide.Glide;
import com.handmark.pulltorefresh.library.ILoadingLayout;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshScrollView;
import com.stx.xhb.xbanner.XBanner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import myyl.com.myyl.R;
import myyl.com.myyl.activity.base.BaseActivity;
import myyl.com.myyl.adapter.AdapterZwMoreList;
import myyl.com.myyl.model.MyZwInfo;
import myyl.com.myyl.utils.MyActivityManager;
import myyl.com.myyl.utils.views.MyListView;
import myyl.com.myyl.utils.views.drawdownMenu.DropDownMenu;
import myyl.com.myyl.utils.views.drawdownMenu.PopNoListener;

public class ActivityZwMore extends BaseActivity {


    private static final String TAG = "ActivityZwMore";
    @BindView(R.id.titleback_btn_back)
    LinearLayout titlebackBtnBack;
    @BindView(R.id.dropDownMenu)
    DropDownMenu dropDownMenu;
    @BindView(R.id.xbanner)
    XBanner xbanner;


    private String headers[] = {"未解答", "已解答"}; //

    private List<MyZwInfo> myZwInfos = new ArrayList<MyZwInfo>();
    private AdapterZwMoreList adapterZwMoreList;

    private PullToRefreshScrollView mPullRefreshScrollView;
    private MyListView myListView;
    private LinearLayout id_no_data;

    private int page = 1;

    private Handler handler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = this;
        setContentView(R.layout.activity_zw_more);
        ButterKnife.bind(this);
        MyActivityManager.getInstance().addActivity(this);
        initView();
        testData();
    }

    private void testData() {

        List<String> imgesUrl = new ArrayList<>();
        imgesUrl.add("http://c.hiphotos.baidu.com/image/pic/item/09fa513d269759eeef490028befb43166d22df3c.jpg");
        //添加轮播图片数据（图片数据不局限于网络图片、本地资源文件、View 都可以）,刷新数据也是调用该方法
        xbanner.setData(imgesUrl, null);//第二个参数为提示文字资源集合

        //加载广告图片
        xbanner.loadImage(new XBanner.XBannerAdapter() {
            @Override
            public void loadBanner(XBanner banner, Object model, View view, int position) {

                //1、此处使用的Glide加载图片，可自行替换自己项目中的图片加载框架
                //2、返回的图片路径为Object类型，你只需要强转成你传输的类型就行，切记不要胡乱强转！
                Glide.with(ActivityZwMore.this).load((String) model).placeholder(R.drawable.default_image).error(R.drawable.default_image).into((ImageView) view);
            }
        });


        myListView.setVisibility(View.VISIBLE);
        id_no_data.setVisibility(View.GONE);
        for (int i = 0; i < 3; i++) {
            MyZwInfo zwInfo = new MyZwInfo();
            zwInfo.setUserUrl("http://d.5857.com/xgs_150428/001.jpg");
            zwInfo.setUserName("测试" + i);
            zwInfo.setContent("等级是否就开始福建省了福建省了房间乱收费上课了的飞机上课的房间看电视放假了的书法家了第三方吉林省福建省的否打开司法局的书法家");
            zwInfo.setNum(12);
            zwInfo.setHours(48);
            zwInfo.setAmount(1000);
            zwInfo.setAmount(1500);
            zwInfo.setCate("肿瘤科");

            myZwInfos.add(zwInfo);
        }
        adapterZwMoreList.notifyDataSetChanged();


    }

    private void initView() {
        showView(R.id.titleback_btn_back, true);
        setTvText(R.id.tv_title, "知问");
        showView(R.id.next_sure, false);


        //init context view
        LayoutInflater inflater = getLayoutInflater();
        View view = inflater.inflate(R.layout.activity_fm_more_list, null);
        mPullRefreshScrollView = view.findViewById(R.id.pull_refresh_scrollview);
        myListView = view.findViewById(R.id.listview);
        id_no_data = view.findViewById(R.id.id_no_data);

        //init dropdownview
        dropDownMenu.setDropDownMenu(Arrays.asList(headers), null, view, new PopNoListener() {
            @Override
            public void noPopCallback(int position) {
                toast("当前选项: " + position);
            }
        });


        //刷新操作
        myListView = findViewById(R.id.listview);
        adapterZwMoreList = new AdapterZwMoreList(mContext, myZwInfos);
        myListView.setAdapter(adapterZwMoreList);
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


                if (myZwInfos.size() == 0) {
                    handler.postDelayed(new Runnable() {

                        @Override
                        public void run() {
                            showErrorMsg("没有更多了");
                            mPullRefreshScrollView.onRefreshComplete();
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
