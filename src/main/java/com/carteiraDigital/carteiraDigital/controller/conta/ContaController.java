package com.carteiraDigital.carteiraDigital.controller.conta;

import com.carteiraDigital.carteiraDigital.dtos.ContaDTO;
import com.carteiraDigital.carteiraDigital.model.conta.Conta;
import com.carteiraDigital.carteiraDigital.service.conta.ContaService;
import com.carteiraDigital.carteiraDigital.util.Resposta;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/conta")
public class ContaController {

    @Autowired
    private ContaService contaService;

    @PostMapping("/cadastro")
    public ResponseEntity<Object> cadastroConta(@RequestBody ContaDTO contaDTO){
        try {
            Resposta<Conta> novaConta = contaService.cadastroConta(contaDTO);
            return ResponseEntity.status(novaConta.getStatus()).body(novaConta.getMenssagem());
        }catch (Exception e){
            return ResponseEntity.status(500).body("Erro interno."+ e.getLocalizedMessage());
        }
    }

    @GetMapping("/listar")
    public ResponseEntity<Object> listarContas(){
        try {
            Resposta<List<Conta>> contas = contaService.listarContas();
            return ResponseEntity.status(contas.getStatus()).body(contas.getData());
        }catch (Exception e){
            return ResponseEntity.status(500).body("Erro interno."+ e.getLocalizedMessage());
        }
    }
}
