insert into cidade(select nextval('id_cidade'),'Joinville','SC');
insert into cidade(select nextval('id_cidade'),'Florianopolis','SC');
insert into cidade(select nextval('id_cidade'),'Curitiba','PR');

insert into cliente(select nextval('id_cliente'),11111,'Joao','Rua João Colin',213123);

insert into reserva(select nextval('id_reserva'),'2020-06-12','11:00:00',100.00,'Comercial',false,null,1,2,1)


