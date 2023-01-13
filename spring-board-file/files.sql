CREATE TABLE files(

	file_no			NUMBER			NOT NULL 	-- 파일 번호
   ,parent_table	VARCHAR2(100)	NOT NULL	-- 참조 테이블
   ,parent_no		NUMBER			NOT NULL	-- 참조 번호
   ,file_name 		VARCHAR2(200)	NOT NULL  	-- 파일 명
   ,file_path		VARCHAR2(2000)	NOT NULL	-- 파일 경로
   ,reg_date		DATE 		DEFUALT sysdate	NOT NULL  --등록일자
   ,upd_date		DATE		DEFUALT sysdate	NOT NULL  --등록일자
   ,CONSTRAINT FILE_PK PRIMARY KEY (file_no) -- 제약조건 --> PK : 파일명
   
   );