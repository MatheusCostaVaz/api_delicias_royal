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

@Table(name = "clientes") //Mapeia a classe para a tabela "clientes" no banco de dados
@Entity(name = "Cliente") //Define a classe como uma entidade JPA
@Getter //Gera automaticamente os métodos getters para todos os atributos
@NoArgsConstructor //Gera um construtor sem argumentos
@AllArgsConstructor //Gera um construtor com todos os argumentos
@EqualsAndHashCode(of = "id") //Gera métodos equals e hashCode baseados no atributo 'id'
public class Cliente {

    @Id //Define o atributo como a chave primária da entidade
    @GeneratedValue(strategy = GenerationType.IDENTITY) //Gera valores de ID automaticamente (auto incremento)
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

    //Construtor para criar um novo cliente a partir dos dados de cadastro.
    public Cliente(DadosCadastroClientes dados) {
        this.ativo = true;
        this.nome = dados.nome();
        this.celular = dados.celular();
        this.cep = dados.cep();
        this.endereco = dados.endereco();
        this.numero = dados.numero();
        this.complemento = dados.complemento();
        this.bairro = dados.bairro();
        this.cidade = dados.cidade();
    }

    //verifica se cada campo nos dados de atualização é nulo antes de atualizar, evitando sobrescrever valores existentes com nulos.
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

    //Exclui (lógica) o cliente, marcando-o como inativo.
    public void excluir() {
        this.ativo = false;
    }
}
