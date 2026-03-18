use only_one;

-- ------------------------------------------------------------------------------------
-- 외래 키 제약 조건 비활성화
-- ------------------------------------------------------------------------------------
SET FOREIGN_KEY_CHECKS = 0;

-- ------------------------------------------------------------------------------------
-- 기존 테이블 삭제 (역순 배치)
-- ------------------------------------------------------------------------------------
DROP TABLE IF EXISTS `lecture_histories`;
DROP TABLE IF EXISTS `payments`;
DROP TABLE IF EXISTS `favorites`;
DROP TABLE IF EXISTS `connection_status`;
DROP TABLE IF EXISTS `blacklists`;
DROP TABLE IF EXISTS `course_reviews`;
DROP TABLE IF EXISTS `studying_courses`;
DROP TABLE IF EXISTS `lectures`;
DROP TABLE IF EXISTS `courses`;
DROP TABLE IF EXISTS `students`;
DROP TABLE IF EXISTS `teachers`;

-- ------------------------------------------------------------------------------------
-- 테이블 생성 (독립 테이블 -> 종속 테이블 순서)
-- ------------------------------------------------------------------------------------

-- 1. 강사 (Teacher)
CREATE TABLE `teachers` (
    `teacher_id`  BIGINT    NOT NULL AUTO_INCREMENT,  
    `name`        VARCHAR(100)    NOT NULL,
    `email`       VARCHAR(255)    NOT NULL UNIQUE,
    `password`    VARCHAR(255)    NOT NULL,
    `enrolled_at` DATETIME        NOT NULL DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY (`teacher_id`)
);

-- 2. 학생 (Student)
CREATE TABLE `students` (
    `student_id`   BIGINT         NOT NULL AUTO_INCREMENT,
    `name`         VARCHAR(100)   NOT NULL,
	`email`        VARCHAR(255)   NOT NULL UNIQUE,
    `password`     VARCHAR(255)   NOT NULL,
    `enrolled_at`  DATETIME       NOT NULL DEFAULT CURRENT_TIMESTAMP,
    `acc_count`    INT            NOT NULL DEFAULT 0,
    PRIMARY KEY (`student_id`)
);

-- 3. 강좌 (Course)
CREATE TABLE `courses` (
    `course_id`    BIGINT         NOT NULL AUTO_INCREMENT,
    `title`        VARCHAR(255)   NOT NULL,
    `teacher_id`   BIGINT         NOT NULL,
   --  `status`       VARCHAR(50)    NOT NULL,
    PRIMARY KEY (`course_id`),
    FOREIGN KEY (`teacher_id`) REFERENCES `teachers` (`teacher_id`) ON DELETE CASCADE
);

-- 4. 강의 (Lecture)
CREATE TABLE `lectures` (
    `lecture_id`   BIGINT         NOT NULL AUTO_INCREMENT,
    `course_id`    BIGINT         NOT NULL,
    `title`        VARCHAR(255)   NOT NULL,
    `order_num`    INT            NOT NULL,
    `status`       VARCHAR(50)    NOT NULL,
    PRIMARY KEY (`lecture_id`),
    FOREIGN KEY (`course_id`) REFERENCES `courses` (`course_id`) ON DELETE CASCADE
);

-- 5. 수강중인강좌 (Studying Course)
CREATE TABLE `studying_courses` (
    `studying_id`  BIGINT         NOT NULL AUTO_INCREMENT,
	`course_id`    BIGINT         NOT NULL,
    `student_id`   BIGINT         NOT NULL,
    `status`       VARCHAR(50)    NOT NULL ,
	PRIMARY KEY (`studying_id`),
    FOREIGN KEY (`course_id`) REFERENCES `courses` (`course_id`) ON DELETE CASCADE,
    FOREIGN KEY (`student_id`) REFERENCES `students` (`student_id`) ON DELETE CASCADE
);

-- 6. 강좌평 (Course Review)
CREATE TABLE `course_reviews` (
    -- `review_id`    BIGINT         NOT NULL AUTO_INCREMENT,
    `course_id`    BIGINT         NOT NULL,
    `student_id`   BIGINT         NOT NULL,
	`contents`     TEXT           NOT NULL,
	`rating`       FLOAT          NOT NULL,
    PRIMARY KEY (`course_id`,`student_id`),
    FOREIGN KEY (`course_id`) REFERENCES `courses` (`course_id`) ON DELETE CASCADE,
    FOREIGN KEY (`student_id`) REFERENCES `students` (`student_id`) ON DELETE CASCADE
);

-- 7. 블랙리스트 (Blacklist)
CREATE TABLE `blacklists` (
    `list_id`      BIGINT         NOT NULL AUTO_INCREMENT,
    `student_id`   BIGINT         NOT NULL,
    PRIMARY KEY (`list_id`),
    FOREIGN KEY (`student_id`) REFERENCES `students` (`student_id`) ON DELETE CASCADE
);

-- 8. 접속여부 (Connection Status) - 설계 결함 수정 (PK 및 시간 컬럼 추가)
CREATE TABLE `connection_status` (
    `student_id`       BIGINT         NOT NULL,
    PRIMARY KEY (`student_id`),
    FOREIGN KEY (`student_id`) REFERENCES `students` (`student_id`) ON DELETE CASCADE
);

-- 9. 선택목록 (Favorites / Wishlist)
CREATE TABLE `favorites` (
    `fav_id`       BIGINT         NOT NULL AUTO_INCREMENT,
    `student_id`   BIGINT         NOT NULL,
    `course_id`    BIGINT         NOT NULL,
    PRIMARY KEY (`fav_id`),
    FOREIGN KEY (`student_id`) REFERENCES `students` (`student_id`) ON DELETE CASCADE,
    FOREIGN KEY (`course_id`) REFERENCES `courses` (`course_id`) ON DELETE CASCADE
);

-- 10. 결제 (Payment)
CREATE TABLE `payments` (
    `pay_id`       BIGINT         NOT NULL AUTO_INCREMENT,
    `student_id`   BIGINT         NOT NULL,
    `payed_at`     DATETIME       NOT NULL DEFAULT CURRENT_TIMESTAMP,
    `price`        INT            NOT NULL,
    PRIMARY KEY (`pay_id`),
    FOREIGN KEY (`student_id`) REFERENCES `students` (`student_id`) ON DELETE CASCADE
);

-- 11. 강의수강이력 (Lecture History)
CREATE TABLE `lecture_histories` (
    `history_id`   BIGINT         NOT NULL AUTO_INCREMENT,
    `student_id`   BIGINT         NOT NULL,
    `lecture_id`   BIGINT         NOT NULL,
    `status`       VARCHAR(50)    NOT NULL,
    PRIMARY KEY (`history_id`),
    FOREIGN KEY (`student_id`) REFERENCES `students` (`student_id`) ON DELETE CASCADE,
    FOREIGN KEY (`lecture_id`) REFERENCES `lectures` (`lecture_id`) ON DELETE CASCADE
);


-- ------------------------------------------------------------------------------------
-- 외래 키 제약 조건 재활성화
-- ------------------------------------------------------------------------------------
SET FOREIGN_KEY_CHECKS = 1;