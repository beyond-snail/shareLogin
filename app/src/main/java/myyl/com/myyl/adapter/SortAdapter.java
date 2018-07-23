package myyl.com.myyl.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.makeramen.roundedimageview.RoundedImageView;

import java.util.List;

import myyl.com.myyl.R;
import myyl.com.myyl.utils.views.User;

public class SortAdapter extends BaseAdapter {

    private List<User> list = null;
    private Context mContext;

    public SortAdapter(Context mContext, List<User> list) {
        this.mContext = mContext;
        this.list = list;
    }

    public int getCount() {
        return this.list.size();
    }

    public Object getItem(int position) {
        return list.get(position);
    }

    public long getItemId(int position) {
        return position;
    }

    public View getView(final int position, View view, ViewGroup arg2) {
        ViewHolder viewHolder;
        final User user = list.get(position);
        if (view == null) {
            viewHolder = new ViewHolder();
            view = LayoutInflater.from(mContext).inflate(R.layout.item_side, null);
            viewHolder.tv_name = (TextView) view.findViewById(R.id.tv_name);
            viewHolder.tv_des = (TextView) view.findViewById(R.id.tv_des);
            viewHolder.catalog = (TextView) view.findViewById(R.id.catalog);
            viewHolder.img = (RoundedImageView)view.findViewById(R.id.iv_img);;
            view.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) view.getTag();
        }

        //根据position获取首字母作为目录catalog
        String catalog = list.get(position).getFirstLetter();

        //如果当前位置等于该分类首字母的Char的位置 ，则认为是第一次出现
        if(position == getPositionForSection(catalog)){
            viewHolder.catalog.setVisibility(View.VISIBLE);
            viewHolder.catalog.setText(user.getFirstLetter().toUpperCase());
        }else{
            viewHolder.catalog.setVisibility(View.GONE);
        }

        viewHolder.tv_name.setText(this.list.get(position).getName());
        viewHolder.tv_des.setText(this.list.get(position).getDes());
        Glide.with(mContext)
                .load(user.getUrl()).asBitmap()
                .placeholder(R.drawable.default_image).error(R.drawable.default_image)
                .into((RoundedImageView)viewHolder.img);

        return view;

    }

    final static class ViewHolder {
        TextView catalog;
        TextView tv_des;
        TextView tv_name;
        RoundedImageView img;

    }

    /**
     * 获取catalog首次出现位置
     */
    public int getPositionForSection(String catalog) {
        for (int i = 0; i < getCount(); i++) {
            String sortStr = list.get(i).getFirstLetter();
            if (catalog.equalsIgnoreCase(sortStr)) {
                return i;
            }
        }
        return -1;
    }

}