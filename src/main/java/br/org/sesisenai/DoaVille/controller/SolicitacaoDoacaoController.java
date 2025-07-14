package br.org.sesisenai.DoaVille.controller;

import br.org.sesisenai.DoaVille.dto.SolicitacaoDoacaoDTO;
import br.org.sesisenai.DoaVille.entity.SolicitacaoDoacao;
import br.org.sesisenai.DoaVille.service.SolicitacaoDoacaoService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/solicitacoes")
public class SolicitacaoDoacaoController {

    private final SolicitacaoDoacaoService service;

    public SolicitacaoDoacaoController(SolicitacaoDoacaoService service) {
        this.service = service;
    }

    @PostMapping
    @PreAuthorize("hasAnyAuthority('ADMIN', 'USER')")
    public SolicitacaoDoacao create(@RequestBody SolicitacaoDoacaoDTO dto) {
        return service.create(dto);
    }

    @GetMapping
    @PreAuthorize("hasAnyAuthority('ADMIN', 'USER')")
    public List<SolicitacaoDoacao> findAll() {
        return service.findAll();
    }

    @GetMapping("/por-item/{idItem}")
    @PreAuthorize("hasAnyAuthority('ADMIN', 'USER')")
    public List<SolicitacaoDoacao> findByItem(@PathVariable Long idItem) {
        return service.findByItemId(idItem);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }


    @GetMapping("/{id}")
    @PreAuthorize("hasAnyAuthority('ADMIN', 'USER')")
    public ResponseEntity<SolicitacaoDoacao> findById(@PathVariable Long id) {
        return service.findById(id)
                      .map(ResponseEntity::ok)
                      .orElse(ResponseEntity.notFound().build());
    }

}