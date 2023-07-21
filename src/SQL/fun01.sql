--SEL_BOARD10 이라는 저장 프로시저 생성
create or replace procedure sel_board10 --or replace 옵션은 기존의 저장 프로시저가 존재할 경우 지우고 재생성 하라는 것
(
 vno in tbl_board10.bno%type, --vno 변수타입은 tbl_board10테이블의 bno컬럼타입을 따라간다. 
                              --결국 정수 숫자 타입 (number(38))in값을  입력받아서 전달할 때 사용
 vname out tbl_board10.bname%type,--out는 btitle컬럼 레코드값을 되돌려 받을 때 사용
 vtitle out tbl_board10.btitle%type,
 vcont out tbl_board10.bcont%type
)

is 
begin --begin ~ end 사이에 실제 실행할 sql문이 들어감
 select bname, btitle, bcont into vname,vtitle,vcont from tbl_board10 where bno = vno;--SQL문
end; --begin end는 SQL문이 아니고 PL-SQL문이다.
/