# Projeto Gym Exercícios e Planos de Exercícios

## Índice

- [Descrição](#descrição)
- [Funcionalidades](#funcionalidades)
- [Tecnologias Utilizadas](#tecnologias-utilizadas)
- [Instalação](#instalação)
- [Configuração](#configuração)
- [Uso](#uso)
- [Endpoints](#endpoints)
- [Contribuição](#contribuição)

## Descrição

Esta API foi desenvolvida para gerenciar exercícios físicos e planos de exercícios personalizados para diferentes
usuários. É possível criar, visualizar, atualizar e deletar exercícios e planos, bem como associar exercícios a planos
específicos.

## Funcionalidades

- **Gerenciamento de Usuários**: CRUD (Create, Read, Update, Delete) de usuários.
- **Gerenciamento de Exercícios**: CRUD de exercícios.
- **Gerenciamento de Planos de Exercícios**: CRUD de planos de exercícios.
- **Associação de Exercícios a Planos**: Associar exercícios específicos a planos de exercícios.
- **Autenticação e Autorização**: Controle de acesso baseado em roles.
- **Navegação HATEOAS**: Inclusão de links de navegação nos recursos retornados.

## Tecnologias Utilizadas

- **Java 20**
- **Spring Boot**
- **Spring Data JPA**
- **H2 Database (para testes)**
- **PostgreSQL (para desenvolvimento)**
- **Spring HATEOAS**
- **Docker**
- **JUnit 5**
- 
## Instalação

1. Clone o repositório:

   ```sh
   git clone https://github.com/MateusHenriquegringo/gym-project-api
   cd gym-project-api
   ```
2. Instale as dependências do Maven:

   ```sh
    mvn clean install
   ```

## Configuração

1. Configure o banco de dados PostgreSQL em um container Docker. Crie um arquivo docker-compose.yml com o seguinte conteúdo:
   ```yaml
   version: '3.1'

   services:
      postgres:
         image: 'postgres:latest'
         environment:
            - 'POSTGRES_DB=nome-do-banco'
            - 'POSTGRES_PASSWORD=secret'
            - 'POSTGRES_USER=myuser'
         ports:
            - '9000:5432'
   ```

2. Inicie o container Docker:

   ```sh
   docker-compose up -d
   ```

3. Configure o banco de dados no arquivo _application.properties_:

   ```properties
   spring.datasource.url=jdbc:postgresql://localhost:9000/nome-do-banco
   spring.datasource.username=myuser
   spring.datasource.password=secret
   spring.jpa.hibernate.ddl-auto=update
   ```


## Uso

Para iniciar o aplicativo, execute o comando abaixo:

   ```sh
    mvn spring-boot:run
   ```

A API estará disponível em http://localhost:8080.

## Endpoints

### Autenticação

- POST /api/auth/signup - Registrar um novo usuário
- POST /api/auth/login - Autenticar um usuário

### Usuários

- GET /api/users - Listar todos os usuários
- GET /api/users/{id} - Obter detalhes de um usuário específico
- PUT /api/users/{id} - Atualizar um usuário
- DELETE /api/users/{id} - Deletar um usuário

### Exercícios

- GET /api/exercises - Listar todos os exercícios
  - Parâmetros:
       - difficulty (opcional): Filtra os exercícios por dificuldade. As opções válidas são: INICIANTE, INTERMEDIARIO, EXPERIENTE.
       - muscles (opcional): Filtra os exercícios por musculos. Pode receber uma lista, retornara apenas exercicios que trabalhem todos os musculos da lista.
- GET /api/exercises/{id} - Obter detalhes de um exercício específico
- POST /api/exercises - Criar um novo exercício
- PUT /api/exercises/{id} - Atualizar um exercício
- DELETE /api/exercises/{id} - Deletar um exercício

### Planos de Exercícios

- GET /api/plans - Listar todos os planos de exercícios
- GET /api/plans/{id} - Obter detalhes de um plano específico
- POST /api/plans - Criar um novo plano de exercícios
- PUT /api/plans/{id} - Atualizar um plano de exercícios
- DELETE /api/plans/{id} - Deletar um plano de exercícios
- POST /api/plans/{id}/exercises - Associar exercícios a um plano

## Contribuição

Contribuições são bem-vindas! Sinta-se à vontade para abrir issues e pull requests.

1. Faça um fork do projeto
2. Crie uma nova branch (git checkout -b feature/nova-feature)
3. Commit suas mudanças (git commit -am 'Adiciona nova feature')
4. Faça o push para a branch (git push origin feature/nova-feature)
5. Abra um Pull Request




