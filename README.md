# Desafio Acelera Maker

### Tecnologias utilizadas

<div>
<img height="30" width="40" src="https://github.com/tandpfun/skill-icons/blob/main/icons/Java-Light.svg"/>
<img height="30" width="40" src="https://github.com/tandpfun/skill-icons/blob/main/icons/Spring-Light.svg" />
<img height="30" width="40" src="https://github.com/tandpfun/skill-icons/blob/main/icons/PostgreSQL-Light.svg" />
<img height="30" width="40" src="https://github.com/tandpfun/skill-icons/blob/main/icons/Docker.svg"/>
</div>

-------------------------------------------------------------------------------------------------------------

### Instalação

#### Passo 1 - Clone o projeto

```shell
cd \
```

```shell
git clone https://github.com/lucasdanilo1/projetoblogpessoal/
```

#### Passo 2 - Acesse a pasta do projeto

```shell
cd projetoblogpessoal/ambiente
```

#### Passo 3 - Execute o docker-compose

```shell
docker compose up
```

#### Não é necessário nenhuma configuração adicional - apenas se quiser rodar sem o docker - se for o caso, altere o datasource.url para localhost ao invés de blogpessoal-db (nome do container) no properties do spring.

-------------------------------------------------------------------------------------------------------------

### Teste e disparo das requisições

Após a conclusão do processo, a API estará acessível para disparo de requisições através do seguinte endereço: http://localhost:8080.

É possível importar a coleção completa de requisições disponíveis na API para teste. 

#### Coleção das requisições

| API Collection                                                                             |
|--------------------------------------------------------------------------------------------|
| [![Run in Insomnia}](https://insomnia.rest/images/run.svg)](https://insomnia.rest/run?label=Blog%20pessoal&uri=https%3A%2F%2Fgithub.com%2Flucasdanilo1%2Fprojetoblogpessoal%2Fblob%2Fmain%2Finsomnia)|

Para mais detalhes dos endpoints, a aplicação foi documentada utilizando Swagger. Você pode acessar essa documentação em: http://localhost:8080/swagger-ui/index.html#/

------------------------------------------------------------------------------
