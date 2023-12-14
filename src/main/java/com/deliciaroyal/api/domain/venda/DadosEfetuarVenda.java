package com.deliciaroyal.api.domain.venda;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.deliciaroyal.api.domain.cliente.ClienteRepository;
import com.deliciaroyal.api.domain.produto.ProdutoRepository;

@Service
public class DadosEfetuarVenda {
	
	@Autowired
	private VendaRepository vendaRepository;
	
	@Autowired
	private ProdutoRepository produtoRepository;
	
	@Autowired
	private ClienteRepository clienteRepository;
	
	public DadosDetalhamentoVenda vender (DadosCadastrarVenda dados) {
		var produtos = produtoRepository.getReferenceById(dados.idProduto());
		var cliente = clienteRepository.getReferenceById(dados.idCliente());
		var venda = new Venda(null, produtos, cliente, dados.data(), dados.qtd(), dados.total());
		vendaRepository.save(venda);
		return new DadosDetalhamentoVenda(venda);
		
	}
}
