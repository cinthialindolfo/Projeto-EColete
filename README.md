# 🌱 API para o Aplicativo de Reciclagem Gamificada E-colete

API RESTful desenvolvida em **Spring Boot** para o aplicativo **E-colete**, 
com o objetivo de incentivar o descarte correto de lixo eletrônico por meio de uma
plataforma interativa e gamificada.

---

## 📦 Funcionalidades

### ✅ Já Implementadas e Funcionais

- **Gestão de contas e perfil**
  - Cadastro de novos usuários com validação e criptografia de senha (`POST /api/v1/auth/cadastrar`)
  - Login com autenticação JWT (`POST /api/v1/auth/login`)
  - Visualização e atualização do perfil do usuário (`GET/PUT /api/v1/usuarios/perfil/{id}`)

- **Agendamento de coletas**
  - Agendamento de coleta com descrição, quantidade e data futura (`POST /api/v1/coletas`)
  - Listagem do histórico de coletas por usuário (`GET /api/v1/coletas?usuarioId=1`)
  - Visualização detalhada de uma coleta específica (`GET /api/v1/coletas/{id}`)
  - Cancelamento de coleta agendada (`DELETE /api/v1/coletas/{id}`)

### ⚠️ Parcialmente Implementado

- **Sistema de gamificação (ranking)**
  - Endpoint `/api/v1/usuarios/ranking` retorna lista de usuários
  - **Lógica de cálculo de pontos** existe no serviço (`10 pontos por unidade coletada`)
  - **Falta endpoint para concluir coletas**, então os pontos **não são atribuídos na prática**

### 🚧 Funcionalidades Pendentes (conforme PDF)

- [ ] **Conclusão de coletas**
  - `PUT /api/v1/coletas/{id}/concluir` → **Não implementado**

- [ ] **Notificações e conteúdo institucional**
  - `GET /api/v1/institucional/parceiros` → **Não implementado**
  - `GET /api/v1/institucional/quem-somos` → **Não implementado**
  - `GET /api/v1/notificacoes` → **Não implementado**
  - `PUT /api/v1/notificacoes/{id}/ler` → **Não implementado**

- [ ] **Mapeamento de pontos de coleta**
  - `GET /api/v1/pontos-de-coleta` (com filtros por cidade/bairro) → **Não implementado**

---

## 🛠️ Tecnologias e Ferramentas

| Categoria         | Tecnologia / Ferramenta        |
|------------------|-------------------------------|
| Linguagem        | Java 17                       |
| Framework        | Spring Boot 3.5.6             |
| Segurança        | Spring Security + JWT         |
| Banco de Dados   | PostgreSQL (Neon.tech)        |
| ORM              | Spring Data JPA + Hibernate   |
| Validação        | Jakarta Validation            |
| Documentação     | Swagger UI (OpenAPI 3)        |
| Build            | Maven                         |

---

## 🚀 Como Rodar o Projeto Localmente

### 📋 Pré-requisitos

- **Java 17+**
- **Maven 3.6+**
- **Git**
- **Neon.tech (faça o cadastro)**

> 💡 **Dica Importante do Banco Neon.tech**: O projeto já está configurado para usar um banco PostgreSQL no **Neon.tech**. As credenciais de conexão estão pré-configuradas no arquivo `application.properties`.

---

## 🎯 Passo a Passo Completo para rodar o sistema

### 1. ✅ **Clone do Repositório**

```bash
git clone https://github.com/seu-usuario/e-colete.git

# Navegue até a pasta do projeto
cd e-colete

# Executar com Maven
mvn clean spring-boot:run

## 📚 Acessar a Documentação Swagger

### URL do Swagger UI
http://localhost:8080/swagger-ui.html