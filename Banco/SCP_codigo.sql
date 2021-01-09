-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema SCP
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema SCP
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `SCP` ;
USE `SCP` ;

-- -----------------------------------------------------
-- Table `SCP`.`produtos`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `SCP`.`produtos` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `discriminacao` VARCHAR(45) NOT NULL,
  `valor_unitario` DECIMAL(8,2) NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `SCP`.`clientes`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `SCP`.`clientes` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `nome` VARCHAR(50) NOT NULL,
  `cpf` VARCHAR(11) NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `SCP`.`nota_fiscal`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `SCP`.`nota_fiscal` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `data_emissao` DATE NOT NULL,
  `valor` DECIMAL(8,2) NOT NULL,
  `id_cliente` INT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_nota_fiscal_1_idx` (`id_cliente` ASC),
  CONSTRAINT `fk_nota_fiscal_1`
    FOREIGN KEY (`id_cliente`)
    REFERENCES `SCP`.`clientes` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `SCP`.`enderecos`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `SCP`.`enderecos` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `cidade` VARCHAR(50) NOT NULL,
  `estado` VARCHAR(2) NOT NULL,
  `logradouro` VARCHAR(50) NOT NULL,
  `bairro` VARCHAR(50) NOT NULL,
  `numero` VARCHAR(50) NOT NULL,
  `cep` VARCHAR(50) NOT NULL,
  `id_cliente` INT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_endereco_1_idx` (`id_cliente` ASC),
  CONSTRAINT `fk_endereco_1`
    FOREIGN KEY (`id_cliente`)
    REFERENCES `SCP`.`clientes` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `SCP`.`telefones`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `SCP`.`telefones` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `numero` VARCHAR(20) NOT NULL,
  `id_cliente` INT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_telefones_1_idx` (`id_cliente` ASC),
  CONSTRAINT `fk_telefones_1`
    FOREIGN KEY (`id_cliente`)
    REFERENCES `SCP`.`clientes` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `SCP`.`produtos_nota_fiscal`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `SCP`.`produtos_nota_fiscal` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `id_nota_fiscal` INT NOT NULL,
  `id_produto` INT NOT NULL,
  `quantidade` INT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_produtos_nota_fiscal_1_idx` (`id_nota_fiscal` ASC),
  INDEX `fk_produtos_nota_fiscal_2_idx` (`id_produto` ASC),
  CONSTRAINT `fk_produtos_nota_fiscal_1`
    FOREIGN KEY (`id_nota_fiscal`)
    REFERENCES `SCP`.`nota_fiscal` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_produtos_nota_fiscal_2`
    FOREIGN KEY (`id_produto`)
    REFERENCES `SCP`.`produtos` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
