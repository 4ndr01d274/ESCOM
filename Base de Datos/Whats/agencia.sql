create database agencia

--Esquema Cliente
create table Cliente(
	codigoC char(10) not null,
	RFC char(15) not null,
	nomPila char(20) not null,
	apPat char(20) not null,
	apMat char(20),
	codigoAval char(10),
	
	constraint cveClient
		primary key (codigoC),
	constraint cveAval
		foreign key (codigoAval) references Cliente(codigoC)
		on delete set default on update cascade);
		
	select * from cliente
	
insert into cliente values ('1','123456','Carlos','Castillo','Ruiz');
insert into cliente values ('2','654321','Francisco','García','García');
insert into cliente (codigoAval) values ('1');--??

--Esquema Reservación
create table Reservación(
	folio char(10) not null,
	codigoC char(10) not null,
	inicio date,
	final date,
	dirección char(50),

	constraint cveReservación
		primary key(folio,codigoC),
	constraint cResCliente
		foreign key (codigoC) references Cliente (codigoC)
		on delete set default on update cascade,
constraint cResAgen
foreign key (dirección) references Agencia (dirección)
on delete set default on update cascade);

--Esquema telefonoContacto
create table telfContacto(
	telefono char(10) not null,
	codigoC char(10) not null,

	constraint cveTel
		primary key (telefono, codigoC),
	constraint cTelCliente
		foreign key (codigoC) references Cliente (codigoC)
		on delete set default on update cascade);

--Esquema Agencia
create table Agencia(
	nombre char(15) not null,
	dirección char(50) not null,

	constraint cveAgencia
		primary key (dirección));
 

--Esquema Coche
create table Coche(
	codEstado char(10) not null,
	numSecuencia char(20) not null,
	marca char(15),
	color char(10),
	precioAlquiler decimal(10, 2),
	folio char(10),
	codigoC char(10),
	numEstacionamiento int,

	constraint cveCoche
		primary key (codEstado, numSecuencia),
	constraint cCocheRes
		foreign key (folio, codigoC) references Reservación (folio, codigoC));
