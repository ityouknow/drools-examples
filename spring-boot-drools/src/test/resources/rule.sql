/*
 Navicat MySQL Data Transfer

 Source Server         : localhost
 Source Server Type    : MySQL
 Source Server Version : 50716
 Source Host           : localhost
 Source Database       : test

 Target Server Type    : MySQL
 Target Server Version : 50716
 File Encoding         : utf-8

 Date: 07/31/2017 19:11:43 PM
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
--  Table structure for `rule`
-- ----------------------------
DROP TABLE IF EXISTS `rule`;
CREATE TABLE `rule` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `content` varchar(2048) NOT NULL,
  `create_time` varchar(255) NOT NULL,
  `last_modify_time` varchar(255) DEFAULT NULL,
  `rule_key` varchar(255) NOT NULL,
  `version` varchar(255) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_9yepjak9olg92holwkr8p3l0f` (`rule_key`),
  UNIQUE KEY `UK_ilmbp99kyt6gy10224pc9bl6n` (`version`),
  UNIQUE KEY `UK_ei48upwykmhx9r5p7p4ndxvgn` (`last_modify_time`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
--  Records of `rule`
-- ----------------------------
BEGIN;
INSERT INTO `rule` VALUES ('1', 'package plausibcheck.adress\n\nimport com.neo.drools.model.Address;\nimport com.neo.drools.model.fact.AddressCheckResult;\n\nrule \"Postcode 6 numbers\"\n\n    when\n        address : Address(postcode != null, postcode matches \"([0-9]{6})\")\n        checkResult : AddressCheckResult();\n    then\n        checkResult.setPostCodeResult(true);\n		System.out.println(\"规则6中打印日志：校验通过!\");\nend', '111', '111', 'score', '1');
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;
