package com.deliciaroyal.api.domain.produto;

public record DadosDetalhamentoProduto(Long id, String nome,
		TipoDeProduto tipodeproduto, 
		double preco, int qtdestoque, Boolean ativo) {
	
	public DadosDetalhamentoProduto(Produto produto) {
		this(produto.getId(), produto.getNome(), 
				produto.getTipodeproduto(), produto.getPreco(),
				produto.getQtdestoque(), produto.getAtivo());
	}

}
