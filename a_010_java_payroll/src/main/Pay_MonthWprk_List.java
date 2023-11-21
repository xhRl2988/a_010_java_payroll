package main;

import java.sql.*;
import java.util.*;
import db.DBconnect;

class PayMonthWprkList{
	int cnt;
	String w_ym;
	String id;
	int month_std;
	int month_work;
	Double month_minus;
	int m13;
	int m14;
	Double not11_2;
	Double nnt11_3;
	Double hol12_1;
	Double hol12_2;
	Double hol12_3;
	
	
	
	Double total_month_minus=0.0;
	int total_m13=0;
	int total_m14=0;
	Double total_not11_2=0.0;
	Double total_nnt11_3=0.0;
	Double total_hol12_1=0.0;
	Double total_hol12_2=0.0;
	Double total_hol12_3=0.0;
	
	void printPayMonthWprk() {
	    System.out.printf("%3d  %s   %s   %d    %d     %.1f   %d   %d   %.1f   %.1f    %.1f    %.1f   %.1f \n",
	            cnt, w_ym, id, month_std, month_work, month_minus, m13, m14, not11_2, nnt11_3, hol12_1, hol12_2, hol12_3);
	}
	void printTotal() {
	    System.out.printf("                             합계    %.1f   %d  %d   %.1f   %.1f    %.1f    %.1f   %.1f \n",
	    		total_month_minus, total_m13, total_m14, total_not11_2, total_nnt11_3, total_hol12_1, total_hol12_2, total_hol12_3);
	}
	
}
public class Pay_MonthWprk_List {
	public static void main(String[] args) {
		DBconnect d=new DBconnect();
		
		Connection conn=d.getConnection();
		PreparedStatement pstmt=null;
		String sql;
		
		Scanner input=new Scanner(System.in);
		try {
			while(true) {
				System.out.println("월 근태 내역을 출력합니다. 해당년월입력(예: 202301) 종료:9");
				int in_order=input.nextInt();
				sql="select w_ym, id, month_std, month_work, "
						+ " month_minus, m13, m14, not11_2, nnt11_3, "
						+ "hol12_1, hol12_2, hol12_3 from hr_month_work";
				
				if(in_order==9) {
					Pay_MainMenu.main(args);
					break;
				}else {
					sql=sql+" where w_ym="+in_order;
				}
				
				pstmt = conn.prepareStatement(sql);
				ResultSet rs=pstmt.executeQuery();
				
				PayMonthWprkList p=new PayMonthWprkList();
				int i_cnt=0;
				System.out.println("====================================월 근태 현황====================================");
				System.out.println("번호  근태년월    사원번호   기준일  지급일   제외일  생리 월차 평일OT 평일NT  공휴지급일  공휴OT 공유NT   ");
				System.out.println("==================================================================================");

				while(rs.next()) {
					p.w_ym=rs.getString("w_ym");
					p.id=rs.getString("id");
					p.month_std=rs.getInt("month_std");
					p.month_work=rs.getInt("month_work");
					p.month_minus=rs.getDouble("month_minus");
					p.m13=rs.getInt("m13");
					p.m14=rs.getInt("m14");
					p.not11_2=rs.getDouble("not11_2");
					p.nnt11_3=rs.getDouble("nnt11_3");
					p.hol12_1=rs.getDouble("hol12_1");
					p.hol12_2=rs.getDouble("hol12_2");
					p.hol12_3=rs.getDouble("hol12_3");
					p.cnt=++i_cnt;
					
					p.total_month_minus = p.total_month_minus+p.month_minus;
					p.total_m13 = p.total_m13+p.m13;
					p.total_m14 = p.total_m14+p.m14;
					p.total_not11_2 = p.total_not11_2+p.not11_2;
					p.total_nnt11_3 = p.total_nnt11_3+p.nnt11_3;
					p.total_hol12_1 = p.total_hol12_1+p.hol12_1;
					p.total_hol12_2 = p.total_hol12_2+p.hol12_2;
					p.total_hol12_3 = p.total_hol12_3+p.hol12_3;
					p.printPayMonthWprk();
				}
				System.out.println("==================================================================================");
				p.printTotal();
//				System.out.println("                            합계     "+total_month_minus+"   "  +total_m13+"   " + total_m14+"  "+ total_not11_2+"   "+total_nnt11_3+"    "+total_hol12_1+"    "+total_hol12_2+"   "+total_hol12_3);
				System.out.println("==================================================================================");
				}                                                                                       																											
			}catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
