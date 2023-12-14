package com.deliciaroyal.api.domain.cliente;

public record DadosListagemCliente(Long id,
		String nome,		
        String celular,
        String cep,
	    String endereco,
	    String numero,
	    String complemento,
	    String bairro,
	    String cidade) {
	
	public DadosListagemCliente (Cliente clientes) {
		this(clientes.getId(), clientes.getNome(), clientes.getCelular(), 
				clientes.getCep(), clientes.getEndereco(), clientes.getNumero(),
				clientes.getComplemento(), clientes.getBairro(), clientes.getCidade());
	}	

}
