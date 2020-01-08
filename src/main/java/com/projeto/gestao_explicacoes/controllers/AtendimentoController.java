package com.projeto.gestao_explicacoes.controllers;

import com.projeto.gestao_explicacoes.models.Atendimento;
import com.projeto.gestao_explicacoes.services.atendimentoServices.AtendimentoService;
import com.projeto.gestao_explicacoes.services.atendimentoServices.filters.AtendimentoObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@Controller
@RequestMapping("/atendimento")
public class AtendimentoController {

    private Logger logger= LoggerFactory.getLogger(this.getClass());

    private AtendimentoService atendimentoService;

    @Autowired
    public AtendimentoController(AtendimentoService atendimentoService) {
        this.atendimentoService = atendimentoService;
    }

    @GetMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Set<Atendimento>> getAllAtendimentos() {
        this.logger.info("Recebido um pedido GET");

        return ResponseEntity.ok(this.atendimentoService.findAll());
    }

    @PostMapping(value = "", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Atendimento> createAtendimento(@RequestBody Atendimento atendimento) {
        this.logger.info("Recebido um pedido POST");


        System.out.println(atendimento.getData());
        System.out.println(atendimento.getAluno());
        System.out.println(atendimento.getCadeira());
        System.out.println(atendimento.getExplicador());
        System.out.println(atendimento.getIdioma());

        return ResponseEntity.ok(atendimento);
    }
}
