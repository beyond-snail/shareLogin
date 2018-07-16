package myyl.com.myyl.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import myyl.com.myyl.R;


public class HorizontalListViewAdapter extends BaseAdapter {

    private Context mContext;

    public HorizontalListViewAdapter(Context con) {
        mInflater = LayoutInflater.from(con);
        mContext = con;
    }
    @Override
    public int getCount() {
        return 15;
    }

    private LayoutInflater mInflater;

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder vh = null;
        if (convertView == null) {
            vh = new ViewHolder();
            convertView = mInflater.inflate(R.layout.horizontallistview_item, null);
            vh.dateTv = (TextView) convertView.findViewById(R.id.tv_date);
            vh.titleTv = (TextView) convertView.findViewById(R.id.tv_weather);
            vh.viewRl = convertView.findViewById(R.id.viewLl);
            convertView.setTag(vh);
        } else {
            vh = (ViewHolder) convertView.getTag();
        }

        return convertView;
    }


    private  class ViewHolder {
        private View viewRl;
        private TextView dateTv;
        private TextView titleTv;
    }

}