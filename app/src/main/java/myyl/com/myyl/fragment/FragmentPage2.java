package myyl.com.myyl.fragment;

import myyl.com.myyl.R;
import myyl.com.myyl.utils.LogUtils;


public class FragmentPage2 extends AbstractFragment{
    private static final String TAG = "FragmentOrderManagerAll";




    @Override
    protected void initView() {

    }





    protected void lazyLoad() {
        LogUtils.e(TAG1+TAG, "lazyLoad()");
//        reloadData();
    }

    @Override
    protected int setContentView() {
        return R.layout.fragment2;
    }

    @Override
    public void onResume() {
        super.onResume();
        LogUtils.e(TAG1+TAG, "onResume");
//        reloadData();
    }









}
