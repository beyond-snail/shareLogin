/*
Copyright 2017 liusmallpig

 Licensed under the Apache License, Version 2.0 (the "License");
 you may not use this file except in compliance with the License.
 You may obtain a copy of the License at

 http://www.apache.org/licenses/LICENSE-2.0

 Unless required by applicable law or agreed to in writing, software
 distributed under the License is distributed on an "AS IS" BASIS,
 WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 See the License for the specific language governing permissions and
 limitations under the License.
 */
package myyl.com.myyl.utils.views.lrecycleviews.adapter;

import android.content.Context;
import android.content.IntentFilter;
import android.support.annotation.LayoutRes;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import java.util.ArrayList;
import java.util.List;

import myyl.com.myyl.utils.views.lrecycleviews.adapter.viewHolder.BaseRecycleViewsHolder;
import myyl.com.myyl.utils.views.lrecycleviews.constants.IRcyclerClickBack;
import myyl.com.myyl.utils.views.lrecycleviews.constants.OnClick;
import myyl.com.myyl.utils.views.lrecycleviews.constants.OnItemClick;
import myyl.com.myyl.utils.views.lrecycleviews.constants.OnItemLongClick;
import myyl.com.myyl.utils.views.lrecycleviews.constants.OnLoading;
import myyl.com.myyl.utils.views.lrecycleviews.constants.OnLongClick;
import myyl.com.myyl.utils.views.lrecycleviews.constants.RecyclerViewStyle;

/**
 * Created by chenhui on 2017/4/25.
 * All Rights Reserved by YiZu
 */

public abstract class CommonRecyclerAdapter<T> extends RecyclerView.Adapter<BaseRecycleViewsHolder> implements IRcyclerClickBack {
    private LayoutInflater mInflater;
    private List<T> mDatas = new ArrayList<>();
    private int mLayoutId;
    private OnClick onClick;
    private OnItemClick onItemClick;
    private OnLongClick onLongClick;
    private OnItemLongClick onItemLongClick;
    //默认显示Listview
    private RecyclerViewStyle mStyle = RecyclerViewStyle.ListView;
//    private final List<Integer> HEADER = new ArrayList<>();
//    private final List<Integer> CENTER = new ArrayList<>();
//    private final List<Integer> FOOTER = new ArrayList<>();
    private Context mContext;
    //用来存放头布局的视图
    private List<Integer> mHeaderArray = new ArrayList<>();
    //    用来存放尾部的视图
    private List<Integer> mFooterArray = new ArrayList<>();
    //    private List<Integer> mCenterArray;
    private OnLoading loading;
    /**
     * 适配器的构造器
     * @param context
     * @param datats
     * @param layoutId
     * @param recyclerViewStyle
     */
    public CommonRecyclerAdapter(Context context,
                                 List<T> datats,
                                 @LayoutRes int layoutId,
                                 RecyclerViewStyle recyclerViewStyle) {
        mContext = context;
        mInflater = LayoutInflater.from(mContext);
        if (datats != null) {
            mDatas.addAll(datats);
        }
        mLayoutId = layoutId;
        mStyle = recyclerViewStyle;
        //多布局处理
//        if (mStyle == RecyclerViewStyle.MultiLayout) {
//            mCenterArray = new ArrayList<>();
//            mCenterArray.add(mLayoutId);
//        }
    }

    /**
     * 创建ViewHolder
     *
     * @param parent
     * @param viewType
     * @return
     */
    @Override
    public BaseRecycleViewsHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        BaseRecycleViewsHolder holder = null;
        //多布局
//        if (mCenterArray != null) {
//            return holder;
//        }
        if (viewType < 20000) {
//            头布局
            View view = mInflater.inflate(mHeaderArray.get(viewType - 10000), parent, false);
            holder = new BaseRecycleViewsHolder(view, this);
        } else if (viewType < 30000) {
            //尾布局
            View view2 = mInflater.inflate(mFooterArray.get(viewType - 20000), parent, false);
            holder = new BaseRecycleViewsHolder(view2, this);
        } else {
            //中间数据
            View view1 = mInflater.inflate(mLayoutId, parent, false);
            holder = new BaseRecycleViewsHolder(view1, this);
        }

        return holder;
    }

    /**
     * 绑定数据
     *
     * @param holder
     * @param position
     */
    @Override
    public void onBindViewHolder(BaseRecycleViewsHolder holder, int position) {
        //此处将每一个item的position传入adapter
        holder.setmPosition(position);
        if (position < mHeaderArray.size()) {
            setWaterFall(holder);
            //设置头部
            setHeadData(holder, position);
        } else if (position < mHeaderArray.size() + mDatas.size()) {
//            设置中间数据（除去头部和尾部）
            setData(holder, position, mDatas.get(position - mHeaderArray.size()));
        } else {
            setWaterFall(holder);
//            设置尾部数据
            setFootData(holder, position, position - mHeaderArray.size() - mDatas.size());
        }
        if (position == getItemCount() - 1 && getItemCount()>2) {
            if (loading != null) {
                loading.loading();
            }
        }
    }

    /**
     * 瀑布流时将头部最外面的布局设置成铺满
     *
     * @param holder
     */
    private void setWaterFall(BaseRecycleViewsHolder holder) {
        if (mStyle == RecyclerViewStyle.WaterFall || mStyle == RecyclerViewStyle.HorizontalWaterFall) {
            StaggeredGridLayoutManager.LayoutParams clp =
                    (StaggeredGridLayoutManager.LayoutParams) holder.getAbroadView().getLayoutParams();
            clp.setFullSpan(true);
        }
    }

    @Override
    public int getItemViewType(int position) {
        if (position < mHeaderArray.size()) {
            return 10000+position;
        } else if (position < mHeaderArray.size() + mDatas.size()) {
//            if (mCenterArray != null) {
//                return 30000;
//            }
            return 30000;

        } else {
            return (20000+position - mHeaderArray.size() - mDatas.size());
        }
    }

    @Override
    public int getItemCount() {
        return mHeaderArray.size() + mDatas.size() + mFooterArray.size();
    }

    /**
     * 设置控件
     *
     * @param holder
     * @param position
     * @param item
     */
    protected abstract void setData(BaseRecycleViewsHolder holder, int position, T item);

    /**
     * 设置HeaderView
     *
     * @param holder
     * @param position
     */
    protected abstract void setHeadData(BaseRecycleViewsHolder holder, int position);

    /**
     * 设置Footerview
     *
     * @param holder
     * @param position     footview的position
     * @param footposition footview的真实位置
     */
    protected abstract void setFootData(BaseRecycleViewsHolder holder, int position, int footposition);

    @Override
    public void onItemClickBack(int position, View view, BaseRecycleViewsHolder holder) {
        if (onItemClick != null) {
            onItemClick.onItemClick(position, view, holder);
        }
    }

    @Override
    public void onLongItemClickBack(int position, View view, BaseRecycleViewsHolder holder) {
        if (onItemLongClick != null) {
            onItemLongClick.onItemLongClick(position, view, holder);
        }
    }

    @Override
    public void onViewClick(int position, View view, BaseRecycleViewsHolder holder) {
        if (onClick != null) {
            onClick.onClick(position, view, holder);
        }
    }

    @Override
    public void onViewLongClick(int position, View view, BaseRecycleViewsHolder holder) {
        if (onLongClick != null) {
            onLongClick.onLongClick(position, view, holder);
        }
    }


    /**
     * 获取头部数组大小
     *
     * @return
     */
    public int getmHeaderArraySize() {
        return mHeaderArray.size();
    }

    /**
     * 获取头部尾部中间部位的数组（listview等）
     *
     * @return
     */
    public List<T> getmDatas() {
        return mDatas;
    }

    /**
     * 获取尾部数组大小
     *
     * @return
     */
    public int getmFooterArraySize() {
        return mFooterArray.size();
    }

    /**
     * 获取recyclerview的样式
     *
     * @return
     */
    public RecyclerViewStyle getmStyle() {
        return mStyle;
    }

    /**
     * 子view点击事件
     *
     * @param onClick
     */
    public void setOnClick(OnClick onClick) {
        this.onClick = onClick;
    }

    /**
     * Item点击事件
     *
     * @param onItemClick
     */
    public void setOnItemClick(OnItemClick onItemClick) {
        this.onItemClick = onItemClick;
    }

    /**
     * 子view长按点击事件
     *
     * @param onLongClick
     */
    public void setOnLongClick(OnLongClick onLongClick) {
        this.onLongClick = onLongClick;
    }

    /**
     * Item长按点击事件
     *
     * @param onItemLongClick
     */
    public void setOnItemLongClick(OnItemLongClick onItemLongClick) {
        this.onItemLongClick = onItemLongClick;
    }

    /**
     * 更新整个recycle
     *
     * @param data
     */
    public void upData(List<T> data) {
        if (data != null) {
            mDatas.clear();
            mDatas.addAll(data);
        }
        notifyDataSetChanged();
    }

    /**
     * 删除指定的Item
     *
     * @param position
     */
    public void removeData(int position) {
        if (position < getItemCount()) {
            mDatas.remove(position);
            notifyItemRemoved(position);
        }
    }

    /**
     * 删除全部数据
     */
    public void removeAllData() {
        mDatas.clear();
        notifyDataSetChanged();
    }

    /**
     * 指定位置插入数据
     *
     * @param position
     * @param data
     */
    public void insertData(int position, T data) {
        if (position < getItemCount()) {
            mDatas.add(position, data);
            notifyItemInserted(position);
        }
    }

    /**
     * 末尾插入数据
     *
     * @param data
     */
    public void insertData(T data) {
        mDatas.add(data);
        notifyItemInserted(mDatas.size() - 1);
    }
    /**
     * 末尾插入数据
     *
     * @param data
     */
    public void insertDatas(List<T> data) {
        if (data != null) {
            mDatas.addAll(data);
        }
        notifyDataSetChanged();
    }

    /**
     * 添加头部(只有竖直滑动时添加)
     *
     * @param viewId 头部的布局
     */
    public void addHeaderView(@LayoutRes int viewId) {
        switch (mStyle) {
            case ListView:
            case GridView:
            case WaterFall:
                mHeaderArray.add(viewId);
                break;
        }
    }

    /**
     * 添加尾部(只有竖直滑动时添加)
     *
     * @param viewId 尾部布局
     */
    public void addFooterView(@LayoutRes int viewId) {
        switch (mStyle) {
            case ListView:
            case GridView:
            case WaterFall:
                mFooterArray.add(viewId);
                break;
        }
    }

    /**
     * 判断是否添加了头部
     *
     * @return
     */
    public boolean isAddHead() {
        if (mHeaderArray.size() == 0) {
            return false;
        } else {
            return true;
        }
    }

    /**
     * 判断是否添加了尾部
     *
     * @return
     */
    public boolean isAddFoot() {
        if (mFooterArray.size() == 0) {
            return false;
        } else {
            return true;
        }
    }

    /**
     * 获取Context
     * @return
     */
    public Context getContext() {
        return mContext;
    }

    public void addOnLoading(OnLoading onLoading) {
        if (onLoading == null) {
            throw new RuntimeException("OnLoading");
        } else {
            this.loading = onLoading;
        }
    }
}
