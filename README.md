# Gym Workout API

**Projeto desenvolvido para fins estudantis, feito em sua grande parte manualmente.**

API para gerenciamento de treinos de academia, permitindo configurar, reconfigurar e acompanhar treinos personalizados para usuários.

## Funcionalidades

- **Configuração de Treino**: Criação de configurações de treino personalizadas com base em objetivos e mapeamentos de grupos musculares
- **Reconfiguração**: Geração de novos treinos a partir de configurações existentes
- **Verificação de Renovação**: Validação automática de quando um treino deve ser renovado
- **Consulta de Treinos**: Visualização do treino atual e histórico de treinos de uma configuração
- **Finalização de Treinos**: Marcação de treinos como concluídos

## Tecnologias

- Spring Boot 4.1.1
- Java 26
- Spring Data JPA
- Flyway (migrations)
- PostgreSQL
- OpenAPI/Swagger (documentação)
- Lombok
- MapStruct

## Documentação da API

A documentação completa da API está disponível através do Swagger UI ao executar a aplicação.

## Infraestrutura e DevOps

A configuração do ambiente PostgreSQL e possíveis microserviços relacionados a este projeto são gerenciados no repositório [rafaelmoriwork011/gym-workout-devops](https://github.com/rafaelmoriwork011/gym-workout-devops). Esse projeto contém toda a infraestrutura necessária para executar a aplicação em diferentes ambientes.

---

# Configuração do projeto

1 - Criar um arquivo application-local.yml em src\main\resources se baseando no arquivo application-local.example.yml 
<br/><br/>
2 - Criar um arquivo flyway.conf na raiz do projeto se baseando no arquivo flyway.conf.example