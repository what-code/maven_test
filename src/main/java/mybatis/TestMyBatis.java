package mybatis;

import java.io.*;
import java.sql.SQLException;
import java.util.*;

import com.ibatis.common.resources.Resources;
import com.ibatis.sqlmap.client.SqlMapClient;
import com.ibatis.sqlmap.client.SqlMapClientBuilder;

/**
 * Title:TestMyBatis.java
 * 
 * Description:TestMyBatis.java
 * 
 * Copyright: Copyright (c) 2014-10-14
 * 
 * Company: IZENE Software(Shanghai) Co., Ltd.
 * 
 * @author duanyu@b5m.com
 * 
 * @version 1.0
 */
public class TestMyBatis {

	private static SqlMapClient sqlMapClient = null;
	// 读取配置文件
	static {
		try {
			Reader reader = Resources
					.getResourceAsReader("mybatis/SqlMapConfig.xml");
			sqlMapClient = SqlMapClientBuilder.buildSqlMapClient(reader);
			reader.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public boolean addStudent(Activity student) {
		Object object = null;
		boolean flag = false;
		try {
			object = sqlMapClient.insert("addStudent", student);
			System.out.println("添加学生信息的返回值：" + object);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		if (object != null) {
			flag = true;
		}
		return flag;
	}

	public boolean deleteStudentById(int id) {
		boolean flag = false;
		Object object = null;
		try {
			object = sqlMapClient.delete("deleteStudentById", id);
			System.out.println("删除学生信息的返回值：" + object + "，这里返回的是影响的行数");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		if (object != null) {
			flag = true;
		}
		return flag;
	}

	public boolean updateStudent(Activity student) {
		boolean flag = false;
		Object object = false;
		try {
			object = sqlMapClient.update("updateStudent", student);
			System.out.println("更新学生信息的返回值：" + object + "，返回影响的行数");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		if (object != null) {
			flag = true;
		}
		return flag;
	}

	public List<Activity> selectAllStudent() {
		List<Activity> students = null;
		try {
			students = sqlMapClient.queryForList("selectAllStudent", students);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return students;
	}

	public List<Activity> selectStudentByName(String name) {
		List<Activity> students = null;
		try {
			students = sqlMapClient.queryForList("selectStudentByName", name);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return students;
	}

	public Activity selectStudentById(int id) {
		Activity student = null;
		try {
			student = (Activity) sqlMapClient.queryForObject(
					"selectStudentById", id);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return student;
	}

	public static void main(String[] args){
		TestMyBatis tm = new TestMyBatis();
		List<Activity> list = tm.selectAllStudent();
		for(Activity s : list){
			System.out.println("----s--->" + s.getActivity_id());
		}
	}
}
