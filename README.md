### MAP SKILLS - REPORT - FACULDADE DE TECNOLOGIA PROF º JESSEN VIDAL

##### Projeto de relatório de alunos que participaram da avaliação de competência nos inicios dos semestres das unidades de ensino Centro Paula Souza.

##### CONFIGURAÇÃO

Perfis
- `local, azure-qas, azure-prd`

Spring-boot : [Spring Boot](https://projects.spring.io/spring-boot/ "Spring Boot")
- Rodar localmente
`mvn spring-boot:run -Plocal`

- Gerar pacote
`mvn clean install -Plocal`

Docker : [Docker](https://www.docker.com/ "Docker")
- Construir imagem a partir do Dockerfile  
`docker build -t mapskills/mapskills-report .`

- Criar instância do container
`docker run -it -d --rm --network mapskills-network --name mapskills-report -p 8083:8083 mapskills/mapskills-report`
