package br.org.sesisenai.DoaVille.repository;

import br.org.sesisenai.DoaVille.entity.SolicitacaoDoacao;
import br.org.sesisenai.DoaVille.entity.ItemDoacao;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SolicitacaoDoacaoRepository extends JpaRepository<SolicitacaoDoacao, Long> {
    List<SolicitacaoDoacao> findByItemDoacao(ItemDoacao itemDoacao);
}