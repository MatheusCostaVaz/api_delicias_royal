package com.deliciaroyal.api.domain.cliente;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public record DadosCadastroClientes(
		@NotBlank (message = "Nome é obrigatório")
		String nome,
		@NotBlank (message = "Celular é obrigatório")
		@Pattern(regexp = "\\(\\d{2}\\) \\d{5}-\\d{4}", message = "Formato do número é inválido")
        String celular,
        @Pattern(regexp = "\\d{5}-\\d{3}", message = "Formato do CEP é inválido")
        String cep,
        @NotBlank (message = "Endereco é obrigatório")
	    String endereco,
	    String numero,
	    String complemento,
	    String bairro,
	    String cidade		
		) {

}
