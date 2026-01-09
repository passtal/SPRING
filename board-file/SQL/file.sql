-- 외래키 무시
SET FOREIGN_KEY_CHECKS=0;

CREATE DATABASE IF NOT EXISTS aloha;

-- 파일 테이블
CREATE TABLE `file` (
  `no` int NOT NULL AUTO_INCREMENT PRIMARY KEY,
  `id` varchar(64) DEFAULT NULL UNIQUE,
  `parent_table` varchar(100) NOT NULL,
  `parent_no` int NOT NULL,
  `name` text NOT NULL,
  `path` text NOT NULL,
  `size` bigint default NULL,
  `content_type` varchar(100) default 'application/octet-stream', -- image/png, application/pdf ...
  `sort_order` int default 0,
  `is_main` tinyint(1) default false,
  `created_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `updated_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);

-- 외래키 활성화
SET FOREIGN_KEY_CHECKS=1;