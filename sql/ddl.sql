CREATE DATABASE IF NOT EXISTS `dynamic_advertisement`;

USE `dynamic_advertisement`;

DROP TABLE IF EXISTS `dad_module`;
CREATE TABLE IF NOT EXISTS `dad_module`
(
    `id`          BIGINT UNSIGNED  NOT NULL AUTO_INCREMENT COMMENT '主键ID',
    `code`        VARCHAR(20)      NOT NULL DEFAULT '' COMMENT '标识',
    `name`        VARCHAR(20)      NOT NULL DEFAULT '' COMMENT '名称',
    `image`       VARCHAR(512)     NOT NULL DEFAULT '' COMMENT '图片',
    `description` VARCHAR(255)     NOT NULL DEFAULT '' COMMENT '描述',
    `is_enabled`  TINYINT UNSIGNED NOT NULL DEFAULT 0 COMMENT '状态 1-上架 0-下架',
    `create_time` DATETIME         NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `update_time` DATETIME         NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后更新时间',
    PRIMARY KEY (`id`) USING BTREE,
    UNIQUE KEY uk_code (`code`) USING BTREE
) ENGINE = INNODB COMMENT = '动态广告-模块项';

DROP TABLE IF EXISTS `dad_config`;
CREATE TABLE IF NOT EXISTS `dad_config`
(
    `id`          BIGINT UNSIGNED  NOT NULL AUTO_INCREMENT COMMENT '主键ID',
    `module_id`   BIGINT UNSIGNED  NOT NULL DEFAULT 0 COMMENT '模块ID',
    `code`        VARCHAR(20)      NOT NULL DEFAULT '' COMMENT '标识',
    `name`        VARCHAR(20)      NOT NULL DEFAULT '' COMMENT '名称',
    `description` VARCHAR(255)     NOT NULL DEFAULT '' COMMENT '描述',
    `image`       VARCHAR(512)     NOT NULL DEFAULT '' COMMENT '图片',
    `sort`        TINYINT          NOT NULL DEFAULT 0 COMMENT '排序 越大优先级越高',
    `is_enabled`  TINYINT UNSIGNED NOT NULL DEFAULT 0 COMMENT '状态 1-上架 0-下架',
    `create_time` DATETIME         NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `update_time` DATETIME         NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后更新时间',
    PRIMARY KEY (`id`) USING BTREE,
    KEY idx_module_id (`module_id`) USING BTREE
) ENGINE = INNODB COMMENT = '动态广告-配置项';

DROP TABLE IF EXISTS `dad_config_metadata`;
CREATE TABLE IF NOT EXISTS `dad_config_metadata`
(
    `id`             BIGINT UNSIGNED  NOT NULL AUTO_INCREMENT COMMENT '主键ID',
    `attribute_id`   BIGINT UNSIGNED  NOT NULL DEFAULT 0 COMMENT '属性ID',
    `is_required`    TINYINT UNSIGNED NOT NULL DEFAULT 0 COMMENT '是否必须填写',
    `value_type`     TINYINT          NOT NULL DEFAULT 0 COMMENT '值类型',
    `value_limit`    TINYINT          NOT NULL DEFAULT 0 COMMENT '值限制 0-不限制 1-限制',
    `value_range`    VARCHAR(512)     NOT NULL DEFAULT '' COMMENT '取值范围:JSON',
    `format_type`    TINYINT          NOT NULL DEFAULT 0 COMMENT '内容格式校验类型',
    `format_rule`    VARCHAR(512)     NOT NULL DEFAULT '' COMMENT '内容格式校验规则',
    `validated_type` TINYINT          NOT NULL DEFAULT 0 COMMENT '业务校验类型',
    `validated_rule` VARCHAR(512)     NOT NULL DEFAULT '' COMMENT '业务校验规则',
    `create_time`    DATETIME         NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `update_time`    DATETIME         NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后更新时间',
    PRIMARY KEY (`id`) USING BTREE,
    KEY idx_attribute_id (`attribute_id`) USING BTREE
) ENGINE = INNODB COMMENT = '动态广告-配置项元数据';

DROP TABLE IF EXISTS `dad_config_attribute`;
CREATE TABLE IF NOT EXISTS `dad_config_attribute`
(
    `id`          BIGINT UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键ID',
    `key`         VARCHAR(20)     NOT NULL DEFAULT '' COMMENT '键',
    `name`        VARCHAR(20)     NOT NULL DEFAULT '' COMMENT '名称',
    `description` VARCHAR(255)    NOT NULL DEFAULT '' COMMENT '描述',
    `create_time` DATETIME        NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `update_time` DATETIME        NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后更新时间',
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = INNODB COMMENT = '动态广告-配置项属性';

DROP TABLE IF EXISTS `dad_config_value_int`;
CREATE TABLE IF NOT EXISTS `dad_config_value_int`
(
    `id`           BIGINT UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键ID',
    `config_id`    BIGINT UNSIGNED NOT NULL DEFAULT 0 COMMENT '属性ID',
    `attribute_id` BIGINT UNSIGNED NOT NULL DEFAULT 0 COMMENT '属性ID',
    `value`        INT             NOT NULL DEFAULT 0 COMMENT '值',
    `is_null`      TINYINT         NOT NULL DEFAULT 0 COMMENT '是否为空',
    `create_time`  DATETIME        NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `update_time`  DATETIME        NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后更新时间',
    PRIMARY KEY (`id`) USING BTREE,
    KEY idx_config_id (`config_id`) USING BTREE
) ENGINE = INNODB COMMENT = '动态广告-配置项INT值';

DROP TABLE IF EXISTS `dad_config_value_long`;
CREATE TABLE IF NOT EXISTS `dad_config_value_long`
(
    `id`           BIGINT UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键ID',
    `config_id`    BIGINT UNSIGNED NOT NULL DEFAULT 0 COMMENT '属性ID',
    `attribute_id` BIGINT UNSIGNED NOT NULL DEFAULT 0 COMMENT '属性ID',
    `value`        BIGINT          NOT NULL DEFAULT 0 COMMENT '值',
    `is_null`      TINYINT         NOT NULL DEFAULT 0 COMMENT '是否为空',
    `create_time`  DATETIME        NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `update_time`  DATETIME        NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后更新时间',
    PRIMARY KEY (`id`) USING BTREE,
    KEY idx_config_id (`config_id`) USING BTREE
) ENGINE = INNODB COMMENT = '动态广告-配置项LONG值';

DROP TABLE IF EXISTS `dad_config_value_string`;
CREATE TABLE IF NOT EXISTS `dad_config_value_string`
(
    `id`           BIGINT UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键ID',
    `config_id`    BIGINT UNSIGNED NOT NULL DEFAULT 0 COMMENT '属性ID',
    `attribute_id` BIGINT UNSIGNED NOT NULL DEFAULT 0 COMMENT '属性ID',
    `value`        TEXT                     DEFAULT NULL COMMENT '值',
    `is_null`      TINYINT         NOT NULL DEFAULT 0 COMMENT '是否为空',
    `create_time`  DATETIME        NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `update_time`  DATETIME        NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后更新时间',
    PRIMARY KEY (`id`) USING BTREE,
    KEY idx_config_id (`config_id`) USING BTREE
) ENGINE = INNODB COMMENT = '动态广告-配置项STRING值';
