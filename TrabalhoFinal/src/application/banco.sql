use lp320182;
drop table sist_usuario;
create table sist_usuario(
	id int auto_increment primary  key,
    login varchar(30),
    senha varchar(20),
    nome Varchar(100),
    isAdmin int,
    isAtivo int default 0
    
);
insert into sist_usuario (login, senha, nome, isAdmin) values 
	('vlad', 'root', 'Vlademir Eleuterio', 1),
    ('sandro', '123', 'Nicolas Mendes', 0);
    
select * from sist_usuario;

create table ingredientes(
	id int primary key,
	ingrediente varchar(50),
    preco double
);
drop table clientes;
create table clientes(
	telefone varchar(20) primary key ,
    nome varchar(50), 
    endereco varchar(80),
    complemento varchar(30),
    bairro varchar(30),
    referencia varchar(200),
    observacoes varchar(200)
);

    

    