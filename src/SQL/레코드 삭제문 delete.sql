select * from dept01;

--문제) dept01테이블의 40번 부서번호만 삭제해 보자
delete from dept01 where deptno=40;

select * from emp27;

--서브쿼리문을 사용한 레코드 삭제
delete from emp27 where deptno = (select deptno from dept01 where dname = '경리부');
/*경리부 부서번호 10을 서브쿼리문으로 구해서 해당 부서번호에 해당하는 레코드를 emp27테이블로부터 삭제 */