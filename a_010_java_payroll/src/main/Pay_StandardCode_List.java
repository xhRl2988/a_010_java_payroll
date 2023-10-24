package main;

import java.util.*;
import java.sql.*;
import db.DBconnect;

class PayStandardCodeList{
	int cnt;
	String p_std1;
	String p_std2;
	String p_name;
	int p_std_won;
	int p_std_rate1;
	int p_std_rate2;
	int p_std_rate3;
	
	void printPayStandardCode() {
		System.out.printf("%3d  %2s  %4s   %6d   %3d   %3d   %3d   %s \n",
				cnt, p_std1, p_std2, p_std_won, p_std_rate1, p_std_rate2, p_std_rate3, p_name);
	}
}
public class Pay_StandardCode_List {
	public static void main(String[] args) {
		DBconnect d=new DBconnect();
		
		Connection conn=d.getConnection();
		PreparedStatement pstmt=null;
		String sql;
		
		Scanner input = new Scanner(System.in);
		try {
			while(true) {
				System.out.println("기준정보를 출력합니다. 전체:1 종료:9 구분(2자리)");
				int in_order=input.nextInt();
				sql="select std1, std2, std_won, std_rate1, std_rate2, std_rate3, name "
						+ "from hr_standard_code";
			if(in_order==9) {
				break;
			}else if(in_order==1) {
				sql=sql+" order by std1";
			}else if(in_order >= 0 && in_order <= 99) {
				sql=sql+" where std1="+in_order;
			}
			
			pstmt = conn.prepareStatement(sql);
			ResultSet rs=pstmt.executeQuery();
			
			PayStandardCodeList p=new PayStandardCodeList();
			int i_cnt=0;
			System.out.println("=======================기준정보현황=======================");
			System.out.println("번호 구분  코드        금액  지급%1  지급%2  지급%3  코드명");
			System.out.println("=======================================================");
			
			while(rs.next()) {
				p.p_std1=rs.getString("std1");
				p.p_std2=rs.getString("std2");
				p.p_name=rs.getString("name");
				p.p_std_won=rs.getInt("std_won");
				p.p_std_rate1=rs.getInt("std_rate1");
				p.p_std_rate2=rs.getInt("std_rate2");
				p.p_std_rate3=rs.getInt("std_rate3");

				p.cnt=++i_cnt;
				p.printPayStandardCode();
			}
			System.out.println("=======================================================");
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
}
