CREATE TABLE IF NOT EXISTS `person` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `first_name` varchar(255) NOT NULL,
  `last_name` varchar(255) NOT NULL,
  `endereco` varchar(200) DEFAULT NULL,
  `gender` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
)

