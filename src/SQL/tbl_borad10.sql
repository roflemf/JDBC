--tbl_board10 테이블 생성
create table tbl_board10(
 bno number(38) primary key --게시판 번호
 ,bname varchar2(50) not null --글쓴이
 ,btitle varchar2(200) not null --글제목
 ,bcont varchar2(4000) not null --글내용
 ,bhit number(38) default 0 --default 0 제약조건을 설정하면 해당 컬럼에 굳이 레코드를 저장하지 않아도 기본값 0이 저장됌.
 ,bdate date --글쓴 등록날짜
 );
 
 select * from tbl_board10 order by bno desc; --bno,즉 번호를 기준으로 내림차순 정렬해서 큰 숫자 번호값부터 먼저 정렬
 
 --bno_seq10 번호 발생기 시퀀스 생성
 create sequence bno_seq10
 start with 1 --1부터 시작
 increment by 1 --1씩 증가
 nocache;--임시 메모리를 사용하지 않겠다는 의미
 
 --시퀀스 번호값 확인
 select bno_seq10.nextval from dual;
 