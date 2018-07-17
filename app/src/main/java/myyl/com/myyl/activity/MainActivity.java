package myyl.com.myyl.activity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.handmark.pulltorefresh.library.PullToRefreshScrollView;
import com.stx.xhb.xbanner.XBanner;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import myyl.com.myyl.R;
import myyl.com.myyl.activity.base.TabBasicActivity;
import myyl.com.myyl.adapter.HorizontalListViewAdapter;
import myyl.com.myyl.adapter.MyMenuAdapter;
import myyl.com.myyl.enums.EnumConsts;
import myyl.com.myyl.model.HomePageHdata;
import myyl.com.myyl.model.Menu;
import myyl.com.myyl.utils.MyActivityManager;
import myyl.com.myyl.utils.views.MyGridView;
import myyl.com.myyl.utils.views.ZQImageViewRoundOval;
import myyl.com.myyl.utils.views.lrecycleviews.adapter.CommonRecyclerAdapter;
import myyl.com.myyl.utils.views.lrecycleviews.adapter.viewHolder.BaseRecycleViewsHolder;
import myyl.com.myyl.utils.views.lrecycleviews.constants.OnClick;
import myyl.com.myyl.utils.views.lrecycleviews.constants.OnItemClick;
import myyl.com.myyl.utils.views.lrecycleviews.constants.RecyclerViewStyle;
import myyl.com.myyl.utils.views.lrecycleviews.utils.RecyclerViewUtils;

public class MainActivity extends TabBasicActivity implements View.OnClickListener {
    private final String TAG = "MainActivity";
    @BindView(R.id.xbanner)
    XBanner xbanner;
    @BindView(R.id.id_gridview)
    MyGridView idGridview;
    @BindView(R.id.pullToRefreshScrollView)
    PullToRefreshScrollView pullToRefreshScrollView;
    @BindView(R.id.recyclerview)
    android.support.v7.widget.RecyclerView recyclerview;
//    @BindView(R.id.horizontal_lv)
//    MyHorizontalListView horizontalLv;

    private List<Menu> list = new ArrayList<Menu>();
    private MyMenuAdapter adapter;
//    private HorizontalListViewAdapter mHorizontalListViewAdapter;

    private CommonRecyclerAdapter<HomePageHdata> rAdapter;
    private List<HomePageHdata> rList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = this;
        setContentView(R.layout.fragment_homepage);
        ButterKnife.bind(this);
        MyActivityManager.getInstance().addActivity(this);
        initBanner();
        initMenu();
        initService();

    }

    private void initService() {
//        mHorizontalListViewAdapter = new HorizontalListViewAdapter(MainActivity.this);
//        horizontalLv.setAdapter(mHorizontalListViewAdapter);

        setdata();
        //item的子view点击事件
        rAdapter.setOnClick(new OnClick() {
            @Override
            public void onClick(int position, View view, BaseRecycleViewsHolder holder) {
                Toast.makeText(rAdapter.getContext(), "您点击了图片"+position, Toast.LENGTH_SHORT).show();
            }
        });
//        item点击事件
        rAdapter.setOnItemClick(new OnItemClick() {
            @Override
            public void onItemClick(int position, View view, BaseRecycleViewsHolder holder) {
                Toast.makeText(rAdapter.getContext(), "您点击了item"+position, Toast.LENGTH_SHORT).show();
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
        rAdapter = new CommonRecyclerAdapter<HomePageHdata>(this, rList, R.layout.item_horizontal_gridview, RecyclerViewStyle.HorizontalGridView) {
            @Override
            protected void setData(BaseRecycleViewsHolder holder, int position, HomePageHdata item) {
                holder.getTextView(R.id.id_index_gallery_item_text).setText(position+"");
                ZQImageViewRoundOval imageView = (ZQImageViewRoundOval)holder.getImageView(R.id.id_index_gallery_item_image);
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
        new RecyclerViewUtils<HomePageHdata>(recyclerview, rAdapter, 1).addItemDecoration(10,R.color.white);
    }

    private void initBanner() {
        List<String> imgesUrl = new ArrayList<>();
        imgesUrl.add("http://c.hiphotos.baidu.com/image/pic/item/09fa513d269759eeef490028befb43166d22df3c.jpg");
        imgesUrl.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1531741997995&di=f8b03f65b05bc5bf0986549445718e1f&imgtype=0&src=http%3A%2F%2Fold.bz55.com%2Fuploads%2Fallimg%2F130706%2F1-130F6095540.jpg");
        imgesUrl.add("http://a.hiphotos.baidu.com/image/pic/item/b219ebc4b74543a96a58c53112178a82b801148f.jpg");
        imgesUrl.add("http://c.hiphotos.baidu.com/image/pic/item/72f082025aafa40f99d4e82aa764034f78f01932.jpg");

        //添加轮播图片数据（图片数据不局限于网络图片、本地资源文件、View 都可以）,刷新数据也是调用该方法
        xbanner.setData(imgesUrl, null);//第二个参数为提示文字资源集合


        //加载广告图片
        xbanner.loadImage(new XBanner.XBannerAdapter() {
            @Override
            public void loadBanner(XBanner banner, Object model, View view, int position) {

                //1、此处使用的Glide加载图片，可自行替换自己项目中的图片加载框架
                //2、返回的图片路径为Object类型，你只需要强转成你传输的类型就行，切记不要胡乱强转！
                Glide.with(MainActivity.this).load((String) model).placeholder(R.drawable.default_image).error(R.drawable.default_image).into((ImageView) view);
            }
        });

    }


    private void initMenu() {
        for (int i = 0; i < EnumConsts.MenuType.values().length; i++) {
            Menu menu = new Menu();
            menu.setBg(EnumConsts.MenuType.values()[i].getBg());
            menu.setName(EnumConsts.MenuType.values()[i].getName());
            list.add(menu);
        }
        adapter = new MyMenuAdapter(mContext, list);
        idGridview.setAdapter(adapter);

        //类目
        idGridview.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Log.e(TAG, "onItemClick " + position);
                // 下拉刷新占据一个位置
                int index = EnumConsts.MenuType.getCodeByName(list.get(position).getName());
                switch (index) {
                    case 1:
//                        startActivity(new Intent(mContext, ActivityBankCardList.class));
                        break;
                    case 2:
                        break;
                    case 3:

                        break;
                    case 4:
                        break;
                    case 5:
                        break;
                    case 6:
                        break;

                }
            }
        });
    }


    @Override
    protected void onResume() {
        super.onResume();
        xbanner.startAutoPlay();
        getdata();
    }

    @Override
    protected void onStop() {
        super.onStop();
        xbanner.stopAutoPlay();
    }

    private void getdata() {

    }


}
