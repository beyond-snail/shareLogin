package myyl.com.myyl.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import myyl.com.myyl.R;
import myyl.com.myyl.activity.base.BaseActivity;
import myyl.com.myyl.utils.MyActivityManager;
import myyl.com.myyl.utils.views.MyListView;

public class ActivityTx extends BaseActivity {


    private static final String TAG = "ActivityTx";
    @BindView(R.id.titleback_btn_back)
    LinearLayout titlebackBtnBack;
    @BindView(R.id.listview)
    MyListView listview;
    @BindView(R.id.tv_add)
    TextView tvAdd;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = this;
        setContentView(R.layout.activity_tx);
        ButterKnife.bind(this);
        MyActivityManager.getInstance().addActivity(this);
        testData();
        initView();
    }

    private void testData() {

    }

    private void initView() {
        showView(R.id.titleback_btn_back, true);
        setTvText(R.id.tv_title, "选择银行卡");
        showView(R.id.next_sure, true);
        setTvText(R.id.next_sure, "提现记录");
        textView(R.id.next_sure).setTextSize(13);
        textView(R.id.next_sure).setTextColor(getResources().getColor(R.color.font_yl));


    }

    private void loadData() {

    }


    @Override
    protected void onResume() {
        super.onResume();
    }


    @OnClick({R.id.titleback_btn_back, R.id.next_sure})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.titleback_btn_back:
                finish();
                break;
            case R.id.next_sure:

                break;
        }
    }

    @OnClick(R.id.tv_add)
    public void onViewClicked() {
    }
}
