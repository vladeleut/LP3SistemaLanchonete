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

create table ingrediente(
	id int primary key,
	nome varchar(50),
    preco double
);

create table clientes(
	telefone varchar(20) primary key ,
    nome varchar(50), 
    endereco varchar(80),
    complemento varchar(30),
    bairro varchar(30),
    referencia varchar(200),
    observacoes varchar(200)
);
select count(*) from sist_usuario where login = 'vijrekwjfdsklad';

create table produto(
	id int primary key, 
    nome varchar(30),
    preco double
);

create table produto_ingrediente(
    id_prod int, 
    id_ing int,
    constraint foreign key id_prod_fk (id_prod) references produto(id),
    constraint foreign key id_ing_fk(id_ing) references ingrediente(id),
    constraint primary key prod_ing_pk (id_prod, id_ing)
);

create table caixa(
	id_sessao int auto_increment not null primary key,
    id_login int, 
    hora_abertura datetime,
    hora_fechamento datetime,
    movimentacao double,
    constraint foreign key login_id_fk(id_login) references sist_usuario(id)
);