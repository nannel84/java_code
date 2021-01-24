package View;

import java.util.ArrayList;

import VO.MemberVO;

//전체회원 목록 화면 출력
public class MemberView {
	
	public void printAll(ArrayList<MemberVO> memberList) {
		for(int i=0;i< memberList.size(); i++ ) {
			System.out.println(memberList.get(i));
		}
	}
	
}
