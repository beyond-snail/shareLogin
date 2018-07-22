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
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.allen.library.SuperButton;
import com.bumptech.glide.Glide;
import com.makeramen.roundedimageview.RoundedImageView;

import java.util.List;

import myyl.com.myyl.R;
import myyl.com.myyl.activity.ActivityAnswer;
import myyl.com.myyl.model.MyZwInfo;
import myyl.com.myyl.utils.StringUtils;


/**********************************************************
 *                                                        *
 *                  Created by wucongpeng on 2017/9/12.        *
 **********************************************************/


public class AdapterZwMoreList extends BaseAdapter {

    private Context mContext;
    private LayoutInflater mInflater;
    private List<MyZwInfo> list;


    public AdapterZwMoreList(Context context, List<MyZwInfo> list) {
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
            convertView = mInflater.inflate(R.layout.item_zw_list, parent, false);
            holder.ivImg = (RoundedImageView) convertView.findViewById(R.id.iv_img);
            holder.tvName = (TextView) convertView.findViewById(R.id.tv_name);
            holder.tvAmount = (TextView) convertView.findViewById(R.id.tv_amount);
            holder.tvContent = (TextView) convertView.findViewById(R.id.tv_content);
            holder.tvCate = (TextView) convertView.findViewById(R.id.tv_cate);
            holder.tvQd = (SuperButton) convertView.findViewById(R.id.tv_qd);
            holder.tvHours = (TextView) convertView.findViewById(R.id.tv_hours);
            holder.tvNum = (TextView) convertView.findViewById(R.id.tv_num);
            convertView.setTag(holder);

        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        MyZwInfo vo = list.get(position);

        Glide.with(mContext)
                .load(vo.getUserUrl()).asBitmap()
                .placeholder(R.drawable.default_image).error(R.drawable.default_image)
                .into((RoundedImageView) holder.ivImg);

        holder.tvName.setText(vo.getUserName());
        holder.tvContent.setText(vo.getContent());
        holder.tvCate.setText(vo.getCate());
        holder.tvAmount.setText("￥"+ StringUtils.formatIntMoney(vo.getAmount()));
        holder.tvHours.setText("还剩" + vo.getHours() + "小时");
        holder.tvNum.setText(vo.getNum() + "人已抢答");
        holder.tvQd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mContext.startActivity(new Intent(mContext, ActivityAnswer.class));
            }
        });


        return convertView;
    }


    static class ViewHolder {
        RoundedImageView ivImg;
        TextView tvName;
        TextView tvAmount;
        TextView tvContent;
        TextView tvCate;
        SuperButton tvQd;
        TextView tvHours;
        TextView tvNum;
    }
}
