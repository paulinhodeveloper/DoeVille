package br.org.sesisenai.DoaVille.config;

import br.org.sesisenai.DoaVille.entity.Usuario;
import br.org.sesisenai.DoaVille.enums.Perfil;
import br.org.sesisenai.DoaVille.repository.UsuarioRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class DataInitializer {

    @Bean
    CommandLineRunner initAdmin(UsuarioRepository usuarioRepository, PasswordEncoder encoder) {
        return args -> {
            if (usuarioRepository.findAll().stream().filter(u -> u.getNomeUsuario().equals("admin")).findFirst().isEmpty()) {
                Usuario admin = new Usuario();
                admin.setNome("Administrador");
                admin.setNomeUsuario("admin");
                admin.setSenha(encoder.encode("admin"));
                admin.setPerfil(Perfil.ADMIN);
                usuarioRepository.save(admin);
                System.out.println("Usuário admin criado com sucesso!");
            }
        };
    }
}