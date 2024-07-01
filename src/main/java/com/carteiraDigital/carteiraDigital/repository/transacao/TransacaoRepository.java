package com.carteiraDigital.carteiraDigital.repository.transacao;

import com.carteiraDigital.carteiraDigital.model.transacao.Transacao;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransacaoRepository extends JpaRepository<Transacao,Long> {
}
