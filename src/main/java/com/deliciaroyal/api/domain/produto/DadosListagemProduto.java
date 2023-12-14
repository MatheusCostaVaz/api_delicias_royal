package com.deliciaroyal.api.domain.produto;

public record DadosListagemProduto(Long id, String nome,
		double preco, int qtdestoque) {
	public DadosListagemProduto(Produto produto) {
		this(produto.getId(), produto.getNome(), produto.getPreco(),
				produto.getQtdestoque());
	}

}
