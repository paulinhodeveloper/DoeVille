package br.org.sesisenai.DoaVille.security;

import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Component;

import java.util.Base64;

/**
 * Utilitário para gerar uma chave JWT segura para o algoritmo HS512
 */
@Component
public class JwtKeyGenerator {

    /**
     * Gera uma chave segura para o algoritmo HS512 e retorna em formato Base64
     * @return String contendo a chave em formato Base64
     */
    public static String generateSecureKey() {
        // Gera uma chave segura para HS512 (pelo menos 512 bits)
        byte[] keyBytes = Keys.secretKeyFor(SignatureAlgorithm.HS512).getEncoded();
        
        // Converte para Base64 para armazenar no application.properties
        return Base64.getEncoder().encodeToString(keyBytes);
    }
    
    /**
     * Método main para gerar e imprimir uma chave segura
     */
    public static void main(String[] args) {
        String secureKey = generateSecureKey();
        System.out.println("Chave JWT segura gerada (copie para o application.properties):");
        System.out.println("jwt.secret=" + secureKey);
    }
}
