

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.Scanner;

/* tbl_board10 테이블에 레코드 저장 소스 */

public class Board_insert {
	public static void main(String[] args) {
		String driver = "oracle.jdbc.OracleDriver"; //oracle.jdbc는 패키지명, OracleDriver는 jdbc드라이버 클래스명
		String url = "jdbc:oracle:thin:@localhost:1521:xe"; //오라클 접속주소,localhost는 모든 내자신 컴퓨터 호스트 이름,1521은 오라클 연결 포트번호,
															//xe는 데이터베이스명
		String user = "night";//오라클 사용자 이름
		String password = "123456"; //오라클 사용자 비번
		
		Connection con = null;//데이터 베이스 연결 con
		Statement stmt = null; //쿼리문 수행  stmt
		String sql = null;//쿼리문 저장변수
		
		try {
			Class.forName(driver);//오라클 jdbc 드라이브 클래스 로드
			con=DriverManager.getConnection(url,user,password); //DB접속주소(오라클 연결주소), 사용자 , 비번을 
																//메서드 인자값으로 전달해서 데이터 베이스 연결 con생성
			
			Scanner scan = new Scanner(System.in);
			
			System.out.println("tbl_board10 테이블에 레코드 저장하기 >> ");
			System.out.println("글쓴이 입력 >> ");
			String bname = scan.nextLine();//문자열로 입력받음
			System.out.println("글제목 입력 >> ");
			String btitle = scan.nextLine();
			System.out.println("글 내용 입력 >> ");
			String bcont = scan.nextLine();
			
			stmt  = con.createStatement();//쿼리문 수행할 stmt 생성
			
			sql = "insert into tbl_board10 (bno,bname,btitle,bcont,bdate) values"
					+"(bno_seq10.nextval,'"+bname+"','"+btitle+"', '"+bcont+"',sysdate)";
			//sysdate는 오라클 날짜함수
			int re = stmt.executeUpdate(sql); //저장 쿼리문 수행 후 성공한 레코드 행의 개수를 반환
			
			if(re ==1) {
				System.out.println("게시판 저장 성공");
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			try {
				if(stmt != null) stmt.close();
				if(con != null) con.close();
				
			}catch(Exception e){e.printStackTrace();}
		}//finally문
	}
}
