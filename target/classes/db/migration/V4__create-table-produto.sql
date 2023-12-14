create table produtos (
  id bigint not null auto_increment,
  nome varchar(100) not null unique,
  tipodeproduto varchar(100) not null,
  preco decimal (10,2),
  qtdestoque int,
  ativo TINYINT NOT NULL DEFAULT 1,
  
  primary key(id)
);
