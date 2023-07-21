import java.io.BufferedReader;
import java.io.InputStreamReader;

import net.daum.dao.BoardDAOImpl;
import net.daum.vo.BoardVO;

public class Board_update {
	
	
	public static void main(String[] args) throws Exception{
	
		BoardDAOImpl bdao = new BoardDAOImpl();// 오라클 DB연동 클래스 객체 생성
		BoardVO eb = new BoardVO();//게시판 저장빈 클래스 객체 생성
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		/* 1. System.in은 키보드 입력장치와 연결
		 * 2. InputStreamReader는 입력받은 바이트를 문자로 변경
		 * 3. BufferedReader는 입력받은 문자를 버퍼링 즉 임시 메모리에 저장한 다음 한꺼번에 효율적으로 읽어들임
		 * 
		 */
		
		
		System.out.print("게시판 번호 입력 >> ");
		int bno = Integer.parseInt(br.readLine());//한줄 끝까지 문자열로 읽어들인 다음 정수숫자로 변경후 저장
		BoardVO dbNo = bdao.getFindNo(bno);//오라클로부터 번호값 검색
		
		if(dbNo == null) {
			System.out.print("번호값이 없어서 수정 불가");
			
		}else {
			System.out.print("수정할 글쓴이 입력 >> ");
			String bname = br.readLine();
			System.out.print("수정할 제목 입력 >> ");
			String btitle = br.readLine();
			System.out.print("수정할 내용 입력 >> ");
			String bcont = br.readLine();
			
			eb.setBno(bno); eb.setBname(bname); eb.setBtitle(btitle); eb.setBcont(bcont);
			
			int re = bdao.editBoard(eb); //번호를 기준으로 글쓴이,글제목,글내용 수정후 1반환
			
			if(re == 1) {System.out.println("게시판 수정 성공");}
	
		}//if else
		
	}
	
	
	
	

}
