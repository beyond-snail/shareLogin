package myyl.com.myyl.activity;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.View;

import net.lucode.hackware.magicindicator.MagicIndicator;
import net.lucode.hackware.magicindicator.ViewPagerHelper;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.CommonNavigator;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.CommonNavigatorAdapter;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.IPagerIndicator;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.IPagerTitleView;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.indicators.LinePagerIndicator;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.titles.ColorTransitionPagerTitleView;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.titles.SimplePagerTitleView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import myyl.com.myyl.R;
import myyl.com.myyl.activity.base.BaseFragmentActivity;
import myyl.com.myyl.activity.base.TabBasicActivity;
import myyl.com.myyl.adapter.FragmentAdapter;
import myyl.com.myyl.fragment.FragmentDiscoverPage1;
import myyl.com.myyl.fragment.FragmentDiscoverPage2;
import myyl.com.myyl.fragment.FragmentHomePage1;
import myyl.com.myyl.fragment.FragmentHomePage2;
import myyl.com.myyl.fragment.FragmentHomePage3;
import myyl.com.myyl.fragment.FragmentHomePage4;
import myyl.com.myyl.fragment.FragmentHomePage5;
import myyl.com.myyl.utils.MyActivityManager;
import myyl.com.myyl.utils.views.MyViewPager;

public class DiscoverActivity extends BaseFragmentActivity{


    private static final String TAG = "DiscoverActivity";


    private static final String[] CHANNELS = new String[]{"说说", "广场"};
    @BindView(R.id.magic_indicator)
    MagicIndicator magicIndicator;
    @BindView(R.id.view_pager)
    MyViewPager viewPager;
    private List<String> mDataList = Arrays.asList(CHANNELS);

    private List<Fragment> mFragmentList = new ArrayList<Fragment>();
    private FragmentAdapter mFragmentAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = this;
        setContentView(R.layout.activity_discover);
        ButterKnife.bind(this);
        MyActivityManager.getInstance().addActivity(this);
        initView();
    }

    private void initView() {


        initMagicIndicator();
        initFragments();

    }


    @Override
    protected void onResume() {
        super.onResume();
    }


    private void initMagicIndicator() {
        magicIndicator.setBackgroundColor(Color.WHITE);
        CommonNavigator commonNavigator = new CommonNavigator(this);
        commonNavigator.setAdjustMode(true);
        commonNavigator.setAdapter(new CommonNavigatorAdapter() {
            @Override
            public int getCount() {
                return mDataList == null ? 0 : mDataList.size();
            }

            @Override
            public IPagerTitleView getTitleView(Context context, final int index) {
                SimplePagerTitleView simplePagerTitleView = new ColorTransitionPagerTitleView(context);
                simplePagerTitleView.setText(mDataList.get(index));
                simplePagerTitleView.setTextSize(13);
                simplePagerTitleView.setNormalColor(Color.parseColor("#333333"));
                simplePagerTitleView.setSelectedColor(Color.parseColor("#1FBCD2"));
                simplePagerTitleView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        viewPager.setCurrentItem(index);
                    }
                });
                return simplePagerTitleView;
            }

            @Override
            public IPagerIndicator getIndicator(Context context) {
                LinePagerIndicator indicator = new LinePagerIndicator(context);
                indicator.setColors(Color.parseColor("#1FBCD2"));
                return indicator;
            }
        });
        magicIndicator.setNavigator(commonNavigator);
        ViewPagerHelper.bind(magicIndicator, viewPager);
    }

    private void initFragments() {

        //将三个页面添加到容器里面
        mFragmentList.add(new FragmentDiscoverPage2());
        mFragmentList.add(new FragmentDiscoverPage1());

        //重写一个FragmentAdapter继承FragmentPagerAdapter，需要传FragmentManager和存放页面的容器过去
        mFragmentAdapter = new FragmentAdapter(getSupportFragmentManager(), mFragmentList);
        //ViewPager绑定监听器
        viewPager.setAdapter(mFragmentAdapter);
        //ViewPager设置默认当前的项
        viewPager.setCurrentItem(0);
        viewPager.setOffscreenPageLimit(1);
    }


}
