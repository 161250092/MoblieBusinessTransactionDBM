create database mobilebussinessDB;
use mobilebussinessDB;
-- 用户表

create table if not exists `users`
(
-- `userId` int primary key auto_increment,
`phoneNumber`   varchar(20),
`balance`   double(16,2),
PRIMARY KEY (`phoneNumber`)
)ENGINE = InnoDB DEFAULT CHARSET =utf8;




-- 用户月数据
create table if not exists `userDataPerMonth`
(
`phoneNumber`   varchar(20),
`d_date`  date,

`callDuration`  double(16,2),
`calledDuration` double(16,2),

`mailsNumber`  int,
`localDataFlow`   double(16,2),
`inlandData`   double(16,2),
primary key(`phoneNumber`,`date`)
)ENGINE = InnoDB DEFAULT CHARSET =utf8;


create table if not exists `userCombos`
(
`phoneNumber`   varchar(20),
`c_date`   date,
`comboId`   int
)ENGINE = InnoDB DEFAULT CHARSET =utf8;

create table if not exists `comboKinds`
(
`comboId`   int primary key auto_increment,
`comboName` varchar(20),

`free_phoneTime`  int,
`phone_excessCost` double(16,2),

`free_mails`   int,
`mail_excessCost`  double(16,2),

`free_localDataFlow`  int,
`localDataFlow_excessCost`  double(16,2),

`free_inlandDataFlow`  int,
`inlandDataFlow_excessCost`  double(16,2),

`cost`   double(16,2)
)ENGINE = InnoDB DEFAULT CHARSET =utf8;

use mobilebussinessDB;
-- 插入基准资费
insert into combokinds(comboName,free_phoneTime,phone_excessCost,free_mails,mail_excessCost,free_localDataFlow,localDataFlow_excessCost,
free_inlandDataFlow,inlandDataFlow_excessCost,cost)values('standard',0,0.5,0,0.1,0,2,0,5,0.00);
-- 四种基础套餐
insert into combokinds(comboName,free_phoneTime,phone_excessCost,free_mails,mail_excessCost,free_localDataFlow,localDataFlow_excessCost,
free_inlandDataFlow,inlandDataFlow_excessCost,cost)values('p1',100,0.5,0,0.1,0,2,0,5,20.00);

insert into combokinds(comboName,free_phoneTime,phone_excessCost,free_mails,mail_excessCost,free_localDataFlow,localDataFlow_excessCost,
free_inlandDataFlow,inlandDataFlow_excessCost,cost)values('m1',0,0.5,200,0.1,0,2,0,5,10);

insert into combokinds(comboName,free_phoneTime,phone_excessCost,free_mails,mail_excessCost,free_localDataFlow,localDataFlow_excessCost,
free_inlandDataFlow,inlandDataFlow_excessCost,cost)values('l1',0,0.5,0,0.1,2048,3,0,5,20);

insert into combokinds(comboName,free_phoneTime,phone_excessCost,free_mails,mail_excessCost,free_localDataFlow,localDataFlow_excessCost,
free_inlandDataFlow,inlandDataFlow_excessCost,cost)values('i1',0,0.5,0,0.1,0,2,2048,3,30);



use mobilebussinessDB;
insert into users(phoneNumber,balance)values('18805199030',100.00);
insert into users(phoneNumber,balance)values('18805199031',100.00);
insert into users(phoneNumber,balance)values('18805199032',100.00);
insert into users(phoneNumber,balance)values('18805199033',100.00);
insert into users(phoneNumber,balance)values('18805199034',100.00);
insert into users(phoneNumber,balance)values('18805199035',100.00);
insert into users(phoneNumber,balance)values('18805199036',100.00);
insert into users(phoneNumber,balance)values('18805199037',100.00);
insert into users(phoneNumber,balance)values('18805199038',100.00);
insert into users(phoneNumber,balance)values('18805199039',100.00);
insert into users(phoneNumber,balance)values('18805199040',100.00);
insert into users(phoneNumber,balance)values('18805199041',100.00);
insert into users(phoneNumber,balance)values('18805199042',100.00);
insert into users(phoneNumber,balance)values('18805199043',100.00);
insert into users(phoneNumber,balance)values('18805199050',100.00);



use mobilebussinessDB;
insert into userDataPerMonth(phoneNumber,d_date,callDuration,calledDuration,mailsNumber,localDataFlow,inlandData)
values('18805199030',date(now()),0,0,0,0,0);
insert into userDataPerMonth(phoneNumber,d_date,callDuration,calledDuration,mailsNumber,localDataFlow,inlandData)
values('18805199031',date(now()),0,0,0,0,0);
insert into userDataPerMonth(phoneNumber,d_date,callDuration,calledDuration,mailsNumber,localDataFlow,inlandData)
values('18805199032',date(now()),0,0,0,0,0);
insert into userDataPerMonth(phoneNumber,d_date,callDuration,calledDuration,mailsNumber,localDataFlow,inlandData)
values('18805199033',date(now()),0,0,0,0,0);
insert into userDataPerMonth(phoneNumber,d_date,callDuration,calledDuration,mailsNumber,localDataFlow,inlandData)
values('18805199034',date(now()),0,0,0,0,0);
insert into userDataPerMonth(phoneNumber,d_date,callDuration,calledDuration,mailsNumber,localDataFlow,inlandData)
values('18805199035',date(now()),0,0,0,0,0);
insert into userDataPerMonth(phoneNumber,d_date,callDuration,calledDuration,mailsNumber,localDataFlow,inlandData)
values('18805199036',date(now()),0,0,0,0,0);
insert into userDataPerMonth(phoneNumber,d_date,callDuration,calledDuration,mailsNumber,localDataFlow,inlandData)
values('18805199037',date(now()),0,0,0,0,0);
insert into userDataPerMonth(phoneNumber,d_date,callDuration,calledDuration,mailsNumber,localDataFlow,inlandData)
values('18805199038',date(now()),0,0,0,0,0);
insert into userDataPerMonth(phoneNumber,d_date,callDuration,calledDuration,mailsNumber,localDataFlow,inlandData)
values('18805199039',date(now()),0,0,0,0,0);
insert into userDataPerMonth(phoneNumber,d_date,callDuration,calledDuration,mailsNumber,localDataFlow,inlandData)
values('18805199040',date(now()),0,0,0,0,0);
insert into userDataPerMonth(phoneNumber,d_date,callDuration,calledDuration,mailsNumber,localDataFlow,inlandData)
values('18805199041',date(now()),0,0,0,0,0);
insert into userDataPerMonth(phoneNumber,d_date,callDuration,calledDuration,mailsNumber,localDataFlow,inlandData)
values('18805199042',date(now()),0,0,0,0,0);
insert into userDataPerMonth(phoneNumber,d_date,callDuration,calledDuration,mailsNumber,localDataFlow,inlandData)
values('18805199043',date(now()),0,0,0,0,0);
insert into userDataPerMonth(phoneNumber,d_date,callDuration,calledDuration,mailsNumber,localDataFlow,inlandData)
values('18805199050',date(now()),0,0,0,0,0);

use mobilebussinessDB;
insert into userCombos(phoneNumber,c_date,comboId)values('18805199030',date(now()),1);
insert into userCombos(phoneNumber,c_date,comboId)values('18805199031',date(now()),1);
insert into userCombos(phoneNumber,c_date,comboId)values('18805199032',date(now()),1);
insert into userCombos(phoneNumber,c_date,comboId)values('18805199033',date(now()),1);
insert into userCombos(phoneNumber,c_date,comboId)values('18805199034',date(now()),1);
insert into userCombos(phoneNumber,c_date,comboId)values('18805199035',date(now()),1);
insert into userCombos(phoneNumber,c_date,comboId)values('18805199036',date(now()),1);
insert into userCombos(phoneNumber,c_date,comboId)values('18805199037',date(now()),1);
insert into userCombos(phoneNumber,c_date,comboId)values('18805199038',date(now()),1);
insert into userCombos(phoneNumber,c_date,comboId)values('18805199039',date(now()),1);
insert into userCombos(phoneNumber,c_date,comboId)values('18805199040',date(now()),1);
insert into userCombos(phoneNumber,c_date,comboId)values('18805199041',date(now()),1);
insert into userCombos(phoneNumber,c_date,comboId)values('18805199042',date(now()),1);
insert into userCombos(phoneNumber,c_date,comboId)values('18805199043',date(now()),1);
insert into userCombos(phoneNumber,c_date,comboId)values('18805199050',date(now()),1);
