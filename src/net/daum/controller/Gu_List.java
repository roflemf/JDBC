package net.daum.controller;

import java.util.ArrayList;
import java.util.List;

import net.daum.dao.GuDAOImpl;
import net.daum.vo.GuVO;

public class Gu_List {
	public static void main(String[] args) {

		GuDAOImpl gd = new GuDAOImpl();

		List<GuVO> list = gd.getGuList();

		if(list==null) {
			System.out.println("값이 없습니다.");
			return; //null값일시 종류
		}

		//일반 for문 혹은 향상된 for문 혹은 foreach()메소드
		for(GuVO g : list) {
			System.out.println("글번호: " + g.getGno());
			System.out.println("글쓴이: " + g.getGname());
			System.out.println("글제목: " + g.getGtitle());
			System.out.println("글내용: " + g.getGcont());
			System.out.println("작성일자: " + g.getGdate());
			System.out.println("====================");
		}
	}
}



//		for(int i=0; i<list.size(); i++) {
//			GuVO g = list.get(i);
//			
//			System.out.println("글제목: " + g.getGtitle());
//			System.out.println("글내용: " + g.getGcont());
//			System.out.println("==============");





