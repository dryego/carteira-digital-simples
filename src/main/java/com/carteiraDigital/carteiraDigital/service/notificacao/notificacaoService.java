package com.carteiraDigital.carteiraDigital.service.notificacao;

import com.carteiraDigital.carteiraDigital.dtos.NotificacaoDTO;
import com.carteiraDigital.carteiraDigital.model.conta.Conta;
import com.carteiraDigital.carteiraDigital.util.Resposta;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class notificacaoService {
    @Autowired
    private RestTemplate restTemplate;

    public Resposta<Conta> pagadorNotificacao(Conta conta, String menssagem){
        String email = conta.getEmail();
        NotificacaoDTO notificacaoRequeste = new NotificacaoDTO(email, menssagem);

       ResponseEntity<String> notificacaoResponse = restTemplate.postForEntity("https://util.devi.tools/api/v1/notify", notificacaoRequeste,String.class);

       if(!(notificacaoResponse.getStatusCode() == HttpStatus.OK)){
           return new Resposta<>(500,"Serviços de notificações esta fora do ar.",null);
       }
       return new Resposta<>(200, "Notificação enviada.", null);
    }
}
