package myyl.com.myyl.adapter;

////////////////////////////////////////////////////////////////////
//                          _ooOoo_                               //
//                         o8888888o                              //
//                         88" . "88                              //
//                         (| ^_^ |)                              //
//                         O\  =  /O                              //
//                      ____/`---'\____                           //
//                    .'  \\|     |//  `.                         //
//                   /  \\|||  :  |||//  \                        //
//                  /  _||||| -:- |||||-  \                       //
//                  |   | \\\  -  /// |   |                       //
//                  | \_|  ''\---/''  |   |                       //
//                  \  .-\__  `-`  ___/-. /                       //
//                ___`. .'  /--.--\  `. . ___                     //
//              ."" '<  `.___\_<|>_/___.'  >'"".                  //
//            | | :  `- \`.;`\ _ /`;.`/ - ` : | |                 //
//            \  \ `-.   \_ __\ /__ _/   .-` /  /                 //
//      ========`-.____`-.___\_____/___.-`____.-'========         //
//                           `=---='                              //
//      ^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^        //
//              佛祖保佑       永无BUG     永不修改                  //
//                                                                //
//          佛曰:                                                  //
//                  写字楼里写字间，写字间里程序员；                   //
//                  程序人员写程序，又拿程序换酒钱。                   //
//                  酒醒只在网上坐，酒醉还来网下眠；                   //
//                  酒醉酒醒日复日，网上网下年复年。                   //
//                  但愿老死电脑间，不愿鞠躬老板前；                   //
//                  奔驰宝马贵者趣，公交自行程序员。                   //
//                  别人笑我忒疯癫，我笑自己命太贱；                   //
//                  不见满街漂亮妹，哪个归得程序员？                   //
////////////////////////////////////////////////////////////////////

import android.content.Context;
import android.graphics.drawable.AnimationDrawable;
import android.media.MediaPlayer;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.makeramen.roundedimageview.RoundedImageView;

import java.util.List;

import myyl.com.myyl.R;
import myyl.com.myyl.model.MyFmInfo;
import myyl.com.myyl.utils.StringUtils;
import myyl.com.myyl.utils.records.MediaManager;
import myyl.com.myyl.utils.records.view.AudioRecorderButton;
import myyl.com.myyl.utils.views.FlikerProgressBar;
import myyl.com.myyl.utils.views.RTextView;


/**********************************************************
 *                                                        *
 *                  Created by wucongpeng on 2017/9/12.        *
 **********************************************************/


public class AdapterFm extends BaseAdapter {

    private Context mContext;
    private LayoutInflater mInflater;
    private List<MyFmInfo> list;

    private AudioRecorderButton mAudioRecorderButton;
    private View mAnimView;

    public AdapterFm(Context context, List<MyFmInfo> list) {
        this.mContext = context;
        mInflater = LayoutInflater.from(context);
        this.list = list;
    }

    @Override
    public int getCount() {
        return list == null ? 0 : list.size();
    }

    @Override
    public Object getItem(int position) {
        return list == null ? null : list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return list == null ? 0 : position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        if (convertView == null) {
            holder = new ViewHolder();
            convertView = mInflater.inflate(R.layout.item_fm, parent, false);
            holder.iv_img = (RoundedImageView)convertView.findViewById(R.id.iv_img);
            holder.tv_name = (TextView) convertView.findViewById(R.id.tv_name);
            holder.iv_type = (ImageView) convertView.findViewById(R.id.iv_type);
            holder.tv_content = (TextView) convertView.findViewById(R.id.tv_content);
            holder.tv_second = (TextView) convertView.findViewById(R.id.tv_second);
            holder.tv_amount = (TextView) convertView.findViewById(R.id.tv_amount);
            holder.iv_sound = (LinearLayout) convertView.findViewById(R.id.iv_sound);
            holder.tv_num = (RTextView) convertView.findViewById(R.id.tv_num);
            convertView.setTag(holder);

        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        MyFmInfo vo = list.get(position);

        Glide.with(mContext)
                .load(vo.getUserUrl()).asBitmap()
                .placeholder(R.drawable.default_image).error(R.drawable.default_image)
                .into((RoundedImageView)holder.iv_img);

        holder.tv_name.setText(vo.getUserName());
        holder.tv_content.setText(vo.getContent());
        holder.tv_num.setText(vo.getNum()+"");
        holder.tv_second.setText(vo.getSecond()+"\"");
        holder.tv_amount.setText("￥"+StringUtils.formatIntMoney(vo.getAmount()));
//        holder.iv_type.setText(vo.getType());
        holder.iv_sound.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //如果第一个动画正在运行， 停止第一个播放其他的
                if (mAnimView != null) {
                    mAnimView.setBackgroundResource(R.drawable.v_anim3);
                    mAnimView = null;
                }
                //播放动画
                mAnimView = v.findViewById(R.id.id_recorder_anim);
                mAnimView.setBackgroundResource(R.drawable.play_anim);
                AnimationDrawable animation = (AnimationDrawable) mAnimView.getBackground();
                animation.start();

                //播放音频  完成后改回原来的background
                MediaManager.playSound("", new MediaPlayer.OnCompletionListener() {
                    @Override
                    public void onCompletion(MediaPlayer mp) {
                        mAnimView.setBackgroundResource(R.drawable.v_anim3);
                    }
                });
            }
        });


        return convertView;
    }

    private static final class ViewHolder {
        RoundedImageView iv_img;
        TextView tv_name;
        ImageView iv_type;
        TextView tv_content;
        LinearLayout iv_sound;
        TextView tv_second;
        TextView tv_amount;
        RTextView tv_num;

    }
}
