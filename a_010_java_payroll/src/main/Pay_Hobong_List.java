package main;

import java.util.*;
import java.sql.*;
import db.DBconnect;

class PayHobongCodeList{
	int cnt;
	String p_grade;
	int p_ho;
	int p_ho_won;
	
	void printPayHobongCode() {
		System.out.printf("%3d   %2s   %2d     %7d        %7d      \n", cnt, p_grade, p_ho, p_ho_won, p_ho_won*12 );
	}
}
public class Pay_Hobong_List {
	public static void main(String[] args) {
		DBconnect d=new DBconnect();
		
		Connection conn=d.getConnection();
		PreparedStatement pstmt=null;
		String sql;
		
		Scanner input = new Scanner(System.in);
		try {
			while(true) {
				System.out.println("호봉코드를 출력합니다. 전체:1 종료:9 직급(2자리)");
				int in_order=input.nextInt();
				sql="select grade, ho, ho_won, ho_won*12 sum   from hr_hobong_code";
			if(in_order==9) {
				break;
			}else if(in_order==1) {
				sql=sql+" order by grade,ho";
			}else if(in_order >= 0 && in_order <= 99) {
				sql=sql+" where grade="+in_order;
			}
			
			pstmt = conn.prepareStatement(sql);
			ResultSet rs=pstmt.executeQuery();
			
			PayHobongCodeList p=new PayHobongCodeList();
			int i_cnt=0;
			System.out.println("=================직급별 호봉 현황==================");
			System.out.println("번호  직급   호봉    금액(월봉)          연봉          ");
			System.out.println("================================================");
			
			while(rs.next()) {
				p.p_grade=rs.getString("grade");
				p.p_ho=rs.getInt("ho");
				p.p_ho_won=rs.getInt("ho_won");
				p.cnt=++i_cnt;
				p.printPayHobongCode();
			}
			System.out.println("================================================");
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
}
