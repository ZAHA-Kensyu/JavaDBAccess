--テーブル作成
--企業テーブル
CREATE TABLE companies(
id serial primary key
,name varchar(255));

--ユーザーテーブル
CREATE TABLE users(
id serial primary key
,name varchar(255)
,company_id int
,score int);

--ユーザーテーブルに値代入
INSERT INTO  users(name,company_id,score) VALUES('test',1,100);

INSERT INTO companies(name) VALUES('株式会社A')
,('株式会社B')
,('株式会社C')
('株式会社D');

--テーブル一覧
SELECT * FROM users;
SELECT * FROM companies;

SELECT u.id,u.name,u.company_id,c.name,u.score
FROM users u
JOIN companies c
ON u.company_id = c.id;
