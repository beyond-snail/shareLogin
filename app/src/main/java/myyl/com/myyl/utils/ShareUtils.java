package myyl.com.myyl.utils;

import cn.sharesdk.framework.Platform;
import cn.sharesdk.framework.PlatformActionListener;
import cn.sharesdk.framework.ShareSDK;
import cn.sharesdk.sina.weibo.SinaWeibo;
import cn.sharesdk.tencent.qq.QQ;
import cn.sharesdk.wechat.friends.Wechat;

public class ShareUtils {
    //QQ好友分享
    public static void shareQQ(String title, String shareurl, String text, String imageUrl, PlatformActionListener listener){
        Platform qq = ShareSDK.getPlatform(QQ.NAME);
        QQ.ShareParams sp = new QQ.ShareParams();
        sp.setTitle(title);
        sp.setTitleUrl(shareurl); // 标题的超链接
        sp.setText(text);
        sp.setImageUrl(imageUrl);
        qq.setPlatformActionListener(listener);
        qq.share(sp);
    }

    //新浪微博
    public static void shareWeibo(String title,String shareurl,String text,String imageUrl,PlatformActionListener listener){
        Platform sina =ShareSDK.getPlatform(SinaWeibo.NAME);
        SinaWeibo.ShareParams sp = new SinaWeibo.ShareParams();
        sp.setTitle(title);
        sp.setTitleUrl(shareurl); // 标题的超链接
        sp.setSiteUrl(shareurl);
        sp.setText(text);
        sp.setImageUrl(imageUrl);
        sina.setPlatformActionListener(listener);
        sina.share(sp);
    }
    //微信
    public static void shareWechat(String title,String shareurl,String text,String imageUrl,PlatformActionListener listener){
        Platform weixin =ShareSDK.getPlatform(Wechat.NAME);
        Wechat.ShareParams sp = new Wechat.ShareParams();
        sp.setShareType(Platform.SHARE_WEBPAGE);
        sp.setTitle(title);
        sp.setImageUrl(imageUrl);
        sp.setUrl(shareurl);
        sp.setText(text);
        sp.setSite("QQ空间标题");
        // siteUrl是分享此内容的网站地址，仅在QQ空间使用
        weixin.setPlatformActionListener(listener);
        weixin.share(sp);
    }

}
