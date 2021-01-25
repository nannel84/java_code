package View;

import java.util.ArrayList;
import java.util.Scanner;
import DAO.MemberDAO;
import Service.MemberService;
import VO.MemberVO;

//전체회원 목록 화면 출력
public class MemberView {
	
	public void printAll(ArrayList<MemberVO> memberList) {
		for(int i=0;i< memberList.size(); i++ ) {
			System.out.println(memberList.get(i));
		}
	}
//회원 추가 화면 출력
	public static void insertMember() {
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
		
		
		MemberDAO.insertMember(member);
		

	
		//scan.close();
	}

//회원 수정하기 화면출력	
	public static void updateMember(int updateMember) {
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

		MemberDAO.updateMember(member);

	}
//회원 삭제하기 화면출력
		public static void deleteMember(int deleteMember) {

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
			
			MemberDAO.deleteMember(member);
		
		}

		
}	
	

