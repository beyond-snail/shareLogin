package myyl.com.myyl.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.stx.xhb.xbanner.XBanner;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import myyl.com.myyl.R;
import myyl.com.myyl.activity.base.TabBasicActivity;
import myyl.com.myyl.model.AdvertiseEntity;
import myyl.com.myyl.utils.MyActivityManager;

public class MainActivity extends TabBasicActivity implements View.OnClickListener {
    private final String TAG = "MainActivity";
    @BindView(R.id.xbanner)
    XBanner xbanner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = this;
        setContentView(R.layout.fragment_homepage);
        ButterKnife.bind(this);
        MyActivityManager.getInstance().addActivity(this);
        initView();
        initBanner();


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
                Glide.with(MainActivity.this).load((String)model).placeholder(R.drawable.default_image).error(R.drawable.default_image).into((ImageView) view);
            }
        });

    }


    private void initView() {
//        showView(R.id.titleback_btn_back, false);
//        setTvText(R.id.tv_title, "首页");

        //获取控件
        xbanner = (XBanner) findViewById(R.id.xbanner);



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
