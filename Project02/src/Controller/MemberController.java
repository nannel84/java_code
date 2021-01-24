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
	    Scanner scan = new Scanner(System.in);
	    MemberVO member = new MemberVO();
		
	    System.out.println();
		System.out.println("수정할 회원의 이름을 입력하세요.");
		System.out.println("이름: ");
		String name1 = scan.nextLine();
		member.setName(name1);
		
		MemberDAO.selectCount(member);
		System.out.println("아래 목록 중 수정할 회원의 번호를 입력하세요.");
		MemberDAO.selectByName(member);
	    
	    String num = scan.nextLine();
	   
		System.out.println();
		System.out.println("수정할 정보를 입력하세요.");
		System.out.println("이름: ");
		String name = scan.nextLine();
		System.out.println("전화번호(ex: 01012345678): ");
		String phone = scan.nextLine();
		System.out.println("주소: ");
		String address = scan.nextLine();
		System.out.println("종류(ex:가족,친구,기타): ");
		String relation = scan.nextLine();
		
		member.setName(name);
		member.setPhone(phone);
		member.setAddress(address);
		member.setRelation(relation);
		member.setName1(name1);
		member.setNum(num);

		
		
		int result = MemberDAO.updateMember(member);
		if(result !=0) {
			System.out.println("수정이 완료되었습니다.");
		}
	}
	


//회원 삭제하기
	private static void deleteMember() {

	    Scanner scan = new Scanner(System.in);
	    MemberVO member = new MemberVO();
		
	    System.out.println();
		System.out.println("삭제할 회원의 이름을 입력하세요.");
		System.out.println("이름: ");
		String name1 = scan.nextLine();
		member.setName(name1);
		
		MemberDAO.selectCount(member);
		System.out.println("아래 목록 중 삭제할 회원의 번호를 입력하세요.");
		MemberDAO.selectByName(member);
	    
	    String num = scan.nextLine();
		
		member.setName1(name1);
		member.setNum(num);
		
		int result = MemberDAO.deleteMember(member);
		if(result !=0) {
			System.out.println("삭제가 완료되었습니다.");
		}
	}

//회원 추가하기
	private static void insertMember() {
	    Scanner scan = new Scanner(System.in);
	    MemberVO member = new MemberVO();
	    
		System.out.println();
		System.out.println("등록할 회원의 정보를 입력하세요.");
		System.out.println("이름: ");
		String name = scan.nextLine();
		System.out.println("전화번호(ex: 01012345678): ");
		String phone = scan.nextLine();
		System.out.println("주소: ");
		String address = scan.nextLine();
		System.out.println("종류(ex:가족,친구,기타): ");
		String relation = scan.nextLine();
		
		member.setName(name);
		member.setPhone(phone);
		member.setAddress(address);
		member.setRelation(relation);
		
		int result = MemberDAO.insertMember(member);
		if(result !=0) {
			System.out.println("회원이 등록되었습니다");
		}
		
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

