package br.org.sesisenai.DoaVille.repository;

import br.org.sesisenai.DoaVille.entity.ItemDoacao;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ItemDoacaoRepository extends JpaRepository<ItemDoacao, Long> {
    Optional<ItemDoacao> findByNome(String nome);
    boolean existsByNome(String nome);
}