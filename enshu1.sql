--�e�[�u���쐬
--��ƃe�[�u��
CREATE TABLE companies(
id serial primary key
,name varchar(255));

--���[�U�[�e�[�u��
CREATE TABLE users(
id serial primary key
,name varchar(255)
,company_id int
,score int);

--���[�U�[�e�[�u���ɒl���
INSERT INTO  users(name,company_id,score) VALUES('test',1,100);

INSERT INTO companies(name) VALUES('�������A')
,('�������B')
,('�������C')
('�������D');

--�e�[�u���ꗗ
SELECT * FROM users;
SELECT * FROM companies;

SELECT u.id,u.name,u.company_id,c.name,u.score
FROM users u
JOIN companies c
ON u.company_id = c.id;
