CREATE TABLE vendas (
  id bigint not null auto_increment,
  produto_id bigint not null,
  cliente_id bigint not null,
  data datetime not null,
  qtd int,
  subtotal decimal (10,2),

 primary key(id),
 constraint fk_vendas_produto_id foreign key(produto_id) references produtos(id),
 constraint fk_vendas_paciente_id foreign key(cliente_id) references clientes(id)
);