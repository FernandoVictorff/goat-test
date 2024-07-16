CREATE TABLE tb_servidor (
    id BIGSERIAL PRIMARY KEY,
    cpf VARCHAR(11) NOT NULL,
    email VARCHAR(256) NOT NULL,
    nome VARCHAR(60) NOT NULL,
    data_nascimento TIMESTAMP(3) NOT NULL,
    sexo CHAR(1) NOT NULL,
    tipo_id INT NOT NULL
);
