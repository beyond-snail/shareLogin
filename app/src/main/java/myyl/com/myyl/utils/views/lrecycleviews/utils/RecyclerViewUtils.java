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
package myyl.com.myyl.utils.views.lrecycleviews.utils;

import android.content.Context;
import android.support.annotation.ColorRes;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;

import myyl.com.myyl.utils.views.lrecycleviews.adapter.CommonRecyclerAdapter;
import myyl.com.myyl.utils.views.lrecycleviews.constants.RecyclerViewStyle;
import myyl.com.myyl.utils.views.lrecycleviews.widget.CommonRecyclerDivider;


/**
 * Created by chenhui on 2017/4/26.
 * All Rights Reserved by YiZu
 */

public class RecyclerViewUtils<T> {
    private Context mContext;
    private RecyclerView mRecyclerView;
    private RecyclerViewStyle mRecyclerViewStyle;
    private int mNunColumns = 2;
    private CommonRecyclerAdapter<T> mAdapter;

    public RecyclerViewUtils(RecyclerView mRecyclerView, CommonRecyclerAdapter<T> adapter,int nunColumns) {
        this.mRecyclerView = mRecyclerView;
        mAdapter = adapter;
        mContext = mAdapter.getContext();
        mRecyclerViewStyle = mAdapter.getmStyle();
        if (nunColumns >= 1) {
            mNunColumns = nunColumns;
        }
        setRecyclerStlty();
    }

    private void setRecyclerStlty() {
        switch (mAdapter.getmStyle()) {
            case ListView:
            case HorizontalListView:
                setLView();
                break;
            case GridView:
            case HorizontalGridView:
                setGView();
                break;
            case WaterFall:
                StaggeredGridLayoutManager manager = new StaggeredGridLayoutManager(mNunColumns, StaggeredGridLayoutManager.VERTICAL);
                mRecyclerView.setLayoutManager(manager);
                break;
            case HorizontalWaterFall:
                StaggeredGridLayoutManager manager1 = new StaggeredGridLayoutManager(mNunColumns, StaggeredGridLayoutManager.HORIZONTAL);
                mRecyclerView.setLayoutManager(manager1);
                break;
            case MultiLayout:
                //多布局暂未实现
                break;

        }

    }
    private void setLView() {
        LinearLayoutManager manager = new LinearLayoutManager(mContext);
        if (mRecyclerViewStyle == RecyclerViewStyle.ListView) {
            manager.setOrientation(LinearLayoutManager.VERTICAL);
        } else if (mRecyclerViewStyle == RecyclerViewStyle.HorizontalListView) {
            manager.setOrientation(LinearLayoutManager.HORIZONTAL);
        }
        mRecyclerView.setLayoutManager(manager);
    }

    private void setGView() {
        GridLayoutManager manager = new GridLayoutManager(mContext, mNunColumns);
        if (mRecyclerViewStyle == RecyclerViewStyle.HorizontalGridView) {
            manager.setOrientation(GridLayoutManager.HORIZONTAL);
        } else {
            manager.setOrientation(GridLayoutManager.VERTICAL);
        }
        if (mAdapter != null) {
            if (mAdapter.isAddFoot() || mAdapter.isAddHead()) {
                //将gridview的头部或者尾部设置成
                manager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
                    @Override
                    public int getSpanSize(int position) {
                        if (position < mAdapter.getmHeaderArraySize()||
                                position>=mAdapter.getmHeaderArraySize()+mAdapter.getmDatas().size()) {
                            return mNunColumns;
                        } else {
                            return 1;
                        }
                    }
                });
            }
        }
        mRecyclerView.setLayoutManager(manager);
    }

    /**
     * 添加带颜色的分割线
     * @param dividerHeight
     * @param dividerColor
     */
    public void addItemDecoration(int dividerHeight, @ColorRes int dividerColor) {
        mRecyclerView.addItemDecoration(new CommonRecyclerDivider<T>(dividerHeight,
                mAdapter,mContext.getResources().getColor(dividerColor),mNunColumns));
    }

}
