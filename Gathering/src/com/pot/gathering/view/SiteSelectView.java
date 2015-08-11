package com.pot.gathering.view;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.PaintFlagsDrawFilter;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

public class SiteSelectView extends View{

	private static final String XPOINT = "x";
	private static final String YPOINT = "y";
	
	private float mXCenter = 250;// 原点x轴
	private float mYCenter = 300;// 原点y轴
	private float mRadio = 130; // 圆半径
	private int mSpace = 10; // 离中心圆距离
	private float mRadioSmall = 20; // 小圆半径
	private int mSize = 8;
	private List<Map<String, Float>> mSitePositions = new ArrayList<Map<String,Float>>();// 记录每个小圆位置
	private List<Integer> mSelectedSitePositions;
	
	private int clickPosition = -1;// 点击位置
	
	private OnSelectListener mOnSelectListener;// 点击事件
	
	public SiteSelectView(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
	}
	
	public SiteSelectView(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
	}
	
	public SiteSelectView(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
		// TODO Auto-generated constructor stub
	}
	
	interface OnSelectListener{
		public void selecte(int position);
	}
	
	@SuppressLint("DrawAllocation")
	@Override
	protected void onDraw(Canvas canvas) {
		// TODO Auto-generated method stub
		super.onDraw(canvas);
		
		Paint paint = new Paint();
		paint.setColor(Color.WHITE);
		paint.setStyle(Style.FILL);
		paint.setDither(true);
		canvas.setDrawFilter(new PaintFlagsDrawFilter(0, Paint.ANTI_ALIAS_FLAG|Paint.FILTER_BITMAP_FLAG));
		canvas.drawCircle(mXCenter, mYCenter, mRadio, paint);
		canvas.save();
		
		
//		canvas.drawCircle(mXCenter, mYCenter - (mYCenter / 2 + mSpace + mRadioSmall), mRadioSmall, paint);
		mSitePositions.clear();
		for (int i = 0; i < mSize; i++) {
			double rat = 360 / mSize * i;
			double radians = Math.toRadians(rat);
			float dis = mRadio + mSpace + mRadioSmall;
			float y = (float) (dis * (Math.sin(radians)));
			float x = (float) (dis * (Math.cos(radians)));
			
			float xr = mXCenter - x;
			float yr = mYCenter - y;
			
			if(rat == 0){
				xr-=20;
			}else if(rat >= 0 && rat < 90){
				xr-=20;
				yr-=20;
			}else if(rat == 90){
				yr -= 20;
			}else if(rat > 90 && rat < 180){
				xr+=20;
				yr-=20;
			}else if(rat == 180){
				xr+=20;
			}else if(rat > 180 && rat < 270){
				xr+=20;
				yr+=20;
			}else if(rat == 270){
				yr+=20;
			}else if(rat > 270 && rat < 360){
				xr-=20;
				yr+=20;
			}else if(rat == 360){
				xr-=20;
			}
			
//			//已选位置
//			if(mSelectedSitePositions != null){
//				for (int j = 0; j < mSelectedSitePositions.size(); j++) {
//					if(i == mSelectedSitePositions.get(j)){
//						paint.setColor(Color.BLUE);
//					}
//				}
//			}
			//已选位置
			setSelectedPosition(paint, i);
			
			if(i == clickPosition){
				paint.setColor(Color.BLUE);
			}
			canvas.drawCircle(xr, yr, mRadioSmall, paint);
			paint.setColor(Color.WHITE);
			Map<String, Float> map = new HashMap<String, Float>();
			map.put(XPOINT, xr);
			map.put(YPOINT, yr);
			mSitePositions.add(map);
		}
		
		Paint paintText = new Paint();
		paintText.setColor(Color.RED);
		paintText.setTextSize(50);
		canvas.drawText("餐桌", mXCenter-45, mYCenter + 5, paintText);// x轴需要减去文本内容长度一般，y轴需要减去文本高度一半
		
		
	}
	
	public void setSiteCount(int size){
		mSize = size;
	}
	
	public void setXCenter(float center){
		mXCenter = center;
	}
	
	public void setSiteHasSelected(List<Integer> selectedSitePositions){
		mSelectedSitePositions = selectedSitePositions;
	}
	
	@Override
	public boolean onTouchEvent(MotionEvent event) {
		// TODO Auto-generated method stub
		float x = event.getX();
		float y = event.getY();
		for (int i = 0; i < mSitePositions.size(); i++) {
			float xPosition = mSitePositions.get(i).get(XPOINT);
			float yPosition = mSitePositions.get(i).get(YPOINT);
			if((x >= xPosition - mRadioSmall && x <= xPosition + mRadioSmall)
					&& (y >= yPosition - mRadioSmall && y <= yPosition + mRadioSmall)){
				if(!isSelected(i)){
					clickPosition = i;
					if(mOnSelectListener != null){
						mOnSelectListener.selecte(i);
					}
				}
			}
		}
		invalidate();
		return super.onTouchEvent(event);
	}
	
	/**
	* @Title: setSelectedPosition
	* @Description: 已选位置
	* @param @param paint
	* @param @param position
	* @return void
	* @throws
	 */
	private void setSelectedPosition(Paint paint, int position){
		if(mSelectedSitePositions != null){
			for (int j = 0; j < mSelectedSitePositions.size(); j++) {
				if(position == mSelectedSitePositions.get(j)){
					paint.setColor(Color.GREEN);
				}
			}
		}
	}
	
	private boolean isSelected(int position){
		if(mSelectedSitePositions != null){
			for (int i = 0; i < mSelectedSitePositions.size(); i++) {
				if(position == mSelectedSitePositions.get(i)){
					return true;
				}
			}
		}
		return false;
	}
	
	public void setSelectListener(OnSelectListener onSelectListener){
		mOnSelectListener = onSelectListener;
	}
}
