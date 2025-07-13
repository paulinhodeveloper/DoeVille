package br.org.sesisenai.DoaVille.repository;


import br.org.sesisenai.DoaVille.entity.Material;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MaterialRepository extends JpaRepository<Material, Long> {
}