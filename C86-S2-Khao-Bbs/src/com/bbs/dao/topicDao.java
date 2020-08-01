package com.bbs.dao;

import java.util.List;
import java.util.Map;

import com.bbs.util.DBHelper;

public class topicDao {
	public List<Map<String, Object>> queryByBoard(String boardid){
		String sql="select * from tbl_topic where boardid=?";
		return new DBHelper().query(sql, boardid);
	}
	
	public List<Map<String, Object>> queryByDetail(String topicid) {
		String sql="select * from tbl_topic a left join tbl_user b on a.uid=b.uid where topicid=?\n" +
				"UNION all\n" +
				"select * from tbl_reply a join tbl_user b on a.uid=b.uid where topicid=?";
		return new DBHelper().query(sql, topicid,topicid);
	}
	public void insert(String title,String content,String uid,String boardid) {
		String sql="insert into tbl_topic values(null,?,?,now(),null,?,?)";
		new DBHelper().update(sql,title,content,uid,boardid);
	}
}
