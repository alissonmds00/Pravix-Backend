create table clientes(
    id bigint GENERATED ALWAYS AS IDENTITY NOT NULL,
    nome varchar(255) not null,
    telefone varchar(155) not null,
    estado varchar(100) not null,
    cidade varchar(100) not null,
    bairro varchar(155) not null,
    rua varchar(155) not null,
    complemento varchar(100),
    PRIMARY KEY(id),
    UNIQUE(telefone)
)