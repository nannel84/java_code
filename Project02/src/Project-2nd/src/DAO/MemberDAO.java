package DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import Controller.MemberController;
import VO.MemberVO;

public class MemberDAO {
	
//드라이버로딩 및 연결 생성
	private static Connection getConnection() {
		String url = "jdbc:oracle:thin:@localhost:1521:xe";
		String user = "ora_user";
		String password = "hong";
		Connection con =null;
		
		try {
			con = DriverManager.getConnection(url,user,password);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return con;
		
	}
//select 작업 수행후 close
	private void selectClose(Connection con, PreparedStatement pstmt, ResultSet rs) {
		try {
			if(rs != null) {
				rs.close();
			}
			if(pstmt != null) {
				pstmt.close();
			}
			if(con != null) {
				con.close();
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}
//insert,update,delete 작업 수행후 close
	private static void iudClose(Connection con, PreparedStatement pstmt) {
		try {
			if(pstmt != null) {
				pstmt.close();
			}
			if(con != null) {
				con.close();
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}
//----------------------------------------------------------------------------------	
	//회원조회(select)
	public ArrayList<MemberVO> getList(){
		
		Connection con 			= null; 
		PreparedStatement pstmt = null;
		ResultSet rs 			= null;
		
		ArrayList<MemberVO> memberList = new ArrayList<MemberVO>();
		//드라이버 로딩
		try {
			//데이터베이스연결 (Connection)
			con = getConnection();
			
			//트럭 생성(Statement)
			//SQL 생성 -> SELECT
			StringBuilder sql = new StringBuilder();
			sql.append("select m.name				");
		    sql.append("     , m.phone				");
		    sql.append("     , m.address			");
		    sql.append("     , m.relation			");
		    sql.append("  from member m				");
		    
			pstmt = con.prepareStatement(sql.toString());
			//결과받기 (ResultSet)
			rs = pstmt.executeQuery();
			//화면출력 
				System.out.println("<회원정보>");
			while(rs.next()) {
				MemberVO member = new MemberVO();

				member.setName(rs.getString("name")); 
				member.setPhone(rs.getString("phone"));
			    member.setAddress(rs.getString("address"));
				member.setRelation(rs.getString("relation"));
				
			   	memberList.add(member);

			}
		//에러시 화면 출력	
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("처리 에러");
		//데이터베이스 연결종료(close)		
		} finally {
			try {
				selectClose(con, pstmt, rs);
			} catch (Exception e) {
				e.printStackTrace();
				System.out.println("Close Error");
			}
		}
		return memberList;
	}
//-----------------------------------------------------------------------------------------------------------	
	//전체 회원 수  구하기
	public static void selectAllCount(MemberVO member){
		
		Connection con 			= null;
		PreparedStatement pstmt = null;
		ResultSet rs 			= null;
//		이름으로 검색할 select
		StringBuilder sql = new StringBuilder(); // 명령을 저장하는 문자열
		sql.append("select count(*)			");
		sql.append("  from member			");
		try {
			con = getConnection();
			
			pstmt = con.prepareStatement(sql.toString());
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				System.out.print("총 "+rs.getString(1)+" 명의 회원이 저장되어 있습니다.");
				System.out.println("");
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			try {
				rs.close();
				pstmt.close();
				con.close();
			}catch(SQLException e) {
				e.printStackTrace();
			}
		}
	}		
//-----------------------------------------------------------------------------------------------------------
	//특정 회원 이름 검색(조회) 메소드 - 반복 사용
	public static void selectByName(MemberVO member) {
		
		Connection con 			= null;
		PreparedStatement pstmt = null;
		ResultSet rs 			= null;
//		이름으로 검색할 select
		StringBuilder sql = new StringBuilder(); // 명령을 저장하는 문자열
		sql.append("select rownum			");
		sql.append("     , name				");
		sql.append("     , phone			");
		sql.append("     , address			");
		sql.append("     , relation			");
		sql.append("  from member			");
		sql.append(" where name = ?			");
		try {
			con = getConnection();
			
			pstmt = con.prepareStatement(sql.toString());
			pstmt.setString(1, member.getName());
			rs = pstmt.executeQuery();
			
			System.out.println("<회원정보>");
			
			
			while(rs.next()) {
				member.setRownum(rs.getString("rownum"));
				System.out.print(rs.getString("rownum")+".");
				member.setName(rs.getString("name")); 
				System.out.print("이름 : " + rs.getString("name"));
				member.setPhone(rs.getString("phone"));
				System.out.print(" , 전화번호 :  " + rs.getString("phone"));
			    member.setAddress(rs.getString("address"));
			    System.out.print(" , 주소 :  " + rs.getString("address"));
				member.setRelation(rs.getString("relation"));
				System.out.print(" , 종류 :  " + rs.getString("relation"));
				System.out.println("");
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			try {
				rs.close();
				pstmt.close();
				con.close();
			}catch(SQLException e) {
				e.printStackTrace();
			}
		}
	}
//-----------------------------------------------------------------------------------------------------------
//특정 회원 검색 수  구하기
	public static void selectCount(MemberVO member){
				
		Connection con 			= null;
		PreparedStatement pstmt = null;
		ResultSet rs 			= null;
//이름으로 검색할 select
		StringBuilder sql = new StringBuilder(); // 명령을 저장하는 문자열
		sql.append("select count(rownum)	");
		sql.append("  from member			");
		sql.append(" where name = ?			");
		try {
			con = getConnection();
					
			pstmt = con.prepareStatement(sql.toString());
			pstmt.setString(1, member.getName());
			rs = pstmt.executeQuery();
					
			while(rs.next()) {
				
				System.out.print("총 "+rs.getString(1)+"개의 목록이 검색되었습니다.");
				System.out.println("");
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			try {
				rs.close();
				pstmt.close();
				con.close();
			}catch(SQLException e) {
				e.printStackTrace();
			}
		}
	}		
//----------------------------------------------------------------------------------	
	//회원추가(Insert)
	public static int insertMember(MemberVO member) {
		
		Connection con 			= null;
		PreparedStatement pstmt = null;
		
		
		StringBuilder sql = new StringBuilder();
		sql.append("insert into member(name, phone, address, relation)	");
		sql.append("            values(?,?,?,?)							");	
		

		try {
			con = getConnection();
			
			
			pstmt = con.prepareStatement(sql.toString());
			
			//값세팅
			pstmt.setString(1, member.getName());
			pstmt.setString(2, member.getPhone());
			pstmt.setString(3, member.getAddress());
			pstmt.setString(4, member.getRelation());
			
			// 반영된 ROW 개수가 반환(return)
			int rowcnt = pstmt.executeUpdate();
			if(rowcnt>0) {
				System.out.println("정상 등록되었습니다");
			}
		
		//에러시 화면 출력	
		} catch (Exception e) {
			e.printStackTrace();
		//데이터베이스 연결종료(close)		
		} finally {
			try {
				iudClose(con, pstmt); // iud :insert,update,delete에 사용되는 종료
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		int rowcnt=0;
		return rowcnt;
		
	}
	
//----------------------------------------------------------------------------------	
	//회원수정	(update)
	
	public static int updateMember(MemberVO member) {
		 
		Connection con 			= null; // DB 연결 처리하는 클래스
		PreparedStatement pstmt = null;
			
		try {
			con = getConnection();
			
			StringBuilder sql = new StringBuilder();
			sql.append("update member 							 ");
			sql.append("   set name = ?	 						 ");
			sql.append("     , phone = ? 						 ");	
			sql.append("     , address = ? 					 	 ");
			sql.append("     , relation = ? 				 	 ");	
			sql.append(" where phone = (select phone 		     ");	
			sql.append("        from (select * 					 ");	
			sql.append("        from (select rownum as num, p.*	 ");
			sql.append("        from (select * from member		 ");
			sql.append("       where name = ?)p)				 ");	
			sql.append("       where num =?	))					 ");
			
			pstmt = con.prepareStatement(sql.toString());
			//값세팅
			//회원이름 (String타입)
			pstmt.setString(1, member.getName());
			//회원전화번호 (String타입)
			pstmt.setString(2, member.getPhone());
			//회원주소 (String타입)
			pstmt.setString(3, member.getAddress());
			//회원종류 (String타입)
			pstmt.setString(4, member.getRelation());
			//where절 조건 (String타입)
			pstmt.setString(5, member.getName1());
			pstmt.setString(6, member.getNum());
			
			
			// 반영된 ROW 개수가 반환(return)
			int rowcnt = pstmt.executeUpdate();
			if(rowcnt>0) {
				System.out.println("수정이 완료되었습니다.");
			}
			
		//에러시 화면 출력	
		} catch (Exception e) {
			e.printStackTrace();
		//데이터베이스 연결종료(close)		
		} finally {
			try {
				iudClose(con, pstmt);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		int rowcnt=0;
		return rowcnt;
	}
		
//----------------------------------------------------------------------------------	
	//회원삭제	
	public static int deleteMember(MemberVO member) {
		
		Connection con 			= null; // DB 연결 처리하는 클래스
		PreparedStatement pstmt = null;
		
		try {
			con = getConnection();
			
			StringBuilder sql = new StringBuilder();
			sql.append("delete from member 						 ");
			sql.append(" where phone = (select phone 		     ");	
			sql.append("        from (select * 					 ");	
			sql.append("        from (select rownum as num, p.*	 ");
			sql.append("        from (select * from member		 ");
			sql.append("       where name = ?)p)				 ");	
			sql.append("       where num =?	))					 ");
			
			pstmt = con.prepareStatement(sql.toString());
			
			//값세팅
			//회원이름 (String타입)
			pstmt.setString(1, member.getName1());
			//회원전화번호 (String타입)
			pstmt.setString(2, member.getNum());
			//회원주소 (String타입)
			
			// 반영된 ROW 개수가 반환(return)
			int rowcnt = pstmt.executeUpdate();
			if(rowcnt>0) {
				System.out.println("회원이 삭제되었습니다.");
			}
			
		//에러시 화면 출력	
		} catch (Exception e) {
			e.printStackTrace();
		//데이터베이스 연결종료(close)		
		} finally {
			try {
				iudClose(con, pstmt);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		int rowcnt=0;
		return rowcnt;
		
	}
	
}	
	

