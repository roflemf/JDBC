package net.daum.controller;

import java.util.List;

import net.daum.dao.Gu2DAOImpl;
import net.daum.vo.Gu2VO;

public class Gu2_List {//방명록 목록 보기
	public static void main(String[] args) {
		
		Gu2DAOImpl gdao = new Gu2DAOImpl();
		List<Gu2VO> glist = gdao.getGuList();//목록 가져오기
		System.out.println("No \t title \t name \t cont \t date");
		System.out.println("==========================================================");
		
		if(glist != null && glist.size() > 0) {
			for(Gu2VO g : glist) {
				System.out.println(g.getGno() + "\t" + g.getGtitle() + "\t" + g.getGname() 
				+ "\t" + g.getGcont() + "\t" + g.getGdate());
			}//for
		}else {
			System.out.println("방명록 목록이 없습니다");
		}//if else
		
	}
	
	
	

}
