/*
MySQL Data Transfer
Source Host: localhost
Source Database: microblog
Target Host: localhost
Target Database: microblog
Date: 2016/8/6 23:22:51
*/
-- Create schema microblog
CREATE DATABASE IF NOT EXISTS microblog;
USE microblog;

SET FOREIGN_KEY_CHECKS=0;
-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `u_id` int(11) NOT NULL auto_increment,
  `u_name` varchar(20) NOT NULL,
  `u_pwd` varchar(20) NOT NULL,
  `u_email` varchar(20) NOT NULL,
  `u_nickname` varchar(30) NOT NULL,
  `u_sex` varchar(8) NOT NULL,
  `u_position` varchar(50) NOT NULL,
  `u_images` varchar(100) NOT NULL,
  `u_sign` varchar(200) default NULL,
  `u_qq` int(10) unsigned default NULL,
  `u_birth` datetime default NULL,
  PRIMARY KEY  (`u_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for mood
-- ----------------------------
DROP TABLE IF EXISTS `mood`;
CREATE TABLE `mood` (
  `m_id` int(11) NOT NULL auto_increment,
  `u_id` int(11) default NULL,
  `m_date` varchar(30) NOT NULL,
  `m_images` varchar(100) default NULL,
  `m_content` varchar(2000) NOT NULL,
  PRIMARY KEY  (`m_id`),
  KEY `FK_user_mood` (`u_id`),
  CONSTRAINT `FK_user_mood` FOREIGN KEY (`u_id`) REFERENCES `user` (`u_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=FIXED;

-- ----------------------------
-- Table structure for comment
-- ----------------------------
DROP TABLE IF EXISTS `comment`;
CREATE TABLE `comment` (
  `c_id` int(11) NOT NULL auto_increment,
  `u_id` int(11) default NULL,
  `m_id` int(11) default NULL,
  `c_content` varchar(45) NOT NULL,
  `c_date` varchar(45) NOT NULL,
  PRIMARY KEY  (`c_id`),
  KEY `FK_user_comment` (`u_id`),
  KEY `FK_mood_comment` (`m_id`),
  CONSTRAINT `FK_user_comment` FOREIGN KEY (`u_id`) REFERENCES `user` (`u_id`),
  CONSTRAINT `FK_mood_comment` FOREIGN KEY (`m_id`) REFERENCES `mood` (`m_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=FIXED;

-- ----------------------------
-- Table structure for friends
-- ----------------------------
DROP TABLE IF EXISTS `friends`;
CREATE TABLE `friends` (
  `f_id` int(11) NOT NULL auto_increment,
  `u_id` int(11) default NULL,
  `f_user_id` int(11) default NULL,
  PRIMARY KEY  (`f_id`),
  KEY `FK_user_friends` (`u_id`),
  KEY `FK_user1_friends` (`u_id`),
  CONSTRAINT `FK_user_friends` FOREIGN KEY (`u_id`) REFERENCES `user` (`u_id`),
  CONSTRAINT `FK_user1_friends` FOREIGN KEY (`u_id`) REFERENCES `user` (`u_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=FIXED;

-- ----------------------------
-- Table structure for transmit
-- ----------------------------
DROP TABLE IF EXISTS `transmit`;
CREATE TABLE `transmit` (
  `t_id` int(11) NOT NULL auto_increment,
  `m_id` int(11) default NULL,
  `u_id` int(11) default NULL,
  `t_date` varchar(45) NOT NULL,
  PRIMARY KEY  (`t_id`),
  KEY `FK_user_transmit` (`u_id`),
  KEY `FK_mood_transmit` (`m_id`),
  CONSTRAINT `FK_user_transmit` FOREIGN KEY (`u_id`) REFERENCES `user` (`u_id`),
  CONSTRAINT `FK_mood_transmit` FOREIGN KEY (`m_id`) REFERENCES `mood` (`m_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=FIXED;

-- ----------------------------
-- Records 
-- ----------------------------
INSERT INTO `user` VALUES ('1', '101589537@qq.com', '123456', '101589537@qq.com', '刘皓冰', 'male	', '山东', '/MicroBlogbeta/face/120160806210813.jpg', '我爱学习！', '101589537', '1997-01-01 00:00:00');
INSERT INTO `user` VALUES ('2', '111@qq.com', '111', '111@qq.com', 'cc', 'male', '辽宁沈阳', '/MicroBlogbeta/face/default.jpg', null, null, null);
INSERT INTO `user` VALUES ('3', '123@gmail.com', '123', '123@gmail.com', '123', 'male', '浙江温州', '/MicroBlogbeta/face/default.jpg', null, null, null);
INSERT INTO `user` VALUES ('4', '123456@neu.edu.cn', '123456', '123456@neu.edu.cn', 'neu', 'male', '上海浦东', '/MicroBlogbeta/face/default.jpg', null, null, null);
INSERT INTO `user` VALUES ('5', 'qwe@163.com', 'qwe', 'qwe@163.com', 'hh', 'male', '河南新乡', '/MicroBlogbeta/face/default.jpg', null, null, null);
INSERT INTO `user` VALUES ('8', '1111@qq.com', '1111', '1111@qq.com', '奇葩', 'male', '重庆沙坪坝', '/MicroBlogbeta/face/default.jpg', null, null, null);
INSERT INTO `user` VALUES ('9', '123456@qq.com', '123456', '123456@qq.com', '叽叽', 'male', '广东深圳', '/MicroBlogbeta/face/default.jpg', null, null, null);
INSERT INTO `user` VALUES ('10', 'liuhaobing@gmail.com', '123456', 'liuhaobing@gmail.com', 'liu', 'male', '山东青岛', '/MicroBlogbeta/face/default.jpg', null, null, null);
INSERT INTO `user` VALUES ('11', 'mrliu@qq.com', '123456', 'mrliu@qq.com', 'Mr.Liu', 'male', '山东烟台', '/MicroBlogbeta/face/default.jpg', null, null, null);
INSERT INTO `user` VALUES ('12', 'MissLi@gmail.com', '123456', 'MissLi@gmail.com', 'Miss.Li', 'female', '辽宁沈阳', '/MicroBlogbeta/face/teacher.PNG', '天才哦！', '666666', '2016-08-01 00:00:00');

INSERT INTO `mood` VALUES ('4', '1', '2016-08-06 21:17:21', '/MicroBlogbeta/upload/pic/lala20160806211721.jpg', '我爱学习，学习使我快乐！！');
INSERT INTO `mood` VALUES ('5', '2', '2016-08-06 21:19:48', '/MicroBlogbeta/upload/pic/lala20160806211948.jpg', '这个微博系统介于朋友圈和腾讯微博之间，我挺喜欢！');
INSERT INTO `mood` VALUES ('6', '8', '2016-08-06 21:33:42', '/MicroBlogbeta/upload/pic/home20160806213342.jpg', '想要回家了');
INSERT INTO `mood` VALUES ('7', '8', '2016-08-06 21:34:25', '/MicroBlogbeta/upload/pic/lala20160806213425.jpg', '坚持住！还有一天！HOME！！');
INSERT INTO `mood` VALUES ('8', '4', '2016-08-06 21:35:22', '/MicroBlogbeta/upload/pic/12320160806213522.jpg', '很高兴能和大家交朋友！');
INSERT INTO `mood` VALUES ('9', '4', '2016-08-06 21:35:51', '/MicroBlogbeta/upload/pic/1809320160806213551.jpg', '我来自东北大学！');
INSERT INTO `mood` VALUES ('10', '5', '2016-08-06 21:38:52', '/MicroBlogbeta/upload/pic/lala20160806213852.jpg', 'jsp+servlet+javabean 数据库使用MySQL。还涉及javascript、css。我猜是这样，MVC架构。这个微博');
INSERT INTO `mood` VALUES ('11', '4', '2016-08-06 21:40:24', '/MicroBlogbeta/upload/pic/1809320160806214024.jpg', '欢迎来浑南校区参观！不亚于南湖校区哦！');
INSERT INTO `mood` VALUES ('12', '9', '2016-08-06 21:44:46', '/MicroBlogbeta/upload/pic/ai20160806214446.gif', 'mysql不支持except关键字！！！不开心');
INSERT INTO `mood` VALUES ('13', '9', '2016-08-06 21:45:44', '/MicroBlogbeta/upload/pic/qqqqq20160806214544.gif', '嗯，不开心去听歌吧！！嗨起来！');
INSERT INTO `mood` VALUES ('14', '10', '2016-08-06 22:53:11', '/MicroBlogbeta/upload/pic/m.jpg', '只有一个中国，中国——一点都不能少');
INSERT INTO `mood` VALUES ('15', '10', '2016-08-06 22:57:17', '/MicroBlogbeta/upload/pic/china.png', '望中国越来越强！');

INSERT INTO `comment` VALUES ('1', '1', '4', '你们都喜欢学习吗？', '2016-08-06 21:17:50');
INSERT INTO `comment` VALUES ('2', '2', '5', '欢迎大家提出看法', '2016-08-06 21:20:21');
INSERT INTO `comment` VALUES ('3', '1', '15', '不能再同意了！祝福祖国', '2016-08-06 23:20:42');
INSERT INTO `comment` VALUES ('4', '1', '5', '真相了', '2016-08-06 23:21:20');

INSERT INTO `friends` VALUES ('1', '1', '2');
INSERT INTO `friends` VALUES ('2', '2', '3');
INSERT INTO `friends` VALUES ('3', '2', '4');
INSERT INTO `friends` VALUES ('4', '2', '5');
INSERT INTO `friends` VALUES ('5', '2', '8');
INSERT INTO `friends` VALUES ('6', '1', '9');
INSERT INTO `friends` VALUES ('7', '9', '10');
INSERT INTO `friends` VALUES ('8', '1', '12');
INSERT INTO `friends` VALUES ('9', '12', '1');
INSERT INTO `friends` VALUES ('10', '10', '3');
INSERT INTO `friends` VALUES ('11', '1', '10');

INSERT INTO `transmit` VALUES ('1', '5', '2', '2016-08-06 21:20:27');
INSERT INTO `transmit` VALUES ('2', '6', '8', '2016-08-06 21:33:51');
INSERT INTO `transmit` VALUES ('3', '13', '1', '2016-08-06 21:56:18');
INSERT INTO `transmit` VALUES ('4', '12', '1', '2016-08-06 21:56:22');
INSERT INTO `transmit` VALUES ('5', '4', '12', '2016-08-06 22:47:12');
INSERT INTO `transmit` VALUES ('6', '15', '1', '2016-08-06 23:20:12');