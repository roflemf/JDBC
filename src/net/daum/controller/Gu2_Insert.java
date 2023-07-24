package net.daum.controller;

import java.util.Scanner;

import net.daum.dao.Gu2DAOImpl;
import net.daum.vo.Gu2VO;

public class Gu2_Insert {//방명록 저장
	public static void main(String[] args) {
		
		Scanner scan = new Scanner(System.in);
		Gu2DAOImpl gdao = new Gu2DAOImpl();
		Gu2VO g = new Gu2VO();
		
		System.out.println("tbl_gu2 테이블에 레코드저장하기");
		System.out.print("글쓴이 입력 >> ");
		String gname = scan.nextLine();//문자열로 입력받음
		System.out.print("글제목 입력 >> ");
		String gtitle = scan.nextLine();
		System.out.print("글내용 입력 >> ");
		String gcont = scan.nextLine();
		
		g.setGname(gname); g.setGtitle(gtitle); g.setGcont(gcont);
		
		int re = gdao.insertGu(g);
		
		if(re ==1 ) {	System.out.println(" 방명록 저장 성공 ");}
		else {System.out.println(" 방명록 저장 실패 ");}
		// 문제) 방명록 저장되게 만들어보고 에러가 발생하면 디버깅을 해본다.
		
	}

}
