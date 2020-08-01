package com.bbs.test;

import java.util.List;
import java.util.Map;

import com.bbs.util.DBHelper;

public class test01 {
	public static void main(String[] args) {
		DBHelper dbh=new DBHelper();
		String sql="select * from tbl_board";
		List<Map<String,Object>>list=dbh.query(sql);
		for (Map<String, Object> map : list) {
			System.out.println(map);
		}
	}
}
