package br.org.sesisenai.DoaVille.controller;

import br.org.sesisenai.DoaVille.dto.JwtResponseDTO;
import br.org.sesisenai.DoaVille.dto.LoginDTO;
import br.org.sesisenai.DoaVille.security.JwtUtil;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.*;

@RestController
public class AuthController {

    private final AuthenticationManager authManager;
    private final JwtUtil jwtUtil;

    public AuthController(AuthenticationManager authManager, JwtUtil jwtUtil) {
        this.authManager = authManager;
        this.jwtUtil = jwtUtil;
    }

    @PostMapping("/auth/login")
    public ResponseEntity<?> loginDirect(@RequestBody LoginRequest request) {
        try {
            System.out.println("Tentativa de login para email: " + request.getEmail());
            
            LoginDTO dto = new LoginDTO();
            dto.setNomeUsuario(request.getEmail());
            dto.setSenha(request.getSenha());
            
            var authToken = new UsernamePasswordAuthenticationToken(dto.getNomeUsuario(), dto.getSenha());
            var authentication = authManager.authenticate(authToken);
            
            System.out.println("Autenticação bem-sucedida para: " + authentication.getName());
            
            String token = jwtUtil.generateToken(dto.getNomeUsuario());
            return ResponseEntity.ok(new JwtResponseDTO(token));
        } catch (BadCredentialsException e) {
            System.out.println("Erro de credenciais inválidas: " + e.getMessage());
            return ResponseEntity.status(401).body("Credenciais inválidas: senha incorreta");
        } catch (AuthenticationException e) {
            System.out.println("Erro de autenticação: " + e.getClass().getName() + " - " + e.getMessage());
            return ResponseEntity.status(401).body("Erro de autenticação: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Erro inesperado: " + e.getClass().getName() + " - " + e.getMessage());
            e.printStackTrace();
            return ResponseEntity.status(500).body("Erro interno: " + e.getMessage());
        }
    }
    
    @PostMapping("/api/auth/login")
    public ResponseEntity<?> login(@RequestBody LoginDTO dto) {
        try {
            System.out.println("Tentativa de login para nomeUsuario: " + dto.getNomeUsuario());
            
            var authToken = new UsernamePasswordAuthenticationToken(dto.getNomeUsuario(), dto.getSenha());
            var authentication = authManager.authenticate(authToken);
            
            System.out.println("Autenticação bem-sucedida para: " + authentication.getName());
            
            String token = jwtUtil.generateToken(dto.getNomeUsuario());
            return ResponseEntity.ok(new JwtResponseDTO(token));
        } catch (BadCredentialsException e) {
            System.out.println("Erro de credenciais inválidas: " + e.getMessage());
            return ResponseEntity.status(401).body("Credenciais inválidas: senha incorreta");
        } catch (AuthenticationException e) {
            System.out.println("Erro de autenticação: " + e.getClass().getName() + " - " + e.getMessage());
            return ResponseEntity.status(401).body("Erro de autenticação: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Erro inesperado: " + e.getClass().getName() + " - " + e.getMessage());
            e.printStackTrace();
            return ResponseEntity.status(500).body("Erro interno: " + e.getMessage());
        }
    }
    
    // Classe interna para mapear o payload com email
    public static class LoginRequest {
        private String email;
        private String senha;
        
        public String getEmail() {
            return email;
        }
        
        public void setEmail(String email) {
            this.email = email;
        }
        
        public String getSenha() {
            return senha;
        }
        
        public void setSenha(String senha) {
            this.senha = senha;
        }
    }
}
