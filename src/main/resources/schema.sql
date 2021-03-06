
DROP TABLE IF EXISTS billing_address;

CREATE TABLE billing_address (
  id bigint(20) NOT NULL AUTO_INCREMENT,
  billingAddressCity varchar(255),
  billingAddressCountry varchar(255),
  billingAddressName varchar(255),
  billingAddressState varchar(255),
  billingAddressStreet1 varchar(255),
  billingAddressStreet2 varchar(255),
  billingAddressZipCode varchar(255),
  order_id bigint  (20),
  PRIMARY KEY (id)
);

DROP TABLE IF EXISTS Book;
DROP TABLE IF EXISTS `book`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `book` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `active` bit(1) NOT NULL,
  `author` varchar(255) DEFAULT NULL,
  `category` varchar(255) DEFAULT NULL,
  `description` text,
  `format` varchar(255) DEFAULT NULL,
  `in_stock_number` int(11) NOT NULL,
  `isbn` int(11) NOT NULL,
  `language` varchar(255) DEFAULT NULL,
  `list_price` double NOT NULL,
  `number_of_pages` varchar(255) DEFAULT NULL,
  `our_price` double NOT NULL,
  `publication_date` varchar(255) DEFAULT NULL,
  `publisher` varchar(255) DEFAULT NULL,
  `shipping_weight` double NOT NULL,
  `title` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


DROP TABLE IF EXISTS `book_to_cart_item`;

CREATE TABLE `book_to_cart_item` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `book_id` bigint(20) DEFAULT NULL,
  `cart_item_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
);

DROP TABLE IF EXISTS cart_item;

DROP TABLE IF EXISTS `cart_item`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `cart_item` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `qty` int(11) NOT NULL,
  `sub_total` decimal(19,2) DEFAULT NULL,
  `book_id` bigint(20) DEFAULT NULL,
  `order_id` bigint(20) DEFAULT NULL,
  `shopping_cart_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
);

DROP TABLE IF EXISTS `password_reset_token`;

CREATE TABLE `password_reset_token` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `expiry_date` datetime DEFAULT NULL,
  `token` varchar(255) DEFAULT NULL,
  `user_id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`)
);

DROP TABLE IF EXISTS `payment`;

CREATE TABLE `payment` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `card_name` varchar(255) DEFAULT NULL,
  `card_number` varchar(255) DEFAULT NULL,
  `cvc` int(11) NOT NULL,
  `expiry_month` int(11) NOT NULL,
  `expiry_year` int(11) NOT NULL,
  `holder_name` varchar(255) DEFAULT NULL,
  `type` varchar(255) DEFAULT NULL,
  `order_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
);

DROP TABLE IF EXISTS `role`;

CREATE TABLE `role` (
  `roleid` int(11) NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  `role_id` int(11) NOT NULL,
  PRIMARY KEY (`roleid`)
);
DROP TABLE IF EXISTS `shipping_address`;

CREATE TABLE `shipping_address` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `shipping_address_city` varchar(255) DEFAULT NULL,
  `shipping_address_country` varchar(255) DEFAULT NULL,
  `shipping_address_name` varchar(255) DEFAULT NULL,
  `shipping_address_state` varchar(255) DEFAULT NULL,
  `shipping_address_street1` varchar(255) DEFAULT NULL,
  `shipping_address_street2` varchar(255) DEFAULT NULL,
  `shipping_address_zip_code` varchar(255) DEFAULT NULL,
  `order_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ;
  DROP TABLE IF EXISTS `shopping_cart`;

 CREATE TABLE `shopping_cart` (
    `id` bigint(20) NOT NULL AUTO_INCREMENT,
    `grand_total` decimal(19,2) DEFAULT NULL,
    `user_id` bigint(20) DEFAULT NULL,
    PRIMARY KEY (`id`)
 );




DROP TABLE IF EXISTS `user`;

CREATE TABLE `user` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `email` varchar(255) NOT NULL,
  `enabled` bit(1) NOT NULL,
  `firstname` varchar(255) DEFAULT NULL,
  `lastname` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `phone` varchar(255) DEFAULT NULL,
  `username` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
);


DROP TABLE IF EXISTS `user_billing`;
 CREATE TABLE `user_billing` (
   `id`                    BIGINT (20) NOT NULL AUTO_INCREMENT,
   `user_billing_city`     VARCHAR(255) DEFAULT NULL,
   `user_billing_country`  VARCHAR(255) DEFAULT NULL,
   `user_billing_name`     VARCHAR(255) DEFAULT NULL,
   `user_billing_state`    VARCHAR(255) DEFAULT NULL,
   `user_billing_street1`  VARCHAR(255) DEFAULT NULL,
   `user_billing_street2`  VARCHAR(255) DEFAULT NULL,
   `user_billing_zip_code` VARCHAR(255) DEFAULT NULL,
   `user_payment_id`       BIGINT (20) DEFAULT NULL,
   PRIMARY KEY (`id`)
 );


DROP TABLE IF EXISTS `user_id`;

CREATE TABLE `user_id` (
  `id`                  BIGINT (20) NOT NULL AUTO_INCREMENT,
  `order_date`          DATETIME       DEFAULT NULL,
  `order_status`        VARCHAR(255)   DEFAULT NULL,
  `order_total`         DECIMAL(19, 2) DEFAULT NULL,
  `shipping_date`       DATETIME       DEFAULT NULL,
  `shipping_method`     VARCHAR(255)   DEFAULT NULL,
  `billing_address_id`  BIGINT (20) DEFAULT NULL,
  `payment_id`          BIGINT (20) DEFAULT NULL,
  `shipping_address_id` BIGINT (20) DEFAULT NULL,
  `user_id`             BIGINT (20) DEFAULT NULL,
  PRIMARY KEY (`id`)
);



DROP TABLE IF EXISTS `user_payment`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user_payment` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `card_name` varchar(255) DEFAULT NULL,
  `card_number` varchar(255) DEFAULT NULL,
  `cvc` int(11) NOT NULL,
  `default_payment` bit(1) NOT NULL,
  `expiry_month` int(11) NOT NULL,
  `expiry_year` int(11) NOT NULL,
  `holder_name` varchar(255) DEFAULT NULL,
  `type` varchar(255) DEFAULT NULL,
  `user_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
);

DROP TABLE IF EXISTS `user_role`;

CREATE TABLE `user_role` (
  `user_role_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `role_id` int(11) DEFAULT NULL,
  `user_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`user_role_id`)
);

DROP TABLE IF EXISTS `user_shipping`;

CREATE TABLE `user_shipping` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `user_shipping_city` varchar(255) DEFAULT NULL,
  `user_shipping_country` varchar(255) DEFAULT NULL,
  `user_shipping_default` bit(1) NOT NULL,
  `user_shipping_name` varchar(255) DEFAULT NULL,
  `user_shipping_state` varchar(255) DEFAULT NULL,
  `user_shipping_street1` varchar(255) DEFAULT NULL,
  `user_shipping_street2` varchar(255) DEFAULT NULL,
  `user_shipping_zip_code` varchar(255) DEFAULT NULL,
  `user_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
);



 alter table billing_address  drop constraint if exists FKn9o6nq40aqjyebaofkolmgv69;
alter table billing_address add constraint FKn9o6nq40aqjyebaofkolmgv69 foreign key (order_id) references user_id ;


 alter table book_to_cart_item drop constraint if exists FKbdyqr108hc7c06xtem0dhv9mk;
alter table book_to_cart_item add constraint FKbdyqr108hc7c06xtem0dhv9mk foreign key (cart_item_id) references cart_item ;


alter table book_to_cart_item drop constraint if exists FK254kg9aacrs8uqa93ijc3garu ;
alter table book_to_cart_item add constraint FK254kg9aacrs8uqa93ijc3garu foreign key (book_id) references book(id);

 alter table cart_item drop constraint if exists FKbfad2pd4ooykdqmav0oaynd1b;
alter table cart_item add constraint FKbfad2pd4ooykdqmav0oaynd1b foreign key (order_id) references user_id ;

 alter table cart_item drop constraint if exists FKe89gjdx91fxnmkkssyoim8xfu;
alter table cart_item add constraint FKe89gjdx91fxnmkkssyoim8xfu foreign key (shopping_cart_id) references shopping_cart ;

 alter table cart_item drop constraint if exists FKis5hg85qbs5d91etr4mvd4tx6;
alter table cart_item add constraint FKis5hg85qbs5d91etr4mvd4tx6 foreign key (book_id) references book ;


 alter table password_reset_token drop constraint if exists FK5lwtbncug84d4ero33v3cfxvl;
alter table password_reset_token add constraint FK5lwtbncug84d4ero33v3cfxvl foreign key (user_id) references user ;


 alter table payment drop constraint if exists FK919xn6cn5gio3mf7a7imk0aep;
alter table payment add constraint FK919xn6cn5gio3mf7a7imk0aep foreign key (order_id) references user_id ;


 alter table shipping_address drop constraint if exists FK13e0im0d0g0etv74kpk1lhks6;
alter table shipping_address add constraint FK13e0im0d0g0etv74kpk1lhks6 foreign key (order_id) references user_id ;

  alter table shopping_cart drop constraint if exists FK254qp5akhuaaj9n5co4jww3fk;
  alter table shopping_cart add constraint FK254qp5akhuaaj9n5co4jww3fk foreign key (user_id) references user(id) ;


 alter table user_billing drop constraint if exists  FK3v6hd7snyc3g9s72u41k1fydu;
alter table user_billing add constraint FK3v6hd7snyc3g9s72u41k1fydu foreign key (user_payment_id) references user_payment ;


 alter table user_id drop constraint if exists FK7stlup30t5kexwbo4tlss2k4x;
alter table user_id add constraint FK7stlup30t5kexwbo4tlss2k4x foreign key (shipping_address_id) references shipping_address ;


 alter table user_id drop constraint if exists FKj1qsusvrhf359t8cgg9fm5r8o;
alter table user_id add constraint FKj1qsusvrhf359t8cgg9fm5r8o foreign key (user_id) references user ;

 alter table user_id drop constraint if exists FKlvpbjicc57x0f2j2d1o0tj9e5;
alter table user_id add constraint FKlvpbjicc57x0f2j2d1o0tj9e5 foreign key (billing_address_id) references billing_address ;

 alter table user_id drop constraint if exists FKqb8pss8wf8dop8m3fdwl1rot5;
alter table user_id add constraint FKqb8pss8wf8dop8m3fdwl1rot5 foreign key (payment_id) references payment ;


 alter table user_payment drop constraint if exists FK8fb9fr82lb1qk2cw55ito9rk6;
alter table user_payment add constraint FK8fb9fr82lb1qk2cw55ito9rk6 foreign key (user_id) references user ;


alter table user_role drop constraint if exists  FK859n2jvi8ivhui0rl0esws6o;
alter table user_role add constraint FK859n2jvi8ivhui0rl0esws6o foreign key (user_id) references user ;

 alter table user_role drop constraint if exists FKa68196081fvovjhkek5m97n3y;
alter table user_role add constraint FKa68196081fvovjhkek5m97n3y foreign key (role_id) references role (roleid);


 alter table user_shipping drop constraint if exists FK9hidca5hndj9y0b5jb0xtpn9u;
alter table user_shipping add constraint FK9hidca5hndj9y0b5jb0xtpn9u foreign key (user_id) references user ;

