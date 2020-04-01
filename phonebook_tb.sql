--테이블 생성
create table phonebook_tb(
    id number(10) primary key,
    name varchar2(10),
    phonenum varchar2(30),
    birth varchar2(10)
);

--시퀀스 생성
create sequence seq_phonebook
    increment by 1  /* 증가치 */
    start with 1  /* 시작값 */
    nomaxvalue    /* 최대값 */
    minvalue 1     /* 최소값 */
    nocycle           /* 사이클 사용여부 */
    nocache;        /* 캐쉬 사용여부 */
    

--데이터사전에서 생성된 시퀀스 확인
select * from user_sequences; --데이터사전

SELECT name, phonenum, birth FROM phonebook_tb where name like '%123%';

--------------------------------------------------------------------------

delete from banking_tb;
drop table banking_tb;
drop sequence seq_banking;
commit;




--테이블 생성
create table banking_tb (
    idx number(10) primary key,
    account varchar2(30),
    name varchar2(30),
    balance number(30)
);

--시퀀스 생성
create sequence seq_banking
    increment by 1  /* 증가치 */
    start with 1    /* 시작값 */
    nomaxvalue      /* 최대값 */
    minvalue 1      /* 최소값 */
    nocycle         /* 사이클 사용여부 */
    nocache;        /* 캐쉬 사용여부 */
    









