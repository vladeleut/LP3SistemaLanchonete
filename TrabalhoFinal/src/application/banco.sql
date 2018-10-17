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
	id int primary key auto_increment,
    nome varchar(50), 
    endereco varchar(80),
    telefone varchar(20)
);

insert into clientes (nome, endereco, telefone) values
	('Vlademir', 'Rua a', '123456789'),
    ('Nicolas', 'Avenida b', '987654321');
    

    