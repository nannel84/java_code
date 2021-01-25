package DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import VO.MemberVO;

public class MemberDAO {

//1. 중복코드 묶어서 관리 	
//1.1 드라이버로딩 및 연결 생성 

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
//1.2 select 작업 수행후 close
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
//1.3 insert,update,delete 작업 수행후 close
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
//2. 전체 회원조회(select)
	public ArrayList<MemberVO> getList(){
		
		Connection con 			= null; 
		PreparedStatement pstmt = null;
		ResultSet rs 			= null; 
		
		ArrayList<MemberVO> memberList = new ArrayList<MemberVO>();
	
		try {
			//드라이버 로딩 / 데이터베이스연결 (Connection)
			con = getConnection();
			
			//트럭 생성(Statement)
			//SQL 생성 -> SELECT   
			//SQL : member테이블에 있는   name(이름), phone(전화번호), address(주소), relation(종류) 컬럼의 데이터 조회
			StringBuilder sql = new StringBuilder();
			sql.append("select name				");
		    sql.append("     , phone			");
		    sql.append("     , address			");
		    sql.append("     , relation			");
		    sql.append("  from member 			");  
		    
		    
			pstmt = con.prepareStatement(sql.toString());
			//결과받기 (ResultSet)
			rs = pstmt.executeQuery();
			
				System.out.println("<회원정보>");
		 //반복 실행 : 각 데이터들을 받아 리스트에 추가시킨다	
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
		return memberList;  //멤버리스트에 데이터 값들이 있음
	}
//-----------------------------------------------------------------------------------------------------------	
//3.전체 회원의 수  구하기
	public static void selectAllCount(MemberVO member){
		
		Connection con 			= null;
		PreparedStatement pstmt = null;
		ResultSet rs 			= null;
		
		//SQL : 멤버테이블에 있는 전체 행의 수 조회 (행은 1명당 정보이므로 전체행은 전체 회원의 수를 뜻함)
		StringBuilder sql = new StringBuilder(); // 명령을 저장하는 문자열
		sql.append("select count(*)			");
		sql.append("  from member			");
		try {
			con = getConnection();
			
			pstmt = con.prepareStatement(sql.toString());
			rs = pstmt.executeQuery();
			
			// 총 회원의 수 값을 얻음
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
//4.특정 회원 검색(조회) 메소드 - 반복 사용
	public static void selectByName(MemberVO member) {
		
		Connection con 			= null;
		PreparedStatement pstmt = null;
		ResultSet rs 			= null;
		//이름으로 검색할 select
		//SQL  : 조건검색(이름을 통한 검색)으로 member테이블에 해당조건이 있는 데이터를 조회 
		//       동명이인이 있을수 있으므로 rownum컬럼을 사용하여 각각의 번호를 지정하여 구별함
		StringBuilder sql = new StringBuilder(); 
		sql.append("select rownum			");
		sql.append("     , name				");
		sql.append("     , phone			");
		sql.append("     , address			");
		sql.append("     , relation			");
		sql.append("  from member			");
		sql.append(" where name = ?			"); //이름으로 조회할 값 
		try {
			con = getConnection();
			
			pstmt = con.prepareStatement(sql.toString());
			pstmt.setString(1, member.getName()); // 1번째 ? 값
			rs = pstmt.executeQuery(); //select문이므로 executeQuery()를 사용
			
			System.out.println("<회원정보>");
			
			//조회 완료한 데이터를 목록으로 보기 편하게 출력
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
//5.특정 회원의 수  구하기
	public static void selectCount(MemberVO member){
				
		Connection con 			= null;
		PreparedStatement pstmt = null;
		ResultSet rs 			= null;

		//이름으로 회원검색
		//SQL : 조건절에 이름을 줘서 member테이블에 조건(이름이 같은)에 맞는 데이터를 조회하고 그 수를 구함
		StringBuilder sql = new StringBuilder(); // 명령을 저장하는 문자열
		sql.append("select count(rownum)	");
		sql.append("  from member			");
		sql.append(" where name = ?			");
		try {
			con = getConnection();
					
			pstmt = con.prepareStatement(sql.toString());
			pstmt.setString(1, member.getName());  // 1번째 ? 값
			rs = pstmt.executeQuery();   //select문이므로 executeQuery()를 사용
					
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
//6.회원추가(Insert)
	public static void insertMember(MemberVO member) {
		
		Connection con 			= null;
		PreparedStatement pstmt = null;
		
		//데이터 추가 : 회원 추가하기
		//SQL : 이름,전화번호,주소,종류의 정보를 하나의 행에 넣어 테이블에 저장
		StringBuilder sql = new StringBuilder();
		sql.append("insert into member(name, phone, address, relation)	");
		sql.append("            values(?,?,?,?)							");	
		

		try {
			con = getConnection();
			
			
			pstmt = con.prepareStatement(sql.toString());
			
			//?가 4개이므로 각각의 값세팅
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
		
		
	}
	
//----------------------------------------------------------------------------------	
//7.회원수정	(update)
	
	public static void updateMember(MemberVO member) {
		 
		Connection con 			= null; // DB 연결 처리하는 클래스
		PreparedStatement pstmt = null;
			
		try {
			con = getConnection();
			
			// 회원수정할때 이름으로 검색된 데이터에 번호가 부여되고 수정하고 싶은 번호를 눌러 수정하기때문에
			// 조건에 이름으로 검색하고  검색된 데이터 중 선택한 번호의 데이터에 정보를 수정함
			//SQL : 회원 데이터를 수정할때 
			StringBuilder sql = new StringBuilder();
			sql.append("update member 							 ");
			sql.append("   set name = ?	 						 ");  //수정할 이름 입력 (새로운데이터)
			sql.append("     , phone = ? 						 ");  //수정할 전화번호 입력	(새로운데이터)
			sql.append("     , address = ? 					 	 ");  //수정할 주소 입력 (새로운데이터)
			sql.append("     , relation = ? 				 	 ");  //수정할 종류 입력	 (새로운데이터)
			sql.append(" where phone = (select phone 		     ");  //동명이인이라도 전화번호가 같을수는 없으니 전화번호 구분
			sql.append("        from (select * 					 ");  	
			sql.append("        from (select rownum as num, p.*	 ");
			sql.append("        from (select * from member		 ");
			sql.append("       where name = ?)p)				 ");  //조건 :수정하고자 하는 이름 (기존데이터)
			sql.append("       where num =?	))					 ");  //조건 :해당번째 데이터  (순번)
			
			pstmt = con.prepareStatement(sql.toString());
			//값세팅 6개의 ?값
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
			//where절 조건 (String타입)
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
	}
		
//----------------------------------------------------------------------------------	
//8.회원삭제	
	public static void deleteMember(MemberVO member) {
		
		Connection con 			= null; 
		PreparedStatement pstmt = null;
		
		try {
			con = getConnection();
			
			//삭제할 데이터를 이름으로 일단 조회하고  지우고자 하는 데이터를 선택하여 삭제
			//SQL : 삭제할 이름을 검색하고  동명이인이 있을경우 번호로 구별하고 지우고자하는 번호의 데이터를 삭제
		
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
//		int rowcnt=0;
//		return rowcnt;
//		
	}
	
}	
	

