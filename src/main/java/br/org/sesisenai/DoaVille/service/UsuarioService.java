package br.org.sesisenai.DoaVille.service;

import br.org.sesisenai.DoaVille.dto.UsuarioDTO;
import br.org.sesisenai.DoaVille.entity.Cliente;
import br.org.sesisenai.DoaVille.entity.Usuario;
import br.org.sesisenai.DoaVille.enums.Perfil;
import br.org.sesisenai.DoaVille.repository.ClienteRepository;
import br.org.sesisenai.DoaVille.repository.UsuarioRepository;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final ClienteRepository clienteRepository;
    private final PasswordEncoder passwordEncoder;
    private final ModelMapper modelMapper;

    public UsuarioService(UsuarioRepository usuarioRepository,
                          ClienteRepository clienteRepository,
                          PasswordEncoder passwordEncoder,
                          ModelMapper modelMapper) {
        this.usuarioRepository = usuarioRepository;
        this.clienteRepository = clienteRepository;
        this.passwordEncoder = passwordEncoder;
        this.modelMapper = modelMapper;
    }

    public UsuarioDTO salvar(UsuarioDTO dto) {
        Usuario usuario = new Usuario();
        usuario.setNome(dto.getNome());
        usuario.setNomeUsuario(dto.getNomeUsuario());
        usuario.setSenha(passwordEncoder.encode(dto.getSenha()));
        usuario.setPerfil(Perfil.valueOf(dto.getPerfil()));

        if (usuario.getPerfil() == Perfil.USER) {
            Cliente cliente = clienteRepository.findById(dto.getClienteId())
                .orElseThrow(() -> new RuntimeException("Cliente não encontrado"));
            usuario.setCliente(cliente);
        }

        return modelMapper.map(usuarioRepository.save(usuario), UsuarioDTO.class);
    }

    public List<UsuarioDTO> listarTodos() {
        return usuarioRepository.findAll()
            .stream()
            .map(u -> modelMapper.map(u, UsuarioDTO.class))
            .collect(Collectors.toList());
    }

    public Optional<UsuarioDTO> buscarPorId(Long id) {
        return usuarioRepository.findById(id)
            .map(u -> modelMapper.map(u, UsuarioDTO.class));
    }

    public Optional<UsuarioDTO> atualizar(Long id, UsuarioDTO dto) {
        return usuarioRepository.findById(id).map(u -> {
            u.setNome(dto.getNome());
            u.setNomeUsuario(dto.getNomeUsuario());
            u.setPerfil(Perfil.valueOf(dto.getPerfil()));
            if (dto.getSenha() != null && !dto.getSenha().isEmpty()) {
                u.setSenha(passwordEncoder.encode(dto.getSenha()));
            }
            return modelMapper.map(usuarioRepository.save(u), UsuarioDTO.class);
        });
    }

    public void excluir(Long id) {
        usuarioRepository.deleteById(id);
    }
}