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
import myyl.com.myyl.model.MyConsultationInfo;
import myyl.com.myyl.model.MyFmInfo;
import myyl.com.myyl.utils.StringUtils;
import myyl.com.myyl.utils.ToastUtils;
import myyl.com.myyl.utils.records.MediaManager;
import myyl.com.myyl.utils.records.view.AudioRecorderButton;
import myyl.com.myyl.utils.views.RTextView;


/**********************************************************
 *                                                        *
 *                  Created by wucongpeng on 2017/9/12.        *
 **********************************************************/


public class AdapterConsultation extends BaseAdapter {

    private Context mContext;
    private LayoutInflater mInflater;
    private List<MyConsultationInfo> list;

    private AudioRecorderButton mAudioRecorderButton;
    private View mAnimView;

    public AdapterConsultation(Context context, List<MyConsultationInfo> list) {
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
            convertView = mInflater.inflate(R.layout.item_consultation, parent, false);
            holder.iv_img = (RoundedImageView)convertView.findViewById(R.id.iv_img);
            holder.tv_name = (TextView) convertView.findViewById(R.id.tv_name);
            holder.tv_type = (TextView) convertView.findViewById(R.id.tv_type);
            holder.tv_content = (TextView) convertView.findViewById(R.id.tv_content);
            holder.tv_amount = (TextView) convertView.findViewById(R.id.tv_amount);
            holder.tv_zx = (RTextView) convertView.findViewById(R.id.tv_zx);
            holder.tv_by = (RTextView) convertView.findViewById(R.id.tv_by);
            holder.tv_zzhz = (RTextView) convertView.findViewById(R.id.tv_zzhz);
            convertView.setTag(holder);

        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        MyConsultationInfo vo = list.get(position);

        Glide.with(mContext)
                .load(vo.getUserUrl()).asBitmap()
                .placeholder(R.drawable.default_image).error(R.drawable.default_image)
                .into((RoundedImageView)holder.iv_img);

        holder.tv_name.setText(vo.getUserName());
        holder.tv_content.setText(vo.getContent());
        holder.tv_amount.setText("￥"+StringUtils.formatIntMoney(vo.getAmount()));
        holder.tv_type.setText(StringUtils.isBlank(vo.getType()) ? "" : vo.getType());

        holder.tv_zx.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ToastUtils.showShort(mContext, "咨询");
            }
        });

        holder.tv_by.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ToastUtils.showShort(mContext, "包月");
            }
        });

        holder.tv_zzhz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ToastUtils.showShort(mContext, "组织会诊");
            }
        });



        return convertView;
    }

    private static final class ViewHolder {
        RoundedImageView iv_img;
        TextView tv_name;
        TextView tv_type;
        TextView tv_content;
        TextView tv_amount;
        RTextView tv_zx;
        RTextView tv_by;
        RTextView tv_zzhz;

    }
}
