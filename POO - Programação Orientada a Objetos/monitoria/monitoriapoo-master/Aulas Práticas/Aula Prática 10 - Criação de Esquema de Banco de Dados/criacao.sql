create sequence id_cidade;

create table cidade(
    id int,
    nome varchar(50),
    estado varchar(50),
    primary key (id)
);

create sequence id_cliente;

create table cliente(
    id int,
    cpf int,
    nome varchar(50),
    endereco varchar(100),
    telefone int,
    primary key (id)
);

create sequence id_reserva;

create table reserva(
    id int,
    data date,
    hora time,
    preco float,
    classeVoo varchar(10),
    idaEvolta boolean,  
    id_volta int,
    id_origem int,
    id_destino int,
    id_cliente int,
    primary key (id),
    foreign key (id_volta) references reserva,
    foreign key (id_origem) references cidade,
    foreign key (id_destino) references cidade,
    foreign key (id_cliente) references cliente
);


