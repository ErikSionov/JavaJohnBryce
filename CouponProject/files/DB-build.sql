-- DB build for Coupon Project
create schema `coupons_db`;

drop tables company, customer,customer_vs_coupon,coupon;

-- company table creation
create table coupons_db.`company`(
`ID` int auto_increment,
`NAME` varchar(100) ,
`EMAIL` varchar(100),
`PASSWORD` varchar(30),
primary key(id)
);

-- coupon table creation
create table coupons_db.`coupon`(
`ID` int auto_increment,
`COMPANY_ID` int ,
`CATEGORY_ID` enum('ELECTRONICS','VACATION','FOOD','RESTAURANTS','HOME','CLOTHING','CAMPING','CONCERTS'),
`TITLE` varchar(200) ,
`DESCRIPTION` varchar(255) ,
`START_DATE` date,
`END_DATE` date,
`AMOUNT` int,
`PRICE` double,
`IMAGE` varchar(255),
primary key(id),
foreign key(company_id) references company(id)
);

-- customers table creation
create table coupons_db.`customer`(
`ID` int auto_increment,
`FIRST_NAME` varchar(100) ,
`LAST_NAME` varchar(100) ,
`EMAIL` varchar(100),
`PASSWORD` varchar(30),
primary key(id)
);

-- customer_vs_coupon table creation
create table coupons_db.`customer_vs_coupon`(
`CUSTOMER_ID` int,
`COUPON_ID` int ,
primary key(CUSTOMER_ID, COUPON_ID),
foreign key(CUSTOMER_ID) references customer(ID),
foreign key(COUPON_ID) references coupon(ID)
);

insert into company values(1, 'aaa', 'aaa@mail', 'aaaPass');
insert into company values(2, 'bbb', 'bbb@mail', 'bbbPass');
insert into company values(3, 'ccc', 'ccc@mail', 'cccPass');
insert into company values(4, 'ddd', 'ddd@mail', 'dddPass');
insert into company values(5, 'eee', 'eee@mail', 'eeePass');

insert into customer values(1, 'erik', 'sionov', 'ggg@mail', 'gggPass');
insert into customer values(2, 'yossi', 'doron', 'yyy@mail', 'yossipass');
insert into customer values(3, 'efi', 'daskal', 'eee@mail', 'efipass');
insert into customer values(4, 'purak', 'ponzi', 'ppp@mail', 'durakpass');
insert into customer values(5, 'lolza', 'lolzon', 'lll@mail', 'lelpass');


insert into coupon values(2,'2', 'FOOD', 'jello shot free', 'get a free jello shot with each purchase', '2019-12-12', '2022-12-12', '100', '75', 'image-2');
insert into coupon values(3,'3', 'HOME', 'lamp coupon', 'more light in house', '2019-12-12', '2023-12-12', '100', '150', 'image-3');
insert into coupon values(4,'3', 'HOME', 'fruits', 'plastic decorative fruits', '2019-12-12', '2023-12-12', '100', '12.20', 'image-4');
insert into coupon values(5,'4', 'CLOTHING', 'hats', 'viking hats sale', '2019-12-12', '2022-12-12', '100', '45.50', 'image-5');
insert into coupon values(1,'2', 'CAMPING', 'free tent', 'get a free tent with each purchase', '2019-12-12', '2020-12-12', '100', '15', 'image-1');

insert into customer_vs_coupon values(2,4);
insert into customer_vs_coupon values(2,5);
insert into customer_vs_coupon values(2,1);
insert into customer_vs_coupon values(3,1);
insert into customer_vs_coupon values(4,2);
insert into customer_vs_coupon values(5,3);
insert into customer_vs_coupon values(5,4);
insert into customer_vs_coupon values(5,5);
insert into customer_vs_coupon values(1,5);
insert into customer_vs_coupon values(1,4);


