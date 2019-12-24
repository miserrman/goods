DROP TABLE IF EXISTS `brand`;
CREATE TABLE `brand` (
  `id` bigint(11) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(31) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `pic_url` varchar(255) DEFAULT NULL,
  `gmt_create` datetime(2) DEFAULT NULL,
  `gmt_modified` datetime(2) DEFAULT NULL,
  `is_deleted` tinyint(1) unsigned DEFAULT '0',
  PRIMARY KEY (`id`)
);

-- ----------------------------
-- Table structure for goods
-- ----------------------------
DROP TABLE IF EXISTS `goods`;
CREATE TABLE `goods` (
  `id` bigint(11) unsigned NOT NULL AUTO_INCREMENT,
  `gmt_create` datetime(2) DEFAULT NULL,
  `gmt_modified` datetime(2) DEFAULT NULL,
  `name` varchar(31) DEFAULT NULL,
  `goods_sn` varchar(63) DEFAULT NULL,
  `short_name` varchar(31) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `brief` varchar(63) DEFAULT NULL,
  `pic_url` varchar(255) DEFAULT NULL,
  `detail` varchar(255) DEFAULT NULL,
  `status` tinyint(2) unsigned DEFAULT NULL,
  `share_url` varchar(255) DEFAULT NULL,
  `gallery` varchar(5000) DEFAULT NULL,
  `goods_category_id` bigint(11) unsigned DEFAULT NULL,
  `brand_id` bigint(11) unsigned DEFAULT NULL,
  `is_deleted` tinyint(1) unsigned DEFAULT '0',
  `weight` decimal(10,2) DEFAULT NULL,
  `volume` varchar(255) DEFAULT NULL,
  `special_freight_id` bigint(11) unsigned DEFAULT NULL,
  `is_special` tinyint(1) unsigned DEFAULT NULL,
  `price` decimal(10,2) DEFAULT NULL,
  PRIMARY KEY (`id`)
);

-- ----------------------------
-- Table structure for goods_category
-- ----------------------------
DROP TABLE IF EXISTS `goods_category`;
CREATE TABLE `goods_category` (
  `id` bigint(11) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `pid` bigint(11) unsigned DEFAULT NULL,
  `gmt_create` datetime(2) DEFAULT NULL,
  `gmt_modified` datetime(2) DEFAULT NULL,
  `is_deleted` tinyint(1) unsigned DEFAULT '1',
  `pic_url` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
);

-- ----------------------------
-- Table structure for groupon_rule
-- ----------------------------
DROP TABLE IF EXISTS `groupon_rule`;
CREATE TABLE `groupon_rule` (
  `id` bigint(11) unsigned NOT NULL AUTO_INCREMENT,
  `gmt_create` datetime(2) DEFAULT NULL,
  `gmt_modified` datetime(2) DEFAULT NULL,
  `is_deleted` tinyint(1) unsigned DEFAULT '0',
  `start_time` datetime(2) DEFAULT NULL,
  `end_time` datetime(2) DEFAULT NULL,
  `status` tinyint(2) unsigned DEFAULT NULL,
  `groupon_level_strategy` varchar(255) DEFAULT NULL,
  `goods_id` bigint(11) unsigned DEFAULT NULL,
  PRIMARY KEY (`id`)
);

-- ----------------------------
-- Table structure for log
-- ----------------------------
DROP TABLE IF EXISTS `log`;
CREATE TABLE `log` (
  `id` bigint(11) unsigned NOT NULL AUTO_INCREMENT,
  `admin_id` bigint(11) unsigned DEFAULT NULL,
  `ip` varchar(31) DEFAULT NULL,
  `type` tinyint(4) unsigned DEFAULT NULL,
  `action` varchar(63) DEFAULT NULL,
  `status` tinyint(4) DEFAULT NULL,
  `gmt_create` datetime(2) DEFAULT NULL,
  `gmt_modified` datetime(2) DEFAULT NULL,
  `action_id` bigint(11) unsigned DEFAULT NULL,
  PRIMARY KEY (`id`)
);

-- ----------------------------
-- Table structure for product
-- ----------------------------
DROP TABLE IF EXISTS `product`;
CREATE TABLE `product` (
  `id` bigint(11) unsigned NOT NULL AUTO_INCREMENT,
  `pic_url` varchar(255) DEFAULT NULL,
  `specifications` varchar(1000) DEFAULT NULL,
  `goods_id` bigint(11) unsigned DEFAULT NULL,
  `price` decimal(10,2) DEFAULT NULL,
  `safety_stock` int(11) unsigned DEFAULT NULL,
  `gmt_create` datetime(2) DEFAULT NULL,
  `gmt_modified` datetime(2) DEFAULT NULL,
  `is_deleted` tinyint(1) unsigned DEFAULT '0',
  PRIMARY KEY (`id`)
);
