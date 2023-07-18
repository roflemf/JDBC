/* 단일행 서브쿼리문이란 서브쿼리문 실행 결과 레코드 행 값이 한 행으로 검색되는 경우를 말한다.

    단일행 서브쿼리문 비교연산자 종류)
    =(같다),>,>=,<,<=,<>(같지않다)

*/

--단일행 서브쿼리문 실습을 위한 테이블 생성
create table dept15(
    deptno number(38) primary key --부서번호
    ,dname varchar2(50) --부서명
    );
    
    --레코드 3개 저장
    insert into dept15 values(10,'관리부');
    insert into dept15 values(20,'개발부');
    insert into dept15 values(30,'기획부');
    
    select * from dept15 order by deptno asc;
    
    --emp15 테이블 생성
    create table emp15(
    empno number(38) primary key--사원번호
    ,ename varchar2(50) --사원명
    ,job varchar2(50) -- 직종
    ,sal number(38)--월급
    ,comm int --보너스 (int 크기 38기본으로 잡힘)
    ,deptno number(38)-- 부서번호
    );
    
    insert into emp15 values(21,'홍길동','관리사원',1500,0,10);
    insert into emp15 values(22,'이순신','자바개발자',3000,300,20);
    insert into emp15 values(23,'강감찬','c언어개발자',2800,200,20);
    insert into emp15 values(24,'세종대왕','기획사원',2000,200,30);
    insert into emp15 values(25,'신사임당','기획팀장',4000,400,30);
    
    update emp15 set sal=4000 where ename ='신사임당';
    
    commit;
    
    select * from emp15 order by empno; --asc문 생략, 사원번호를 기준으로 오름차순 정렬
    
    /*서브쿼리문으로 홍길동 사원의 부서번호를 검색해서 그 부서번호에 해당하는 부서명을 검색하는 단일행 서브쿼리문이다. */
    select dname as "부서명" from dept15 
    where deptno = (select deptno from emp15 where ename='홍길동');

    --사원들의 급여평균
    select avg(sal) as "급여평균" from emp15;
    
    --단일행 서브쿼리문으로 급여평균보다 큰 사원명과 급여를 검색
    select ename,sal from emp15 where sal>(select avg(sal) from emp15);
    
    
    /* 1. 다중행 서브쿼리는 서브 쿼리문(괄호 안)의 실행 결과 레코드 행이 하나 이상인 경우를 말한다. 
          다중행 서브쿼리문 연산자로 단일행 서브쿼리문 연산자를 사용할 수 없다.
       2. 다중행 서브쿼리문 연산자 종류
          가. in연산자 : 서브쿼리문의 실행 결과 레코드 값 중에서 하나라도 일치하면 메인 쿼리의 where 조건절이 참이 되는 연산자를 말한다.
          
    */
    
    select distinct deptno from emp15 where sal>=1200;
    
    select ename,sal,deptno from emp15 where deptno = (select distinct deptno from emp15 where sal >= 1200);
    --다중행 서브쿼리문에서는 단일행 서브쿼리문 연산자(=)는 사용할 수 없다.
    
    select ename,sal,deptno from emp15 
    where deptno in (select distinct deptno from emp15 where sal >=1200);
    --in 다중행 서버 쿼리문 연산자
    
    /*문제)
    20번 부서의 최대급여를 구해보자 */
    select max(sal) from emp15 group by deptno having deptno=20;
    
    select ename,sal,deptno from emp15 where sal > (select max(sal) from emp15 group by deptno having deptno=20);
    --20번 부서번호의 최대급여보다 큰 사원명,급여,부서번호글 검색하는 단일행 서브쿼리문.
    
    select sal from emp15 where deptno=20;
    
    select ename,sal,deptno from emp15 where sal>all(select sal from emp15 where deptno=20);
    /* 다중행 서브쿼리문 연산자 종류)
        >ALL 연산자는 20번 부서번호의 급여중에서 최대급여보다 크면 참이 되는 연산이다.
        결국 20번 부서의 최대급여보다 큰 사원명,급여,부서번호를 검색한다.
    */
    
    /* 문제)
        20번부서의 최소급여보다 큰 사원명, 급여, 부서번호를 검색하는 단일행 서브쿼리문을 만들어보자.
    */
    select ename,sal,deptno from emp15 where sal> (select min(sal) from emp15 group by deptno having deptno=20);
    
    select ename,sal,deptno from emp15 where sal>ANY(select sal from emp15 where deptno =20);
    /* >Any 다중행 서브쿼리문 연산자
      1. 20번 부서의 급여중에서 최소값보다 크면 참이 되는 연산이다. 
         즉, 20번 부서의 급여중에서 최소급여보다 큰 사원명, 급여, 부서번호를 검색한다.

    */

    --원본 테이블 구조와 레코드를 복제
    select * from emp16;
    create table emp16 as select * from emp15; --emp15 원본테이블의 테이블구조와 레코드까지 모두 복제한 emp16 테이블을 생성
    
    --emp15 원본 테이블의 empno,ename,sal,deptno 컬럼과 레코드만 보고제한 emp17테이블을 생성
    create table emp17 as select empno,ename,sal,deptno from emp15;
    select * from emp17 order by empno asc;
    --cf) primary key같은 조건은 복제 못함!
    
    create table emp18 as select * from emp15 where deptno=30; --30번 부서의 레코드행만 복제
    select * from emp18;
    
    /*
    문제)
    원본 테이블의 구조만 복제하고 레코드는 복제하지 않는 emp19 복제 테이블을 만들기
    */
    
    create table emp19 as select * from emp15 where 1=2;
    
    select * from emp19;