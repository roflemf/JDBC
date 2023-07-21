import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;

/* 자바에서 오라클에 정의된 저장 프로시저를 호출해서 실행하는 예제 */
public class Board_Pro {
	public static void main(String[] args) {
		
		String driver = "oracle.jdbc.OracleDriver"; //oracle.jdbc는 패키지명, OracleDriver는 jdbc드라이버 클래스명
		String url = "jdbc:oracle:thin:@localhost:1521:xe"; //오라클 접속주소,localhost는 모든 내자신 컴퓨터 호스트 이름,1521은 오라클 연결 포트번호,
															//xe는 데이터베이스명
		String user = "night";//오라클 사용자 이름
		String password = "123456"; //오라클 사용자 비번
		
		Connection con = null;//데이터 베이스 연결 con
		PreparedStatement pt = null; //쿼리문을 수행할 pt
		ResultSet rs = null; //검색 결과 레코드를 저장할 rs
		CallableStatement cs = null; //저장프로시저를 실행할 cs
		String sql = null; //sql문 저장
		
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, user, password);
			Scanner scan = new Scanner(System.in);
			System.out.print("게시판 번호 입력 >> ");
			int bno = Integer.parseInt(scan.nextLine());
			sql = "select * from tbl_board10 where bno = ?"; //번호값 검색
			pt = con.prepareStatement(sql); //쿼리문을 미리 컴파일 하여 수행할 pt 생성
			pt.setInt(1, bno);//쿼리문의 첫번쨰 물음표에 정수 숫자로 번호값 저장
			rs=pt.executeQuery(); //select문 수행후 결과 레코드를 rs에 저장
			if(rs.next()) {//한개 행 레코드가 검색 될 때는 if문으로 처리, next()는 다음 레코드행이 존재하면 참
				//검색된 번호(레코드값)가 있을 때 실행
				sql = "{call sel_board10(?,?,?,?)}";//sel_board 저장 프로시저 호출문
				cs = con.prepareCall(sql); //저장 프로시저를 실행할 cs생성
				cs.setInt(1, bno);//첫번쨰 물음표에 정수 숫자로 번호값 저장
				
				cs.registerOutParameter(2, java.sql.Types.VARCHAR); // 두번째 물음표에 해당하는 컬럼으로부터 레코드값을 얻어오기 위해 
																	// 가변문자 타입(varchar)으로 미리 타입을 지정
				cs.registerOutParameter(3, java.sql.Types.VARCHAR);
				cs.registerOutParameter(4, java.sql.Types.VARCHAR);
				
				cs.execute();// 저장 프로시저 실행
				
				System.out.println("번호 \t 글쓴이 \t 제목 \t 글내용");
				System.out.println(bno+"\t" + cs.getString(2)+"\t"+cs.getNString(3)+"\t"+cs.getString(4));
				
				
			}else {
				System.out.println("검색된 번호값이 없어서 저장 프로시저 실행 불가");
			}
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			try {
				if(rs != null) rs.close();
				if(pt != null) pt.close();
				if(cs != null) cs.close();
				if(con != null) con.close();
				
			}catch (Exception e) {e.printStackTrace();}
		}//finally
		
		
		
	}

}
