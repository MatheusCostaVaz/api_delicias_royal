create table clientes (
  id bigint not null auto_increment,
  nome varchar(100) not null,
  celular varchar(30)not null unique,
  cep varchar(100)not null,
  endereco varchar (255)not null,
  numero varchar(20),
  complemento varchar (200),
  bairro varchar (100) not null,
  cidade varchar (100)not null,
  
  primary key(id)
);
