package myyl.com.myyl.fragment;


import android.widget.ListView;

import java.util.ArrayList;
import java.util.Collections;

import myyl.com.myyl.R;
import myyl.com.myyl.adapter.SortAdapter;
import myyl.com.myyl.utils.LogUtils;
import myyl.com.myyl.utils.views.SideBar;
import myyl.com.myyl.utils.views.User;

public class FragmentSufferer1 extends AbstractFragment{
    private static final String TAG = "FragmentHomePage1";

    private ListView listView;
    private SideBar sideBar;
    private ArrayList<User> list;



    @Override
    protected void initView() {
        listView = (ListView) findViewById(R.id.listView);
        sideBar = (SideBar) findViewById(R.id.side_bar);
        sideBar.setOnStrSelectCallBack(new SideBar.ISideBarSelectCallBack() {
            @Override
            public void onSelectStr(int index, String selectStr) {
                for (int i = 0; i < list.size(); i++) {
                    if (selectStr.equalsIgnoreCase(list.get(i).getFirstLetter())) {
                        listView.setSelection(i); // 选择到首字母出现的位置
                        return;
                    }
                }
            }
        });
    }

    private void testData() {
        list = new ArrayList<>();
        list.add(new User("亳州","http://d.5857.com/xgs_150428/001.jpg", "接口来大房间" )); // 亳[bó]属于不常见的二级汉字
        list.add(new User("大娃","http://d.5857.com/xgs_150428/001.jpg", "接口来大房间" ));
        list.add(new User("二娃","http://d.5857.com/xgs_150428/001.jpg", "接口来大房间" ));
        list.add(new User("三娃","http://d.5857.com/xgs_150428/001.jpg", "接口来大房间" ));
        list.add(new User("四娃","http://d.5857.com/xgs_150428/001.jpg", "接口来大房间" ));
        list.add(new User("五娃","http://d.5857.com/xgs_150428/001.jpg", "接口来大房间" ));
        list.add(new User("六娃","http://d.5857.com/xgs_150428/001.jpg", "接口来大房间" ));
        list.add(new User("七娃","http://d.5857.com/xgs_150428/001.jpg", "接口来大房间" ));
        list.add(new User("喜羊羊","http://d.5857.com/xgs_150428/001.jpg", "接口来大房间" ));
        list.add(new User("美羊羊","http://d.5857.com/xgs_150428/001.jpg", "接口来大房间" ));
        list.add(new User("懒羊羊","http://d.5857.com/xgs_150428/001.jpg", "接口来大房间" ));
        list.add(new User("沸羊羊","http://d.5857.com/xgs_150428/001.jpg", "接口来大房间" ));
        list.add(new User("暖羊羊","http://d.5857.com/xgs_150428/001.jpg", "接口来大房间" ));
        list.add(new User("慢羊羊","http://d.5857.com/xgs_150428/001.jpg", "接口来大房间" ));
        list.add(new User("灰太狼","http://d.5857.com/xgs_150428/001.jpg", "接口来大房间" ));
        list.add(new User("红太狼","http://d.5857.com/xgs_150428/001.jpg", "接口来大房间" ));
        list.add(new User("孙悟空","http://d.5857.com/xgs_150428/001.jpg", "接口来大房间" ));
        list.add(new User("黑猫警长","http://d.5857.com/xgs_150428/001.jpg", "接口来大房间" ));
        list.add(new User("舒克","http://d.5857.com/xgs_150428/001.jpg", "接口来大房间" ));
        list.add(new User("贝塔","http://d.5857.com/xgs_150428/001.jpg", "接口来大房间" ));
        list.add(new User("海尔","http://d.5857.com/xgs_150428/001.jpg", "接口来大房间" ));
        list.add(new User("阿凡提","http://d.5857.com/xgs_150428/001.jpg", "接口来大房间" ));
        list.add(new User("邋遢大王","http://d.5857.com/xgs_150428/001.jpg", "接口来大房间" ));
        list.add(new User("哪吒","http://d.5857.com/xgs_150428/001.jpg", "接口来大房间" ));
        list.add(new User("没头脑","http://d.5857.com/xgs_150428/001.jpg", "接口来大房间" ));
        list.add(new User("不高兴","http://d.5857.com/xgs_150428/001.jpg", "接口来大房间" ));
        list.add(new User("蓝皮鼠","http://d.5857.com/xgs_150428/001.jpg", "接口来大房间" ));
        list.add(new User("大脸猫","http://d.5857.com/xgs_150428/001.jpg", "接口来大房间" ));
        list.add(new User("大头儿子","http://d.5857.com/xgs_150428/001.jpg", "接口来大房间" ));
        list.add(new User("小头爸爸","http://d.5857.com/xgs_150428/001.jpg", "接口来大房间" ));
        list.add(new User("蓝猫","http://d.5857.com/xgs_150428/001.jpg", "接口来大房间" ));
        list.add(new User("淘气","http://d.5857.com/xgs_150428/001.jpg", "接口来大房间" ));
        list.add(new User("叶峰","http://d.5857.com/xgs_150428/001.jpg", "接口来大房间" ));
        list.add(new User("楚天歌","http://d.5857.com/xgs_150428/001.jpg", "接口来大房间" ));
        list.add(new User("江流儿","http://d.5857.com/xgs_150428/001.jpg", "接口来大房间" ));
        list.add(new User("Tom","http://d.5857.com/xgs_150428/001.jpg", "接口来大房间" ));
        list.add(new User("Jerry","http://d.5857.com/xgs_150428/001.jpg", "接口来大房间" ));
        list.add(new User("12345","http://d.5857.com/xgs_150428/001.jpg", "接口来大房间" ));
        list.add(new User("54321","http://d.5857.com/xgs_150428/001.jpg", "接口来大房间" ));
        list.add(new User("_(:з」∠)_","http://d.5857.com/xgs_150428/001.jpg", "接口来大房间" ));
        list.add(new User("……%￥#￥%#","http://d.5857.com/xgs_150428/001.jpg", "接口来大房间" ));
        Collections.sort(list); // 对list进行排序，需要让User实现Comparable接口重写compareTo方法
        SortAdapter adapter = new SortAdapter(mContext, list);
        listView.setAdapter(adapter);
    }


    protected void lazyLoad() {
        LogUtils.e(TAG1+TAG, "lazyLoad()");
//        reloadData();
        testData();
    }

    @Override
    protected int setContentView() {
        return R.layout.fragment_sufferer_1;
    }

    @Override
    public void onResume() {
        super.onResume();
        LogUtils.e(TAG1+TAG, "onResume");
//        reloadData();
    }








}
