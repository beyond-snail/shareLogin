package myyl.com.myyl.activity;

import android.annotation.SuppressLint;
import android.media.MediaRecorder;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.ScaleAnimation;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.allen.library.SuperButton;

import java.io.File;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import myyl.com.myyl.R;
import myyl.com.myyl.activity.base.BaseActivity;
import myyl.com.myyl.utils.MyActivityManager;
import myyl.com.myyl.utils.views.recordButton.HorVoiceView;
import myyl.com.myyl.utils.views.recordButton.RecorderButton;

public class ActivityAnswer extends BaseActivity {


    private static final String TAG = "ActivityFmDetail";
    @BindView(R.id.titleback_btn_back)
    LinearLayout titlebackBtnBack;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.et_text)
    EditText etText;
    @BindView(R.id.wave1)
    ImageView wave1;
    @BindView(R.id.wave2)
    ImageView wave2;
    @BindView(R.id.wave3)
    ImageView wave3;
    @BindView(R.id.btn_record)
    RecorderButton btnRecord;
    @BindView(R.id.hv_voice)
    HorVoiceView hvVoice;
    @BindView(R.id.tv_state)
    TextView tvState;
    @BindView(R.id.tv_time)
    TextView tvTime;
    @BindView(R.id.tv_fb)
    SuperButton tvFb;


    private MediaRecorder mMediaRecorder;
    private String str_record1;
    private String str_record2;

    private static final int MAX_RECORD_TIME = 360;
    private AnimationSet mAnimationSet1;
    private AnimationSet mAnimationSet2;
    private AnimationSet mAnimationSet3;
    private static final int OFFSET = 600;  //每个动画的播放时间间隔
    private static final int MSG_WAVE2_ANIMATION = 2;
    private static final int MSG_WAVE3_ANIMATION = 3;
    //  这个标志用于防止
    private boolean isShowingWave = false;


    @SuppressLint("HandlerLeak")
    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case MSG_WAVE2_ANIMATION:
                    if (isShowingWave) {
                        wave2.startAnimation(mAnimationSet2);
                    } else {
                        wave2.clearAnimation();
                    }
                    break;
                case MSG_WAVE3_ANIMATION:
                    if (isShowingWave) {
                        wave3.startAnimation(mAnimationSet3);
                    } else {
                        wave3.clearAnimation();
                    }
                    break;
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = this;
        setContentView(R.layout.activity_answer);
        ButterKnife.bind(this);
        MyActivityManager.getInstance().addActivity(this);
        initView();
    }

    private void initView() {
        showView(R.id.titleback_btn_back, true);
        setTvText(R.id.tv_title, "回答");
        showView(R.id.next_sure, false);

        init();
        testData();


    }

    private void init() {
        mAnimationSet1 = initAnimationSet();
        mAnimationSet2 = initAnimationSet();
        mAnimationSet3 = initAnimationSet();

        str_record1 = getResources().getString(R.string.record_txt4);
        str_record2 = getResources().getString(R.string.record_txt5);
        updateTime(MAX_RECORD_TIME);

        btnRecord.setAudioStateRecorderListener(new MyRecordListener());
    }

    private void testData() {


    }


    /**
     * 上传录音
     * 需要修改URL
     *
     * @param seconds    语音的时间长度
     * @param recordFile 录音文件
     */
    private void uploadRecord(final int seconds, final File recordFile) {

        String ts = System.currentTimeMillis() + "";

        //......
        toast("录音完成");
        resetTextAndTime();
    }


    /**
     * 更新时间
     *
     * @param time 更新显示的时间
     */
    private void updateTime(int time) {
        tvTime.setText(str_record1 + time + str_record2);
    }

    /**
     * 文案的还原
     */
    private void resetTextAndTime() {

        tvState.setText(R.string.record_normal);
        updateTime(MAX_RECORD_TIME);

    }

    private AnimationSet initAnimationSet() {
        AnimationSet as = new AnimationSet(true);
        ScaleAnimation sa = new ScaleAnimation(1f, 3.5f, 1f, 3.5f, ScaleAnimation.RELATIVE_TO_SELF, 0.5f, ScaleAnimation.RELATIVE_TO_SELF, 0.5f);
        sa.setDuration(OFFSET * 3);
        sa.setRepeatCount(Animation.INFINITE);// 设置循环
        AlphaAnimation aa = new AlphaAnimation(1, 0.1f);
        aa.setDuration(OFFSET * 3);
        aa.setRepeatCount(Animation.INFINITE);//设置循环
        as.addAnimation(sa);
        as.addAnimation(aa);
        return as;
    }

    private void startWaveAnimation() {
        wave1.startAnimation(mAnimationSet1);
        mHandler.sendEmptyMessageDelayed(MSG_WAVE2_ANIMATION, OFFSET);
        mHandler.sendEmptyMessageDelayed(MSG_WAVE3_ANIMATION, OFFSET * 2);
        isShowingWave = true;
    }

    private void stopWaveAnimation() {
        wave1.clearAnimation();
        wave2.clearAnimation();
        wave3.clearAnimation();
        isShowingWave = false;
    }

    @OnClick({R.id.titleback_btn_back, R.id.tv_fb})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.titleback_btn_back:
                finish();
                break;
            case R.id.tv_fb:
                toast("发布");
                break;
        }
    }


    class MyRecordListener implements RecorderButton.AudioStateRecorderListener {

        @Override
        public void onStart(float time) {

//            tv_txt0.setVisibility(View.GONE);
            startWaveAnimation();
            tvTime.setVisibility(View.VISIBLE);
            tvState.setText(R.string.record_ing);
//                Toast.makeText(AudioRecordActivity.this, "开始录制", Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onUpdateTime(float currentTime, float minTime, float maxTime) {

            //保留一位小数
            int max = (int) maxTime;
            int time = (int) currentTime;
            hvVoice.setText(" " + (max - time) + "s ");
            updateTime(max - time);

            if (time >= 60) {
//                tv_txt0.setText(R.string.record_enougth);
//                tv_txt0.setVisibility(View.VISIBLE);
            }
        }

        @Override
        public void onReturnToRecord() {
            tvState.setText(R.string.record_ing);
        }

        @Override
        public void onWantToCancel() {
            tvState.setText(R.string.record_want_to_cancel);
        }

        @Override
        public void onFinish(float seconds, String filePath) {

            btnRecord.setEnabled(false);
            stopWaveAnimation();
            tvState.setText(R.string.record_success);
            tvTime.setVisibility(View.GONE);

            uploadRecord((int) seconds, new File(filePath));
//                Toast.makeText(AudioRecordActivity.this, (int) seconds + "\n" + filePath, Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onCancel(boolean isTooShort) {
            stopWaveAnimation();
            tvState.setText(R.string.record_normal);
            if (isTooShort) {
                tvTime.setText(R.string.record_too_short);
            }

        }

        @Override
        public void onVoiceChange(int voiceLevel) {
            hvVoice.setVoice(voiceLevel);
        }
    }


    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        if (mMediaRecorder != null) {
            mMediaRecorder.release();
        }
    }


}
