package com.carteiraDigital.carteiraDigital.controller.transacao;

import com.carteiraDigital.carteiraDigital.dtos.TransacaoDTO;
import com.carteiraDigital.carteiraDigital.model.transacao.Transacao;
import com.carteiraDigital.carteiraDigital.repository.transacao.TransacaoRepository;
import com.carteiraDigital.carteiraDigital.service.transacao.TransacaoService;
import com.carteiraDigital.carteiraDigital.util.Resposta;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/transacao")
@Tag(name = "Criar uma nova transação.", description = "Cadastra uma nova transação")
public class TransacaoController {

    @Autowired
    TransacaoService transacaoService;

    @Operation(summary = "Cadastra uma nova transação.", description = "Cadastro de transação. OBS: só uma pessoa com conta juridica pode realizar transferencias.")
    @PostMapping("/cadastro")
    public ResponseEntity<Object> postCadastroTransacao(@RequestBody TransacaoDTO transacaoDTO){
        try {
            Resposta<Transacao> novaTransacao = transacaoService.novaTransacao(transacaoDTO);
            return ResponseEntity.status(novaTransacao.getStatus()).body(novaTransacao.getMenssagem());
        }catch (Exception e){
            return ResponseEntity.status(500).body("Erro interno. " + e.getLocalizedMessage());
        }
    }
}
