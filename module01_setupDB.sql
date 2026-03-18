-- 일편한 세상 DB : Only_One


-- 기존 데이터베이스 삭제 (있다면)
DROP DATABASE IF EXISTS Only_One;


-- 새로운 데이터베이스 생성
CREATE DATABASE Only_One;

create user 'only'@'%' IDENTIFIED BY 'only';

-- 계정 권한 부여
-- GRANT 권한을 부여하기 위한 명령문이다.
-- ALL PRIVILEGES 'root'계정과 비슷한 수준의 권한을 준다.
-- GRANT 권한목록 ON 데이터베이스.테이블 TO '사용자명'@'호스트';
-- 데이터베이스.*에서 *은 모든 테이블을 의미한다.
GRANT ALL PRIVILEGES ON Only_One.* TO 'only'@'%';

-- WANTED_LMS 사용
USE Only_One;

-- SHOW TABLES: 현재 테이블 목록 확인.
SHOW TABLES;      