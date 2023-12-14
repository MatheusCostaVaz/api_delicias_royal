package com.deliciaroyal.api.domain.cliente;

import jakarta.validation.constraints.NotNull;

public record DadosAtualizacaoCliente(
		@NotNull
		Long id,
		String nome,		
        String celular,
        String cep,
	    String endereco,
	    String numero,
	    String complemento,
	    String bairro,
	    String cidade	
		
		) {

}
