package main;

import java.sql.*;
import java.util.Scanner;

import db.DBconnect;

class PayMasterList{
	int cnt;				//순서
	String p_id;			//아원번호
	String p_name;			//서명
	String p_jumin1;		//주민번호1
	String p_jumin2;		//주민번호2
	String p_department;	//소속
	String p_grade;			//봉급
	int p_ho;				//호봉
	String p_status;		//재직상태
	String p_date_in;		//입사일자
	String p_date_out;		//퇴사일자
	
	void printPayMaster() {
		System.out.printf("%3d  %s %s %s %s  %s  %s  %d  %4s  %s   %s \n", 
				cnt, p_id, p_name, p_jumin1, p_jumin2, p_department, p_grade, p_ho, p_status, p_date_in, p_date_out);
	}
}
public class Pay_Master_List {
	public static void main(String[] args) {
		DBconnect d=new DBconnect();
		
		Connection conn = d.getConnection();
		PreparedStatement pstmt = null;
		String sql;
		
		
		Scanner input = new Scanner(System.in);
		try {
			while(true) {
				System.out.println("인사마스터를 출력합니다. 전체:1 종료:9 사번입력 소속코드입력");
				int in_order = input.nextInt();
				sql = "select id, name, jumin1, jumin2, department, grade, ho , status "
						+" ,to_char(date_in, 'yyyy-mm-dd') date_in " 
						+" ,to_char(date_out,'yyyy-mm-dd') date_out "
						+" from hr_master";
				if (in_order == 9) {
//					Pay_MainMenu.main(args);
					System.exit(0);
				} else if(in_order == 1){
					sql = sql + " order by id";
				} else if(in_order > 1000 && in_order < 9999) {
					sql = sql + " where department=" + in_order;
				} else if(in_order > 170000 && in_order < 999999) {
					sql = sql + " where id=" + in_order;
				}

				pstmt = conn.prepareStatement(sql);
				ResultSet rs = pstmt.executeQuery();

				PayMasterList p = new PayMasterList();
				int i_cnt = 0;
				
				System.out.println("==================================인사 마스터==================================");
				System.out.println("번호 사원번호  성명    주민등록번호      소속   직급 호봉   상태   입사일자       퇴직일자");
				System.out.println("=============================================================================");

				while(rs.next()) {
					p.p_id = rs.getString("id");
					p.p_name = rs.getString("name");
					p.p_jumin1 = rs.getString("jumin1");
					p.p_jumin2 = rs.getString("jumin2");
					p.p_department = rs.getString("department");
					p.p_grade = rs.getString("grade");
					p.p_ho = rs.getInt("ho");
					p.p_status = rs.getString("status");
					p.p_date_in = rs.getString("date_in");
					p.p_date_out = rs.getString("date_out");
					p.cnt = ++i_cnt;
					p.printPayMaster();
				}
				System.out.println("=============================================================================");
//				rs.close();
//				pstmt.close();
//				conn.close();
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
	}

}
