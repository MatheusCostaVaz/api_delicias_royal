package com.deliciaroyal.api.domain.cliente;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Table(name = "clientes")
@Entity(name = "Cliente")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Cliente {
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String celular;
    private String cep;
    private String endereco;
    private String numero;
    private String complemento;
    private String bairro;
    private String cidade;
    private Boolean ativo;
    
    public Cliente(DadosCadastroClientes dados) {
    	this.ativo=true;
    	this.nome = dados.nome();
    	this.celular = dados.celular();
    	this.cep = dados.cep();
    	this.endereco = dados.endereco();
    	this.numero = dados.numero();
    	this.complemento = dados.complemento();
    	this.bairro = dados.bairro();
    	this.cidade = dados.cidade();   	
    }
    
    public void atualizarInformacoes(DadosAtualizacaoCliente dados) {
    	if (dados.nome() != null) {
    		this.nome = dados.nome();
    	}
    	if (dados.celular() != null) {
    		this.celular = dados.celular();
    	}
    	if (dados.cep() != null) {
    		this.cep = dados.cep();
    	}
    	if (dados.endereco() != null) {
    		this.endereco = dados.endereco();
    	}
    	if (dados.numero() != null) {
    		this.numero = dados.numero();
    	}
    	if (dados.complemento() != null) {
    		this.complemento = dados.complemento();
    	}
    	if (dados.bairro() != null) {
    		this.bairro = dados.bairro();
    	}
    	if (dados.cidade() != null) {
    		this.cidade = dados.cidade();
    	}    	
    }

	public void excluir() {
		this.ativo = false;		
	}	
}
