package com.deliciaroyal.api.domain.cliente;

public record DadosDetalhamentoCliente(Long id,
		String nome,		
        String celular,
        String cep,
	    String endereco,
	    String numero,
	    String complemento,
	    String bairro,
	    String cidade) {
	
	public DadosDetalhamentoCliente (Cliente clientes) {
		this(clientes.getId(), clientes.getNome(), clientes.getCelular(), 
				clientes.getCep(), clientes.getEndereco(), clientes.getNumero(),
				clientes.getComplemento(), clientes.getBairro(), clientes.getCidade());
	}
	

}
