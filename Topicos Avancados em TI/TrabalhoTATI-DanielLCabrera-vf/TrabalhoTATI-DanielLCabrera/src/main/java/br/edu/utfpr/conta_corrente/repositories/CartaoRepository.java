package br.edu.utfpr.conta_corrente.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import br.edu.utfpr.conta_corrente.domain.Cartao;

@Repository
public interface CartaoRepository extends JpaRepository<Cartao, Integer> {

}
