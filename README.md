# Tecnologias
- **Back-end**
    - Java 17+
    - Maven
    - Spring-Boot(3.1.3)
        - Spring Security
        - Jpa
        - Lombok
        - Spring Web
        - Thymeleaf

- **Front-end**
    - HTML 5
    - CSS 3
    - Bootstrap 5
    - [Padrão de governo](https://www.gov.br/ds/home)
    - JavaScript
        - Vanillas Masker
        - Tiny Slider
        - DataTable
        - Sweet Alert 2
    

- **Banco de dados**
    - MySql
        - MySql Workbench

## Design Patterns

- **Classes:** ClasseComposta
- **Objetos:** objetoComposto
- **Variáveis:** variavelComposto
- **Arquivos:** nome_composto

# Modelagens

### Modelagem De Dados

<img src="documentation/modelagem de dados.png">

### Fluxo de Navegação de Páginas

<img src="documentation/modelagem de fluxo.png">

# **Instruções de Compilação e Execução (Local)**

### Para rodar o projeto localmente é necessário configurar a variáveis de ambiente que são:

- **DB_SERVER**=<server>
- **DB_USERNAME**=<username>
- **DB_PASSWORD**=<password>
- **LDAP_URL**=<URL do servidor LDAP ultilizado>
- **LDAP_USERNAME**=<username do servidor LDAP>
- **LDAP_PASS**="<senha do servidor LDAP>
- **SMTP_LOGIN**=<login do servidor SMTP>
- **SMTP_PASSWORD**=<senha do SMTP>

### Instalações necessárias

- **Mysql**
    - Mysql Workbench
- **Java 17**
- **IDE(IntelliJ ou NetBeans)**

# **Instruções de Compilação e Execução (Servidor)**

Para rodar o projeto no servidor é necessário seguir os seguintes paços:

**1.** Buildar o projeto localmente para gerar o .jar necessário.
**2**. Montar um docker com java 17  instalada no servidor que será utilizado.
**3.** Montar a dockerfile com as configuesções do projeto.
```docker
    #image
    FROM eclipse-temurin:17-jdk-alpine
    
    #info
    LABEL author="Thiago Ribeiro/Vinicius Teixeira"
    LABEL maintainer="thiagoribeirods@outlook.com/vini.barrost@gmail.com"
    LABEL description="Imagem para rodar aplicações Spring Boot com JDK 17"
    LABEL version="1.0"
    LABEL release="2023-10-11"
    
    WORKDIR /opt
    
    COPY processo-seletivo.jar /opt
    
    EXPOSE 53080
    
    ENTRYPOINT ["java","-jar","processo-seletivo.jar"]
```

**4.** Colocar o arquivo .jar do projeto dentro da pasta dockerfile da docker.
**5.** Em seguida devemos rodas os seguintes comandos:
    Para construir a docker
   ```sh
    docker build -t <nome_container> .   
   ```
   Iniciando a docker e definindo as variaveis de ambiente necessarias para rodar o projeto:
   ```sh
    sudo docker run -d -p 53080:53080 --name="processo-seletivo" -e DB_SERVER=<server> -e DB_USERNAME=<username> -e DB_PASSWORD=<password> -e LDAP_URL=<url> -e LDAP_PASS=<password> -e SMTP_LOGIN=<login> -e SMTP_PASSWORD=<password>
