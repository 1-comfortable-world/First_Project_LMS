
SET FOREIGN_KEY_CHECKS = 0;

TRUNCATE TABLE lecture_histories;
TRUNCATE TABLE payments;
TRUNCATE TABLE favorites;
TRUNCATE TABLE connection_status;
TRUNCATE TABLE blacklists;
TRUNCATE TABLE course_reviews;
TRUNCATE TABLE studying_courses;
TRUNCATE TABLE lectures;
TRUNCATE TABLE courses;
TRUNCATE TABLE students;
TRUNCATE TABLE teachers;

-- DUMMY DATA
-- =============================================
-- 1. 강사 (Teachers) - 10명
INSERT INTO `teachers` (`name`, `email`, `password`, `enrolled_at`) VALUES
('김민준', 'minjun.kim@teach.com', 'hashed_pw_001', '2022-03-05 09:00:00'),
('이서연', 'seoyeon.lee@teach.com', 'hashed_pw_002', '2022-05-12 10:30:00'),
('박지호', 'jiho.park@teach.com', 'hashed_pw_003', '2022-07-20 11:00:00'),
('최수빈', 'subin.choi@teach.com', 'hashed_pw_004', '2022-09-01 08:45:00'),
('정하은', 'haeun.jung@teach.com', 'hashed_pw_005', '2022-11-15 13:00:00'),
('강도윤', 'doyun.kang@teach.com', 'hashed_pw_006', '2023-01-10 09:30:00'),
('윤서준', 'seojun.yoon@teach.com', 'hashed_pw_007', '2023-03-22 14:00:00'),
('임지아', 'jia.lim@teach.com', 'hashed_pw_008', '2023-06-05 10:00:00'),
('한예준', 'yejun.han@teach.com', 'hashed_pw_009', '2023-08-18 11:30:00'),
('오다인', 'dain.oh@teach.com', 'hashed_pw_010', '2023-10-01 09:00:00');
-- 2. 학생 (Students) - 40명
INSERT INTO `students` (`name`, `email`, `password`, `enrolled_at`, `acc_count`) VALUES
('강나연', 'nayeon.kang@student.com', 'hashed_pw_s001', '2022-04-01 10:00:00', 0),
('김태양', 'taeyang.kim@student.com', 'hashed_pw_s002', '2022-04-15 11:00:00', 1),
('이유진', 'yujin.lee@student.com', 'hashed_pw_s003', '2022-05-01 09:30:00', 0),
('박현우', 'hyunwoo.park@student.com', 'hashed_pw_s004', '2022-05-20 14:00:00', 2),
('최지민', 'jimin.choi@student.com', 'hashed_pw_s005', '2022-06-10 13:00:00', 0),
('정소율', 'soyul.jung@student.com', 'hashed_pw_s006', '2022-06-25 10:00:00', 0),
('장민서', 'minseo.jang@student.com', 'hashed_pw_s007', '2022-07-05 09:00:00', 1),
('윤하늘', 'haneul.yoon@student.com', 'hashed_pw_s008', '2022-07-18 11:30:00', 0),
('임채원', 'chaewon.lim@student.com', 'hashed_pw_s009', '2022-08-02 10:00:00', 0),
('한지우', 'jiwoo.han@student.com', 'hashed_pw_s010', '2022-08-20 14:30:00', 3),
('오서현', 'seohyun.oh@student.com', 'hashed_pw_s011', '2022-09-05 09:00:00', 0),
('신동현', 'donghyun.shin@student.com', 'hashed_pw_s012', '2022-09-22 11:00:00', 0),
('배수아', 'sua.bae@student.com', 'hashed_pw_s013', '2022-10-10 13:30:00', 1),
('조예린', 'yerin.jo@student.com', 'hashed_pw_s014', '2022-10-28 10:00:00', 0),
('권민혁', 'minhyuk.kwon@student.com', 'hashed_pw_s015', '2022-11-12 09:30:00', 0),
('유아인', 'ain.yu@student.com', 'hashed_pw_s016', '2022-11-30 14:00:00', 2),
('김하진', 'hajin.kim@student.com', 'hashed_pw_s017', '2022-12-15 10:30:00', 0),
('이준호', 'junho.lee@student.com', 'hashed_pw_s018', '2023-01-05 11:00:00', 0),
('박소미', 'somi.park@student.com', 'hashed_pw_s019', '2023-01-20 09:00:00', 1),
('최도경', 'dokyung.choi@student.com', 'hashed_pw_s020', '2023-02-08 13:00:00', 0),
('정우찬', 'woohan.jung@student.com', 'hashed_pw_s021', '2023-02-25 10:00:00', 0),
('강리아', 'ria.kang@student.com', 'hashed_pw_s022', '2023-03-10 11:30:00', 0),
('윤지성', 'jisung.yoon@student.com', 'hashed_pw_s023', '2023-03-28 09:00:00', 4),
('임나현', 'nahyun.lim@student.com', 'hashed_pw_s024', '2023-04-12 14:00:00', 0),
('한승민', 'seungmin.han@student.com', 'hashed_pw_s025', '2023-04-30 10:30:00', 1),
('오지훈', 'jihoon.oh@student.com', 'hashed_pw_s026', '2023-05-15 09:30:00', 0),
('신예은', 'yeeun.shin@student.com', 'hashed_pw_s027', '2023-05-28 13:00:00', 0),
('배태민', 'taemin.bae@student.com', 'hashed_pw_s028', '2023-06-10 11:00:00', 2),
('조하린', 'harin.jo@student.com', 'hashed_pw_s029', '2023-06-25 10:00:00', 0),
('권서영', 'seoyoung.kwon@student.com', 'hashed_pw_s030', '2023-07-08 09:00:00', 0),
('유채현', 'chaehyun.yu@student.com', 'hashed_pw_s031', '2023-07-22 14:30:00', 1),
('김도연', 'doyeon.kim@student.com', 'hashed_pw_s032', '2023-08-05 10:00:00', 0),
('이민찬', 'minchan.lee@student.com', 'hashed_pw_s033', '2023-08-20 11:30:00', 0),
('박지율', 'jiyul.park@student.com', 'hashed_pw_s034', '2023-09-02 09:00:00', 3),
('최아영', 'ayoung.choi@student.com', 'hashed_pw_s035', '2023-09-18 13:00:00', 0),
('정현준', 'hyunjun.jung@student.com', 'hashed_pw_s036', '2023-10-05 10:30:00', 0),
('강민아', 'mina.kang@student.com', 'hashed_pw_s037', '2023-10-20 09:00:00', 1),
('윤태희', 'taehee.yoon@student.com', 'hashed_pw_s038', '2023-11-03 11:00:00', 0),
('임서진', 'seojin.lim@student.com', 'hashed_pw_s039', '2023-11-18 14:00:00', 0),
('한다은', 'daeun.han@student.com', 'hashed_pw_s040', '2023-12-01 10:00:00', 2);
-- 3. 강좌 (Courses) - 15개
-- status: 'ACTIVE', 'INACTIVE', 'DRAFT'
INSERT INTO `courses` (`title`, `teacher_id`) VALUES
('파이썬 입문부터 실전까지', 1), -- course_id: 1
('자바스크립트 완전 정복', 2), -- course_id: 2
('Spring Boot 백엔드 개발', 3), -- course_id: 3
('React로 만드는 현대 웹 앱', 4), -- course_id: 4
('데이터베이스 설계와 SQL 실습', 5), -- course_id: 5
('머신러닝 기초와 실습', 6), -- course_id: 6
('Docker & Kubernetes 입문', 7), -- course_id: 7
('iOS 앱 개발 with Swift', 8), -- course_id: 8
('알고리즘 & 코딩테스트 완성', 9), -- course_id: 9
('Git/GitHub 협업 워크플로우', 10), -- course_id: 10
('Node.js REST API 구축', 1), -- course_id: 11
('CSS/Tailwind 디자인 마스터', 2), -- course_id: 12
('클라우드 AWS 핵심 서비스', 3), -- course_id: 13
('TypeScript 심화 과정', 4), -- course_id: 14
('Linux 서버 운영 기초', 5); -- course_id: 15
-- 4. 강의 (Lectures) - 50개
-- 강좌별 배분: 총 50개, 강좌당 2~5개
-- course 1:5개, 2:4개, 3:4개, 4:3개, 5:3개,
-- 6:4개, 7:3개, 8:4개, 9:5개, 10:2개,
-- 11:3개, 12:2개, 13:3개, 14:2개, 15:3개 = 50개
INSERT INTO `lectures` (`course_id`, `title`, `order_num`, `status`) VALUES
-- course 1 (5개)
(1, '환경설정 및 파이썬 기본 문법', 1, 'ACTIVE'),
(1, '자료형과 제어문', 2, 'ACTIVE'),
(1, '함수와 모듈', 3, 'ACTIVE'),
(1, '객체지향 프로그래밍', 4, 'ACTIVE'),
(1, '파일 입출력과 예외처리', 5, 'ACTIVE'),
-- course 2 (4개)
(2, 'JS 기초 문법과 DOM', 1, 'ACTIVE'),
(2, 'ES6+ 최신 문법', 2, 'ACTIVE'),
(2, '비동기 처리 async/await', 3, 'ACTIVE'),
(2, '브라우저 API와 이벤트', 4, 'ACTIVE'),
-- course 3 (4개)
(3, 'Spring Boot 프로젝트 세팅', 1, 'ACTIVE'),
(3, 'REST API 설계와 구현', 2, 'ACTIVE'),
(3, 'JPA와 데이터베이스 연동', 3, 'ACTIVE'),
(3, '스프링 시큐리티 JWT 인증', 4, 'ACTIVE'),
-- course 4 (3개)
(4, 'React 컴포넌트와 JSX', 1, 'ACTIVE'),
(4, 'Hooks와 상태 관리', 2, 'ACTIVE'),
(4, 'React Router와 API 연동', 3, 'ACTIVE'),
-- course 5 (3개)
(5, '관계형 DB 개념과 설계', 1, 'ACTIVE'),
(5, 'SQL 기본 쿼리 실습', 2, 'ACTIVE'),
(5, '인덱스와 성능 최적화', 3, 'ACTIVE'),
-- course 6 (4개)
(6, '머신러닝 개요와 환경 세팅', 1, 'ACTIVE'),
(6, '지도학습: 분류와 회귀', 2, 'ACTIVE'),
(6, '비지도학습과 클러스터링', 3, 'ACTIVE'),
(6, '모델 평가와 하이퍼파라미터', 4, 'ACTIVE'),
-- course 7 (3개)
(7, 'Docker 기초와 이미지 관리', 1, 'ACTIVE'),
(7, 'Docker Compose 실습', 2, 'ACTIVE'),
(7, 'Kubernetes 핵심 개념', 3, 'ACTIVE'),
-- course 8 (4개)
(8, 'Swift 기초 문법', 1, 'ACTIVE'),
(8, 'UIKit 화면 구성', 2, 'ACTIVE'),
(8, 'SwiftUI 입문', 3, 'ACTIVE'),
(8, '앱 배포와 TestFlight', 4, 'ACTIVE'),
-- course 9 (5개)
(9, '시간복잡도와 Big-O', 1, 'ACTIVE'),
(9, '배열과 문자열 알고리즘', 2, 'ACTIVE'),
(9, '스택, 큐, 해시맵', 3, 'ACTIVE'),
(9, '그래프와 탐색(DFS/BFS)', 4, 'ACTIVE'),
(9, '동적 프로그래밍', 5, 'ACTIVE'),
-- course 10 (2개)
(10, 'Git 기초 명령어와 브랜치', 1, 'ACTIVE'),
(10, 'GitHub Flow와 PR 리뷰', 2, 'ACTIVE'),
-- course 11 (3개)
(11, 'Node.js 기초와 Express', 1, 'ACTIVE'),
(11, 'MongoDB 연동 및 CRUD', 2, 'ACTIVE'),
(11, '인증 미들웨어와 배포', 3, 'ACTIVE'),
-- course 12 (2개)
(12, 'CSS Flexbox & Grid', 1, 'DRAFT'),
(12, 'Tailwind CSS 실전 활용', 2, 'DRAFT'),
-- course 13 (3개)
(13, 'AWS EC2와 S3 기초', 1, 'ACTIVE'),
(13, 'RDS와 Lambda 활용', 2, 'ACTIVE'),
(13, 'CloudFront와 Route53', 3, 'ACTIVE'),
-- course 14 (2개)
(14, 'TypeScript 타입 시스템', 1, 'INACTIVE'),
(14, '제네릭과 고급 타입', 2, 'INACTIVE'),
-- course 15 (3개)
(15, 'Linux 기본 명령어', 1, 'ACTIVE'),
(15, '파일 시스템과 권한 관리', 2, 'ACTIVE'),
(15, 'Shell 스크립트 작성', 3, 'ACTIVE');
-- 5. 수강중인강좌 (Studying Courses)
-- status: 'IN_PROGRESS', 'COMPLETED', 'DROPPED'
INSERT INTO `studying_courses` (`course_id`, `student_id`, `status`) VALUES
(1, 1, 'IN_PROGRESS'),
(1, 2, 'COMPLETED'),
(1, 5, 'IN_PROGRESS'),
(1, 11, 'IN_PROGRESS'),
(2, 3, 'IN_PROGRESS'),
(2, 4, 'COMPLETED'),
(2, 8, 'IN_PROGRESS'),
(2, 15, 'IN_PROGRESS'),
(3, 2, 'COMPLETED'),
(3, 6, 'IN_PROGRESS'),
(3, 12, 'IN_PROGRESS'),
(3, 20, 'IN_PROGRESS'),
(4, 7, 'IN_PROGRESS'),
(4, 9, 'COMPLETED'),
(4, 17, 'IN_PROGRESS'),
(5, 10, 'IN_PROGRESS'),
(5, 13, 'COMPLETED'),
(5, 25, 'IN_PROGRESS'),
(6, 3, 'IN_PROGRESS'),
(6, 14, 'IN_PROGRESS'),
(6, 21, 'COMPLETED'),
(6, 30, 'IN_PROGRESS'),
(7, 16, 'IN_PROGRESS'),
(7, 22, 'IN_PROGRESS'),
(7, 33, 'IN_PROGRESS'),
(8, 18, 'IN_PROGRESS'),
(8, 26, 'COMPLETED'),
(8, 35, 'IN_PROGRESS'),
(9, 1, 'IN_PROGRESS'),
(9, 19, 'IN_PROGRESS'),
(9, 28, 'COMPLETED'),
(9, 37, 'IN_PROGRESS'),
(10, 4, 'IN_PROGRESS'),
(10, 23, 'COMPLETED'),
(10, 31, 'IN_PROGRESS'),
(11, 6, 'IN_PROGRESS'),
(11, 24, 'IN_PROGRESS'),
(11, 38, 'IN_PROGRESS'),
(13, 5, 'IN_PROGRESS'),
(13, 27, 'COMPLETED'),
(13, 32, 'IN_PROGRESS'),
(15, 7, 'IN_PROGRESS'),
(15, 29, 'IN_PROGRESS'),
(15, 36, 'COMPLETED'),
(15, 40, 'IN_PROGRESS');
-- 6. 강좌평 (Course Reviews)
INSERT INTO `course_reviews` (`course_id`, `student_id`, `contents`, `rating`) VALUES
(1, 2, '파이썬을 처음 배우는데 정말 쉽게 설명해주셔서 좋았어요!', 5.0),
(1, 5, '예제가 많아서 따라하기 편했습니다. 강추!', 4.5),
(2, 4, '자바스크립트 핵심을 빠르게 잡을 수 있었어요.', 4.0),
(2, 8, 'ES6 부분이 특히 유익했습니다.', 4.5),
(3, 2, 'Spring Boot 입문에 최고의 강의입니다.', 5.0),
(3, 6, 'JPA 설명이 조금 부족한 것 같아요. 그래도 전반적으로 좋습니다.', 3.5),
(4, 9, 'React Hooks 부분이 명확하게 이해됐어요!', 4.5),
(5, 13, 'SQL 실습 위주라 실무에 바로 써먹을 수 있었어요.', 5.0),
(5, 10, '인덱스 강의가 특히 알찼습니다.', 4.0),
(6, 21, '머신러닝 입문자에게 딱 맞는 강의입니다.', 4.5),
(6, 14, '실습 환경 구축 설명이 좀 더 자세했으면 좋겠어요.', 3.0),
(7, 16, 'Docker 기초를 확실히 잡을 수 있었습니다.', 4.0),
(8, 26, 'Swift 문법부터 앱 배포까지 체계적으로 배웠어요.', 5.0),
(9, 28, '코딩테스트 준비에 정말 도움이 됐습니다!', 5.0),
(9, 19, 'DP 부분이 어렵긴 했지만 설명이 친절했어요.', 4.0),
(10, 23, 'Git 협업 방식을 실무처럼 배울 수 있어 좋았어요.', 4.5),
(13, 27, 'AWS 실습 위주라 이해가 빨랐어요.', 4.5),
(15, 36, 'Linux 기초를 탄탄히 다질 수 있었습니다.', 4.0);
-- 7. 블랙리스트 (Blacklists)
-- acc_count가 높은 학생들 (student_id: 10, 23, 34, 4, 16, 28)
INSERT INTO `blacklists` (`student_id`) VALUES
(10),
(23),
(34);
-- 8. 접속여부 (Connection Status)
-- INSERT INTO `connection_status` (`student_id`, `last_accessed_at`) VALUES
-- (1, '2024-01-15 09:23:00'),
-- (2, '2024-01-15 10:05:00'),
-- (3, '2024-01-14 18:30:00'),
-- (4, '2024-01-13 22:10:00'),
-- (5, '2024-01-15 08:50:00'),
-- (6, '2024-01-12 14:20:00'),
-- (7, '2024-01-15 11:00:00'),
-- (8, '2024-01-10 16:45:00'),
-- (9, '2024-01-14 20:00:00'),
-- (10, '2024-01-15 07:30:00'),
-- (11, '2023-12-28 09:00:00'),
-- (12, '2024-01-11 13:10:00'),
-- (13, '2024-01-15 10:30:00'),
-- (14, '2024-01-09 19:20:00'),
-- (15, '2024-01-14 15:00:00'),
-- (16, '2024-01-15 08:00:00'),
-- (17, '2024-01-03 12:00:00'),
-- (18, '2024-01-13 21:30:00'),
-- (19, '2024-01-15 09:00:00'),
-- (20, '2023-12-20 10:00:00'),
-- (21, '2024-01-14 17:00:00'),
-- (22, '2024-01-07 11:30:00'),
-- (23, '2024-01-15 06:55:00'),
-- (24, '2024-01-12 20:40:00'),
-- (25, '2024-01-15 10:10:00'),
-- (26, '2024-01-11 14:00:00'),
-- (27, '2024-01-13 16:30:00'),
-- (28, '2024-01-15 09:45:00'),
-- (29, '2024-01-08 18:00:00'),
-- (30, '2024-01-14 22:00:00'),
-- (31, '2024-01-15 08:20:00'),
-- (32, '2024-01-10 10:50:00'),
-- (33, '2024-01-15 11:20:00'),
-- (34, '2024-01-06 15:00:00'),
-- (35, '2024-01-13 19:10:00'),
-- (36, '2024-01-14 13:00:00'),
-- (37, '2024-01-15 07:50:00'),
-- (38, '2024-01-09 20:30:00'),
-- (39, '2024-01-12 09:00:00'),
-- (40, '2024-01-15 10:55:00');
-- 9. 선택목록 (Favorites)
INSERT INTO `favorites` (`student_id`, `course_id`) VALUES
(1, 3),
(1, 6),
(2, 5),
(3, 1),
(3, 9),
(4, 7),
(5, 2),
(5, 9),
(6, 4),
(7, 6),
(8, 3),
(9, 1),
(10, 2),
(11, 8),
(12, 5),
(13, 9),
(14, 4),
(15, 6),
(16, 11),
(17, 13),
(18, 7),
(19, 3),
(20, 10),
(21, 1),
(22, 15),
(23, 9),
(24, 6),
(25, 2),
(26, 13),
(27, 8),
(28, 4),
(29, 11),
(30, 5),
(31, 9),
(32, 7),
(33, 1),
(34, 3),
(35, 15),
(36, 2),
(37, 6),
(38, 10),
(39, 4),
(40, 13);
-- 10. 결제 (Payments)
INSERT INTO `payments` (`student_id`, `payed_at`, `price`) VALUES
(1, '2022-04-05 10:30:00', 29900),
(1, '2022-09-10 11:00:00', 29900),
(2, '2022-04-20 14:00:00', 29900),
(2, '2023-02-15 09:30:00', 29900),
(3, '2022-05-05 13:00:00', 29900),
(4, '2022-05-25 10:00:00', 29900),
(4, '2023-01-20 15:00:00', 29900),
(5, '2022-06-15 09:00:00', 29900),
(5, '2023-03-05 11:00:00', 29900),
(6, '2022-07-01 14:30:00', 29900),
(7, '2022-07-10 10:00:00', 29900),
(8, '2022-07-22 13:00:00', 29900),
(9, '2022-08-08 09:30:00', 29900),
(10, '2022-08-25 11:00:00', 29900),
(11, '2022-09-10 10:00:00', 29900),
(12, '2022-09-28 14:00:00', 29900),
(13, '2022-10-15 09:00:00', 29900),
(14, '2022-10-30 11:30:00', 29900),
(15, '2022-11-18 13:00:00', 29900),
(16, '2022-12-05 10:00:00', 29900),
(17, '2022-12-20 14:30:00', 29900),
(18, '2023-01-10 09:00:00', 29900),
(19, '2023-01-25 11:00:00', 29900),
(20, '2023-02-10 13:30:00', 29900),
(21, '2023-02-28 10:00:00', 29900),
(22, '2023-03-15 14:00:00', 29900),
(23, '2023-04-01 09:30:00', 29900),
(24, '2023-04-18 11:00:00', 29900),
(25, '2023-05-05 10:00:00', 29900),
(26, '2023-05-20 14:30:00', 29900),
(27, '2023-06-08 09:00:00', 29900),
(28, '2023-06-22 11:30:00', 29900),
(29, '2023-07-10 13:00:00', 29900),
(30, '2023-07-25 10:00:00', 29900),
(31, '2023-08-12 14:00:00', 29900),
(32, '2023-08-28 09:30:00', 29900),
(33, '2023-09-15 11:00:00', 29900),
(34, '2023-09-30 10:00:00', 29900),
(35, '2023-10-18 14:30:00', 29900),
(36, '2023-11-05 09:00:00', 29900),
(37, '2023-11-22 11:00:00', 29900),
(38, '2023-12-08 13:30:00', 29900),
(39, '2023-12-20 10:00:00', 29900),
(40, '2024-01-05 14:00:00', 29900);
-- 11. 강의수강이력 (Lecture Histories)
-- status: 'COMPLETED', 'IN_PROGRESS', 'NOT_STARTED'
INSERT INTO `lecture_histories` (`student_id`, `lecture_id`, `status`) VALUES
-- 학생 1: course1(lec 1~5), course9(lec 31~35)
(1, 1, 'COMPLETED'),
(1, 2, 'COMPLETED'),
(1, 3, 'NOT_STARTED'),
(1, 4, 'NOT_STARTED'),
(1, 5, 'NOT_STARTED'),
(1, 31, 'COMPLETED'),
(1, 32, 'NOT_STARTED'),
(1, 33, 'NOT_STARTED'),
-- 학생 2: course1(lec 1~5) COMPLETED, course3(lec 10~13) COMPLETED
(2, 1, 'COMPLETED'),
(2, 2, 'COMPLETED'),
(2, 3, 'COMPLETED'),
(2, 4, 'COMPLETED'),
(2, 5, 'COMPLETED'),
(2, 10, 'COMPLETED'),
(2, 11, 'COMPLETED'),
(2, 12, 'COMPLETED'),
(2, 13, 'COMPLETED'),
-- 학생 3: course2(lec 6~9)
(3, 6, 'COMPLETED'),
(3, 7, 'COMPLETED'),
(3, 8, 'NOT_STARTED'),
(3, 9, 'NOT_STARTED'),
-- 학생 4: course2(lec 6~9) COMPLETED, course10(lec 36~37)
(4, 6, 'COMPLETED'),
(4, 7, 'COMPLETED'),
(4, 8, 'COMPLETED'),
(4, 9, 'COMPLETED'),
(4, 36, 'COMPLETED'),
(4, 37, 'NOT_STARTED'),
-- 학생 5: course1(lec 1~5), course13(lec 43~45)
(5, 1, 'COMPLETED'),
(5, 2, 'COMPLETED'),
(5, 3, 'COMPLETED'),
(5, 4, 'NOT_STARTED'),
(5, 5, 'NOT_STARTED'),
(5, 43, 'COMPLETED'),
(5, 44, 'NOT_STARTED'),
-- 학생 6: course3(lec 10~13), course11(lec 38~40)
(6, 10, 'COMPLETED'),
(6, 11, 'COMPLETED'),
(6, 12, 'NOT_STARTED'),
(6, 13, 'NOT_STARTED'),
(6, 38, 'COMPLETED'),
(6, 39, 'NOT_STARTED'),
-- 학생 7: course4(lec 14~16), course15(lec 48~50)
(7, 14, 'COMPLETED'),
(7, 15, 'NOT_STARTED'),
(7, 16, 'NOT_STARTED'),
(7, 48, 'COMPLETED'),
(7, 49, 'NOT_STARTED'),
-- 학생 8: course2(lec 6~9)
(8, 6, 'COMPLETED'),
(8, 7, 'COMPLETED'),
(8, 8, 'NOT_STARTED'),
-- 학생 9: course4(lec 14~16) COMPLETED
(9, 14, 'COMPLETED'),
(9, 15, 'COMPLETED'),
(9, 16, 'COMPLETED'),
-- 학생 10: course5(lec 17~19)
(10, 17, 'COMPLETED'),
(10, 18, 'NOT_STARTED'),
(10, 19, 'NOT_STARTED'),
-- 학생 13: course5(lec 17~19) COMPLETED
(13, 17, 'COMPLETED'),
(13, 18, 'COMPLETED'),
(13, 19, 'COMPLETED'),
-- 학생 14: course6(lec 20~23)
(14, 20, 'COMPLETED'),
(14, 21, 'COMPLETED'),
(14, 22, 'NOT_STARTED'),
(14, 23, 'NOT_STARTED'),
-- 학생 16: course7(lec 24~26)
(16, 24, 'COMPLETED'),
(16, 25, 'NOT_STARTED'),
(16, 26, 'NOT_STARTED'),
-- 학생 18: course8(lec 27~30)
(18, 27, 'COMPLETED'),
(18, 28, 'NOT_STARTED'),
(18, 29, 'NOT_STARTED'),
-- 학생 19: course9(lec 31~35)
(19, 31, 'COMPLETED'),
(19, 32, 'COMPLETED'),
(19, 33, 'NOT_STARTED'),
(19, 34, 'NOT_STARTED'),
-- 학생 21: course6(lec 20~23) COMPLETED
(21, 20, 'COMPLETED'),
(21, 21, 'COMPLETED'),
(21, 22, 'COMPLETED'),
(21, 23, 'COMPLETED'),
-- 학생 23: course10(lec 36~37) COMPLETED
(23, 36, 'COMPLETED'),
(23, 37, 'COMPLETED'),
-- 학생 25: course5(lec 17~19)
(25, 17, 'COMPLETED'),
(25, 18, 'COMPLETED'),
(25, 19, 'NOT_STARTED'),
-- 학생 26: course8(lec 27~30) COMPLETED
(26, 27, 'COMPLETED'),
(26, 28, 'COMPLETED'),
(26, 29, 'COMPLETED'),
(26, 30, 'COMPLETED'),
-- 학생 27: course13(lec 43~45) COMPLETED
(27, 43, 'COMPLETED'),
(27, 44, 'COMPLETED'),
(27, 45, 'COMPLETED'),
-- 학생 28: course9(lec 31~35) COMPLETED
(28, 31, 'COMPLETED'),
(28, 32, 'COMPLETED'),
(28, 33, 'COMPLETED'),
(28, 34, 'COMPLETED'),
(28, 35, 'COMPLETED'),
-- 학생 29: course15(lec 48~50)
(29, 48, 'COMPLETED'),
(29, 49, 'NOT_STARTED'),
-- 학생 30: course6(lec 20~23)
(30, 20, 'COMPLETED'),
(30, 21, 'NOT_STARTED'),
-- 학생 31: course10(lec 36~37)
(31, 36, 'COMPLETED'),
(31, 37, 'NOT_STARTED'),
-- 학생 32: course13(lec 43~45)
(32, 43, 'COMPLETED'),
(32, 44, 'NOT_STARTED'),
-- 학생 33: course7(lec 24~26)
(33, 24, 'COMPLETED'),
(33, 25, 'NOT_STARTED'),
-- 학생 35: course8(lec 27~30)
(35, 27, 'COMPLETED'),
(35, 28, 'NOT_STARTED'),
-- 학생 36: course15(lec 48~50) COMPLETED
(36, 48, 'COMPLETED'),
(36, 49, 'COMPLETED'),
(36, 50, 'COMPLETED'),
-- 학생 37: course9(lec 31~35)
(37, 31, 'COMPLETED'),
(37, 32, 'NOT_STARTED'),
-- 학생 40: course15(lec 48~50)
(40, 48, 'COMPLETED'),
(40, 49, 'NOT_STARTED');

SET FOREIGN_KEY_CHECKS = 1;