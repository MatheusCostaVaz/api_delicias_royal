package com.deliciaroyal.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.deliciaroyal.api.domain.funcionario.DadosAutenticacao;
import com.deliciaroyal.api.domain.funcionario.Funcionario;
import com.deliciaroyal.api.infra.security.DadosTokenJWT;
import com.deliciaroyal.api.infra.security.TokenService;

import jakarta.validation.Valid;

//Controlador responsável pelo processo de autenticação de funcionários.
@RestController
@RequestMapping("/login")
public class AutenticacaoController {

    //Injeção do gerenciador de autenticação
    @Autowired
    private AuthenticationManager manager;

    //Injeção do serviço de geração de tokens
    @Autowired
    private TokenService tokenService;

    //Realiza o login do funcionário, validando suas credenciais e gerando um token JWT.
    @PostMapping
    //A anotação @Valid já garante a validação básica dos DadosAutenticacao. Considere adicionar validações mais específicas (e.g., tamanho mínimo da senha) se necessário.
    public ResponseEntity efetuarLogin(@RequestBody @Valid DadosAutenticacao dados) {

        //Cria um token de autenticação com base nos dados fornecidos
        var autthenticationtoken = new UsernamePasswordAuthenticationToken(dados.login(), dados.senha());

        //Tenta autenticar o usuário utilizando o AuthenticationManager
        var authentication = manager.authenticate(autthenticationtoken);

        //Gera um token JWT para o funcionário autenticado
        var tokenJWT = tokenService.gerarToken((Funcionario) authentication.getPrincipal());

        //Retorna uma resposta de sucesso com os dados do token JWT
        return ResponseEntity.ok(new DadosTokenJWT(tokenJWT));
    }

}
