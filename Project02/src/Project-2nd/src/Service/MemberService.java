package Service;

import java.util.ArrayList;

import DAO.MemberDAO;
import VO.MemberVO;

public class MemberService {
	
	
//회원 조회
	public ArrayList<MemberVO> selectAll(){
			
		ArrayList<MemberVO> memberList = new ArrayList<MemberVO>();
		MemberDAO memberDao = new MemberDAO();
		memberList = memberDao.getList();
			
		return memberList;
		}

//회원 수정
	public static int operationMember(MemberVO member) {
		int rowcnt = 0;
		
		MemberDAO.updateMember(member);
		
		MemberDAO.deleteMember(member);
		
		MemberDAO.insertMember(member);
		
		
		return rowcnt;
}

// 회원 추가
//	public  int insertMember(MemberVO member) {
//		int rowcnt = 0;
//	    
//	    return rowcnt;
//	}  
//// 회원 삭제
//	public static int deleteMember(MemberVO member) {
//		int rowcnt = 0;
//		    
//		return rowcnt;
//	}  

}