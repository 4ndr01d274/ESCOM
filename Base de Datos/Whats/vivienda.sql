create database vivienda

--Esquema Persona
create table persona(
	curp char(18) not null,
	nombre char(20) not null,
	apPat char(20) not null,
	apMat char(20),
	edad int not null,
	sexo char,
	fechaNac date,
	curpDep char(18),
	dirHabita char(50),
	cMunicipio char(20),
	estado char(20) not null,

	constraint cPersona
		primary key (curp),
	constraint cPerDep
		foreign key (curpDep) references Persona (curp),
	constraint cPerViv
		foreign key (dirHabita) references Vivienda (dirección),
	constraint cPerMun
		foreign key (cMunicipio, estado) references Municipio (nombre, estado));
 
--Esquema Vivienda
create table Vivienda(
	dirección char(50) not null,
	tamaño char(20) not null,
	curpPosee char(18) not null,
	cMunicipio char(20) not null,
	estado char(20) not null,

	constraint cVivienda
		primary key (dirección),
	constraint cVivPer
		foreign key (curpPosee) references Persona (curp),
	constrain cVivMun
		foreing key (cMunicipio, estado) references Municipio (nombre, estado));

--Esquema Municipio
create table Municipio(
	nombre char(20) not null,
	estado char(20) not null,

	constraint cMunicipio
		primary key (nombre, estado));
