package com.pot.gathering.activitys;

import org.apache.http.Header;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.view.Gravity;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.TextView;
import android.widget.Toast;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;
import com.pot.gathering.BaseActivity;
import com.pot.gathering.R;
import com.pot.gathering.R.id;
import com.pot.gathering.R.layout;
import com.pot.gathering.R.string;
import com.pot.gathering.config.Config;
import com.pot.gathering.http.AsynHttpManager;

public class LoginActivity extends BaseActivity implements OnCheckedChangeListener{

	/**********************login************************/
	private TextView textLogin;
	private EditText edtPhoneNum;
	private EditText edtPwd;
	
	/***********************Register************************/
	private DrawerLayout mDrawerLayout;
	private LinearLayout layoutRegist;
	private EditText edtRegistName;
	private EditText edtRegistNick;
	private EditText edtRegistPhone;
	private EditText edtRegistPwd;
	private EditText edtRegistPwdAgin;
	private TextView textRegistBirth;
	private TextView textRegistCancel;
	private TextView textRegistSure;
	private RadioGroup rdgRegistSex;
	private RadioButton rdbRegistMale;
	private RadioButton rdbRegistFemale;
	
	private int mSex = 0;// 性别：0表示男，1表是女
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.login);
		
		initObject();
		
		initView();
	}
	
	public void initObject() {
		
	}
	
	public void initView() {
		
		textLogin 		= (TextView) findViewById(R.id.text_login);
		edtPhoneNum 	= (EditText) findViewById(R.id.edt_phonenum);
		edtPwd 			= (EditText) findViewById(R.id.edt_password);
		
		edtRegistName 	= (EditText) findViewById(R.id.edt_register_name);
		edtRegistNick 	= (EditText) findViewById(R.id.edt_register_nick);
		edtRegistPhone	= (EditText) findViewById(R.id.edt_register_phonenum);
		edtRegistPwd	= (EditText) findViewById(R.id.edt_register_pwd);
		edtRegistPwdAgin= (EditText) findViewById(R.id.edt_register_pwd_agin);
		textRegistBirth = (TextView) findViewById(R.id.text_register_birth);
		textRegistCancel= (TextView) findViewById(R.id.text_cancel);
		textRegistSure	= (TextView) findViewById(R.id.text_sure);
		rdgRegistSex	= (RadioGroup) findViewById(R.id.rdg_sex);
		rdbRegistFemale = (RadioButton) findViewById(R.id.rdb_female);
		rdbRegistMale	= (RadioButton) findViewById(R.id.rdb_male);
		mDrawerLayout 	= (DrawerLayout) findViewById(R.id.drawer_layout);
		layoutRegist 	= (LinearLayout) findViewById(R.id.layout_regist);
		
		setOnClickObj();
	}
	
	private void setOnClickObj(){
		textLogin.setOnClickListener(this);
		
		textRegistBirth.setOnClickListener(this);
		textRegistCancel.setOnClickListener(this);
		textRegistSure.setOnClickListener(this);
		rdgRegistSex.setOnCheckedChangeListener(this);
		layoutRegist.setOnClickListener(this);
	}
	
	
	@SuppressLint("RtlHardcoded")
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		super.onClick(v);
		switch (v.getId()) {
		case R.id.text_login:// 登录
//			loginData();
			Intent intent = new Intent(LoginActivity.this, MainActivity.class);
			startActivity(intent);
			break;
		case R.id.text_register_birth:// 出生日期
			break;
		case R.id.text_cancel:// 取消
			mDrawerLayout.closeDrawers();
			clearRegistData();
			break;
		case R.id.text_sure:// 确定
			registData();
			break;
		case R.id.layout_regist:
			mDrawerLayout.openDrawer(Gravity.RIGHT);
			break;
		default:
			break;
		}
	}

	@Override
	public void onCheckedChanged(RadioGroup group, int checkedId) {
		// TODO Auto-generated method stub
		if(rdbRegistFemale.isChecked()){
			rdbRegistFemale.setChecked(true);
			mSex = 1;
		}else{
			rdbRegistMale.setChecked(true);
			mSex = 0;
		}
	}
	
	/**
	 * 清空注册数据
	 */
	private void clearRegistData(){
		edtRegistName.setText("");
		edtRegistNick.setText("");
		edtRegistPhone.setText("");
		edtRegistPwd.setText("");
		edtRegistPwdAgin.setText("");
		textRegistBirth.setText("");
		rdbRegistMale.setChecked(true);
	}
	
	/**
	 * 提交注册信息
	 */
	private void registData(){
		String name = edtRegistName.getText().toString();
		String nick = edtRegistNick.getText().toString();
		String phoneNum = edtRegistPhone.getText().toString();
		String password = edtRegistPwd.getText().toString();
		String birth = textRegistBirth.getText().toString();
		RequestParams params = new RequestParams();
		params.put("name", name);
		params.put("nickName", nick);
		params.put("phonenum", phoneNum);
		params.put("password", password);
		params.put("birthday", birth);
		params.put("imagePaht", "");
		params.put("sex", mSex);
		AsynHttpManager.getInstance().post(Config.URL+Config.REGIST, params, new AsyncHttpResponseHandler() {
			
			@Override
			public void onSuccess(int arg0, Header[] arg1, byte[] arg2) {
				// TODO Auto-generated method stub
				String result = new String(arg2);
				if(result.equals(Config.SUCCESS)){
					mDrawerLayout.closeDrawers();
					clearRegistData();
				}
			}
			
			@Override
			public void onFailure(int arg0, Header[] arg1, byte[] arg2, Throwable arg3) {
				// TODO Auto-generated method stub
			}
		});
	}
	
	/**
	 * 登录验证
	 */
	private void loginData(){
		String phoneNum = edtPhoneNum.getText().toString();
		String password = edtPwd.getText().toString();
		RequestParams params = new RequestParams();
		params.put("phonenum", phoneNum);
		params.put("password", password);
		AsynHttpManager.getInstance().post(Config.URL+Config.LOGIN, params, new AsyncHttpResponseHandler() {
			
			@Override
			public void onSuccess(int arg0, Header[] arg1, byte[] arg2) {
				// TODO Auto-generated method stub
				String result = new String(arg2);
				if(result.equals(Config.SUCCESS)){
					Toast.makeText(LoginActivity.this, R.string.login_success, Toast.LENGTH_LONG).show();
				}else{
					Toast.makeText(LoginActivity.this, R.string.login_failed, Toast.LENGTH_LONG).show();
				}
			}
			
			@Override
			public void onFailure(int arg0, Header[] arg1, byte[] arg2, Throwable arg3) {
				// TODO Auto-generated method stub
				Toast.makeText(LoginActivity.this, R.string.login_failed, Toast.LENGTH_LONG).show();
			}
		});
	}
}
