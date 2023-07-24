

create table tbl_gu(
            gno int primary key,
            gname varchar(20) not null,
            gtitle varchar(20) not null,
            gcont varchar(200) not null,
            gdate date default sysdate);
            
    insert into tbl_gu VALUES ( gno_seq10.nextval, '강감찬', '제목123', '내용123', sysdate);
    commit;
    select * from tbl_gu;
    
    create sequence gno_seq10
    start with 1
    increment by 1
    nocache;
    
    select gno_seq10.nextval from dual;