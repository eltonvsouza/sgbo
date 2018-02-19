SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL';

CREATE SCHEMA IF NOT EXISTS `sgbo` ;
USE `sgbo`;

-- -----------------------------------------------------
-- Table `sgbo`.`usuario`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `sgbo`.`usuario` (
  `idUsuario` INT NOT NULL AUTO_INCREMENT ,
  `nome` VARCHAR(50) NOT NULL ,
  `login` VARCHAR(10) NOT NULL ,
  `senha` VARCHAR(10) NOT NULL ,
  `funcao` VARCHAR(45) NULL ,
  PRIMARY KEY (`idUsuario`) )
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `sgbo`.`causaMorte`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `sgbo`.`causaMorte` (
  `idCausaMorte` INT NOT NULL AUTO_INCREMENT ,
  `descricao` VARCHAR(50) NOT NULL ,
  PRIMARY KEY (`idCausaMorte`) )
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `sgbo`.`estabelecimento`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `sgbo`.`estabelecimento` (
  `idEstabelecimento` INT NOT NULL AUTO_INCREMENT ,
  `nome` VARCHAR(50) NOT NULL ,
  `endereco` VARCHAR(100) NULL ,
  `telefone` VARCHAR(9) NULL ,
  PRIMARY KEY (`idEstabelecimento`) )
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `sgbo`.`medico`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `sgbo`.`medico` (
  `idMedico` INT NOT NULL AUTO_INCREMENT ,
  `nome` VARCHAR(50) NOT NULL ,
  `endereco` VARCHAR(100) NULL ,
  `telefone` VARCHAR(9) NULL ,
  `crm` VARCHAR(10) NOT NULL ,
  `especialidade` VARCHAR(45) NULL ,
  PRIMARY KEY (`idMedico`) )
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `sgbo`.`registro`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `sgbo`.`registro` (
  `idRegistro` INT NOT NULL AUTO_INCREMENT ,
  `numero` VARCHAR(16) NOT NULL ,
  `nomeDoador` VARCHAR(50) NULL ,
  `idadeDoador` INT NULL ,
  `sexo` CHAR NULL ,
  `causaMorteDoador` INT NULL ,
  `localObitoDoador` INT NULL ,
  `dataObito` DATE NULL ,
  `horaObito` TIME NULL ,
  `localCaptacao` INT NULL ,
  `dataCaptacao` DATE NULL ,
  `horaCaptacao` TIME NULL ,
  `dataEntreda` DATE NULL ,
  `horaEntrada` TIME NULL ,
  `sorologia` VARCHAR(45) NULL ,
  `preservada` TINYINT(1) NULL ,
  `dataPreservacao` DATE NULL ,
  `horaPreservacao` TIME NULL ,
  `dataPreservacaoFinal` DATE NULL ,
  `horaPreservacaoFinal` TIME NULL ,
  `resultadoAvaliacao` VARCHAR(45) NULL ,
  `validade` DATE NOT NULL ,
  `aih` TINYINT(1) NULL ,
  `procedimento` MEDIUMTEXT NULL ,
  PRIMARY KEY (`idRegistro`) ,
  INDEX `causaMorte` (`causaMorteDoador` ASC) ,
  INDEX `localObito` (`localObitoDoador` ASC) ,
  INDEX `captacao` (`localCaptacao` ASC) ,
  CONSTRAINT `causaMorte`
    FOREIGN KEY (`causaMorteDoador` )
    REFERENCES `sgbo`.`causaMorte` (`idCausaMorte` )
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `localObito`
    FOREIGN KEY (`localObitoDoador` )
    REFERENCES `sgbo`.`estabelecimento` (`idEstabelecimento` )
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `captacao`
    FOREIGN KEY (`localCaptacao` )
    REFERENCES `sgbo`.`estabelecimento` (`idEstabelecimento` )
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `sgbo`.`distribuicao`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `sgbo`.`distribuicao` (
  `idDistribuicao` INT NOT NULL AUTO_INCREMENT ,
  `numero` INT NOT NULL ,
  `dataLiberacao` DATE NULL ,
  `horaLiberacao` TIME NULL ,
  `dataSaida` DATE NULL ,
  `horaSaida` TIME NULL ,
  `nomeReceptor` VARCHAR(50) NOT NULL ,
  `rgctReceptor` VARCHAR(45) NOT NULL ,
  `sexoReceptor` CHAR NULL ,
  `transplantador` INT NULL ,
  `localCirurgia` INT NULL ,
  `nomePortador` VARCHAR(50) NOT NULL ,
  `rgPortador` VARCHAR(8) NOT NULL ,
  `vinculoPortador` VARCHAR(45) NULL ,
  `motivoSaida` VARCHAR(12) NOT NULL ,
  PRIMARY KEY (`idDistribuicao`) ,
  INDEX `transplantador` (`transplantador` ASC) ,
  INDEX `localCirurgia` (`localCirurgia` ASC) ,
  INDEX `numeroDistribuicao` (`numero` ASC) ,
  CONSTRAINT `transplantador`
    FOREIGN KEY (`transplantador` )
    REFERENCES `sgbo`.`medico` (`idMedico` )
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `localCirurgia`
    FOREIGN KEY (`localCirurgia` )
    REFERENCES `sgbo`.`estabelecimento` (`idEstabelecimento` )
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `numeroDistribuicao`
    FOREIGN KEY (`numero` )
    REFERENCES `sgbo`.`registro` (`idRegistro` )
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `sgbo`.`reingresso`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `sgbo`.`reingresso` (
  `idReingresso` INT NOT NULL AUTO_INCREMENT ,
  `numero` INT NOT NULL ,
  `dataReingresso` DATE NULL ,
  `horaReingresso` TIME NULL ,
  `nomeRecusou` INT NOT NULL ,
  `rgctRecusou` INT NOT NULL ,
  `medicoDevolveu` INT NULL ,
  `motivoDevolucao` VARCHAR(45) NULL ,
  `dataReavaliacao` DATE NULL ,
  `horaReavaliacao` TIME NULL ,
  `resultadoReavaliacao` VARCHAR(45) NULL ,
  `destinoTecido` VARCHAR(45) NULL ,
  PRIMARY KEY (`idReingresso`) ,
  INDEX `medico` (`medicoDevolveu` ASC) ,
  INDEX `numeroReingresso` (`numero` ASC) ,
  INDEX `nomeRecusou` (`nomeRecusou` ASC) ,
  INDEX `rgctRecusou` (`rgctRecusou` ASC) ,
  CONSTRAINT `medico`
    FOREIGN KEY (`medicoDevolveu` )
    REFERENCES `sgbo`.`distribuicao` (`transplantador` )
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `numeroReingresso`
    FOREIGN KEY (`numero` )
    REFERENCES `sgbo`.`distribuicao` (`idDistribuicao` )
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `nomeRecusou`
    FOREIGN KEY (`nomeRecusou` )
    REFERENCES `sgbo`.`distribuicao` (`idDistribuicao` )
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `rgctRecusou`
    FOREIGN KEY (`rgctRecusou` )
    REFERENCES `sgbo`.`distribuicao` (`idDistribuicao` )
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB;



SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
