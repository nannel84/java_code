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

//회원 조작 (변경,삭)
	public static int operationMember(MemberVO member) {
		int rowcnt = 0;
		
		MemberDAO.updateMember(member);
		
		MemberDAO.deleteMember(member);
		
		
		return rowcnt;
}


}