package br.org.sesisenai.DoaVille.controller;

import br.org.sesisenai.DoaVille.dto.ItemDoacaoDTO;
import br.org.sesisenai.DoaVille.entity.ItemDoacao;
import br.org.sesisenai.DoaVille.service.ItemDoacaoService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/item-doacao")
public class ItemDoacaoController {

    private final ItemDoacaoService service;

    public ItemDoacaoController(ItemDoacaoService service) {
        this.service = service;
    }

    @PostMapping
    @PreAuthorize("hasAuthority('ADMIN')")
    public ItemDoacao create(@RequestBody ItemDoacaoDTO dto) {
        return service.create(dto);
    }

    @GetMapping
    @PreAuthorize("hasAuthority('ADMIN')")
    public List<ItemDoacao> findAll() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ItemDoacao findById(@PathVariable Long id) {
        return service.findById(id);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ItemDoacao update(@PathVariable Long id, @RequestBody ItemDoacaoDTO dto) {
        return service.update(id, dto);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        if (service.findById(id) != null) {
            service.delete(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

}