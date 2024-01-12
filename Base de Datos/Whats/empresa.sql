create database Empresa

--Esquema Empleado
create table Empleado(
	nombre char(20) not null,
	apPat char(20) not null,
	apMat char(20),
	nss char(9) not null,
	dir char(50),
	sexo char,
	salario decimal(10, 2),
	fnac date,
	nssSuper char(9),
	nd int not null,
	
	constraint cveEmp
		primary key (nss),
	constraint cveSupEmp
		foreign key(nssSuper) references Empleado(nss)
		on delete set default on update cascade);
		
--alter table Table drop constraint cveSupEmp cascade;		
--alter table Table add constraint cveSupEmp cascade;
		
alter table Empleado add
	constraint cveDepEmp
	foreign key(nd) references Departamento(numD)
	on delete set default on update cascade;
	
insert into Empleado values ('Juan','Lopez','Lopez','1','San Antonio','M',13000.00,'1995-10-05','1',1);
--insert into Empleado (nd) values (1) where nss is "1";
insert into Empleado values ('Diana','Sánchez','Perez','2','Buenavista','F',15000.00,'1998-06-08','2',2);
insert into Empleado values ('Luis','Gutierrez','Lopez','3','Tulipan','M',12000.00,'2000-11-21','2',3);
	
	select * from Empleado
	
--Esquema Departamento
create table Departamento(
	numD int not null  default 1,
	nomDepa char(20) not null,
	nssGte char(9),
	fechaIn date,
	
	constraint cveDep
		primary key (numD),
	constraint GteDep
		foreign key (nssGte) references Empleado(nss)
		on delete set default on update cascade);
		
insert into departamento values (1,'Administración');
insert into departamento (nssGte,fechaIn) values ('1','10-10-2021');
insert into departamento values (2,'Finanzas');
insert into departamento (nssGte,fechaIn) values ('2','9-10-2021');
insert into departamento values (3,'Logistica');
insert into departamento (nssGte,fechaIn) values ('3','8-09-2021');

	select * from Departamento
	
--Esquema Dependiente
create table Dependiente(
	nombreD char(20) not null,
	sexo char,
	parent char(15) not null,
	fechaN date,
	nssEmp char(9) not null,
	
	constraint cveDepend
		primary key (nombreD, nssEmp),
	constraint cveEmp
		foreign key (nssEmp) references Empleado(nss)
		on delete set default on update cascade);
		
	select * from dependiente		
		
--Esquema Proyecto
create table Proyecto(
	numeroP int not null,
	nombreP char(20) not null,
	lugarP char(20) not null,
	numDControla int not null,
	
	constraint cveProy
		primary key (numeroP),
	constraint DepaProy
		foreign key (numDControla) references Departamento(numD)
	on delete set default on update cascade);
	
	select * from proyecto	
	
--Esquema TrabajaEn
create table trabajaEn(
	numeroP int not null,
	nssEmp char(9) not null,
	hrs int not null,

	constraint cTrabajaen 
		primary key (numeroP, nssEmp),
	constraint cProy
		foreign key (numeroP) references Proyecto (numeroP)
		on delete set default on update cascade,
	constraint cEmp
		foreign key (nssEmp) references Empleado (nss)
		on delete set default on update cascade);
 
 	select * from trabajaen
 
--Esquema Lugar
create table Lugar(
	numDep int not null,
	lugarU char(20) not null,

	constraint cLugar 
		primary key (numDep, lugarU),
	constraint cDep
		foreign key (numDep) references Departamento (numD)
		on delete set default on update cascade);
		
	select * from lugar
	