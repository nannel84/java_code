import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class MemberManagement {
	
	
//select 
//전체회원 조회
	public static void selectAll() {
		//변수 지정
		String driver = "oracle.jdbc.driver.OracleDriver";
		String url = "jdbc:oracle:thin:@localhost:1521:xe";
		String user = "ora_user";
		String password = "hong";
		
		Connection con 			= null; // DB 연결 처리하는 클래스
		PreparedStatement pstmt = null;
		ResultSet rs 			= null;
		
		//드라이버 로딩
		try {
			Class.forName(driver);
			//데이터베이스연결 (Connection)
			con = DriverManager.getConnection(url, user, password);
			
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
				System.out.print(" 이름 : " + rs.getString("name"));
				System.out.print(" , 전화번호 : " + rs.getString("phone"));
				System.out.print(" , 주소 : " + rs.getString("address"));
				System.out.print(" , 종류 : " + rs.getString("relation"));
				System.out.println("");
			}
		//에러시 화면 출력	
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("처리 에러");
		//데이터베이스 연결종료(close)		
		} finally {
			try {
				rs.close();
				pstmt.close();
				con.close();
			} catch (Exception e) {
				e.printStackTrace();
				System.out.println("Close Error");
			}
		}
		
		
	}
//-----------------------------------------------------------------------------------------------------------
			//전체 회원 수  구하기
			public static void selectAllCount(){
				
				String driver = "oracle.jdbc.driver.OracleDriver";
				String url = "jdbc:oracle:thin:@localhost:1521:xe";
				String user = "ora_user";
				String password = "hong";
				
				Connection con 			= null;
				PreparedStatement pstmt = null;
				ResultSet rs 			= null;
//				이름으로 검색할 select
				StringBuilder sql = new StringBuilder(); // 명령을 저장하는 문자열
				sql.append("select count(*)	");
				sql.append("  from member			");
				try {
					Class.forName(driver);
					con = DriverManager.getConnection(url,user,password);
					
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
		public static void selectByName(String name) {
			
			String driver = "oracle.jdbc.driver.OracleDriver";
			String url = "jdbc:oracle:thin:@localhost:1521:xe";
			String user = "ora_user";
			String password = "hong";
			
			Connection con 			= null;
			PreparedStatement pstmt = null;
			ResultSet rs 			= null;
//			이름으로 검색할 select
			StringBuilder sql = new StringBuilder(); // 명령을 저장하는 문자열
			sql.append("select rownum			");
			sql.append("     , name				");
			sql.append("     , phone			");
			sql.append("     , address			");
			sql.append("     , relation			");
			sql.append("  from member			");
			sql.append(" where name = ?			");
			try {
				Class.forName(driver);
				con = DriverManager.getConnection(url,user,password);
				
				pstmt = con.prepareStatement(sql.toString());
				pstmt.setString(1, name);
				rs = pstmt.executeQuery();
				
				System.out.println("<회원정보>");
				while(rs.next()) {
					System.out.print(rs.getString("rownum")+".");
					System.out.print("이름 : " + rs.getString("name"));
					System.out.print(" , 전화번호 :  " + rs.getString("phone"));
					System.out.print(" , 주소 :  " + rs.getString("address"));
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
		public static void selectCount(String name){
			
			String driver = "oracle.jdbc.driver.OracleDriver";
			String url = "jdbc:oracle:thin:@localhost:1521:xe";
			String user = "ora_user";
			String password = "hong";
			
			Connection con 			= null;
			PreparedStatement pstmt = null;
			ResultSet rs 			= null;
//			이름으로 검색할 select
			StringBuilder sql = new StringBuilder(); // 명령을 저장하는 문자열
			sql.append("select count(rownum)	");
			sql.append("  from member			");
			sql.append(" where name = ?			");
			try {
				Class.forName(driver);
				con = DriverManager.getConnection(url,user,password);
				
				pstmt = con.prepareStatement(sql.toString());
				pstmt.setString(1, name);
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
//-----------------------------------------------------------------------------------------------------------
	//회원 추가하기 insert
	public static void insertMember(String name,String phone,String address, String relation) {
		
		String driver = "oracle.jdbc.driver.OracleDriver";
		String url = "jdbc:oracle:thin:@localhost:1521:xe";
		String user = "ora_user";
		String password = "hong";
		
		Connection con 			= null;
		PreparedStatement pstmt = null;
		
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url,user,password);
			
			StringBuilder sql = new StringBuilder();
			sql.append("insert into member(name, phone, address, relation)	");
			sql.append("            values(?,?,?,?)							");	
			
			pstmt = con.prepareStatement(sql.toString());
			
			//값세팅
			//회원이름 (String타입)
			pstmt.setString(1, name);
			//회원전화번호 (String타입)
			pstmt.setString(2, phone);
			//회원주소 (String타입)
			pstmt.setString(3, address);
			//회원종류 (String타입)
			pstmt.setString(4, relation);
			
			// 반영된 ROW 개수가 반환(return)
			int rowcnt = pstmt.executeUpdate();
			if(rowcnt>0) {
				System.out.println("정상 등록되었습니다");
			}else {
				System.out.println("등록 에러입니다");
			}
			
		//에러시 화면 출력	
		} catch (Exception e) {
			e.printStackTrace();
		//데이터베이스 연결종료(close)		
		} finally {
			try {
				pstmt.close();
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		
	}
//-----------------------------------------------------------------------------------------------------------	
	//회원 수정하기 update
	public static void updateMember(String name,String phone,String address, String relation, String wherename, String num) {
		
		String driver = "oracle.jdbc.driver.OracleDriver";
		String url = "jdbc:oracle:thin:@localhost:1521:xe";
		String user = "ora_user";
		String password = "hong";
		
		Connection con 			= null; // DB 연결 처리하는 클래스
		PreparedStatement pstmt = null;
		
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url,user,password);
			
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
			pstmt.setString(1, name);
			//회원전화번호 (String타입)
			pstmt.setString(2, phone);
			//회원주소 (String타입)
			pstmt.setString(3, address);
			//회원종류 (String타입)
			pstmt.setString(4, relation);
			//where절 조건 (String타입)
			pstmt.setString(5, wherename);
			pstmt.setString(6, num);
			
			
			// 반영된 ROW 개수가 반환(return)
			int rowcnt = pstmt.executeUpdate();
			if(rowcnt>0) {
				System.out.println("정보가 수정되었습니다");
			}
			
		//에러시 화면 출력	
		} catch (Exception e) {
			e.printStackTrace();
		//데이터베이스 연결종료(close)		
		} finally {
			try {
				pstmt.close();
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		
	}
//-----------------------------------------------------------------------------------------------------------	
	//회원 삭제하기 delete
	public static void deleteMember (String wherename,String num) {
		
		String driver = "oracle.jdbc.driver.OracleDriver";
		String url = "jdbc:oracle:thin:@localhost:1521:xe";
		String user = "ora_user";
		String password = "hong";
		
		Connection con 			= null; // DB 연결 처리하는 클래스
		PreparedStatement pstmt = null;
		
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url,user,password);
			
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
			pstmt.setString(1, wherename);
			//회원전화번호 (String타입)
			pstmt.setString(2, num);
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
				pstmt.close();
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		
	}
	
	public static void main(String[] args) {
		selectAll();
		//selectAllCount();
		//insertMember("박지성","01013130707","수원시","가족");
		//updateMember("김태경", "7777", "부산시", "친구", "박지성", "1");
		//deleteMember("김밤이","1");
		//selectCount("박지성");
	}
}
