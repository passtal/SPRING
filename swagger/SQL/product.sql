-- Active: 1767915726149@@127.0.0.1@3306@aloha
-- 데이터베이스 생성

CREATE DATABASE IF NOT EXISTS aloha;

SET FOREIGN_KEY_CHECKS=0;

DROP TABLE IF EXISTS `product`;
-- 게시판 테이블 생성
CREATE TABLE `product` (
    `no` INT AUTO_INCREMENT PRIMARY KEY COMMENT '상품 고유번호',
    `id` VARCHAR(64) NOT NULL UNIQUE COMMENT 'UK',
    `name` VARCHAR(100) NOT NULL COMMENT '상품명',
    `price` INT NOT NULL DEFAULT 0 COMMENT '가격',
    `stock` INT NOT NULL DEFAULT 0 COMMENT '재고수',
    `created_at` TIMESTAMP DEFAULT CURRENT_TIMESTAMP COMMENT '등록일자',
    `updated_at` TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '수정일자'
) COMMENT '상품';

-- 샘플 데이터
TRUNCATE Table `product`;

INSERT INTO `product` (`id`, `name`, `price`, `stock`)
VALUES 
  (UUID(), '상품1', '1000', '1'),
  (UUID(), '상품2', '2000', '2'),
  (UUID(), '상품3', '3000', '3'),
  (UUID(), '상품4', '4000', '4'),
  (UUID(), '상품5', '5000', '5')
;

-- 외래키 활성화
SET FOREIGN_KEY_CHECKS=1;

SELECT * FROM `product`;