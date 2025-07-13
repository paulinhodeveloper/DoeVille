# 🏙️ DoaVille - Plataforma de Doações

O **DoaVille** é uma API REST desenvolvida em Java com Spring Boot, que permite gerenciar itens de doação e solicitações feitas por usuários. A aplicação visa promover a reutilização de recursos e minimizar o desperdício, com foco em sustentabilidade social e ambiental.

---

## 🚀 Funcionalidades

### 🔐 Autenticação & Controle de Acesso (JWT)
- Login via email e senha
- Perfis:
    - `ADMIN`: acesso total
    - `USER`: pode listar/criar solicitações de doação

### 📦 Itens de Doação (`ItemDoacao`)
- Criar, editar, listar, consultar por ID e deletar itens
- Validação: nomes únicos, campos obrigatórios

### 📥 Solicitações de Doação (`SolicitacaoDoacao`)
- Criar solicitações (com data automática)
- Listar todas as solicitações
- Filtrar por item de doação
- Deletar solicitações (apenas `ADMIN`)

### 👤 Usuários (`Usuario`)
- CRUD completo com `perfil` (`ADMIN` ou `USER`)
- Usuário `admin` criado automaticamente:
    - **email:** `admin@email.com`
    - **senha:** `123456`

---

## 🧱 Tecnologias Utilizadas

- Java 21
- Spring Boot 3
- Spring Security + JWT
- PostgreSQL
- JPA (Hibernate)
- Maven

---

## 🔄 Estrutura da API

### Endpoints de Autenticação

POST   /api/auth/login

### Itens de Doação (ADMIN)

- POST   /api/item-doacao        → criar item
- GET    /api/item-doacao        → listar todos
- GET    /api/item-doacao/{id}   → consultar por ID
- PUT    /api/item-doacao/{id}   → editar item
- DEL    /api/item-doacao/{id}   → deletar item


### Solicitações de Doação (USER e ADMIN)

- POST   /api/solicitacoes                    → nova solicitação
- GET    /api/solicitacoes                    → listar todas
- GET    /api/solicitacoes/por-item/{idItem}  → listar por item
- DEL /api/solicitacoes/{id}                  → excluir (apenas ADMIN)

### Usuários (ADMIN)

- POST   /api/usuarios         → criar usuário
- GET    /api/usuarios         → listar todos
- GET    /api/usuarios/{id}    → consultar por ID
- PUT    /api/usuarios/{id}    → editar
- DEL /api/usuarios/{id}       → deletar

