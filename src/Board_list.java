import java.util.ArrayList;
import java.util.List;

import net.daum.dao.BoardDAOImpl;
import net.daum.vo.BoardVO;

//게시판 목록
public class Board_list {
	public static void main(String[] args) {
		
		
		BoardDAOImpl bdao = new BoardDAOImpl();
		List<BoardVO> blist = bdao.getBoardList();//게시판 목록
		

		
		System.out.println(">>>>게시판 목록 <<<<<");
		System.out.println("번호 \t 제목 \t 글쓴이 \t 글내용 \t 등록날짜");
		System.out.println("========================================================>");
		if(blist != null && blist.size() > 0) {
			for(BoardVO b:blist) {
				System.out.println(b.getBno()+"\t"+b.getBtitle()+"\t" + b.getBname() + "\t" + b.getBcont() + "\t" + b.getBdate());
				
				
				/*
				 * 이 내용 for each문으로 작성한 것
				 * 		BoardVO b = blist.get(0);
						
						b.getBname();
						b.getBno();
						System.out.println(b.getBname() + b.getBno());
				 * 
				 */
			}
		}else {
				System.out.println("게시물 목록이 없습니다!");
			}//if else
		}
	}