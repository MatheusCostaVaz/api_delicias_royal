package com.deliciaroyal.api.domain.venda;

import java.time.LocalDateTime;
import java.util.List;

import com.deliciaroyal.api.domain.cliente.Cliente;
import com.deliciaroyal.api.domain.produto.Produto;

public record DadosDetalhamentoVenda(Long id,
		Long idProduto, String nomeProduto, Long idCliente, String nomeCliente, LocalDateTime data,
		int qtd, double total ) {
	
	public DadosDetalhamentoVenda(Venda venda){
		this(venda.getId(), venda.getProduto().getId(), venda.getProduto().getNome(), venda.getCliente().getId(),
				venda.getCliente().getNome(), venda.getData(), venda.getQtd(), venda.getTotal());		
	}
}
