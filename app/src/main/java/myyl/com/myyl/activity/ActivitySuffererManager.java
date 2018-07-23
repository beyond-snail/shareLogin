package myyl.com.myyl.activity;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;

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
import butterknife.OnClick;
import myyl.com.myyl.R;
import myyl.com.myyl.activity.base.BaseFragmentActivity;
import myyl.com.myyl.adapter.FragmentAdapter;
import myyl.com.myyl.fragment.FragmentSufferer1;
import myyl.com.myyl.fragment.FragmentSufferer2;
import myyl.com.myyl.fragment.FragmentSufferer3;
import myyl.com.myyl.utils.MyActivityManager;

public class ActivitySuffererManager extends BaseFragmentActivity {


    private static final String TAG = "ActivitySuffererManager";

    private static final String[] CHANNELS = new String[]{"我的招募", "我的会诊患者", "所有患者"};
    @BindView(R.id.titleback_btn_back)
    LinearLayout titlebackBtnBack;
    @BindView(R.id.magic_indicator)
    MagicIndicator magicIndicator;
    @BindView(R.id.view_pager)
    ViewPager viewPager;
    private List<String> mDataList = Arrays.asList(CHANNELS);
    private List<Fragment> mFragmentList = new ArrayList<Fragment>();
    private FragmentAdapter mFragmentAdapter;
    //ViewPager的当前选中页
    private int currentIndex;
    private Context mContext;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = this;
        setContentView(R.layout.fragment_sufferer_manager);
        ButterKnife.bind(this);
        MyActivityManager.getInstance().addActivity(this);
        initView();
    }

    private void initView() {
        showView(R.id.titleback_btn_back, true);
        setTvText(R.id.tv_title, "患者管理");

        initMagicIndicator();

        //将三个页面添加到容器里面
        mFragmentList.add(new FragmentSufferer1());
        mFragmentList.add(new FragmentSufferer2());
        mFragmentList.add(new FragmentSufferer3());

        //重写一个FragmentAdapter继承FragmentPagerAdapter，需要传FragmentManager和存放页面的容器过去
        mFragmentAdapter = new FragmentAdapter(getSupportFragmentManager(), mFragmentList);
        //ViewPager绑定监听器
        viewPager.setAdapter(mFragmentAdapter);
        //ViewPager设置默认当前的项
        viewPager.setCurrentItem(0);
        viewPager.setOffscreenPageLimit(6);
        //ViewPager设置监听器，需要重写onPageScrollStateChanged，onPageScrolled，onPageSelected三个方法
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {

            /**
             * state滑动中的状态 有三种状态（0，1，2） 1：正在滑动 2：滑动完毕 0：什么都没做。
             * 三个方法的执行顺序为：用手指拖动翻页时，最先执行一遍onPageScrollStateChanged（1），
             * 然后不断执行onPageScrolled，放手指的时候，直接立即执行一次onPageScrollStateChanged（2），
             * 然后立即执行一次onPageSelected，然后再不断执行onPageScrolled，
             * 最后执行一次onPageScrollStateChanged（0）。
             */

            /**
             * state滑动中的状态 有三种状态（0，1，2） 1：正在滑动 2：滑动完毕 0：什么都没做。
             */
            @Override
            public void onPageScrollStateChanged(int state) {
                Log.i("PageScroll：", "onPageScrollStateChanged" + ":" + state);
            }

            /**
             * position :当前页面，及你点击滑动的页面 offset:当前页面偏移的百分比
             * offsetPixels:当前页面偏移的像素位置
             */
            @Override
            public void onPageScrolled(int position, float offset,
                                       int offsetPixels) {
            }

            /**
             * 将当前选择的页面的标题设置字体颜色为蓝色
             */
            @Override
            public void onPageSelected(int position) {
                Log.i("PageScroll：", "onPageSelected" + ":" + position);
                currentIndex = position;
            }
        });
    }


    private void initMagicIndicator() {
        MagicIndicator magicIndicator = (MagicIndicator) findViewById(R.id.magic_indicator);
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




    @Override
    protected void onResume() {
        super.onResume();
    }


    @OnClick(R.id.titleback_btn_back)
    public void onViewClicked() {
        finish();
    }
}
