package br.org.sesisenai.DoaVille.service;

import br.org.sesisenai.DoaVille.dto.MaterialDTO;
import br.org.sesisenai.DoaVille.entity.Material;
import br.org.sesisenai.DoaVille.repository.MaterialRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class MaterialService {

    private final MaterialRepository materialRepository;
    private final ModelMapper modelMapper;

    public MaterialService(MaterialRepository materialRepository, ModelMapper modelMapper) {
        this.materialRepository = materialRepository;
        this.modelMapper = modelMapper;
    }

    public MaterialDTO salvar(MaterialDTO dto) {
        Material material = modelMapper.map(dto, Material.class);
        return modelMapper.map(materialRepository.save(material), MaterialDTO.class);
    }

    public List<MaterialDTO> listarTodos() {
        return materialRepository.findAll()
                .stream()
                .map(m -> modelMapper.map(m, MaterialDTO.class))
                .collect(Collectors.toList());
    }

    public Optional<MaterialDTO> buscarPorId(Long id) {
        return materialRepository.findById(id).map(m -> modelMapper.map(m, MaterialDTO.class));
    }

    public Optional<MaterialDTO> atualizar(Long id, MaterialDTO dto) {
        return materialRepository.findById(id).map(m -> {
            m.setNome(dto.getNome());
            m.setPercentualCompensacao(dto.getPercentualCompensacao());
            return modelMapper.map(materialRepository.save(m), MaterialDTO.class);
        });
    }

    public void excluir(Long id) {
        materialRepository.deleteById(id);
    }
}