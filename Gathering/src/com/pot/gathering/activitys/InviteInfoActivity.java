package com.pot.gathering.activitys;

import java.util.ArrayList;
import java.util.List;

import android.os.Bundle;

import com.pot.gathering.BaseActivity;
import com.pot.gathering.R;
import com.pot.gathering.view.SiteSelectView;

public class InviteInfoActivity extends BaseActivity {

	private SiteSelectView viewSite;
	private List<Integer> mSelectedSitePositions;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.invite_info);
		
		initObj();
		
		initView();
	}
	
	private void initObj(){
		mSelectedSitePositions = new ArrayList<Integer>();
		for (int i = 0; i < 3; i++) {
			mSelectedSitePositions.add(i);
		}
	}
	
	private void initView(){
		viewSite = (SiteSelectView) findViewById(R.id.view_site);
		getScreenSize();
//		viewSite.setXCenter(screenWidth/2 - 100);
		viewSite.setSiteCount(8);
		viewSite.setSiteHasSelected(mSelectedSitePositions);
		viewSite.invalidate();
	}
}
