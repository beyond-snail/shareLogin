package myyl.com.myyl;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.HashMap;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.sharesdk.framework.Platform;
import cn.sharesdk.framework.PlatformActionListener;
import cn.sharesdk.framework.PlatformDb;
import cn.sharesdk.framework.ShareSDK;
import cn.sharesdk.onekeyshare.OnekeyShare;
import cn.sharesdk.sina.weibo.SinaWeibo;
import cn.sharesdk.tencent.qq.QQ;
import cn.sharesdk.wechat.friends.Wechat;
import myyl.com.myyl.utils.ShareUtils;
import myyl.com.myyl.utils.dialog.ShareDiaog;

public class MainActivity extends AppCompatActivity implements PlatformActionListener {

    private static final String TAG = "MainActivity";
    @BindView(R.id.share)
    Button share;
    @BindView(R.id.qq)
    Button qq;
    @BindView(R.id.wx)
    Button wx;
    @BindView(R.id.aly)
    Button aly;


    private PlatformDb platDB; //平台授权数据DB




    ShareDiaog shareDiaog;
    //分享标题
    private String share_title="百度一下";
    //分享链接
    private String share_url="http://blog.csdn.net/qq_31390699";
    //分享封面图片
    private String share_img="http://img.zcool.cn/community/0183b855420c990000019ae98b9ce8.jpg@900w_1l_2o_100sh.jpg";
    //分享描述
    private String share_desc="不懂你就百度啊";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
    }


    private void showShare() {
        OnekeyShare oks = new OnekeyShare();
        //关闭sso授权
        oks.disableSSOWhenAuthorize();

        // title标题，微信、QQ和QQ空间等平台使用
        oks.setTitle(getString(R.string.share));
        // titleUrl QQ和QQ空间跳转链接
        oks.setTitleUrl("http://sharesdk.cn");
        // text是分享文本，所有平台都需要这个字段
        oks.setText("我是分享文本");
        // imagePath是图片的本地路径，Linked-In以外的平台都支持此参数
        oks.setImagePath("/sdcard/test.jpg");//确保SDcard下面存在此张图片
//        oks.setImageUrl(share_img);
        // url在微信、微博，Facebook等平台中使用
        oks.setUrl("http://sharesdk.cn");
        // comment是我对这条分享的评论，仅在人人网使用
        oks.setComment("我是测试评论文本");

        // 启动分享GUI
        oks.show(this);
    }




    //第三方授权登录
    private void authorize(Platform plat) {

        if (plat == null) {
            return;
        }

        //判断指定平台是否已经完成授权
        if (plat.isAuthValid()) {
            String token = plat.getDb().getToken();
            String userId = plat.getDb().getUserId();
            String name = plat.getDb().getUserName();
            String gender = plat.getDb().getUserGender();
            String headImageUrl = plat.getDb().getUserIcon();
            String platformNname = plat.getDb().getPlatformNname();
            if (userId != null) {

                //已经授权过，直接下一步操作
                if (platformNname.equals(SinaWeibo.NAME)) {
                    //微博授权
                } else if (platformNname.equals(QQ.NAME)) {
                    //QQ授权
                } else if (platformNname.equals(Wechat.NAME)) {
                    //微信授权
                }
                return;
            }
        }

        // true不使用SSO授权，false使用SSO授权
        plat.SSOSetting(false);
        plat.setPlatformActionListener(this);
        plat.authorize();

        //获取用户资料
        plat.showUser(null);
    }


    @Override
    public void onComplete(Platform platform, int i, HashMap<String, Object> hashMap) {
        String headImageUrl = null;//头像
        String userId;//userId
        String token;//token
        String gender;//性别
        String name = null;//用户名

        if (i == Platform.ACTION_USER_INFOR) {

            platDB = platform.getDb(); // 获取平台数据DB

            if (platform.getName().equals(Wechat.NAME)) {
                //微信登录

                // 通过DB获取各种数据
                token = platDB.getToken();
                userId = platDB.getUserId();
                name = platDB.getUserName();
                gender = platDB.getUserGender();
                headImageUrl = platDB.getUserIcon();
                if ("m".equals(gender)) {
                    gender = "1";
                } else {
                    gender = "2";
                }

                Toast.makeText(this, "微信："+name + "/"+ gender, Toast.LENGTH_SHORT).show();

            } else if (platform.getName().equals(SinaWeibo.NAME)) {
                // 微博登录

                token = platDB.getToken();
                userId = platDB.getUserId();
                name = hashMap.get("nickname").toString(); // 名字
                gender = hashMap.get("gender").toString(); // 年龄
                headImageUrl = hashMap.get("figureurl_qq_2").toString(); // 头像figureurl_qq_2 中等图，figureurl_qq_1缩略图
                Toast.makeText(this, "新浪： "+name + "/"+ gender, Toast.LENGTH_SHORT).show();

            } else if (platform.getName().equals(QQ.NAME)) {
                // QQ登录

                token = platDB.getToken();
                userId = platDB.getUserId();
                name = hashMap.get("nickname").toString(); // 名字
                gender = hashMap.get("gender").toString(); // 年龄
                headImageUrl = hashMap.get("figureurl_qq_2").toString(); // 头像figureurl_qq_2 中等图，figureurl_qq_1缩略图
                Toast.makeText(this, "QQ："+name + "/"+ gender, Toast.LENGTH_SHORT).show();

            }
        }
    }

    @Override
    public void onError(Platform platform, int i, Throwable throwable) {

    }

    @Override
    public void onCancel(Platform platform, int i) {

    }

    @OnClick({R.id.share, R.id.qq, R.id.wx, R.id.aly})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.share:
                showShare();
                break;
            case R.id.qq:
                // qq登录
                Platform qq = ShareSDK.getPlatform(QQ.NAME);
                authorize(qq);
                break;
            case R.id.wx:
                // 微信登录
                Platform wechat = ShareSDK.getPlatform(Wechat.NAME);
                authorize(wechat);
                break;
            case R.id.aly:
                // 微博登录
//                Platform weibo = ShareSDK.getPlatform(SinaWeibo.NAME);
//                authorize(weibo);
                shareDiaog = new ShareDiaog(MainActivity.this);
                shareDiaog.builder().show();
                shareDiaog.setShareClickListener(shareClickListener);
                break;
        }
    }




    /**
     * 各平台分享按钮点击事件
     */
    private ShareDiaog.ShareClickListener shareClickListener=new ShareDiaog.ShareClickListener() {
        @Override
        public void shareWechat() {
            ShareUtils.shareWechat(share_title,share_url,share_desc,share_img,platformActionListener);
        }
        @Override
        public void sharePyq() {
//            ShareUtils.sharepyq(share_title,share_url,share_desc,share_img,platformActionListener);
        }
        @Override
        public void shareQQ() {
            ShareUtils.shareQQ(share_title,share_url,share_desc,share_img,platformActionListener);
        }
        @Override
        public void shareQzone() {
//            ShareUtils.shareQQzone(share_title,share_url,share_desc,share_img,platformActionListener);
        }
    };
    /**
     * 分享回调
     */
    PlatformActionListener platformActionListener=new PlatformActionListener() {
        @Override
        public void onComplete(Platform platform, int i, HashMap<String, Object> hashMap) {
            Log.e("kid","分享成功");
        }
        @Override
        public void onError(Platform platform, int i, Throwable throwable) {
            Log.e("kid","分享失败");
        }

        @Override
        public void onCancel(Platform platform, int i) {
            Log.e("kid","分享取消");
        }
    };



}

