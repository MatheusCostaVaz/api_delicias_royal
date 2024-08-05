package com.deliciaroyal.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.deliciaroyal.api.domain.cliente.Cliente;
import com.deliciaroyal.api.domain.cliente.ClienteRepository;
import com.deliciaroyal.api.domain.cliente.DadosAtualizacaoCliente;
import com.deliciaroyal.api.domain.cliente.DadosCadastroClientes;
import com.deliciaroyal.api.domain.cliente.DadosDetalhamentoCliente;
import com.deliciaroyal.api.domain.cliente.DadosListagemCliente;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;

@RestController
//Mapeamento base para todos os endpoints relacionados a clientes
@RequestMapping("clientes")
//Exige autenticação com token Bearer para acessar os endpoints
@SecurityRequirement(name = "bearer-key")
public class ClienteController {

    //Injeção do repositório de clientes
    @Autowired
    private ClienteRepository repository;

    //Cadastra um novo cliente com base nos dados fornecidos.
    @PostMapping
    @Transactional //Abre uma transação para garantir a persistência do cliente
    public ResponseEntity cadastrar(@RequestBody @Valid DadosCadastroClientes dados, UriComponentsBuilder uriBuilder) {
        var cliente = new Cliente(dados);
        repository.save(cliente); //Salva o cliente no banco de dados

        //Constrói a URI do cliente criado e retorna um status 201 Created
        var uri = uriBuilder.path("/clientes/{id}").buildAndExpand(cliente.getId()).toUri();
        return ResponseEntity.created(uri).body(new DadosDetalhamentoCliente(cliente));
    }

    //Lista os clientes cadastrados, paginados e ordenados por nome.
    @GetMapping
    public ResponseEntity<Page<DadosListagemCliente>> listar(@PageableDefault(size = 10, sort = {"nome"}) Pageable paginacao) {
        //Busca clientes ativos (não excluídos), paginados e ordenados, mapeando para DTO de listagem
        var page = repository.findAllByAtivoTrue(paginacao).map(DadosListagemCliente::new);
        return ResponseEntity.ok(page);
    }

    //Atualiza os dados de um cliente existente.
    @PutMapping
    @Transactional
    public ResponseEntity atualizar(@RequestBody @Valid DadosAtualizacaoCliente dados) {
        //Obtém a referência do cliente
        var cliente = repository.getReferenceById(dados.id());
        //Atualiza as informações
        cliente.atualizarInformacoes(dados);

        return ResponseEntity.ok(new DadosDetalhamentoCliente(cliente));
    }

    //Exclui (lógica) um cliente pelo ID.
    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity excluir(@PathVariable Long id) {
        var cliente = repository.getReferenceById(id);
        cliente.excluir();

        return ResponseEntity.noContent().build();
    }

    //Retorna os detalhes de um cliente pelo ID.
    @GetMapping("/{id}")
    public ResponseEntity detalhar(@PathVariable Long id) {
        var cliente = repository.getReferenceById(id);
        return ResponseEntity.ok(new DadosDetalhamentoCliente(cliente));
    }

}
