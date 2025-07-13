package br.org.sesisenai.DoaVille.repository;

import br.org.sesisenai.DoaVille.entity.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {
}