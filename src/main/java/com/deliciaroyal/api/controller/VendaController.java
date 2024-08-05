package com.deliciaroyal.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.deliciaroyal.api.domain.venda.DadosCadastrarVenda;
import com.deliciaroyal.api.domain.venda.DadosEfetuarVenda;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;

@RestController
//Define o caminho base para os endpoints relacionados a vendas
@RequestMapping("vendas")
//Requer autenticação com token Bearer para acessar os endpoints
@SecurityRequirement(name = "bearer-key")
public class VendaController {

    //Injeção do serviço responsável pela lógica de efetuar vendas
    @Autowired
    private DadosEfetuarVenda vende;

//	@PostMapping
//	@Transactional
//	public ResponseEntity cadastrar(@RequestBody @Valid DadosCadastrarVenda dados, UriComponentsBuilder uriBuilder) {
//		var venda = new Venda(dados);
//		vendaRepository.save(venda);
//		
//		var uri = uriBuilder.path("/vendas/{id}").buildAndExpand(venda.getId()).toUri();
//		return ResponseEntity.created(uri).body(new DadosDetalhamentoVenda(venda));
//	}
	
    //Realiza o processamento de uma venda.
    @PostMapping
    //@Transactional é importante para garantir que a venda seja processada de forma consistente (se algo der errado, a transação é revertida).
    @Transactional
    public ResponseEntity vender(@RequestBody @Valid DadosCadastrarVenda dados) {
        //Chama o serviço para realizar a venda e obter o DTO resultante
        var dto = vende.vender(dados);
        //Retorna o DTO com o status HTTP 200 OK
        return ResponseEntity.ok(dto);
    }

}
