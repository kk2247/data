create database dataStructure;
use datastructure;
create table scenic_spot(
  id int auto_increment comment '景点ID',
  name varchar(50) not null comment '景点名称',
  introduce varchar(150) not null comment '景点简介',
  welcome int not null default 0 comment '欢迎度',
  relax int not null default 0 comment '休息区',
  toilet int not null default 0 comment '公厕',
  primary key (id)
)ENGINE=InnoDB default charset =utf8

create table line(
  id int not null auto_increment,
  length int not null ,
  side_name1 varchar(50) default null,
  side_name2 varchar(50) default null,
  primary key (id)
)ENGINE=InnoDB default charset = utf8

insert into line(side_name1,side_name2,length)
values
('北门', '狮子山' ,9)
('北门', '仙云石', 8)
('狮子山', '一线天', 7)
('狮子山', '飞流瀑', 6)
('仙云石' ,'仙武湖', 4)
('仙云石', '九曲桥', 5)
('仙武湖','九曲桥' ,7)
('一线天', '观云台' ,11)
('飞流瀑', '观云台' ,3)
('一线天' ,'花卉园', 10)
('观云台', '红叶亭', 15)
('花卉园', '红叶亭', 9)
('观云台', '碧水亭' ,16)
('仙武湖', '碧水亭', 20)
('朝日峰', '碧水潭', 17)
('朝日峰', '红叶亭', 10)
('九曲桥', '朝日峰' ,20)


insert into scenic_spot(name,introduce,welcome)
values
('北门','北门',0),
('狮子山','狮子山',0),
('仙云石','仙云石',0),
('一线天','一线天',0),
('飞流瀑','飞流瀑',0),
('仙武湖','仙武湖',0),
('九曲桥','九曲桥',0),
('观云台','观云台',0),
('花卉园','花卉园',0),
('红叶亭','红叶亭',0),
('碧水亭','碧水亭',0),
('朝日峰','朝日峰',0),
('碧水潭','碧水潭',0)

