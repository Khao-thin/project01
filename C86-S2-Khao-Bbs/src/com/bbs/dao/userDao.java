package com.bbs.dao;

import java.util.List;
import java.util.Map;

import com.bbs.util.DBHelper;

public class userDao {
	/**
	 * 
	 * @param uname	用户名
	 * @param upass	密码
	 * @param head	头像
	 * @param gender 性别
	 */
	private DBHelper dbh=new DBHelper();
	public void insert(String uname,String upass,String head,String gender) {
		String sql="insert into tbl_user values(null,?,?,?,now(),?)";
		
		dbh.update(sql, uname,upass,head,gender);
	}
	/**
	 * 代码重构
	 * @param uname
	 * @param upass
	 * @return
	 */
	public Map<String, Object> login(String uname,String upass) {
		String sql="select * from tbl_user where uname=? and upass=?";
		//return dbh.count(sql, uname,upass)>0;
		List<Map<String,Object>>list=dbh.query(sql, uname,upass);
		if(list.size()==0) {
			return null;
		}else {
			Map<String,Object>user=list.get(0);
			return user;
		}
	}
}
