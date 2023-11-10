***Sistema de Agendamento de Transferências Financeiras***

Este projeto é como o maestro das suas finanças, permitindo que você agende transferências e tenha um histórico completo desses agendamentos.

***Tecnologias Utilizadas:***
* Spring Boot 3.1.5: Framework utilizado para o desenvolvimento eficiente da aplicação Java.
* Spring Data JPA: Facilita a manipulação e acesso ao banco de dados;
* Spring Data REST: Funciona como um mensageiro, simplificando a comunicação da aplicação através de APIs REST.
* H2 Database: Um cofre em memória onde guardamos todas as informações sobre os agendamentos.
* Lombok: Reduz a verbosidade do código.
* Maven: Ferramenta de automação que gerencia as dependências e compilação do projeto.

***Decisões Arquiteturais:***
* Para o design da aplicação, optei por uma estrutura modular, onde diferentes partes desempenham funções específicas, a fim de ter facilidade no momento de manutenção.

***Versões Utilizadas:***
* Escolhi o Spring Boot 3.1.5 por ser uma escolha solida diante as novas versões, junto com as praticas recomendadas e as atualizações recentes da comunidade Spring, com total compatibilidade com o Java 11.

***Frontend:***
* O frontend deste projeto está disponível em '[transf-scheduler-ui](https://github.com/matheusksn/transf-scheduler-ui)'.

***Como Executar o Projeto:***
* Clone este repositório para sua máquina.
* Verifique se você tem o Java 11 e o Maven instalados.
* Execute mvn clean install na raiz do projeto.
* Inicie a aplicação executando a classe principal TransfSchedulerApiApplication.
* Acesse a API em http://localhost:8080/api/transferencias.
