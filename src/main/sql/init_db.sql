create table checkout
(
	userid integer not null
		constraint checkout_users_id_fk
			references users,
	name varchar(255) not null,
	email varchar(255) not null,
	telephone varchar(255) not null,
	billcountry varchar(255) not null,
	billcity varchar(255) not null,
	billzipcode integer,
	billaddress varchar(255) not null,
	shipcountry varchar(255) not null,
	shipcity varchar(255) not null,
	shipzipcode integer not null,
	shipaddress varchar(255) not null
)
;

create table product
(
	id integer not null
		constraint product_pkey
			primary key,
	name varchar(255) not null,
	description text,
	price double precision not null,
	currency varchar(3) not null,
	productcategoryid integer not null
		constraint product_productcategory_id_fk
			references productcategory,
	supplierid integer not null
		constraint product_supplier_id_fk
			references supplier
)
;

create unique index product_id_uindex
	on product (id)
;

create table productcategory
(
	id integer not null
		constraint productcategory_pkey
			primary key,
	name varchar(255) not null,
	department varchar(255),
	description varchar(255)
)
;

create unique index productcategory_id_uindex
	on productcategory (id)
;

create table supplier
(
	id integer not null
		constraint supplier_pkey
			primary key,
	name varchar(255) not null,
	description varchar(255)
)
;

create unique index supplier_id_uindex
	on supplier (id)
;

create table userorder
(
	userid integer not null
		constraint userorder_product_id_fk
			references product
		constraint userorder_users_id_fk
			references users,
	productid integer not null
)
;

create table users
(
	id integer not null
		constraint user_pkey
			primary key,
	name varchar(255) not null,
	password varchar(255) not null
)
;

create unique index user_id_uindex
	on users (id)
;

create unique index user_name_uindex
	on users (email)
;