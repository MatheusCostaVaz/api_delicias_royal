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

import com.deliciaroyal.api.domain.produto.DadosAtualizacaoProduto;
import com.deliciaroyal.api.domain.produto.DadosCadastroProduto;
import com.deliciaroyal.api.domain.produto.DadosDetalhamentoProduto;
import com.deliciaroyal.api.domain.produto.DadosListagemProduto;
import com.deliciaroyal.api.domain.produto.Produto;
import com.deliciaroyal.api.domain.produto.ProdutoRepository;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;

@RestController
//Define o caminho base para todos os endpoints relacionados a produtos
@RequestMapping("produtos")
//Requer autenticação com token Bearer para acessar os endpoints
@SecurityRequirement(name = "bearer-key")
public class ProdutoController {

    //Injeção do repositório de produtos
    @Autowired
    private ProdutoRepository repository;

    //Cadastra um novo produto no sistema.
    @PostMapping
    //Anotação para garantir que a operação seja atômica (tudo ou nada)
    @Transactional
    public ResponseEntity<DadosDetalhamentoProduto> cadastrar(@RequestBody @Valid DadosCadastroProduto dados, UriComponentsBuilder uriBuilder) {
        //Cria uma instância de Produto com base nos dados recebidos
        var produto = new Produto(dados);
        repository.save(produto);

        var uri = uriBuilder.path("/produto/{id}").buildAndExpand(produto.getId()).toUri();

        return ResponseEntity.created(uri).body(new DadosDetalhamentoProduto(produto));
    }

    //Lista todos os produtos ativos (não excluídos), paginados e ordenados por nome.
    @GetMapping
    public ResponseEntity<Page<DadosListagemProduto>> listar(@PageableDefault(size = 10, sort = {"nome"}) Pageable paginacao) {
        var page = repository.findAllByAtivoTrue(paginacao).map(DadosListagemProduto::new);
        return ResponseEntity.ok(page);
    }

    //Atualiza as informações de um produto existente.
    @PutMapping
    @Transactional
    public ResponseEntity<DadosDetalhamentoProduto> atualizar(@RequestBody @Valid DadosAtualizacaoProduto dados) {
        var produto = repository.getReferenceById(dados.id());
        produto.atualizarInformacoes(dados);

        return ResponseEntity.ok(new DadosDetalhamentoProduto(produto));
    }

    //Exclui (lógica) um produto pelo ID.
    @DeleteMapping("/{id}")
    @Transactional
    //Obtém o ID do produto da URL
    public ResponseEntity<Void> excluir(@PathVariable Long id) {
        var produto = repository.getReferenceById(id);
        //Marca o produto como excluído (assumindo uma exclusão lógica)
        produto.excluir();

        return ResponseEntity.noContent().build();
    }

    //Retorna os detalhes de um produto pelo ID.
    @GetMapping("/{id}")
    public ResponseEntity<DadosDetalhamentoProduto> detalhar(@PathVariable Long id) {
        var produto = repository.getReferenceById(id);
        return ResponseEntity.ok(new DadosDetalhamentoProduto(produto));
    }

}
