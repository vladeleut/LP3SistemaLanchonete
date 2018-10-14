use lp320182;
drop table acesso;
create table sist_usuario(
	id int auto_increment primary  key,
    login varchar(30),
    nome Varchar(100),
    isAdmin int,
    isAtivo int
    
);
alter table sist_usuario add column isAtivo int default 0;
insert into sist_usuario (login, nome, isAdmin) values 
	('vlad', 'Vlademir Eleuterio', 1),
    ('sandro', 'Nicolas Mendes', 0);
    
select * from sist_usuario;


    