/*
Navicat MySQL Data Transfer

Source Server         : localhost_3306
Source Server Version : 50626
Source Host           : localhost:3306
Source Database       : permission_db

Target Server Type    : MYSQL
Target Server Version : 50626
File Encoding         : 65001

Date: 2019-01-22 00:08:43
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for sys_dept
-- ----------------------------
DROP TABLE IF EXISTS `sys_dept`;
CREATE TABLE `sys_dept` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '部门id',
  `name` varchar(20) NOT NULL DEFAULT '' COMMENT '部门名称',
  `parent_id` int(11) NOT NULL DEFAULT '0' COMMENT '上级部门id',
  `level` varchar(200) NOT NULL DEFAULT '' COMMENT '部门层级',
  `seq` int(11) NOT NULL DEFAULT '0' COMMENT '部门在当前层级下的顺序，由小到大',
  `remark` varchar(200) DEFAULT '' COMMENT '备注',
  `operator` varchar(20) NOT NULL DEFAULT '' COMMENT '操作者',
  `operate_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '最后一次操作时间',
  `operate_ip` varchar(20) NOT NULL DEFAULT '' COMMENT '最后一次更新操作者的ip地址',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=27 DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of sys_dept
-- ----------------------------
INSERT INTO `sys_dept` VALUES ('1', '技术部', '0', '0', '1', '技术部', 'system', '2017-10-11 07:21:40', '127.0.0.1');
INSERT INTO `sys_dept` VALUES ('2', '后端开发', '1', '0.1', '1', '后端项目组', 'system-update', '2019-01-20 23:01:49', '127.0.0.1');
INSERT INTO `sys_dept` VALUES ('3', '前端开发', '1', '0.1', '2', '前端项目组', 'system-update', '2019-01-18 09:32:36', '127.0.0.1');
INSERT INTO `sys_dept` VALUES ('4', 'UI设计', '1', '0.1', '3', '', 'system', '2017-10-12 07:55:43', '127.0.0.1');
INSERT INTO `sys_dept` VALUES ('11', '产品部', '0', '0', '2', '', 'Admin', '2017-10-16 22:52:29', '0:0:0:0:0:0:0:1');
INSERT INTO `sys_dept` VALUES ('12', '客服部', '0', '0', '4', '', 'Admin', '2017-10-17 00:22:55', '0:0:0:0:0:0:0:1');
INSERT INTO `sys_dept` VALUES ('13', '财务部', '0', '0', '3', '财务部门', 'system', '2019-01-16 16:36:01', '127.0.0.1');
INSERT INTO `sys_dept` VALUES ('14', '行政部', '0', '0', '3', '行政部门', 'system', '2019-01-16 17:21:08', '127.0.0.1');
INSERT INTO `sys_dept` VALUES ('15', '人事部', '0', '0', '3', '人事部门', 'system', '2019-01-16 17:31:09', '127.0.0.1');
INSERT INTO `sys_dept` VALUES ('17', '财务一组', '13', '0.13', '1', '财务部门', 'system', '2019-01-18 10:15:34', '127.0.0.1');
INSERT INTO `sys_dept` VALUES ('18', '财务二组', '13', '0.13', '2', '财务部门', 'system', '2019-01-18 10:16:06', '127.0.0.1');
INSERT INTO `sys_dept` VALUES ('19', '人事一组', '15', '0.15', '1', '人事部门', 'system', '2019-01-18 14:28:56', '127.0.0.1');
INSERT INTO `sys_dept` VALUES ('20', '客服一组', '12', '0.12', '1', '客服部门', 'system', '2019-01-19 18:17:33', '127.0.0.1');
INSERT INTO `sys_dept` VALUES ('21', '行政A组', '14', '0.14', '1', '行政部门', 'system', '2019-01-19 23:24:51', '127.0.0.1');
INSERT INTO `sys_dept` VALUES ('22', '产品开发', '11', '0.11', '1', '开发编码1', 'system-update', '2019-01-20 20:12:20', '127.0.0.1');
INSERT INTO `sys_dept` VALUES ('23', '销售中心', '0', '0', '1', 'mg销售中心', 'Admin', '2019-01-21 23:54:36', '0:0:0:0:0:0:0:1');
INSERT INTO `sys_dept` VALUES ('24', '销售一组', '23', '0.23', '1', '销售部门', 'Admin', '2019-01-21 23:55:08', '0:0:0:0:0:0:0:1');
INSERT INTO `sys_dept` VALUES ('25', '销售二组', '23', '0.23', '2', '销售部门', 'Admin', '2019-01-21 23:55:30', '0:0:0:0:0:0:0:1');
