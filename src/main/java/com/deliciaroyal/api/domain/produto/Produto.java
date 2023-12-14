package com.deliciaroyal.api.domain.produto;

import jakarta.persistence.Entity;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Table(name = "produtos")
@Entity(name = "Produto")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Produto {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	private String nome;
	
	@Enumerated
	private TipoDeProduto tipodeproduto;
	
	private double preco;
	private int qtdestoque;
	private Boolean ativo;
	
	
	public Produto(DadosCadastroProduto dados) {
		this.ativo = true;
		this.nome = dados.nome();
		this.tipodeproduto = dados.tipodeproduto();
		this.preco = dados.preco();
		this.qtdestoque = dados.qtdestoque();
	}
	
	public void atualizarInformacoes(DadosAtualizacaoProduto dados) {
    	if (dados.nome() != null) {
    		this.nome = dados.nome();
    	}
    	if (dados.preco() != 0) {
    		this.preco = dados.preco();
    	}    	
    	if (dados.qtdestoque() != 0) {
    		this.qtdestoque = dados.qtdestoque();
    	}
    	if (dados.ativo() != null) {
    		this.ativo = dados.ativo();
    	}
    } 

	public void excluir() {
		this.ativo = false;
		
	}
	
	

}
