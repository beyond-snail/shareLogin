package myyl.com.myyl.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.makeramen.roundedimageview.RoundedImageView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import myyl.com.myyl.R;
import myyl.com.myyl.activity.base.TabBasicActivity;
import myyl.com.myyl.utils.MyActivityManager;
import myyl.com.myyl.utils.views.CornerImageView;
import myyl.com.myyl.utils.views.RTextView;

public class MineActivity extends TabBasicActivity implements View.OnClickListener {


    private static final String TAG = "MineActivity";
    @BindView(R.id.tv_name)
    TextView tvName;
    @BindView(R.id.tv_rz)
    RTextView tvRz;
    @BindView(R.id.tv_gz)
    TextView tvGz;
    @BindView(R.id.iv_img)
    RoundedImageView ivImg;
    @BindView(R.id.ll_sr)
    LinearLayout llSr;
    @BindView(R.id.ll_tx)
    LinearLayout llTx;
    @BindView(R.id.ll_balance)
    LinearLayout llBalance;
    @BindView(R.id.rl_tj)
    RelativeLayout rlTj;
    @BindView(R.id.rl_my_fm)
    RelativeLayout rlMyFm;
    @BindView(R.id.rl_my_zw)
    RelativeLayout rlMyZw;
    @BindView(R.id.rl_my_ss)
    RelativeLayout rlMySs;
    @BindView(R.id.rl_my_bl)
    RelativeLayout rlMyBl;
    @BindView(R.id.rl_sc)
    RelativeLayout rlSc;
    @BindView(R.id.rl_coupon)
    RelativeLayout rlCoupon;
    @BindView(R.id.rl_setting)
    RelativeLayout rlSetting;
    @BindView(R.id.tv_num)
    CornerImageView tvNum;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = this;
        setContentView(R.layout.activity_mine);
        ButterKnife.bind(this);
        MyActivityManager.getInstance().addActivity(this);
        initView();
    }

    private void initView() {
        showView(R.id.titleback_btn_back, false);
        setTvText(R.id.tv_title, "我的");
        showView(R.id.next_sure, false);


    }


    @Override
    protected void onResume() {
        super.onResume();
    }


    @OnClick({R.id.ll_sr, R.id.ll_tx, R.id.ll_balance, R.id.rl_tj, R.id.rl_my_fm, R.id.rl_my_zw, R.id.rl_my_ss, R.id.rl_my_bl, R.id.rl_sc, R.id.rl_coupon, R.id.rl_setting})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ll_sr:
                startActivity(new Intent(mContext, ActivityIncome.class));
                break;
            case R.id.ll_tx:
                startActivity(new Intent(mContext, ActivityTx.class));
                break;
            case R.id.ll_balance:
                startActivity(new Intent(mContext, ActivityBalance.class));
                break;
            case R.id.rl_tj:
                break;
            case R.id.rl_my_fm:
                startActivity(new Intent(mContext, ActivityFmMore.class));
                break;
            case R.id.rl_my_zw:
                startActivity(new Intent(mContext, ActivityZwMore.class));
                break;
            case R.id.rl_my_ss:
                startActivity(new Intent(mContext, ActivityShuoShuo.class));
                break;
            case R.id.rl_my_bl:
                break;
            case R.id.rl_sc:
                break;
            case R.id.rl_coupon:
                break;
            case R.id.rl_setting:
                break;
        }
    }
}
