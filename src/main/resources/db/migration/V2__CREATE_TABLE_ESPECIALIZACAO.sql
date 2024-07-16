CREATE TABLE tb_especializacao (
    id SERIAL PRIMARY KEY,
    area VARCHAR(255),
    tipo VARCHAR(255),
    cargaHoraria INTEGER,
    idServidor INTEGER,
    idStatusDeferimento INTEGER,
    valorTotalDoCurso DOUBLE PRECISION
);
