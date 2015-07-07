package com.pot.gathering.activitys;

import java.lang.ref.WeakReference;
import java.util.ArrayList;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.TextView;

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

	private TextView textName;
	private TextView textNick;
	private TextView textPhone;
	private TextView textDelete;
	private TextView textEdit;
	private ListView mListView;
	private ContactAdapter mAdapter;
	private ArrayList<WeakReference<ContactBean>> mArrayList;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.contact_main);
		
		initObj();
		
		initView();
		
		initData();
	}
	
	private void initObj(){
		mArrayList = new ArrayList<WeakReference<ContactBean>>();
		prepareData();
		mAdapter = new ContactAdapter(this, mArrayList);
	}
	
	private void initView(){
		
		setTitleStyle(true);
		
		setTitle(true, getResources().getString(R.string.contact));
		
		textName 	= (TextView) findViewById(R.id.text_name);
		textNick 	= (TextView) findViewById(R.id.text_nick_name);
		textPhone 	= (TextView) findViewById(R.id.text_phonenum);
		textDelete	= (TextView) findViewById(R.id.text_delete);
		textEdit	= (TextView) findViewById(R.id.text_edit);
		
		mListView = (ListView) findViewById(R.id.listview_contact);
		mListView.setAdapter(mAdapter);
		
		mListView.setOnItemClickListener(onItemClickListener);
	}
	
	private void initData(){
		if(mArrayList != null && mArrayList.size() > 0){
			setDetailInfo(mArrayList.get(0).get());
		}
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
			bean.setNickName("nikename--"+i);
			bean.setPhoneNum("1597049341"+i);
			WeakReference<ContactBean> wBean = new WeakReference(bean);
			mArrayList.add(wBean);
		}
	}
	
	private void setDetailInfo(ContactBean bean){
		if(bean != null){
			textName.setText(bean.getName());
			textNick.setText(bean.getNickName());
			textPhone.setText(bean.getPhoneNum());
		}
	}
	
	private OnItemClickListener onItemClickListener = new OnItemClickListener() {

		@Override
		public void onItemClick(AdapterView<?> parent, View view, int position,
				long id) {
			// TODO Auto-generated method stub
			setDetailInfo(mArrayList.get(position).get());
		}
	};
}
