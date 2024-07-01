package com.carteiraDigital.carteiraDigital.controller.conta;

import com.carteiraDigital.carteiraDigital.dtos.ContaDTO;
import com.carteiraDigital.carteiraDigital.model.conta.Conta;
import com.carteiraDigital.carteiraDigital.service.conta.ContaService;
import com.carteiraDigital.carteiraDigital.util.Resposta;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
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
@Tag(name = "Conta", description = "conta")
public class ContaController {

    @Autowired
    private ContaService contaService;

    @Operation(summary = "Cadastra uma nova conta", description = "Adicionar uma nova conta Pessoa Fisica ou Juridica.")
    @PostMapping("/cadastro")
    public ResponseEntity<Object> cadastroConta(@RequestBody ContaDTO contaDTO){
        try {
            Resposta<Conta> novaConta = contaService.cadastroConta(contaDTO);
            return ResponseEntity.status(novaConta.getStatus()).body(novaConta.getMenssagem());
        }catch (Exception e){
            return ResponseEntity.status(500).body("Erro interno."+ e.getLocalizedMessage());
        }
    }

    @Operation(summary = "Listar contas cadastradas.", description = "Fornece uma lista com todas as contas cadastradas.")
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
