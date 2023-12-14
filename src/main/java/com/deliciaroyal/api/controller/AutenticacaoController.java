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

@RestController
@RequestMapping("/login")
public class AutenticacaoController {

    @Autowired
    private AuthenticationManager manager;
    
    @Autowired
    private TokenService tokenService;

    @PostMapping
    public ResponseEntity efetuarLogin(@RequestBody @Valid DadosAutenticacao dados) {
        var autthenticationtoken = new UsernamePasswordAuthenticationToken(dados.login(), dados.senha());
        var authentication = manager.authenticate(autthenticationtoken);
        
        var tokenJWT = tokenService.gerarToken((Funcionario) authentication.getPrincipal());
        
        return ResponseEntity.ok(new DadosTokenJWT(tokenJWT));
    }	

}
