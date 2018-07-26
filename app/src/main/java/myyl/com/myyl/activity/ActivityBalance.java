package myyl.com.myyl.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.allen.library.SuperButton;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import myyl.com.myyl.R;
import myyl.com.myyl.activity.base.BaseActivity;
import myyl.com.myyl.utils.MyActivityManager;

public class ActivityBalance extends BaseActivity {


    private static final String TAG = "ActivityBalance";
    @BindView(R.id.titleback_btn_back)
    LinearLayout titlebackBtnBack;
    @BindView(R.id.tv_balanceAmt)
    TextView tvBalanceAmt;
    @BindView(R.id.tv_cz)
    SuperButton tvCz;
    @BindView(R.id.tv_tx)
    SuperButton tvTx;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = this;
        setContentView(R.layout.activity_balance);
        ButterKnife.bind(this);
        MyActivityManager.getInstance().addActivity(this);
        testData();
        initView();
    }

    private void testData() {

    }

    private void initView() {
        showView(R.id.titleback_btn_back, true);
        setTvText(R.id.tv_title, "我的余额");
        showView(R.id.next_sure, true);
        setTvText(R.id.next_sure, "记录");
        textView(R.id.next_sure).setTextSize(13);
        textView(R.id.next_sure).setTextColor(getResources().getColor(R.color.font_yl));


    }

    private void loadData() {

    }


    @Override
    protected void onResume() {
        super.onResume();
    }


    @OnClick({R.id.titleback_btn_back, R.id.next_sure, R.id.tv_cz, R.id.tv_tx})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.titleback_btn_back:
                finish();
                break;
            case R.id.next_sure:

                break;
            case R.id.tv_cz:
                break;
            case R.id.tv_tx:
                break;
        }
    }

}
