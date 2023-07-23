package net.daum.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import net.daum.vo.GuVO;

public class GuDAOImpl {
	
	String driver = "oracle.jdbc.OracleDriver"; 
	String url = "jdbc:oracle:thin:@localhost:1521:xe";
	String user = "night";
	String password = "123456";
	
	Connection con = null;
	PreparedStatement pt = null;
	ResultSet rs= null;
	String sql = null;
	
	public List<GuVO> getGuList(){
		List<GuVO> blist = new ArrayList<>();
		
		try {
			Class.forName(driver);
			sql = "select * from tbl_gu order by gno desc";
			con=DriverManager.getConnection(url, user, password);
			pt = con.prepareStatement(sql); 
			rs = pt.executeQuery();
			
			while(rs.next()) {
				GuVO vo = new GuVO();
				
				vo.setGname(rs.getString("gname"));
				vo.setGno(rs.getInt("gno"));
				vo.setGdate(rs.getString("gdate"));
				vo.setGcont(rs.getString("gcont"));
				vo.setGtitle(rs.getString("gtitle"));
				
				blist.add(vo);
				
				//System.out.println(vo.getGname());
			}
			
			return blist;
		}catch(Exception e) {e.printStackTrace();}
		finally {
			try {
				if(rs != null) rs.close();
				if(pt !=null) pt.close();
				if(con != null) con.close();
			}catch(Exception e){e.printStackTrace();}
		}		
		
		return null;
	}

	public int insertGu(GuVO g){
		//Oracle 데이터베이스에서는 insert 성공시 int 1 반환, 실패시 int -1 반환
		int result=-1;
		
		try {
			Class.forName(driver); //oracle.jdbc.OracleDriver를 로드한다. (import와 유사)
			con = DriverManager.getConnection(url, user, password);
			sql = "insert into tbl_gu values (gno_seq10.nextval, ?, ?, ?, sysdate)";
			pt = con.prepareStatement(sql);
			pt.setString(1, g.getGname());
			pt.setString(2, g.getGtitle());
			pt.setString(3, g.getGcont());
			//pt.setInt(4, g.getGno());
			
			result = pt.executeUpdate(); //executeQuery는 리턴이 RusultSet 자료형
										//executeUpdate는 리터이 Int 형. 성공시 1 실패시 -1
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				con.close();
				pt.close();
			}catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		return result;
	}
}
