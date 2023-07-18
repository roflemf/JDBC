package net.daum.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import net.daum.vo.BoardVO;

public class BoardDAOImpl { //데이터 베이스 연결 클래스
	
	String driver = "oracle.jdbc.OracleDriver"; //oracle.jdbc는 패키지명, OracleDriver는 jdbc드라이버 클래스명
	String url = "jdbc:oracle:thin:@localhost:1521:xe"; //오라클 접속주소,localhost는 모든 내자신 컴퓨터 호스트 이름,1521은 오라클 연결 포트번호,
														//xe는 데이터베이스명
	String user = "night";//오라클 사용자 이름
	String password = "123456"; //오라클 사용자 비번
	
	Connection con = null;//데이터 베이스 연결 con
	PreparedStatement pt = null; //쿼리문 수행 pt
	ResultSet rs = null; //검색 결과 레코드를 저장할 rs
	String sql = null;//쿼리문 저장변수
	
	//사이에 생성자 생략
	
	//게시판 목록
	public List<BoardVO> getBoardList() {
		List<BoardVO> blist = new ArrayList<>();
		
		try {
			getClass().forName(driver);//오라클 jdbc드라이버 클래스 로드
			con = DriverManager.getConnection(url, user, password);
			sql = "select * from tbl_board10 order by bno desc";//번호를 기준으로 내림차순 정렬
			pt  = con.prepareStatement(sql); //쿼리문을 미리 컴파일 하여 수행할 pt 생성
			rs = pt.executeQuery();//select문 수행후 검색결과 레코드를 rs에 저장
			//rs = ResultSet
			
			while(rs.next()) {
				BoardVO b = new BoardVO();
				b.setBno(rs.getInt("bno")); //bno컬럼 레코드가 정수숫자이면 getInt()메서드로 가져온다.
				b.setBname(rs.getString("bname")); //bname 컬럼 레코드가 문자열이면 getString()메서드로 가져와서 setter()메서드에 저장
				b.setBtitle(rs.getString("btitle"));
				b.setBcont(rs.getString("bcont"));
				b.setBdate(rs.getNString("bdate"));
				
				blist.add(b); //컬렉션에 추가
	
			}
			
		}catch(Exception e) {e.printStackTrace();}
		finally {
			try {
				if(rs != null) rs.close();
				if(pt !=null) pt.close();
				if(con != null) con.close();
			}catch(Exception e){e.printStackTrace();}
			
		}
		return blist;
		
	}//getBoardList()

}
