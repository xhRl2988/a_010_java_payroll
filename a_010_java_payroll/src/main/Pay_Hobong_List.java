package main;

import java.sql.*;
import java.util.*;
import db.DBconnect;

class PayHobongList{
	int cnt;
	String id;
	String name;
	String jumin1;
	String jumin2;
	String grade;
	int ho_won;
	// pay_ho*12 연봉
	String status;
	String date_in;
	String date_out;
	String department;
	String std_name;
	
	void printPayHobong() {
	    System.out.printf("%3d  %s  %s  %s %s  %s  %d  %s  %s  %s  %10s  %s %s \n",
	            cnt, id, name, jumin1, jumin2, grade, ho_won, ho_won*12, status, date_in, date_out, department, std_name);
	}
	
}
public class Pay_Hobong_List {
	public static void main(String[] args) {
		DBconnect d=new DBconnect();
		
		Connection conn=d.getConnection();
		PreparedStatement pstmt=null;
		String sql;
		
		Scanner input=new Scanner(System.in);
		try {
			while(true) {
				System.out.println("급여마스터를 출력합니다. 연봉순:1 소속순:2 입사일기준:3 종료:9 사번입력 소속코드입력");
				int in_order=input.nextInt();
				sql="select a.id, a.name, a.jumin1, a.jumin2, "
						+ " (select b.name from hr_standard_code b where a.grade = b.std2 and b.std1 = '21') grade, "
						+ " (select c.ho_won from hr_hobong_code c where a.ho = c.ho and a.grade = c.grade) ho_won, "
						+ " (select c.ho_won*12 from hr_hobong_code c where a.ho = c.ho and a.grade = c.grade) ho_year, "
						+ " (select b.name from hr_standard_code b where a.status = b.std2 and b.std1 = '99') status, "
						+ " to_char(date_in,  'yyyy-mm-dd') date_in, "
						+ " coalesce(nullif(to_char(date_out, 'yyyy-mm-dd'), '2299-12-31'), ' ') date_out, "
						+ " a.department, "
						+ " (select b.name from hr_standard_code b where a.department = b.std2 and b.std1 = '11') std_name "
						+ " from hr_master a";
				
				if(in_order==9) {
					break;
				}else if(in_order==1) {
					sql=sql+" order by ho_year desc";
				}else if(in_order==2) {
					sql=sql+" order by department asc";
				}else if(in_order==3) {
					sql=sql+" order by date_in desc";
				}else if(in_order > 170000 && in_order < 999999) {
					sql = sql + " where id=" + in_order;
				}else if(in_order > 1000 && in_order < 9999) {
					sql = sql + " where department=" + in_order;
				} 
				
				pstmt = conn.prepareStatement(sql);
				ResultSet rs=pstmt.executeQuery();
				
				PayHobongList p=new PayHobongList();
				int i_cnt=0;
				System.out.println("===========================================================================================================");
				System.out.println("번호  사원번호   성명  주민등록번호        직급     월급       연봉   상태  입사일자      퇴직일자      소속  소속명   ");
				System.out.println("===========================================================================================================");
				
				while(rs.next()) {
					p.id=rs.getString("id");
					p.name=rs.getString("name");
					p.jumin1=rs.getString("jumin1");
					p.jumin2=rs.getString("jumin2");
					p.grade=rs.getString("grade");
					p.ho_won=rs.getInt("ho_won");
					p.status=rs.getString("status");
					p.date_in=rs.getString("date_in");
					p.date_out=rs.getString("date_out");
					p.department=rs.getString("department");
					p.std_name=rs.getString("std_name");
					p.cnt=++i_cnt;
					p.printPayHobong();
				}
				System.out.println("===========================================================================================================");
				}
			}catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
