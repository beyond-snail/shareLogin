package myyl.com.myyl.utils.views;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;


import org.xmlpull.v1.XmlPullParser;

import java.util.ArrayList;
import java.util.List;

import myyl.com.myyl.R;

public class SegmentView extends LinearLayout {

	public int mNum = 0;
	private int textSize = 0;
	private int textHeight = 0;
	//当前选中索引
	public int index = 0;
	
	private List<TextView> textViews = new ArrayList<TextView>();;
	
	private String[] textValues ;

	private onSegmentViewClickListener listener;
	
	@SuppressLint("Recycle")
	public SegmentView(Context context, AttributeSet attrs) {
		super(context, attrs);
		TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.seg_attrs);
		//String text = ta.getString(R.styleable.test_testAttr);
		mNum = ta.getInteger(R.styleable.seg_attrs_mNum, 0);
		textSize = ta.getInteger(R.styleable.seg_attrs_seg_text_size, 12);
		textHeight = ta.getInteger(R.styleable.seg_attrs_seg_text_height, 35);
		init();
	}
	
	public SegmentView(Context context) {
		super(context);
		init();
	}
	
	public SegmentView(Context context, int mNum) {
		super(context);
		this.mNum = mNum;
		init();
	}
	
	@SuppressWarnings("ResourceType")
	private void init() {
		this.setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));
		this.removeAllViews();
		for(int i=0;i<mNum;i++){
			TextView textView = new TextView(getContext());
			textView.setLayoutParams(new LayoutParams(0, dp2Px(getContext(), textHeight), 1));
			textView.setTag(""+i);
			textView.setText("SEG"+i);
			XmlPullParser xrp = getResources().getXml(R.color.seg_text_color_selector);
		    try {
		        ColorStateList csl = ColorStateList.createFromXml(getResources(), xrp);
		        textView.setTextColor(csl);
		    } catch (Exception e) {}
		    textView.setGravity(Gravity.CENTER);
		    textView.setPadding(3, 5, 3, 5);
		    textView.setTextSize(TypedValue.COMPLEX_UNIT_DIP, textSize);
			
		    if(i==0){
		    	textView.setBackgroundResource(R.drawable.seg_left);
		    	textView.setSelected(true);
		    }
		    else if(i==mNum-1){textView.setBackgroundResource(R.drawable.seg_right);}
		    else {textView.setBackgroundResource(R.drawable.seg_middle);}
		    
		    if(i!=0){
		    	MarginLayoutParams params = (MarginLayoutParams) textView.getLayoutParams();
		    	params.setMargins(-1*dp2Px(getContext(), 1), 0, 0, 0);
		    	textView.setLayoutParams(params);
		    }
		    
		    textViews.add(textView);
		    this.addView(textView);
		    this.invalidate();
		    textView.setOnClickListener(new SegClickListener(i));
		}

	}
	
	class SegClickListener implements OnClickListener {
		int click_index = 0;
		SegClickListener(){}
		SegClickListener(int position){
			this.click_index = position;
			index = position;
		}
		
		@Override
		public void onClick(View v) {
			if(textViews.get(click_index).isSelected()){return;}
			for(int i=0;i<mNum;i++){
				textViews.get(i).setSelected(i==click_index?true:false);
			}
			if (listener != null) {
				listener.onSegmentViewClick(click_index);
			}
		}
		
	}
	
	private static int dp2Px(Context context, float dp) {
		final float scale = context.getResources().getDisplayMetrics().density;
		return (int) (dp * scale + 0.5f);
	}
	
	public void setOnSegmentViewClickListener(onSegmentViewClickListener listener) {
		this.listener = listener;
	}
	
	public static interface onSegmentViewClickListener{
		public void onSegmentViewClick(int index);
	}

	public String[] getTextValues() {
		return textValues;
	}

	public void setTextValues(String[] textValues) {
		this.textValues = textValues;
		if(textValues!=null&&textValues.length==mNum){
			for(int i=0;i<mNum;i++){
				textViews.get(i).setText(textValues[i]);
			}
		}
	}

	public int getIndex() {return index;}
	public void setIndex(int index) {this.index = index;}

}
