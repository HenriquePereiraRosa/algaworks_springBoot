/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 * Author:  User
 * Created: Oct 18, 2017
 */

CREATE TABLE personagem (
    id BIGINT(20) PRIMARY KEY AUTO_INCREMENT,
    nome VARCHAR(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO personagem( nome ) values('Spawn');
INSERT INTO personagem( nome ) values('Goku');
INSERT INTO personagem( nome ) values('Vegeta');
INSERT INTO personagem( nome ) values('Ken');
INSERT INTO personagem( nome ) values('Riu');
INSERT INTO personagem( nome ) values('Akuma');
INSERT INTO personagem( nome ) values('Naruto');