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

}
