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
package myyl.com.myyl.utils.views.lrecycleviews.constants;

import android.view.View;


import myyl.com.myyl.utils.views.lrecycleviews.adapter.viewHolder.BaseRecycleViewsHolder;


/**
 * Created by chenhui on 2017/5/31.
 * All Rights Reserved by YiZu
 * 子view点击事件
 */

public interface OnClick {

    void onClick(int position, View view, BaseRecycleViewsHolder holder);
}