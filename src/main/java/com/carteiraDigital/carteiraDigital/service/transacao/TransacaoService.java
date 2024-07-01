package com.carteiraDigital.carteiraDigital.service.transacao;

import com.carteiraDigital.carteiraDigital.dtos.TransacaoDTO;
import com.carteiraDigital.carteiraDigital.model.conta.Conta;
import com.carteiraDigital.carteiraDigital.model.transacao.Transacao;
import com.carteiraDigital.carteiraDigital.repository.transacao.TransacaoRepository;
import com.carteiraDigital.carteiraDigital.service.conta.ContaService;
import com.carteiraDigital.carteiraDigital.util.Resposta;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Map;

@Service
public class TransacaoService {
    @Autowired
    private TransacaoRepository transacaoRepository;

    @Autowired
    private ContaService contaService;

    @Autowired
    private RestTemplate restTemplate;

    private static final Logger logger = LoggerFactory.getLogger(TransacaoService.class);

    public Resposta<Transacao> novaTransacao(TransacaoDTO transacao){
        Long pagadorID = transacao.pagadorID();
        Long beneficiarioID = transacao.beneficiarioID();

        logger.info("Pagador ID: {}", pagadorID);
        logger.info("Beneficiário ID: {}", beneficiarioID);

        if (pagadorID == null || beneficiarioID == null) {
            return new Resposta<>(404, "IDs do pagador ou beneficiário não podem ser nulos.", null);
        }

        Conta pagador = this.contaService.buscarContaId(pagadorID).getData();
        Conta beneficiario = this.contaService.buscarContaId(beneficiarioID).getData();

        if(pagador == null || beneficiario == null){
            return new Resposta<>(404,"Transação nao pode ser realizada.", null);
        }

        Resposta<Conta> transacaoValida = contaService.validarTransacao(pagador, transacao.valor());

        if(transacaoValida.getStatus() != 200){
            return  new Resposta<>(404, transacaoValida.getMenssagem(), null);
        }

        Boolean autorizado = this.autorizarTransacao(pagador,transacao.valor());
        if(!autorizado){
            return new Resposta<>(404,"Tranzação não autorizada.", null);
        }

        Transacao novaTransacao = new Transacao(
                transacao.valor(),
                pagador,
                beneficiario,
                LocalDateTime.now()
        );
        pagador.setSaldo(pagador.getSaldo().subtract(transacao.valor()));
        beneficiario.setSaldo(beneficiario.getSaldo().add(transacao.valor()));

        this.transacaoRepository.save(novaTransacao);
        this.contaService.contaSalva(pagador);
        this.contaService.contaSalva(beneficiario);

        return new Resposta<>(200,"Transferencia realizada com sucesso.", novaTransacao);
    }

    private Boolean autorizarTransacao(Conta usuario, BigDecimal valor){
        ResponseEntity<Map> autorizacao = restTemplate.getForEntity("https://util.devi.tools/api/v2/authorize", Map.class);

        /*
        if (autorizacao.getStatusCode() == HttpStatus.OK){
            String menssagem = (String) autorizacao.getBody().get("message");
            return "autorizado".equalsIgnoreCase(menssagem);
        }else return true;
        */

        return true;
    }
}
