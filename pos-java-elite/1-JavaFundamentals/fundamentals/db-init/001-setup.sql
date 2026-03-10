-- drop table tb_usuario_perfil;
-- drop table tb_usuario;
-- drop table tb_perfil;
-- drop table tb_conta;

CREATE TABLE tb_usuario
(
    id    INTEGER PRIMARY KEY AUTO_INCREMENT,
    nome  VARCHAR(50) NOT NULL,
    ativo BOOLEAN     NOT NULL
);
CREATE TABLE tb_conta
(
    id         INTEGER PRIMARY KEY AUTO_INCREMENT,
    id_usuario INTEGER     NOT NULL,
    email      VARCHAR(50) NOT NULL,
    pass       VARCHAR(50) NOT NULL,

    FOREIGN KEY (id_usuario) REFERENCES tb_usuario (id)
);
CREATE TABLE tb_endereco
(
    id         INTEGER PRIMARY KEY AUTO_INCREMENT,
    id_usuario INTEGER      NOT NULL,
    cep        VARCHAR(100) NOT NULL,

    FOREIGN KEY (id_usuario) REFERENCES tb_usuario (id)
);
CREATE TABLE tb_perfil
(
    id   INTEGER PRIMARY KEY AUTO_INCREMENT,
    nome VARCHAR(20) NOT NULL
);
CREATE TABLE tb_usuario_perfil
(
    id         INTEGER PRIMARY KEY AUTO_INCREMENT,
    id_perfil  INTEGER NOT NULL,
    id_usuario INTEGER NOT NULL,

    FOREIGN KEY (id_usuario) REFERENCES tb_usuario (id),
    FOREIGN KEY (id_perfil) REFERENCES tb_perfil (id)
);
CREATE TABLE tb_pedido
(
    id         INTEGER PRIMARY KEY AUTO_INCREMENT,
    id_usuario INTEGER NOT NULL,
    total DOUBLE NOT NULL,

    FOREIGN KEY (id_usuario) REFERENCES tb_usuario (id)
);
