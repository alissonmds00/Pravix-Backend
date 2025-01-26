API CRUD para gerenciamento de Clientes, feita em Java

<h2>Requisitos:</h2>
- JDK versão 21 ou superior download em: https://www.oracle.com/br/java/technologies/downloads/#java21
- Maven
- Uma database no Postgresql nomeada "pravix"

OPÇÃO COM DOCKER (RECOMENDADO):
- Docker (apenas)

- configurar os valores de DB_PASSWORD e DB_USERNAME, no arquivo application.properties
localizado em ./src/main/resources/
os valores devem ser armazenados nas variáveis do ambiente, e os valores devem o usuário e senha do servidor postgres, por padrão o usuário é "postgres".
os valores podem ser alterados diretamente nas variáveis, ao invés de utilizar variáveis de ambiente

<h2>Execução:</h2>
- navegue até o diretório raiz do projeto, utilizando um CLI
    cd caminho/para/o/projeto
- mvn clean install
- mvn spring-boot:run
- a porta definida para a aplicação é 555, mas pode ser alterada no arquivo application.properties

EXECUÇÃO ATRAVÉS DO DOCKER (RECOMENDADO):
Ao acessar a pasta raiz do projeto, execute o comando:
- docker-compose up --build

<h2>Endpoints:</h2>
- Caso hospedado localmente, o uri padrão será: 
http://localhost:555/*
- Documentação e detalhamento dos endpoints:
http://localhost:555/swagger-ui/index.html

<h2> Diagrama de classes </h2>
<img src="Diagrama%20de%20classes.png" alt="diagrama de classes">