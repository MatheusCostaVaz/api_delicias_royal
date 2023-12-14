package com.deliciaroyal.api.domain.venda;

import java.time.LocalDateTime;
import java.util.List;

import com.deliciaroyal.api.domain.cliente.Cliente;
import com.deliciaroyal.api.domain.produto.Produto;
import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

public record DadosCadastrarVenda(
		@Valid		
		@NotNull
		Long idProduto,
		
		@Valid		
		@NotNull
		Long idCliente,
		
		@NotNull
		@JsonFormat(pattern = "dd/MM/yyyy HH:mm")
		LocalDateTime data,
		@NotNull
		int qtd,
		double total
		) {

}
