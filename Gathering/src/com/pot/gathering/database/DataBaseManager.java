package com.pot.gathering.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.pot.gathering.Bean.ContactBean;

public class DataBaseManager {

	private Context mContext;
	private DBHelper mHelper = null;

	private static DataBaseManager mDatabasBaseManager = null;

	private String mTbName = "contact";

	public DataBaseManager(Context context) {
		// TODO Auto-generated constructor stub
		mHelper = new DBHelper(context);
	}

	public static DataBaseManager getInstance(Context context) {
		mDatabasBaseManager = new DataBaseManager(context);
		return mDatabasBaseManager;
	}

	// 插入操作
	public void insertData(ContactBean bean) {
		// String sql = "insert into contact " +
		// "(name,phonenum,nickname,imgurl,comment,type,sex)" +
		// "values(?,?,?,?,?,?,?)";
		// SQLiteDatabase db = mHelper.getWritableDatabase();
		// db.execSQL(sql, new Object[] { bean.getName(), bean.getPhoneNum(),
		// bean.getNickName(), bean.getImgUrl(), bean.getType(),
		// bean.getSex()});
		SQLiteDatabase db = mHelper.getWritableDatabase();
		ContentValues values = new ContentValues();
		values.put("name", bean.getName());
		values.put("phonenum", bean.getPhoneNum());
		values.put("nickname", bean.getNickName());
		values.put("imgurl", bean.getImgUrl());
		values.put("type", bean.getType());
		values.put("sex", bean.getSex());
		long rowid = db.insert(mTbName, null, values);// 返回新添记录的行号，与主键id无关
		db.close();
	}

	// 修改
	public void updateData(ContactBean bean) {
		SQLiteDatabase db = mHelper.getWritableDatabase();
		ContentValues values = new ContentValues();
		values.put("name", bean.getName());
		values.put("phonenum", bean.getPhoneNum());
		values.put("nickname", bean.getNickName());
		values.put("imgurl", bean.getImgUrl());
		values.put("type", bean.getType());
		values.put("sex", bean.getSex());
		int rowid = db.update(mTbName, values, "phonenum=?",
				new String[] { bean.getPhoneNum() });
		db.close();
	}
	
	// 删除
	public void deleteData(String phoneNum){
		SQLiteDatabase db = mHelper.getWritableDatabase();
		db.delete(mTbName, "phonenum=?", new String[]{phoneNum});
		db.close();
	}
}
