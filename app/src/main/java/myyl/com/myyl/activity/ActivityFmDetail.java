package myyl.com.myyl.activity;

import android.graphics.drawable.AnimationDrawable;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.makeramen.roundedimageview.RoundedImageView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import myyl.com.myyl.R;
import myyl.com.myyl.activity.base.BaseActivity;
import myyl.com.myyl.adapter.AdapterPj;
import myyl.com.myyl.model.MyPjInfo;
import myyl.com.myyl.utils.MyActivityManager;
import myyl.com.myyl.utils.StringUtils;
import myyl.com.myyl.utils.records.MediaManager;
import myyl.com.myyl.utils.views.MyListView;
import myyl.com.myyl.utils.views.RTextView;
import myyl.com.myyl.utils.views.ratingstar.RatingStarView;

public class ActivityFmDetail extends BaseActivity {


    private static final String TAG = "ActivityFmDetail";
    @BindView(R.id.titleback_btn_back)
    LinearLayout titlebackBtnBack;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.iv_img)
    RoundedImageView ivImg;
    @BindView(R.id.tv_name)
    TextView tvName;
    @BindView(R.id.iv_type)
    ImageView ivType;
    @BindView(R.id.tv_time)
    TextView tvTime;
    @BindView(R.id.tv_content)
    TextView tvContent;
    @BindView(R.id.id_recorder_anim)
    View idRecorderAnim;
    @BindView(R.id.tv_amount)
    TextView tvAmount;
    @BindView(R.id.iv_sound)
    LinearLayout ivSound;
    @BindView(R.id.tv_second)
    TextView tvSecond;
    @BindView(R.id.tv_num)
    RTextView tvNum;
    @BindView(R.id.tv_ratingstar)
    RatingStarView tvRatingstar;
    @BindView(R.id.tv_sorce)
    TextView tvSorce;
    @BindView(R.id.tv_tj)
    TextView tvTj;
    @BindView(R.id.listview)
    MyListView listview;


    private List<MyPjInfo> myPjInfos = new ArrayList<MyPjInfo>();
    private AdapterPj adapterPj;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = this;
        setContentView(R.layout.activity_fm_detail);
        ButterKnife.bind(this);
        MyActivityManager.getInstance().addActivity(this);
        initView();
    }

    private void initView() {
        showView(R.id.titleback_btn_back, true);
        setTvText(R.id.tv_title, "我的FM");
        showView(R.id.next_sure, false);

        adapterPj = new AdapterPj(mContext, myPjInfos);
        listview.setAdapter(adapterPj);

        testData();


    }

    private void testData() {
        Glide.with(mContext)
                .load("http://d.5857.com/xgs_150428/001.jpg").asBitmap()
                .placeholder(R.drawable.default_image).error(R.drawable.default_image)
                .into(ivImg);

        tvName.setText("测试");
        tvTime.setText("4天前");
        tvContent.setText("等级是否就开始福建省了福建省了房间乱收费上课了的飞机上课的房间看电视放假了的书法家了第三方吉林省福建省的否打开司法局的书法家");

        tvAmount.setText("￥"+ StringUtils.formatIntMoney(1000));
        tvSecond.setText(22222+"\"");
        tvNum.setText("4");
        tvRatingstar.setRating(4.50f);
        tvSorce.setText("4.50分");
        tvTj.setText("等级是否就开始福建省了福建省了房间乱收费上课了的飞机上课的房间看电视放假了的书法家了第三方吉林省福建省的否打开司法局的书法家");

        for (int i = 0; i < 3; i++){
            MyPjInfo pjInfo = new MyPjInfo();
            pjInfo.setUrl("http://d.5857.com/xgs_150428/001.jpg");
            pjInfo.setUserName("测试");
            pjInfo.setContent("等级是否就开始福建省了福建省了房间乱收费上课了的飞机上课的房间看电视放假了的书法家了第三方吉林省福建省的否打开司法局的书法家");
            myPjInfos.add(pjInfo);
        }
        adapterPj.notifyDataSetChanged();

    }


    @Override
    protected void onResume() {
        super.onResume();
    }


    @OnClick({R.id.titleback_btn_back, R.id.iv_sound})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.titleback_btn_back:
                finish();
                break;
            case R.id.iv_sound:
                //播放动画
                idRecorderAnim.setBackgroundResource(R.drawable.play_anim);
                AnimationDrawable animation = (AnimationDrawable) idRecorderAnim.getBackground();
                animation.start();


                //播放音频  完成后改回原来的background
                MediaManager.playSound("", new MediaPlayer.OnCompletionListener() {
                    @Override
                    public void onCompletion(MediaPlayer mp) {
                        idRecorderAnim.setBackgroundResource(R.drawable.v_anim3);
                    }
                });
                break;
        }
    }
}
