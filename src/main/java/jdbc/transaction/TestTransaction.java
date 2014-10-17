package jdbc.transaction;

import java.io.BufferedReader;
import java.io.*;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;
import java.sql.*;

/**
 * Title:TestTransaction.java
 * 
 * Description:TestTransaction.java
 * 
 * Copyright: Copyright (c) 2014-9-25
 * 
 * Company: IZENE Software(Shanghai) Co., Ltd.
 * 
 * @author duanyu@b5m.com
 * 
 * @version 1.0
 */
public class TestTransaction {
	
	public static void logCount(){
		String path = "/home/gsj/doc/counter0927.log";
		String writePath = "/home/gsj/doc/counter0927_.log";
		try {
			FileOutputStream file = new FileOutputStream(new File(writePath));
			FileInputStream is = new FileInputStream(path);
			BufferedReader br = new BufferedReader(new InputStreamReader(is));
			String temp = null;
			String lastLine = null;
			while((temp = br.readLine()) != null){
				if(lastLine == null){
					lastLine = temp;
					System.out.println(temp);
				}else{
					long[] l1 = getLineData(lastLine);
					long[] l2 = getLineData(temp);
					lastLine = temp;
					double result = (double)(l2[1] - l1[1])/(double)(l2[0] - l1[0]);
					long mz = (l2[0] - l1[0]) - (l2[1] - l1[1]);
					String finalStr = temp + "---traffic--->" + (l2[0] - l1[0]) + "----mz rate(" + mz + ")---->" + (1 - result) + "\n";
					file.write(finalStr.getBytes());
					//System.out.println(temp + "---traffic--->" + (l2[0] - l1[0]) + "----mz rate(" + mz + ")---->" + (1 - result));
				}
			}
			
			file.flush();
			file.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static long[] getLineData(String str){
		if(str != null){
			String temp = str.split("---unionId_1-->")[0];
			String temp1 = str.split("---unionId_1-->")[1];
			
			long l1 = Long.parseLong(temp.split("---unionId_0-->")[1]);
			long l2 = Long.parseLong(temp1.split("----other--->")[0]); 
			return new long[]{l1,l2};
		}
		return null;
	} 
	
	public static void testTransaction(){
		Connection conn = null;
		Statement stmt = null;
		Statement stmt1 = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://172.16.11.207:3306/b5m_you?user=b5m&password=izene123");
			//conn.setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);
			
			conn.setAutoCommit(false);
			stmt = conn.createStatement();
			stmt1 = conn.createStatement();
			
			stmt.execute("insert into test03 values (1,1,'test','apple','test','apple','test','apple','1')");
			stmt1.execute("insert into test03 values (2,2,'test','apple','test','apple','test','apple','2')");
			
			//stmt.execute("update test03 t set t.keyword_name = 'test01' where t.id=1");
			/*ResultSet set = stmt.executeQuery("select code from union_resource;");
			while(set.next()){
				System.out.println(set.getString(1));
			}*/
			
			//TYPE1
			//conn.commit();
			
			//TYPE2
			/*conn.rollback();
			conn.commit();
			conn.setAutoCommit(true);*/
			
			//TYPE3
			conn.setAutoCommit(true);
		} catch (Exception e) {
			/*if (conn != null) {
				try {
					conn.rollback();
					conn.setAutoCommit(true);
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}*/
			e.printStackTrace();
		} finally {
			if (stmt != null && stmt1 != null) {
				try {
					stmt.close();
					stmt1.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
	}

	public static void main(String[] args) {
		testTransaction();
	}
}
