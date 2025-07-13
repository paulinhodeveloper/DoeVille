package br.org.sesisenai.DoaVille.service;

import br.org.sesisenai.DoaVille.dto.SolicitacaoDoacaoDTO;
import br.org.sesisenai.DoaVille.entity.ItemDoacao;
import br.org.sesisenai.DoaVille.entity.SolicitacaoDoacao;
import br.org.sesisenai.DoaVille.repository.ItemDoacaoRepository;
import br.org.sesisenai.DoaVille.repository.SolicitacaoDoacaoRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class SolicitacaoDoacaoService {

    private final SolicitacaoDoacaoRepository repository;
    private final ItemDoacaoRepository itemRepo;

    public SolicitacaoDoacaoService(SolicitacaoDoacaoRepository repository, ItemDoacaoRepository itemRepo) {
        this.repository = repository;
        this.itemRepo = itemRepo;
    }

    public SolicitacaoDoacao create(SolicitacaoDoacaoDTO dto) {
        if (dto.quantidade <= 0) {
            throw new IllegalArgumentException("Quantidade deve ser maior que zero.");
        }

        ItemDoacao item = itemRepo.findById(dto.idItemDoacao)
                .orElseThrow(() -> new EntityNotFoundException("Item não encontrado."));

        if (!item.isAtivo()) {
            throw new IllegalArgumentException("Item inativo.");
        }

        SolicitacaoDoacao solicitacao = new SolicitacaoDoacao();
        solicitacao.setItemDoacao(item);
        solicitacao.setQuantidade(dto.quantidade);
        solicitacao.setEnderecoEntrega(dto.enderecoEntrega);
        solicitacao.setBairroEntrega(dto.bairroEntrega);
        solicitacao.setDataSolicitacao(LocalDateTime.now());

        return repository.save(solicitacao);
    }

    public List<SolicitacaoDoacao> findAll() {
        return repository.findAll();
    }

    public List<SolicitacaoDoacao> findByItemId(Long itemId) {
        ItemDoacao item = itemRepo.findById(itemId)
                .orElseThrow(() -> new EntityNotFoundException("Item não encontrado."));
        return repository.findByItemDoacao(item);
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }
}