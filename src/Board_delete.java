import java.util.Scanner;

import net.daum.dao.BoardDAOImpl;
import net.daum.vo.BoardVO;

/*게시판 번호값을 입력받아 오라클 db에 해당 번호값이 있으면 삭제, 없으면 유효성 검증 메세지 출력 */
public class Board_delete {
	public static void main(String[] args) {

		
		BoardDAOImpl bdao = new BoardDAOImpl();
		Scanner scan = new Scanner(System.in);
		
		System.out.println("게시물 삭제하기 연습");
		System.out.print("게시판 번호 입력 >>");
		int bno  = Integer.parseInt(scan.nextLine());//문자열로 입력받아서 정수 숫자로 변경
		
		BoardVO dbNo = bdao.getFindNo(bno); //오라클 DB로부터 번호값 검색
		if(dbNo != null) {//오라클에 해당 번호값이 있는 경우
			int re = bdao.delBoard(bno); //번호 기준으로 삭제
			
			if(re == 1) {
				System.out.println("게시물 삭제 성공");
			}
		}else {
			System.out.println("번호값이 없어서 삭제 못함");
		}
	
	}

}
