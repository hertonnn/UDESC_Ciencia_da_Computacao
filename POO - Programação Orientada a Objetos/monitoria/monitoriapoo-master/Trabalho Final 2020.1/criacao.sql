create sequence id_medico;
create sequence id_consulta;
create sequence id_paciente;

create table medico(
    id int,
    nome varchar(50),
    idade int,
    cpf bigint,
    cidade varchar(50),
    especialidade varchar(50),
    primary key (id)
);

create table paciente(
    id int,
    nome varchar(50),
    idade int,
    cpf bigint,
    cidade varchar(50),
    descricao varchar(200),
    primary key (id)
);

create table consulta(
    id int,
    valor float,
    data date,
    hora time,
    diagnostico varchar(200),
    id_medico int,
    id_paciente int,
    primary key (id),
    foreign key (id_medico) references medico,
    foreign key (id_paciente) references paciente
);