package Controller;

import java.util.ArrayList;
import java.util.Scanner;

import DAO.MemberDAO;
import Service.MemberService;
import VO.MemberVO;
import View.MemberView;

public class MemberController {
	
//전체 회원 목록 보기	
	private static void selectAll() {
		MemberService memberService = new MemberService();
		ArrayList<MemberVO> memberList = memberService.selectAll();
		MemberView memberView = new MemberView();
		memberView.printAll(memberList);
		
	}

//전체 회원 수 조회	
	private static void slectAllcount() {
		MemberVO member = new MemberVO();
		MemberDAO.selectAllCount(member);
		
	}
	

//회원정보 수정하기	
	private static void updateMember() {
		MemberVO member = new MemberVO();
		
		int updateMember = MemberService.operationMember(member);
	    MemberView.updateMember(updateMember);
	    
	}

//회원 삭제하기
	private static void deleteMember() {
	    MemberVO member = new MemberVO();
	    
		int deleteMember = MemberService.operationMember(member);
	    MemberView.deleteMember(deleteMember);
	    
		
	}

//회원 추가하기
	private static void insertMember() {
	    //MemberVO member = new MemberVO();
	    
		//int insertMember = MemberService.operationMember(member);
	    MemberView.insertMember();
	    	   
		
		
		
//		MemberVO member = new MemberVO();
//	    
//	    MemberView.insertMember(member);
		
	}	
	  
//메뉴	
	private int MemberMenu() {

		System.out.println("");
	    System.out.println("================================");
	    System.out.println("     다음 메뉴 중 하나를 선택하세요.");
	    System.out.println("================================");
	    System.out.println("1.회원 추가");
	    System.out.println("2.회원 목록 보기");
	    System.out.println("3.회원 정보 수정하기");
	    System.out.println("4.회원 삭제하기");
	    System.out.println("5.종료");
	    System.out.println("");
	    System.out.println("입력 -> ");
	    
	    Scanner scan = new Scanner(System.in);
	    String menu1 =scan.nextLine();
	    int menu = Integer.parseInt(menu1);  //문자열을 정수로 변환

	    return menu;
	}

//실행하기
	public static void main(String[] args) {
		
		EXIT:while(true) {
			MemberController memberCtl = new MemberController();
			int menu = memberCtl.MemberMenu();
			
			switch(menu){
			case 1:  //회원 추가
				insertMember();
				break;
			case 2:  //회원 목록 보기
				slectAllcount();
				selectAll();
				break;
			case 3:  //회원 정보 수정하기
				//selectCount();
				updateMember();
				break;
			case 4:  //회원 삭제
				deleteMember();
				break;
			case 5:  // 종료
				System.out.println("종료되었습니다.");
				break EXIT; // while문까지 종료
			default:
				System.out.println(" 입력오류 (1~5까지의 번호만 입력가능합니다.)");
				break;
			}		
		}
		
	}
}

