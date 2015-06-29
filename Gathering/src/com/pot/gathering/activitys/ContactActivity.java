package com.pot.gathering.activitys;

import java.util.ArrayList;

import android.os.Bundle;
import android.widget.ListView;

import com.pot.gathering.BaseActivity;
import com.pot.gathering.R;
import com.pot.gathering.Bean.ContactBean;
import com.pot.gathering.adapter.ContactAdapter;

/**
 * 联系人管理
* @ClassName: ContactActivity
* @Description: TODO
* @author cfh
* @date 2015-6-29 上午10:44:48
*
 */
public class ContactActivity extends BaseActivity {

	private ListView mListView;
	private ContactAdapter mAdapter;
	private ArrayList<ContactBean> mArrayList;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.contact_main);
		
		initObj();
		
		initView();
	}
	
	private void initObj(){
		mArrayList = new ArrayList<ContactBean>();
		prepareData();
		mAdapter = new ContactAdapter(this, mArrayList);
	}
	
	private void initView(){
		
		setTitleStyle(true);
		
		setTitle(true, getResources().getString(R.string.contact));
		
		mListView = (ListView) findViewById(R.id.listview_contact);
		mListView.setAdapter(mAdapter);
	}
	
	private void prepareData(){
		for (int i = 0; i < 10; i++) {
			ContactBean bean = new ContactBean();
			bean.setName("name:"+i);
			bean.setType(0);
			if(i == 0 || i == 5){
				bean.setTitle(true);
			}else{
				bean.setTitle(false);
			}
			mArrayList.add(bean);
		}
	}
}
