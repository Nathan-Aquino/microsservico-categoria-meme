CREATE SEQUENCE IF NOT EXISTS sequence_categoria_meme START WITH 1 INCREMENT BY 50;

CREATE TABLE IF NOT EXISTS categoria_meme (
  id BIGINT NOT NULL,
  nome VARCHAR(255) NOT NULL,
  descricao VARCHAR(255) NOT NULL,
  data_cadastro DATE NOT NULL,
  usuario_id BIGINT,
  CONSTRAINT pk_categoriameme PRIMARY KEY (id),
  CONSTRAINT FK_CATEGORIAMEME_ON_USUARIO FOREIGN KEY (usuario_id) REFERENCES usuario(id)
);