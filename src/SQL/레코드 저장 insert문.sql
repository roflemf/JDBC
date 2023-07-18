--insert문 실습을 위한 emp26 테이블 생성
create table emp26(
    empno number(38) primary key --사원번호
    ,ename varchar2(30)--사원명
    ,LOC varchar2(50)--사원이 사는 지역
);

select * from emp26 order by ename asc;

--테이블명 다음에 컬럼명을 명시해서 레코드 저장
insert into emp26 (LOC, ename, empno)values('서울','홍길동',11);

--전체컬럼에 레코드 저장시 테이블명 다음에 컬럼 목록을 생략
insert into emp26 values(12,'세종대왕님','경복궁');

/*
문제)
emp26 원본테이블 구조만 복제한 emp26_copy 테이블을 생성하자,
원본테이블의 레코드는 복제 안되어있다
그러므로 insert~select 서브쿼리문 사용해서 원본 테이블의 레코드로 복제 테이블에 저장시켜보자.
*/

create table emp26_copy as select * from emp26 where 1=2; --조건식을 거짓으로 해서 테이블 구조만 복제한다.
insert into emp26_copy select * from emp26; --서브쿼리문으로 emp26_copy 복제테이블에 레코드를 저장하는 실습

select * from emp26_copy;
select * from emp26;

/*insert ALL문 실습을 위한 원본 테이블 생성 */
create table emp27(
    empno number(38) primary key --사원번호
    ,ename varchar2(50) --사원명
    ,sal number(38) --급여
    ,LOC varchar2(50) --사는 지역
    ,deptno number(38) -- 부서번호
    );
    
    insert into emp27 values(101,'강감찬',1000,'서울시 동대문구',10);
    insert into emp27 values(102,'홍길동',1500,'서울시 광진구',20);
    insert into emp27 values(103,'세종대왕',2000,'서울시 강동구',20);
    
    select * from emp27 order by empno asc;
    
    /*insert ALL문이란?
    서브쿼리문의 결과를 조건없이 여러 테이블에 동시에 저장하는 경우 사용
    ; 조건을 거짓으로 해서 사원번호,사원명,급여, 컬럼구조만 복제한 EMP28테이블 생성
    */
    create table emp28 as select empno,ename,sal from emp27 where 1=2;
    
    /* 
    조건을 거짓으로 해서 사원번호,사원명, 사는 지역 컬럼만 복제한 emp29 테이블 생성
    */
    create table emp29 as select empno,ename,LOC from emp27 where 1=2;
    
    
    /* emp27 원본 테이블의 부서번호 20에 해당하는 사원번호, 사원명, 급여, 사는지역을 검색해서 
    emp28 테이블에서는 사원번호,사원명,급여 만 저장하고, emp29테이블에서는 사원번호, 사원명, 사는지역을 동시에 저장하는 insert all문이다.
    */
    insert ALL
    into emp28 values(empno,ename,sal)
    into emp29 values(empno,ename,LOC)
    select empno,ename,sal,LOC from emp27 where deptno=20;
    
    select * from emp28 order by empno;
    select * from emp29 order by empno asc;
    
    /* insert all when 조건식 then문 특징)
        복수개의 테이블에 다중행 레코드 저장할 때 when조건식에 맞는 자료만 저장시킨다
    */
    --조건식을 거짓으로 해서 emp27원본 테이블의 empno,ename,deptno 컬럼만 복제한 emp30 테이블 생성
    create table emp30 as select empno, ename, deptno from emp27 where 1=2;
    
    select * from emp30;
    
    --where 조건식을 거짓으로 해서 emp27 원본 테이블의 empno,ename,sal컬럼 구조만 복제한 emp31테이블을 생성
    create table emp31 as select empno,ename,sal from emp27 where 1=2;

    select * from emp31;
    select empno,ename,sal,deptno from emp27;
    
    insert all
    when sal >= 1000 then --급여가 1000이상인 경우 다중행 레코드 저장
    into emp31 values(empno,ename,sal)
    
    when deptno=20 then--부서번호가 20인 경우 다중행 레코드 저장
    into emp30 values(empno,ename,deptno)
    select empno,ename,sal,deptno from emp27;
    
    
    select * from emp30 order by empno asc;
    select * from emp31 order by empno;