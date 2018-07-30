package myyl.com.myyl.fragment;


import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.handmark.pulltorefresh.library.PullToRefreshScrollView;
import com.stx.xhb.xbanner.XBanner;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import myyl.com.myyl.R;
import myyl.com.myyl.activity.MainActivity;
import myyl.com.myyl.adapter.AdapterGc;
import myyl.com.myyl.adapter.AdapterZx;
import myyl.com.myyl.model.HomePageHdata;
import myyl.com.myyl.model.MyGcInfo;
import myyl.com.myyl.model.MyZxInfo;
import myyl.com.myyl.utils.LogUtils;
import myyl.com.myyl.utils.views.MyListView;
import myyl.com.myyl.utils.views.ZQImageViewRoundOval;
import myyl.com.myyl.utils.views.lrecycleviews.adapter.CommonRecyclerAdapter;
import myyl.com.myyl.utils.views.lrecycleviews.adapter.viewHolder.BaseRecycleViewsHolder;
import myyl.com.myyl.utils.views.lrecycleviews.constants.OnClick;
import myyl.com.myyl.utils.views.lrecycleviews.constants.OnItemClick;
import myyl.com.myyl.utils.views.lrecycleviews.constants.RecyclerViewStyle;
import myyl.com.myyl.utils.views.lrecycleviews.utils.RecyclerViewUtils;

public class FragmentDiscoverPage1 extends AbstractFragment {
    private static final String TAG = "FragmentDiscoverPage1";
    private TextView tvEdit;
    private MyListView listview;
    private LinearLayout idNoData;
    private PullToRefreshScrollView pullToRefreshScrollView;
    private RecyclerView recyclerview;

    private List<MyGcInfo> myGcInfos = new ArrayList<>();
    private AdapterGc adapterGc;

    private CommonRecyclerAdapter<HomePageHdata> rAdapter;
    private List<HomePageHdata> rList;


    @Override
    protected void initView() {

        recyclerview = findViewById(R.id.recyclerview);

        tvEdit = findViewById(R.id.tv_edit);
        idNoData = findViewById(R.id.id_no_data);
        pullToRefreshScrollView = findViewById(R.id.pullToRefreshScrollView);


        listview = findViewById(R.id.listview);
        adapterGc = new AdapterGc(mContext, myGcInfos);
        listview.setAdapter(adapterGc);
    }




    private void initService() {

        setdata();
        //item的子view点击事件
        rAdapter.setOnClick(new OnClick() {
            @Override
            public void onClick(int position, View view, BaseRecycleViewsHolder holder) {
                Toast.makeText(rAdapter.getContext(), "您点击了图片" + position, Toast.LENGTH_SHORT).show();
            }
        });
//        item点击事件
        rAdapter.setOnItemClick(new OnItemClick() {
            @Override
            public void onItemClick(int position, View view, BaseRecycleViewsHolder holder) {
                Toast.makeText(rAdapter.getContext(), "您点击了item" + position, Toast.LENGTH_SHORT).show();
            }
        });


    }


    private void setdata() {
        rList = new ArrayList<>();
        rList.add(new HomePageHdata("http://d.5857.com/xgs_150428/001.jpg"));
        rList.add(new HomePageHdata("http://d.5857.com/xgs_150428/002.jpg"));
        rList.add(new HomePageHdata("https://a-ssl.duitang.com/uploads/item/201502/27/20150227112432_jcwSt.jpeg"));
        rList.add(new HomePageHdata("http://d.5857.com/xgs_150428/003.jpg"));
        rList.add(new HomePageHdata("http://d.5857.com/xgs_150428/004.jpg"));
        rAdapter = new CommonRecyclerAdapter<HomePageHdata>(mContext, rList, R.layout.item_horizontal_gridview, RecyclerViewStyle.HorizontalGridView) {
            @Override
            protected void setData(BaseRecycleViewsHolder holder, int position, HomePageHdata item) {
                holder.getTextView(R.id.id_index_gallery_item_text).setText(position + "");
                ZQImageViewRoundOval imageView = (ZQImageViewRoundOval) holder.getImageView(R.id.id_index_gallery_item_image);
                imageView.setType(ZQImageViewRoundOval.TYPE_ROUND);
                imageView.setRoundRadius(15);
                Glide.with(rAdapter.getContext())
                        .load(item.getImg())
                        .into(imageView);
                holder.setClickListener(R.id.id_index_gallery_item_image);
                holder.setItemClickListener();
            }

            @Override
            protected void setFootData(BaseRecycleViewsHolder holder, int position, int footposition) {

            }

            @Override
            protected void setHeadData(BaseRecycleViewsHolder holder, int position) {

            }
        };
        recyclerview.setAdapter(rAdapter);
        new RecyclerViewUtils<HomePageHdata>(recyclerview, rAdapter, 1).addItemDecoration(10, R.color.white);
    }




    private void testData() {
        myGcInfos.clear();
        for (int i = 0; i < 3; i++) {
            MyGcInfo zxInfo = new MyGcInfo();
            zxInfo.setUserUrl("http://d.5857.com/xgs_150428/001.jpg");
            zxInfo.setName("测试" + i);
            zxInfo.setTime("4小时前");
            zxInfo.setContent("等级是否就开始福建省了福建省了房间乱收费上课了的飞机上课的房间看电视放假了的书法家了第三方吉林省福建省的否打开司法局的书法家");
            zxInfo.setCommentCount(11);
            zxInfo.setLikeCount(444);
            zxInfo.setCommentImg("http://d.5857.com/xgs_150428/001.jpg,http://d.5857.com/xgs_150428/001.jpg,http://d.5857.com/xgs_150428/001.jpg,http://d.5857.com/xgs_150428/001.jpg,http://d.5857.com/xgs_150428/001.jpg,http://d.5857.com/xgs_150428/001.jpg");
            myGcInfos.add(zxInfo);
        }
        adapterGc.notifyDataSetChanged();


        initService();
    }


    protected void lazyLoad() {
        LogUtils.e(TAG1 + TAG, "lazyLoad()");
//        reloadData();
        testData();
    }

    @Override
    protected int setContentView() {
        return R.layout.activity_discover_list;
    }

    @Override
    public void onResume() {
        super.onResume();
        LogUtils.e(TAG1 + TAG, "onResume");
//        reloadData();
    }

}
