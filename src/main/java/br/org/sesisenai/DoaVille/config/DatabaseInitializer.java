package br.org.sesisenai.DoaVille.config;

import br.org.sesisenai.DoaVille.entity.Usuario;
import br.org.sesisenai.DoaVille.enums.Perfil;
import br.org.sesisenai.DoaVille.repository.UsuarioRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * Configuração para inicialização automática do banco de dados e criação do usuário administrador
 */
@Configuration
public class DatabaseInitializer {

    /**
     * Cria o usuário administrador se não existir
     */
    @Bean
    @Order(1)
    public CommandLineRunner initDatabase(UsuarioRepository usuarioRepository, PasswordEncoder passwordEncoder) {
        return args -> {
            System.out.println("\n===== INICIALIZAÇÃO DO BANCO DE DADOS =====");
            
            // Verifica se existe algum usuário administrador
            boolean adminExists = usuarioRepository.findAll().stream()
                    .anyMatch(u -> u.getPerfil() == Perfil.ADMIN);
            
            if (!adminExists) {
                System.out.println("Nenhum administrador encontrado. Criando usuário administrador padrão...");
                
                Usuario admin = new Usuario();
                admin.setNome("Administrador");
                admin.setEmail("admin@email.com");
                admin.setNomeUsuario("admin@email.com");
                admin.setSenha(passwordEncoder.encode("123456"));
                admin.setPerfil(Perfil.ADMIN);
                
                usuarioRepository.save(admin);
                
                System.out.println("Usuário administrador criado com sucesso!");
                System.out.println("Email: admin@email.com");
                System.out.println("Senha: 123456");
            } else {
                System.out.println("Usuário administrador já existe no banco de dados.");
            }
            
            System.out.println("===== INICIALIZAÇÃO CONCLUÍDA =====\n");
        };
    }
}
