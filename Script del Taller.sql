DROP TABLE IF EXISTS Categorias;


create table Categorias (
	id serial primary key,
	descripcion varchar(50),
	estado boolean default true
);

insert into Categorias (id,descripcion,estado) values (100,'Cuadernos',true);
insert into Categorias (id,descripcion,estado) values (200,'Pegantes',true);
insert into Categorias (descripcion,estado) values ('Block de Papel',true);

select * from Categorias;

update Categorias set descripcion = 'Plastico', estado = false where Categorias.id = 1;

delete from Categorias where Categorias.id = 3;