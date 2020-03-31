--������ ����
create sequence seq_phonebook
    increment by 1  /* ����ġ */
    start with 1  /* ���۰� */
    nomaxvalue    /* �ִ밪 */
    minvalue 1     /* �ּҰ� */
    nocycle           /* ����Ŭ ��뿩�� */
    nocache;        /* ĳ�� ��뿩�� */
    
create table phonebook_tb(
    id number(10) primary key,
    name varchar2(10),
    phonenum varchar2(30),
    birth varchar2(10)
);
    
drop table phonebook_tb;

commit;
--�����ͻ������� ������ ������ Ȯ��
select * from user_sequences; --�����ͻ���

SELECT name, phonenum, birth FROM phonebook_tb where name like '%123%';

create table banking_tb (
    idx number(10) primary key,
    account varchar2(30),
    name varchar2(30),
    balance number(30)
);


--������ ����
create sequence seq_banking
    increment by 1  /* ����ġ */
    start with 1  /* ���۰� */
    nomaxvalue    /* �ִ밪 */
    minvalue 1     /* �ּҰ� */
    nocycle           /* ����Ŭ ��뿩�� */
    nocache;        /* ĳ�� ��뿩�� */
    
commit;