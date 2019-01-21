/*
Navicat MySQL Data Transfer

Source Server         : localhost_3306
Source Server Version : 50626
Source Host           : localhost:3306
Source Database       : permission_db

Target Server Type    : MYSQL
Target Server Version : 50626
File Encoding         : 65001

Date: 2019-01-22 00:08:28
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for sys_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '用户id',
  `username` varchar(20) NOT NULL DEFAULT '' COMMENT '用户名称',
  `telephone` varchar(13) NOT NULL DEFAULT '' COMMENT '手机号',
  `mail` varchar(20) NOT NULL DEFAULT '' COMMENT '邮箱',
  `password` varchar(40) NOT NULL DEFAULT '' COMMENT '加密后的密码',
  `dept_id` int(11) NOT NULL DEFAULT '0' COMMENT '用户所在部门的id',
  `status` int(11) NOT NULL DEFAULT '1' COMMENT '状态，1：正常，0：冻结状态，2：删除',
  `remark` varchar(200) DEFAULT '' COMMENT '备注',
  `operator` varchar(20) NOT NULL DEFAULT '' COMMENT '操作者',
  `operate_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '最后一次更新时间',
  `operate_ip` varchar(20) NOT NULL DEFAULT '' COMMENT '最后一次更新者的ip地址',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of sys_user
-- ----------------------------
INSERT INTO `sys_user` VALUES ('1', 'Admin', '18612344321', 'admin@qq.com', '25D55AD283AA400AF464C76D713C07AD', '1', '1', 'admin', 'system', '2017-10-13 08:46:16', '127.0.0.1');
INSERT INTO `sys_user` VALUES ('2', 'Jimin', '13188889999', 'jimin@qq.com', '25D55AD283AA400AF464C76D713C07AD', '1', '1', 'jimin.zheng', 'Admin', '2017-10-14 14:45:19', '127.0.0.1');
INSERT INTO `sys_user` VALUES ('3', 'Jimmy', '13812344311', 'jimmy@qq.com', '25D55AD283AA400AF464C76D713C07AD', '2', '1', '', 'Admin', '2017-10-16 12:57:35', '0:0:0:0:0:0:0:1');
INSERT INTO `sys_user` VALUES ('4', 'Kate', '13144445555', 'kate@qq.com', '25D55AD283AA400AF464C76D713C07AD', '1', '0', 'sss', 'Admin', '2019-01-21 23:53:19', '0:0:0:0:0:0:0:1');
INSERT INTO `sys_user` VALUES ('5', '服务员A', '18677778888', 'service@qq.com', '25D55AD283AA400AF464C76D713C07AD', '12', '1', '', 'Admin', '2017-10-17 00:22:15', '0:0:0:0:0:0:0:1');
INSERT INTO `sys_user` VALUES ('6', 'Carlk', '18376642114', 'carl@qq.com', '25D55AD283AA400AF464C76D713C07AD', '1', '1', 'carl', 'system', '2019-01-20 22:13:49', '127.0.0.1');
INSERT INTO `sys_user` VALUES ('7', 'shipp', '6980110', 'shipp@mg-pen.com', '25D55AD283AA400AF464C76D713C07AD', '4', '1', 'UI设计', 'Admin', '2019-01-21 23:50:49', '0:0:0:0:0:0:0:1');
INSERT INTO `sys_user` VALUES ('8', '李木', '13937363321', 'limu@qq.com', '25D55AD283AA400AF464C76D713C07AD', '3', '1', '前端', 'Admin', '2019-01-21 23:52:30', '0:0:0:0:0:0:0:1');
INSERT INTO `sys_user` VALUES ('9', 'yanhuiyu', '13913149454', 'yanhuiyu@mg-pen.com', '25D55AD283AA400AF464C76D713C07AD', '24', '1', '销售部', 'Carlk', '2019-01-22 00:04:59', '0:0:0:0:0:0:0:1');
INSERT INTO `sys_user` VALUES ('10', '杜一文', '13145201314', 'duyiwen@mg-pen.com', '25D55AD283AA400AF464C76D713C07AD', '24', '1', '销售部', 'Carlk', '2019-01-22 00:06:18', '0:0:0:0:0:0:0:1');
