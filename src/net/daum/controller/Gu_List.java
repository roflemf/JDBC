package net.daum.controller;

import java.util.ArrayList;
import java.util.List;

import net.daum.dao.GuDAOImpl;
import net.daum.vo.GuVO;

public class Gu_List {
	public static void main(String[] args) {
		
		GuDAOImpl gd = new GuDAOImpl();
		
		List<GuVO> list = new ArrayList<>();
		list = gd.getGuList();
		
		if(list==null) {
			System.out.println("값이 없습니다.");
			return; //null값일시 종류
			}
		}
		
//		for(int i=0; i<list.size(); i++) {
//			GuVO g = list.get(i);
//			
//			System.out.println("글제목: " + g.getGtitle());
//			System.out.println("글내용: " + g.getGcont());
//			System.out.println("==============");
		
		
		
	
}
}