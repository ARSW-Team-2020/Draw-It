 create table if not exists Sala (
    codigo integer not null,
    ronda integer not null
 );

create table Equipo (
    Nombre varchar(50) not null,
    rondasGanadas integer not null,
    sala integer not null
);

create table Jugador(
    userName varchar(50) not null,
    puntaje integer not null,
    correo varchar(70),
    equipo varchar(50) not null
);



create table Palabra (
    texto varchar(20) not null,
    sala integer not null
);

--esta tabla guardara las palabras que son adivinadas por un equipo especifico
create table PalabraEquipo (
    Palabra varchar(20) not null,
    equipo varchar(50) not null,
    tiempo integer
); 

create table Tablero (
    id integer not null,
);

create table Lapiz (
    id integer not null,
    color varchar(50) not null,
    tipo varchar(20),
    size varchar(20),
    tablero integer not null
);