# Arquivos referentes ao banco de dados

- Sobre o banco de dados.pdf

Arquivo contendo uma pequena documentação referente as tabelas do banco e com alguns exemplos utilizando data e hora no postgreSQL.

- clinica.backup

Arquivo contendo o banco de dados já pronto para ser utilizado no postgreSQL. Utilize a plataforma pgadmin4 para restaurar esse banco. Caso tenha dúvidas de como fazer isso, veja nos slides [Persistência de Dados Utilizando PostgreSQL][linkSlide].

- criando.sql

Arquivo contento o script sql utilizando na criação do banco disponível no backup. O script criou 3 tabelas e 3 sequências para os id's das respectivas tabelas.

- populando.sql

Arquivo contendo o script sql que populou o banco de dados com algumas informações. Foram adicionados 10 médicos, 10 pacientes e 8 consultas.

[linkSlide]:https://github.com/takeofriedrich/monitoriapoo/blob/master/Slides%20de%20Aula/Persistência%20de%20Dados%20Utilizando%20PostgreSQL.pdf

# Banco de dados disponibilizado aos alunos

Tabelas presentes no banco de dados:

## Tabela Médicos

|  id  |     nome    | idade |   cpf  |    cidade   | especialidade |
|:----:|:-----------:|:-----:|:------:|:-----------:|:-------------:|
| int  | varchar(50) |  int  | bigint | varchar(50) |  varchar(50)  |

## Tabela Pacientes

|  id  |     nome    | idade |   cpf  |    cidade   |   descrição  |
|:----:|:-----------:|:-----:|:------:|:-----------:|:------------:|
| int  | varchar(50) |  int  | bigint | varchar(50) | varchar(200) |

## Tabela Consultas

|  id | valor |  dataHora |  diagnostico | id_medico | id_paciente |
|:---:|:-----:|:---------:|:------------:|:---------:|:-----------:|
| int | float | timestamp | varchar(200) |    int    |     int     |

