package com.carteiraDigital.carteiraDigital.service.conta;

import com.carteiraDigital.carteiraDigital.dtos.ContaDTO;
import com.carteiraDigital.carteiraDigital.model.conta.Conta;
import com.carteiraDigital.carteiraDigital.model.conta.Tipo;
import com.carteiraDigital.carteiraDigital.repository.conta.ContaRepository;
import com.carteiraDigital.carteiraDigital.util.Resposta;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class ContaService {
    @Autowired
    private ContaRepository contaRepository;

    public Resposta<Conta> cadastroConta(ContaDTO conta){

       Conta documentoExistente = this.buscarDocumento(conta.documento());

       Conta emailExistente = this.buscarEmail(conta.email());

       if(documentoExistente != null && emailExistente != null){
           return new Resposta<>(404, "Usuario já cadastrado.", null);
       }

       Conta novaConta = new Conta(conta);
       this.contaRepository.save(novaConta);

       return new Resposta<>(200,"Usuario cadastrado com sucesso.", novaConta);
    }

    public Resposta<Conta> validarTransacao(Conta usuario, BigDecimal valor){
        if(usuario.getTipo() == Tipo.juridica){
            return new Resposta<>(404,"Usuario nao pode realizar transferencias.",null);
        }

        if(usuario.getSaldo().compareTo(valor) <= 0){
            return new Resposta<>(404,"Saldo insuficiente.", null);
        }

        return new Resposta<>(200,"Usuario autorizado.",usuario);
    }

    public Resposta<Conta> buscarContaId(Long id){
        Conta contaExistente = this.buscarContaID(id);

        if(contaExistente != null){
            return new Resposta<>(200,"Usuario encontrado.",contaExistente);
        }
        return new Resposta<>(404,"Usuario nao encontrado.",null);
    }

    public void contaSalva(Conta conta){
        contaRepository.save(conta);
    }

    public Resposta<List<Conta>> listarContas(){
        List<Conta> contas = contaRepository.findAll();

        if (contas != null){
            return new Resposta<>(200,"Lista de usuarios", contas);
        }
        return new Resposta<>(404,"Não A contas a contas Cadastradas.",null);
    }

    private Conta buscarDocumento(String documento){
        return contaRepository.findByDocumento(documento).orElse(null);
    }

    private Conta buscarEmail(String email){
        return contaRepository.findByEmail(email).orElse(null);
    }

    private Conta buscarContaID(Long id){
        return contaRepository.findById(id).orElse(null);
    }
}
