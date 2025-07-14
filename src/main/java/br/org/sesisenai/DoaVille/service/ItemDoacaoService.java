package br.org.sesisenai.DoaVille.service;

import br.org.sesisenai.DoaVille.dto.ItemDoacaoDTO;
import br.org.sesisenai.DoaVille.entity.ItemDoacao;
import br.org.sesisenai.DoaVille.repository.ItemDoacaoRepository;
import br.org.sesisenai.DoaVille.repository.SolicitacaoDoacaoRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ItemDoacaoService {

    @Autowired
    private SolicitacaoDoacaoRepository solicitacaoRepository;

    private final ItemDoacaoRepository repository;

    public ItemDoacaoService(ItemDoacaoRepository repository) {
        this.repository = repository;
    }

    public ItemDoacao create(ItemDoacaoDTO dto) {
        if (repository.existsByNome(dto.nome)) {
            throw new IllegalArgumentException("Item com esse nome já existe.");
        }
        ItemDoacao item = new ItemDoacao();
        item.setNome(dto.nome);
        item.setDescricao(dto.descricao);
        item.setAtivo(dto.ativo);
        return repository.save(item);
    }

    public List<ItemDoacao> findAll() {
        return repository.findAll();
    }

    public ItemDoacao findById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Item não encontrado."));
    }

    public ItemDoacao update(Long id, ItemDoacaoDTO dto) {
        ItemDoacao item = findById(id);
        if (!item.getNome().equals(dto.nome) && repository.existsByNome(dto.nome)) {
            throw new IllegalArgumentException("Outro item com esse nome já existe.");
        }
        item.setNome(dto.nome);
        item.setDescricao(dto.descricao);
        item.setAtivo(dto.ativo);
        return repository.save(item);
    }

    public void delete(Long id) {
        if (solicitacaoRepository.existsByItemDoacaoId(id)) {
            throw new RuntimeException("Este item de doação está vinculado a uma ou mais solicitações.");
        }
        repository.deleteById(id);
    }
}