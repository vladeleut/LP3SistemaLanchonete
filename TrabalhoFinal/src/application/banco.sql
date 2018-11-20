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
	id int primary key auto_increment,
	nome varchar(50),
    preco double,
    descricao varchar(200)
);

create table ingrediente2(
	id int primary key auto_increment,
	nome varchar(50),
    preco double,
    descricao varchar(200)
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
#drop table produto;
create table produto(
	id int auto_increment primary key, 
    nome varchar(30),
    preco double
);
ALTER TABLE document MODIFY id INT AUTO_INCREMENT PRIMARY KEY;
#drop table produto_ingrediente;
create table produto_ingrediente(
    id_prod int, 
    id_ing int,
    #constraint foreign key id_prod_fk (id_prod) references produto(id),
    #constraint foreign key id_ing_fk(id_ing) references ingrediente(id),
    constraint primary key prod_ing_pk (id_prod, id_ing)
);
#delete from produto;
create table caixa(
	id_sessao int auto_increment not null primary key,
    id_login int, 
    hora_abertura datetime,
    hora_fechamento datetime,
    movimentacao double,
    constraint foreign key login_id_fk(id_login) references sist_usuario(id)
);

select * from sist_usuario;
select * from caixa;

SELECT u.nome, c.hora_abertura, c.hora_fechamento, c.movimentacao 
FROM sist_usuario u, caixa c 
WHERE u.id = c.id_login