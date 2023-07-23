package net.daum.controller;

import java.util.Scanner;

import net.daum.dao.GuDAOImpl;
import net.daum.vo.GuVO;

public class Gu_Insert {
	public static void main(String[] args) {
		GuDAOImpl gd = new GuDAOImpl();
			//스캐너로 글쓴이, 글제목, 글내용 입력받아서 전달
			//일반적으로 String 3개 넘기면된다, 그러나 데이터 저장 빈 클래스를 이용해서 전달해본다.
		
		Scanner sc = new Scanner(System.in);
		System.out.print("글쓴이: ");
		String gname = sc.nextLine();
		System.out.print("글제목: ");
		String gtitle = sc.nextLine();
		System.out.print("글내용: ");
		String gcont = sc.nextLine();
	
		
		GuVO gv = new GuVO();
		gv.setGname(gname);
		gv.setGtitle(gtitle);
		gv.setGcont(gcont);
		
		//gd.insertGu(gname, gtitle, gcont); 각 String형을 넘기는 대신 데이터 저장 빈 클래스에 값을 저장 후 
		//객체 자체를 넘김
		int result = gd.insertGu(gv);	
		
		if(result==1) {
			System.out.println("Insert 완료");
		}else {
			System.out.println("Insert 실패");
		}
		}
	}



/* 문제겸 과제물
 *  1. tbl_gu 테이블 생성(SQL문을 작성)제목; tbl_gu.sql
 *     컬럼명	    자료형        크기              제약조건
 * 	   gno    int	            primary		
 * 	   gname  varchar 20        not null
 * 	   gtitle varchar 200       not null
 * 	   gcont  varchar 4000      not null
 * 	   gdate date    default   sysdate
 * 
 * 	2. net.daum.vo패키지의 GuVo.java 데이터 저장 빈 클래스를 생성후 
 * 	   tbl_gu테이블의 컬럼명과 같은 변수명 정의
 * 	      그 후 setter(), getter() 메서드를 정의
 * 
 *  3. gno_seq10 시퀀스 생성
 *     1부터 시작, 1씩 증가, 캐쉬를 사용하지 않게 함
 *     이 시퀀스는 gno컬럼 레코드값 저장 용도로 활용
 *     
 *  4. net.daum.dao 패키지에 GuDAOImpl.java 오라클 db연동클래스를 만들어서 jdbc 프로그래밍을 함
 *     Gu_Insert.java에서 스캐너로 입력한 글쓴이, 글제목, 글내용과 함께 글번호를 저장하는 
 *     public int insertGu(GuVO g){} 메서드를 GuDAOImpl.java작성
 *    
 *  5. net.daum.controller 패키지에 방명록 목록을 가져오는 Gu_List.java를 작성해서 목록을 가져오는 
 *     public List<GuVO> getGuList(){} 를 GuDAOImpl.java에 작성한다.
 *     
 *  
 */