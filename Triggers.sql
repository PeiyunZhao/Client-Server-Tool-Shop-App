DROP TRIGGER IF EXISTS `TOOLSHOP`.`TOOL_AFTER_UPDATE`;

DELIMITER $$
USE `TOOLSHOP`$$
CREATE DEFINER = CURRENT_USER TRIGGER `TOOLSHOP`.`TOOL_AFTER_UPDATE` AFTER UPDATE ON `TOOL` FOR EACH ROW
BEGIN
IF (NEW.Ttype = 'E') THEN
INSERT INTO ELECTRICAL VALUES (NEW.ToolID,NULL);
END IF;
END;$$
DELIMITER ;

DROP TRIGGER IF EXISTS `TOOLSHOP`.`TOOL_AFTER_INSERT`;

DELIMITER $$
USE `TOOLSHOP`$$
CREATE DEFINER = CURRENT_USER TRIGGER `TOOLSHOP`.`TOOL_AFTER_INSERT` AFTER INSERT ON `TOOL` FOR EACH ROW
BEGIN
IF (NEW.Ttype = 'E') THEN
INSERT INTO ELECTRICAL VALUES (NEW.ToolID,NULL);
END IF;
END$$
DELIMITER ;


