import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class MemberManagement {
	
	
//select 
//전체회원 조회
	public static void selectAll() {
		//변수 지정
		String driver = "orcale.jdbc.driver.OracleDriver";
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
			sql.append("select e.name				");
		    sql.append("     , e.phone				");
		    sql.append("     , e.address			");
		    sql.append("     , e.relation			");
		    sql.append("  from member e				");
		    
			pstmt = con.prepareStatement(sql.toString());
			//결과받기 (ResultSet)
			rs = pstmt.executeQuery();
			//화면출력 
			while(rs.next()) {
				System.out.print("<회원정보> 이름 : " + rs.getString("name"));
				System.out.print(" , 전화번호 :  " + rs.getString("phone"));
				System.out.print(" , 주소 :  " + rs.getString("address"));
				System.out.print(" , 종류 :  " + rs.getString("relation"));
			}
		//데이터베이스 연결종료(close)		
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("처리 에러");
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
	
	
	public static void main(String[] args) {
		selectAll();
		
		
	}

}
