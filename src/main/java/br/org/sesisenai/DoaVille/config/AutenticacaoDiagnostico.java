package br.org.sesisenai.DoaVille.config;

import br.org.sesisenai.DoaVille.entity.Usuario;
import br.org.sesisenai.DoaVille.enums.Perfil;
import br.org.sesisenai.DoaVille.repository.UsuarioRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;

/**
 * Script para diagnóstico e correção de problemas de autenticação
 */
@Configuration
public class AutenticacaoDiagnostico {

    @Bean
    public CommandLineRunner diagnosticarAutenticacao(UsuarioRepository usuarioRepository, PasswordEncoder passwordEncoder) {
        return args -> {
            // 1. Listar todos os usuários existentes no banco
            List<Usuario> usuarios = usuarioRepository.findAll();
            System.out.println("\n===== DIAGNÓSTICO DE AUTENTICAÇÃO =====");
            System.out.println("Total de usuários no banco: " + usuarios.size());
            
            if (usuarios.isEmpty()) {
                System.out.println("ALERTA: Nenhum usuário encontrado no banco de dados!");
            } else {
                System.out.println("\nUsuários existentes:");
                for (Usuario u : usuarios) {
                    System.out.println("ID: " + u.getId() + 
                                      " | Nome: " + u.getNome() + 
                                      " | Email: " + u.getEmail() + 
                                      " | NomeUsuario: " + u.getNomeUsuario() + 
                                      " | Perfil: " + u.getPerfil() +
                                      " | Senha (hash): " + u.getSenha().substring(0, 10) + "...");
                }
            }
            
            // 2. Criar um usuário de teste com credenciais conhecidas
            String testEmail = "teste@email.com";
            String testSenha = "123456";
            String senhaCriptografada = passwordEncoder.encode(testSenha);
            
            // Verificar se o usuário de teste já existe
            if (usuarioRepository.findByEmail(testEmail).isEmpty()) {
                Usuario usuarioTeste = new Usuario();
                usuarioTeste.setNome("Usuário de Teste");
                usuarioTeste.setEmail(testEmail);
                usuarioTeste.setNomeUsuario(testEmail);
                usuarioTeste.setSenha(senhaCriptografada);
                usuarioTeste.setPerfil(Perfil.ADMIN);
                usuarioRepository.save(usuarioTeste);
                System.out.println("\nUsuário de teste criado com sucesso:");
            } else {
                Usuario usuarioTeste = usuarioRepository.findByEmail(testEmail).get();
                usuarioTeste.setSenha(senhaCriptografada);
                usuarioRepository.save(usuarioTeste);
                System.out.println("\nUsuário de teste atualizado com sucesso:");
            }
            
            System.out.println("Email: " + testEmail);
            System.out.println("Senha (texto): " + testSenha);
            System.out.println("Senha (hash): " + senhaCriptografada);
            
            // 3. Atualizar o usuário admin se existir
            usuarioRepository.findByEmail("admin@email.com").ifPresent(admin -> {
                admin.setSenha(passwordEncoder.encode("123456"));
                admin.setNomeUsuario("admin@email.com"); // Garantir que o nomeUsuario seja igual ao email
                usuarioRepository.save(admin);
                System.out.println("\nUsuário admin atualizado com sucesso!");
                System.out.println("Email: admin@email.com");
                System.out.println("NomeUsuario: admin@email.com");
                System.out.println("Senha: 123456");
            });
            
            System.out.println("\n===== INSTRUÇÕES DE TESTE =====");
            System.out.println("1. Tente fazer login com o usuário de teste:");
            System.out.println("   Endpoint: /auth/login");
            System.out.println("   Payload: { \"email\": \"teste@email.com\", \"senha\": \"123456\" }");
            System.out.println("\n2. Tente fazer login com o usuário admin:");
            System.out.println("   Endpoint: /auth/login");
            System.out.println("   Payload: { \"email\": \"admin@email.com\", \"senha\": \"123456\" }");
            System.out.println("\n===== FIM DO DIAGNÓSTICO =====\n");
        };
    }
}
