/*
SQLyog Ultimate v13.1.1 (64 bit)
MySQL - 5.6.10 : Database - employee-ms
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`employee-ms` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `employee-ms`;

/*Table structure for table `appuser` */

DROP TABLE IF EXISTS `appuser`;

CREATE TABLE `appuser` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `age` varchar(255) DEFAULT NULL,
  `dateCreated` date DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `firstName` varchar(255) DEFAULT NULL,
  `gender` varchar(255) DEFAULT NULL,
  `lastName` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `username` varchar(255) DEFAULT NULL,
  `walletId` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKt6i5kkyslq1spr5ykgonjbd7w` (`walletId`),
  CONSTRAINT `FKt6i5kkyslq1spr5ykgonjbd7w` FOREIGN KEY (`walletId`) REFERENCES `wallet` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

/*Data for the table `appuser` */

insert  into `appuser`(`id`,`age`,`dateCreated`,`email`,`firstName`,`gender`,`lastName`,`password`,`username`,`walletId`) values 
(1,'27','2022-03-28','cole@gmail.com','Cole','male','Amadi','$2a$12$mEKskLcK9k6UTHpQQHoOZO19z9oQz5m6zHmZBJcpIfTXbxgimrHaK','cole',1),
(3,'60','2022-03-28','nf@gmail.com','Nate','male','Fury','$2a$10$a9XrDc8vu9B0TCxdAcPgjevgsDIUlNAOnRCA77n97tZwXwpBgkqsi','nf',2),
(4,'45','2022-03-28','tee@gmail.com','Tee','male','Tee','$2a$10$fitseNPjuthYIJD2n3PWKeWko/lTdeFqeSU2OMkQU9Jf03WoaX1gm','tee',3);

/*Table structure for table `appuser_roles` */

DROP TABLE IF EXISTS `appuser_roles`;

CREATE TABLE `appuser_roles` (
  `AppUser_id` bigint(20) NOT NULL,
  `roles_id` bigint(20) NOT NULL,
  KEY `FKa60livhaogoea0kiyggyytves` (`roles_id`),
  KEY `FKcxio2g2vrqtyq6qhoel1ovbg5` (`AppUser_id`),
  CONSTRAINT `FKa60livhaogoea0kiyggyytves` FOREIGN KEY (`roles_id`) REFERENCES `role` (`id`),
  CONSTRAINT `FKcxio2g2vrqtyq6qhoel1ovbg5` FOREIGN KEY (`AppUser_id`) REFERENCES `appuser` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `appuser_roles` */

insert  into `appuser_roles`(`AppUser_id`,`roles_id`) values 
(1,1),
(1,2),
(3,2),
(4,2);

/*Table structure for table `jobdept` */

DROP TABLE IF EXISTS `jobdept`;

CREATE TABLE `jobdept` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `deptName` varchar(255) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `jobName` varchar(255) DEFAULT NULL,
  `salary` double DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

/*Data for the table `jobdept` */

insert  into `jobdept`(`id`,`deptName`,`description`,`jobName`,`salary`) values 
(3,'IT','IT Dept','IT',NULL);

/*Table structure for table `jobdept_salaries` */

DROP TABLE IF EXISTS `jobdept_salaries`;

CREATE TABLE `jobdept_salaries` (
  `JobDept_id` bigint(20) NOT NULL,
  `salaries_id` bigint(20) NOT NULL,
  UNIQUE KEY `UK_9fxaodghc7mxy0gefcxlgwseq` (`salaries_id`),
  KEY `FKh70wyib5fgvoqqlpco4jrn09p` (`JobDept_id`),
  CONSTRAINT `FKh70wyib5fgvoqqlpco4jrn09p` FOREIGN KEY (`JobDept_id`) REFERENCES `jobdept` (`id`),
  CONSTRAINT `FKjxlxqfk3l5d3ckmctd95valt4` FOREIGN KEY (`salaries_id`) REFERENCES `salary` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `jobdept_salaries` */

/*Table structure for table `payroll` */

DROP TABLE IF EXISTS `payroll`;

CREATE TABLE `payroll` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `amount` double DEFAULT NULL,
  `date` date DEFAULT NULL,
  `jobId` bigint(20) DEFAULT NULL,
  `salaryId` bigint(20) DEFAULT NULL,
  `userId` bigint(20) DEFAULT NULL,
  `adminUserId` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK9chsrgh7ntl7j07rq2fblmbst` (`jobId`),
  KEY `FKs7btae31a9mmkdx8v5cq41e5i` (`salaryId`),
  KEY `FKlic05p3jeivlf1p6ky9g31kwm` (`userId`),
  KEY `FK5aqisa66puj6vmci5p7ol5hyn` (`adminUserId`),
  CONSTRAINT `FK5aqisa66puj6vmci5p7ol5hyn` FOREIGN KEY (`adminUserId`) REFERENCES `appuser` (`id`),
  CONSTRAINT `FK9chsrgh7ntl7j07rq2fblmbst` FOREIGN KEY (`jobId`) REFERENCES `jobdept` (`id`),
  CONSTRAINT `FKlic05p3jeivlf1p6ky9g31kwm` FOREIGN KEY (`userId`) REFERENCES `appuser` (`id`),
  CONSTRAINT `FKs7btae31a9mmkdx8v5cq41e5i` FOREIGN KEY (`salaryId`) REFERENCES `salary` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

/*Data for the table `payroll` */

insert  into `payroll`(`id`,`amount`,`date`,`jobId`,`salaryId`,`userId`,`adminUserId`) values 
(1,1000,'2022-03-29',3,1,4,NULL),
(2,1000,'2022-03-29',NULL,1,4,1);

/*Table structure for table `role` */

DROP TABLE IF EXISTS `role`;

CREATE TABLE `role` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `roleName` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

/*Data for the table `role` */

insert  into `role`(`id`,`roleName`) values 
(1,'ROLE_ADMIN'),
(2,'ROLE_USER');

/*Table structure for table `salary` */

DROP TABLE IF EXISTS `salary`;

CREATE TABLE `salary` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `amount` double DEFAULT NULL,
  `jobId` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK3dj4ckh3bg7dmjilg85iirglc` (`jobId`),
  CONSTRAINT `FK3dj4ckh3bg7dmjilg85iirglc` FOREIGN KEY (`jobId`) REFERENCES `jobdept` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

/*Data for the table `salary` */

insert  into `salary`(`id`,`amount`,`jobId`) values 
(1,1000,3);

/*Table structure for table `wallet` */

DROP TABLE IF EXISTS `wallet`;

CREATE TABLE `wallet` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `balance` double DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

/*Data for the table `wallet` */

insert  into `wallet`(`id`,`balance`) values 
(1,499999000),
(2,0),
(3,2000);

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
