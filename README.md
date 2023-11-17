# Processos Seletivos

# Tecnologias

- Back-end
    - Java 17+
    - Maven
    - Spring-Boot
        - Spring Security
        - Jpa
        - Lombok
        - Spring Web
        - Thymeleaf

- Front-end
    - HTML 5
    - CSS 3
    - JavaScript
        - Vanillas Masker
        - Tiny Slider
        - DataTable
        - Sweet Alert 2
    - Padrão de governo
    - BootsTrap 512

- Banco de dados
    - MySql
        - MySql Workbench

# Design Patterns

- **Classes:** ClasseComposta
- **Objetos:** objetoComposto
- **Variáveis:** variavelComposto
- **Arquivos:** nome_composto

# Modelagens

### Modelagem De Dados

![modelagem de dados da aplicação](doc/modelagem de dados.png)

### Fluxo de Navegação de Páginas

![modelagem do fluxo de uso da apliocação](doc/modelagem de fluxo.png)

# **Instruções de Compilação e Execução (Local)**

### Para rodar o projeto localmente é necessário configurar a variáveis de ambiente que são:

- DB_SERVER="localhost"
- DB_USERNAME=”{Username do banco de dados usado}”
- DB_PASSWORD="{Senha do banco de dados usado}"
- LDAP_URL="{Ip do servidor LDAP ultilizado}"
- LDAP_USERNAME="{LDAP username}"
- LDAP_PASS="{Senha do LDAP}"
- SMTP_LOGIN="{Login do SMTP"
- SMTP_PASSWORD="{Senha do SMTP}"

### Instalações necessárias

- Mysql
    - Mysql Workbench
- Java 17
- IDE(IntelliJ ou NetBeans)

# **Instruções de Compilação e Execução (Servidor)**

Para rodar o projeto no servidor é necessário seguir os seguintes paços:

1. Buildar o projeto localmente para gerar o .jar necessário.
2. Montar um docker com java 17  instalada no servidor que será utilizado.
3. Colocar o arquivo .jar do projeto dentro da pasta dockerfile da docker.
4. Em seguida devemos rodas os seguintes comandos:
