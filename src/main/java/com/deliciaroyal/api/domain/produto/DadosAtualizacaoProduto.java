package com.deliciaroyal.api.domain.produto;

import jakarta.validation.constraints.NotNull;

public record DadosAtualizacaoProduto(	
		@NotNull
		Long id,
		String nome,
		double preco,
		int qtdestoque,
		Boolean ativo 
		)
{
}
