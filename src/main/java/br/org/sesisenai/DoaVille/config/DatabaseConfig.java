package br.org.sesisenai.DoaVille.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;

/**
 * Configuração para criar o banco de dados automaticamente se não existir
 */
@Configuration
public class DatabaseConfig {

    /**
     * Configura o DataSource para criar o banco de dados automaticamente se não existir
     * Isso é aplicado apenas no perfil "dev" para evitar problemas em produção
     */
    @Bean
    @Profile("dev")
    public DataSource dataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("org.postgresql.Driver");
        dataSource.setUrl("jdbc:postgresql://localhost:5432/postgres");
        dataSource.setUsername("postgres");
        dataSource.setPassword("postgres");
        
        // Cria o banco de dados DoaVille se não existir
        try {
            java.sql.Connection conn = dataSource.getConnection();
            java.sql.Statement stmt = conn.createStatement();
            
            // Verifica se o banco de dados já existe
            java.sql.ResultSet rs = stmt.executeQuery(
                "SELECT 1 FROM pg_database WHERE datname = 'doaville'"
            );
            
            if (!rs.next()) {
                // O banco de dados não existe, então cria
                stmt.execute("CREATE DATABASE doaville");
                System.out.println("Banco de dados 'doaville' criado com sucesso!");
            } else {
                System.out.println("Banco de dados 'doaville' já existe.");
            }
            
            rs.close();
            stmt.close();
            conn.close();
            
            // Retorna o DataSource apontando para o banco de dados DoaVille
            dataSource.setUrl("jdbc:postgresql://localhost:5432/doaville");
            return dataSource;
            
        } catch (Exception e) {
            System.err.println("Erro ao verificar/criar o banco de dados: " + e.getMessage());
            
            // Em caso de erro, retorna o DataSource apontando para o banco de dados DoaVille
            // assumindo que ele já existe ou será criado pelo Hibernate
            dataSource.setUrl("jdbc:postgresql://localhost:5432/doaville");
            return dataSource;
        }
    }
}
