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
@RequestMapping("vendas")
@SecurityRequirement(name = "bearer-key")
public class VendaController {
	
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
	
	@PostMapping
	@Transactional
	public ResponseEntity vender(@RequestBody @Valid DadosCadastrarVenda dados) {
		var dto = vende.vender(dados);
		return ResponseEntity.ok(dto);
	}

}
