--emp27테이블의 구조 레코드 모두 복제한 emp35 테이블 생성
create table emp35 as select * from emp27;
select * from emp35;

/*
문제1)
emp35 테이블의 모든 사원의 부서번호를 30으로 변경하는 수정문 작성
*/

update emp35 set deptno=30;

/*
문제2)
101번 사원의 부서번호만 20으로 변경
*/

update emp35 set deptno=20 where empno=101;

commit;