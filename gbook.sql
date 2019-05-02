/*
SQLyog Ultimate v12.09 (64 bit)
MySQL - 5.6.24 : Database - gbook
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`gbook` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `gbook`;

/*Table structure for table `message` */

DROP TABLE IF EXISTS `message`;

CREATE TABLE `message` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `userid` int(11) DEFAULT NULL,
  `message` varchar(255) DEFAULT NULL,
  `date` varchar(50) DEFAULT NULL,
  `ip` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `userid` (`userid`),
  CONSTRAINT `message_ibfk_1` FOREIGN KEY (`userid`) REFERENCES `user` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=53 DEFAULT CHARSET=utf8;

/*Data for the table `message` */

insert  into `message`(`id`,`userid`,`message`,`date`,`ip`) values (1,1,'故人西辞黄鹤楼，烟花三月下扬州。','2016-10-12 12:33:06','0:0:0:0:0:0:0:1'),(2,9,'孤帆远影碧空尽，唯见长江天际流。','2016-10-12 12:34:40','0:0:0:0:0:0:0:1'),(3,2,'床前明月光，疑是地上霜。','2016-10-12 12:33:27','0:0:0:0:0:0:0:1'),(4,8,'行路难！行路难！多歧路，今安在？长风破浪会有时，直挂云帆济沧海。','2016-10-12 12:34:10','0:0:0:0:0:0:0:1'),(42,10,'飞流直下三千尺，疑是银河落九天。','2016-10-12 12:36:13','0:0:0:0:0:0:0:1'),(43,10,'弃我去者，昨日之日不可留','2016-10-12 12:37:04','0:0:0:0:0:0:0:1'),(44,10,'朝辞白帝彩云间，千里江陵一日还。','2016-10-12 12:37:27','0:0:0:0:0:0:0:1'),(45,10,'两岸猿声啼不住，轻舟已过万重山。','2016-10-12 12:41:03','0:0:0:0:0:0:0:1'),(46,10,'日照香炉生紫烟，遥看瀑布挂前川。','2016-10-12 12:41:11','0:0:0:0:0:0:0:1'),(47,10,'举杯邀明月，对影成三人。','2016-10-12 12:41:32','0:0:0:0:0:0:0:1'),(48,10,'举头望明月，低头思故乡。','2016-10-12 12:41:43','0:0:0:0:0:0:0:1'),(49,10,'抽刀断水水更流，举杯消愁愁更愁。','2016-10-12 12:41:58','0:0:0:0:0:0:0:1'),(50,2,'两岸青山相对出，孤帆一片日边来。','2016-10-12 12:42:18','0:0:0:0:0:0:0:1'),(51,1,'相看两不厌，只有敬亭山。','2016-10-12 12:42:36','0:0:0:0:0:0:0:1'),(52,8,'我寄愁心与明月，随风直到夜郎西。','2016-10-12 12:42:57','0:0:0:0:0:0:0:1');

/*Table structure for table `user` */

DROP TABLE IF EXISTS `user`;

CREATE TABLE `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `type` int(11) DEFAULT NULL,
  `name` char(200) DEFAULT NULL,
  `email` char(200) DEFAULT NULL,
  `passwd` char(200) DEFAULT NULL,
  `imgurl` char(200) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `email` (`email`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8;

/*Data for the table `user` */

insert  into `user`(`id`,`type`,`name`,`email`,`passwd`,`imgurl`) values (1,2,'安迪','licyun@outlook.com','123456','andy.jpg'),(2,1,'邱莹莹','licyun@qq.com','123456','qyy.jpg'),(8,1,'关关','licyun@gmail.com','12345678','gg.jpg'),(9,1,'曲筱绡','licyun@163.com','12345678','qxx.jpg'),(10,1,'樊胜美','licyun@live.com','12345678','fsm.jpg'),(11,1,'赵医生','licyun@foxmail.com','12345678',NULL);

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
