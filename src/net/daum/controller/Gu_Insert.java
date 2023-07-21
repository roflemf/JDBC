package net.daum.controller;

public class Gu_Insert {
	public static void main(String[] args) {

			
		}
	}



/* 문제겸 과제물
 *  1. tbl_gu 테이블 생성(SQL문을 작성)제목; tbl_gu.sql
 *     컬럼명	    자료형        크기              제약조건
 * 	   gno    int	            primary		
 * 	   gname  varchar 20        not null
 * 	   gtitle varchar 200       not null
 * 	   gcont  varchar 4000      not null
 * 	   gcdate date    default   sysdate
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