select * from company;
select * from coupon;
select * from customer;
select * from customers_vs_coupons;

insert into company values(0,"apple@mail","apple","12345");
insert into company values(0,"google@mail","google","12345");
insert into company values(0,"facebook@mail","facebook","12345");

insert into customer values(0,"dani@mail","dani","yossef","12345");
insert into customer values(0,"mimi@mail","mimi","maimon","12345");
insert into customer values(0,"yossi@mail","yossi","ashkenazi","12345");

insert into coupon values(0,100,"HOME","home appliance 2+1","2023-12-12","image2",25.5,"2022-01-01","home coupon",1);
insert into coupon values(0,100,"ELECTRONICS","free toaster","2023-12-12","image3",25.5,"2022-01-01","toaster coupon",2);
insert into coupon values(0,50,"ELECTRONICS","free minifridge","2023-12-12","image3",25.5,"2022-01-01","fridge coupon",2);
insert into coupon values(0,10,"TRAVEL","free museum enterance","2023-12-12","image3",25.5,"2022-01-01","travel coupon",3);