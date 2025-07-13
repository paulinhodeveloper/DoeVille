package br.org.sesisenai.DoaVille.service;

import br.org.sesisenai.DoaVille.dto.ClienteDTO;
import br.org.sesisenai.DoaVille.entity.Cliente;
import br.org.sesisenai.DoaVille.repository.ClienteRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ClienteService {

    private final ClienteRepository clienteRepository;
    private final ModelMapper modelMapper;

    public ClienteService(ClienteRepository clienteRepository, ModelMapper modelMapper) {
        this.clienteRepository = clienteRepository;
        this.modelMapper = modelMapper;
    }

    public ClienteDTO salvar(ClienteDTO dto) {
        Cliente cliente = modelMapper.map(dto, Cliente.class);
        return modelMapper.map(clienteRepository.save(cliente), ClienteDTO.class);
    }

    public List<ClienteDTO> listarTodos() {
        return clienteRepository.findAll()
                .stream()
                .map(c -> modelMapper.map(c, ClienteDTO.class))
                .collect(Collectors.toList());
    }

    public Optional<ClienteDTO> buscarPorId(Long id) {
        return clienteRepository.findById(id).map(c -> modelMapper.map(c, ClienteDTO.class));
    }

    public Optional<ClienteDTO> atualizar(Long id, ClienteDTO dto) {
        return clienteRepository.findById(id).map(c -> {
            c.setNome(dto.getNome());
            c.setCnpj(dto.getCnpj());
            c.setAtividadeEconomica(dto.getAtividadeEconomica());
            c.setResponsavel(dto.getResponsavel());
            return modelMapper.map(clienteRepository.save(c), ClienteDTO.class);
        });
    }

    public void excluir(Long id) {
        clienteRepository.deleteById(id);
    }
}