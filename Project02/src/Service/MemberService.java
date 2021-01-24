package Service;

import java.util.ArrayList;

import DAO.MemberDAO;
import VO.MemberVO;

public class MemberService {

public ArrayList<MemberVO> selectAll(){
		
	ArrayList<MemberVO> memberList = new ArrayList<MemberVO>();
	MemberDAO memberDao = new MemberDAO();
	memberList = memberDao.getList();
		
	return memberList;
	}

public int updateMember(MemberVO member) {
	int rowcnt = 0;
	MemberDAO memberDao = new MemberDAO();
	//사원수정
	memberDao.updateMember(member);
	
	return rowcnt;
}
	

}