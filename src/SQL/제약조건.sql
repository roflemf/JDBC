--기본키인 primary key와 not null 제약조건 실습을 위한 테이블 생성
create table dept60(
    deptno number(38) primary key --부서번호
    ,dname varchar2(50) not null--부서명
    ,LOC varchar2(100)--부서지역
    );
    
    insert into dept60 values(10,'영업부','서울');
    insert into dept60 values(10,'영업부 부산사무소','부산');--deptno 컬럼에 동일 부서번호를 저장하려다가 
                                                         -- 무결성 제약조건 위배 에러가 나서 저장할 수 없다.
                                                         -- 이유는 기본키로 설정되어서 중복 부서번호를 저장 할 수 없다.
    
    insert into dept60 values(20,null,'서울');--dname컬럼이 not null제약조건으로 설정되어서 null 저장 불가
    
    /* dept60테이블의 제약조건 이름, 제약조건 유형, 제약조건이 속한 테이블명을 확인 */
    select constraint_name, constraint_type, table_name from user_constrant where table_name in('DEPT60');
    --제약조건 유형에서 C는 NOT NULL과 CHECK제약조건을 의미하고, P는 Primary key 즉 기본키 제약조건을 의미한다.
    --오라클에서 기본으로 제공하는 SYS C 숫자번호 형태의 제약조건이름이 지정된다.
    --in연산의 영문 테이블명은 반드시 영문 대문자로 해야 검색된다. 이유는 영문자 레코드는 대소문자를 구별한다. 
    --sql문은 대소문자를 구별하지 않는다.
    
    --CHECK 제약조건; 지정한 데이터만 입력될 수 있도록 제한
    
    /* not null제약조건 실습을 위한 테이블 생성*/
    create table emp101(
     empno number(38) --null제약조건
     ,ename varchar2(50) --사원명
     ,job varchar2(20) --직종
     ,deptno number(38) --부서명
     );
     
     insert into emp101 values(null,null,'영업부',20);
     select * from emp101;
     
     create table emp102(
      empno number(20) not null
      ,ename varchar2(50) not null
      ,job varchar2(50) 
      ,deptno number(38)
      );
    
    insert into emp102 values(null,null,'관리부',10);
    --not null제약조건으로 설정된 컬럼에 null저장 금지
    
    insert into emp102 values(501,'이순신','관리부',10);
    select * from emp102;
    
    /* 테이블 칼럼을 정의할 때 사용하는 unique 제약조건의 특징 
        1. 특정 칼럼에 자료가 중복되지 않게 함
        2. null저장되는 것은 허용
        3. 중복 자료에서 null은 체크하지 않는다. 즉, null은 중복을 허용하는 특징이 있다.
    
    */
    
    --unique 제약조건 실습을 위한 테이블 생성
    create table emp103(
     empno number(10) unique
     ,ename varchar2(30) not null
     ,job varchar2(30) 
     ,deptno number(38)
     );
     insert into emp103 values(502,'홍길동','인사부',40);
     insert into emp103 values(502,'이순신','영업부',30);--동일 중복 부서번호로 저장 안됌. 
                                                       --이유는 해당 컬럼 empno가 unique제약조건으로 설정되었기 때문
     select * from emp103;
     
     insert into emp103 values(null,'강감찬','관리부',50);
     insert into emp103 values(null,'신사임당','개발부',60);
    --unique키 제약조건으로 설정된 컬럼: null저장 허용, 중복도 허용
    --               ; 무결성 제약조건(빈값 X중복x)에 위배
    
    --constraint 키워드로 사용자 정의 제약조건명을 지정하여 만들어봄
    create table emp105(
        empno number(38) constraint emp105_empno_uk unique
        ,ename varchar2(50) constraint emp105_ename_nn not null
        ,job varchar2(50)
        ,deptno number(38)
        );
    /* 사용자 정의 제약조건명 지정 방법
        테이블명_컬럼명_제약조건유형
        emp105_empno_uk와 emp105_enmae_nn이 바로 사용자 정의 제약조건명이다.
    */
    --emp105테이블의 제약조건명이 생성된 테이블명, 생성된 제약조건 이름을 확인
    select table_name,constraint_name from user_constraints where table_name in('EMP105');
    
    --사원번호를 기본키로 가지는 EMP105 테이블 생성
    create table emp106(
    empno number(38) constraint emp106_empno_pk primary key
    ,ename varchar2(50) constraint emp106_ename_nn not null
    ,job varchar2(50) 
    ,deptno number(38)
    );
    
    insert into emp106 values(201,'강감찬','관리부',10);
    insert into emp106 values(201,'이순신','영업부',20);
    --중복사원번호는 저장 안됀다.
    
    select * from emp106;
      
      
    /*
    참조 무결성을 위한 외래키 제약조건 실습)
     1.기본키로 설정된 부모 즉 주인테이블 dept71부터 먼저 만든다.
     2.dept71 부모 테이블부터 먼저 자료를 저장한다.
    */
    
    create table dept71(
     deptno number(38) constraint dept71_deptno_pk primary key --부서번호
     ,dname varchar2(100) constraint dept71_dname_nn not null --부서명
     ,LOC varchar2(50)--부서지역
     );
     
     drop table dept71;
     insert into dept71 values(10,'관리부','서울');
     insert into dept71 values(20,'영업부','부산');
     insert into dept71 values(30,'개발부','판교');
     
     select * from dept71 order by deptno asc;
     
     /* 외래키(foreign key)제약조건 특징)
        1. 기본키를 포함한 dept71 테이블의 deptno컬럼을 참조하고 있다.
        2. dept71테이블의 deptno컬럼에 저장된 부서번호만 외래키로 설정된 컬럼 자료값으로 저장된다. 이것이 곧 참조 무결성이다.
           이것이 가능한 것은 주종관계이기 때문이다.
     */
     
     --외래키가 포함된 종속테이블 emp71을 생성
     create table emp71(
      empno number(38)constraint emp71_empno_pk primary key--사원번호
      ,ename varchar2(50)constraint emp71_ename_ename_nn not null--사원명
      ,job varchar2(50)--직종
      ,deptno number(38) constraint emp71_deptno_fk references dept71(deptno)
      );
      
      insert into emp71 values(11,'홍길동','관리사원',10);
      insert into emp71 values(12,'이순신','영업사원',20);
      
      select * from emp71 order by empno asc;
      
      insert into emp71 values(13,'강감찬','기획사원',50);
      /* 무조건 제약조건 위배 에러가 발생하여 레코드가 저장 안된다. 
      이유는 부모 테이블 dept71의 기본키 컬럼인 deptno에 부서번호가 없기 때문
      */
      
      select table_name, constraint_type, constraint_name, r_constraint_name from user_constraints
      where table_name in('DEPT71','EMP71');
      
      
      /* 1. table_name 컬럼: 제약조건명이 지정된 테이블명
         2. constraint_type: 제약조건 유형
         3. constraint_name: 제약조건 이름
         4. r_constraint_name: 외래키가 어느 기본키를 참조하고 있는가에 대한 정보
     */
     
     /*문제) 주종관계인 dept71과 emp71 두 테이블의 공통 컬럼인 deptno 를 기준으로 미국 표준협회 ANSI Inner JOIN문을 만들기
     */
     
     select * from dept71 inner join emp71 on dept71.deptno=emp71.deptno;
     select * from dept71 inner join emp71 using (deptno);
     
     
     
     
     --0724
     
     --기본키가 있는 학과 테이블 생성
     create table depart71(
     deptcode varchar2(10) constraint depart71_dpetcode_pk primary key --학과 코드
     ,deptname varchar2(50) constraint depart71_deptname_nn not null --학과 이름
     );
     
     insert into depart71 values('a001','영어교육학과');
     insert into depart71 values('a002','컴퓨터공학과');
     
     select * from depart71 order by deptcode asc;
     
     --외래키가 있는 학생테이블 student71을 생성
     create table student71(
     sno number(38) constraint student71_sno_pk primary key --학번 (primary key : 중복자료가 없고 null이 없음)
     ,sname varchar2(50) constraint sutdent71_sname_nn not null --학생이름
     ,gender varchar2(10) constraint sutdent71_gender_nn not null --성별
     ,addr varchar2(300) --주소
     ,deptcode varchar2(10) constraint sutdent71_deptcode_fk references depart71(deptcode)-- 외래키 설정
     );
     
     insert into student71 values(101,'홍길동','남','서울시','a001');
     insert into student71 values(102,'이순신','남','서울시','a002');
     
     select * from student71 order by sno asc;
     
     /*문제 ) 
     inner join말고 학과 코드 공통 컬럼을 기준으로 equi JOIN 검색문을 작성 */
     select * from student71, depart71 where student71.deptcode=depart71.deptcode;
     
     
     select table_name,constraint_type, constraint_name, r_constraint_name from user_constraints --  r_은 외래키
     where table_name in('DEPART71','STUDENT71');
     
     --CHECK 제약조건 실습을 위한 테이블 생성
     create table emp73(
      empno number(38) constraint emp73_empno_pk primary key--사원번호
      ,ename varchar2(50) constraint emp73_enmae_nn not null--사원명
      ,sal number(38) constraint emp73_sal_ck check(sal between 500 and 5000) --급여, check제약조건을 주면서 급여가 500부터 5000까지만 저장되게 함
      ,gender varchar2(6) constraint emp73_gender_ck check(gender in('M','F'))--성별, in;or연산
      );
      
      
      insert into emp73 values(1101,'홍길동',8000,'M');--급여가 500에서 5000사이 범위를 벗어나 check제약조건 위배
      insert into emp73 values(1101,'홍길동',5000,'M');
      insert into emp73 values(1102,'홍길동',500,'M');
      insert into emp73 values(1103,'신사임당',3000,'C'); --gender컬럼에는 M이나 F 벗어나면 check제약조건 위배
      
      select * from emp73;
      