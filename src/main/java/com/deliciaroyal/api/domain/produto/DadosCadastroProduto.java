package com.deliciaroyal.api.domain.produto;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DadosCadastroProduto(
		@NotBlank(message = "Nome é obrigatório")
		String nome,
		@NotNull(message = "Tipo de produto é obrigatório")
		TipoDeProduto tipodeproduto,
		@DecimalMin(value = "0.0", inclusive = false, message = "O campo 'preco' deve ser maior que zero")
		double preco,
		@NotNull(message = "Quantidade é obrigatório")
		int qtdestoque		
		) {

}
