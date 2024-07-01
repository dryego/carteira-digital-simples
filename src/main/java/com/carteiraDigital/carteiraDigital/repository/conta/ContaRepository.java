package com.carteiraDigital.carteiraDigital.repository.conta;

import com.carteiraDigital.carteiraDigital.model.conta.Conta;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ContaRepository extends JpaRepository<Conta, Long> {
    @Override
    Optional<Conta> findById(Long id);

    Optional<Conta> findByDocumento(String documento);

    Optional<Conta> findByEmail(String email);
}
