package main;

import java.util.*;

public class Pay_MainMenu {
	public static void main(String[] args) {
		Scanner stdIn = new Scanner(System.in);

		while (true) {
			System.out.println("\n===============================================================================");
			System.out.println("********************* 인사급여 시스템(성일정보고 2학년 12반 제공) *********************");
			System.out.println("===============================================================================");
			System.out.println("   1. 인사마스터 검색(선택 : 전체/소속별/개인별)");
			System.out.println("   2. 급여마스터 검색(선택 : 연봉순 : 1  소속순 : 2  입사일기준 : 3  종료 : 9)");
			System.out.println("                 (사번입력 or 소속코드입력");
			System.out.println("   3. 기준정보 검색");
			System.out.println("   4. 호봉정보 검색");
			System.out.println("   5. 월 근태정보 검색(선택 : 전체/소속별/개인별)");
			System.out.println("   6. 월 급여지급내역 검색(선택 : 전체/소속별/개인별)");
			System.out.println("   7. 월 급여작업");
			System.out.println("   0. 작업을 종료합니다.");
			System.out.println("===============================================================================\n");

			System.out.print("작업 번호를 선택하세요 > ");
			int num = stdIn.nextInt();

			if (num == 1) {
				Pay_Master_List.main(args);
			} else if (num == 2) {
				Pay_StandardCode_List.main(args);
			} else if (num == 3) {
				Pay_Hobong_List.main(args);
//			} else if (num == 4) {
//				
//			} else if (num == 5) {
//				
//			} else if (num == 6) {
//				
//			} else if (num == 7) {
//				
			} else if (num == 0) {
				System.out.println("\n작업을 종료합니다");
				break;
			}
		}
	}

}
