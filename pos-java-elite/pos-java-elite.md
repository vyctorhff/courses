#####################################################################################################
Github

https://github.com/unipds-projetos

#####################################################################################################
#####################################################################################################
#####################################################################################################
#####################################################################################################
# Testes

# Módulo 9 - Aula 01 - ?? ------------------------------------------------------------------

Junit
TDD
Mockito
AssertJ

# Módulo 9 - Módulo 4 - Aula 01 ----------------------------------------------------------------

SpotBugs/PMD

- Analisador de código estático
- Analisam o código procurando por más práticas, descuidos de código e outros

# Módulo 9 - Módulo 4 - Aula 02,03,04 ----------------------------------------------------------------

SpotBugs

- Analisa o bytecode
- tem mais de 400 padrões de bugs
- maven plugin: spotbugs-maven-plugiin
	mvn spotbugs:spotbugs
	mvn spotbugs:check
	mvn spotbugs:gui
- Gera um arquivo spotbugsXml.xml
- No Gui exibe bem os problemas
- Pode executar o maven site para mostrar os relatórios
	mvn site

# Módulo 9 - Módulo 4 - Aula 05 ----------------------------------------------------------------

PMD

- Analisa o código .java
- Atua bem em conjuto com o spotbug
- Analisa código morto, empty catch, repetições etc
- maven plugin
	mvn pmd:check
- O relatório aparece no mvn site
	mvn site
- Tem um plugin do PMD para intelliJ

# Módulo 9 - Módulo 5 - Aula 05 ----------------------------------------------------------------



#####################################################################################################
# Banco de Dados

# Módulo 8 - Aula 01 ------------------------------------------------------------------

Formar Normais

- Dependência Funcional
- Dependência Funcional Parcial
- Dependência Funcional Transitiva
- Formas Normais

# Módulo 8 - Aula 02 ------------------------------------------------------------------
# Módulo 8 - Aula 03 ------------------------------------------------------------------
# Módulo 8 - Aula 04 ------------------------------------------------------------------
# Módulo 8 - Aula 05 ------------------------------------------------------------------
# Módulo 8 - Aula 06 ------------------------------------------------------------------

Flyway e Liquibase

https://github.com/unipds-projetos/modulo8-bancodedados-liquibase
https://github.com/unipds-projetos/modulo8-bancodedados-flyway

- flyway: usa a estratégia declarativa
- flyway: arquivo sql e sem rollback
- liquibase: arquivos yaml, xml, json ou sql formatado; com rollback
- liquibase: 

# Módulo 8 - Aula 07 ------------------------------------------------------------------

NoSQL

- Relacionas seguem o modelo ACID
- NoSQL segue o modelo BASE
	Basically Available
	Soft State
	Eventual Consistency
- Basically Available
	O sistema garante que sempre haverá uma resposta para qualquer requisição, mesmo que ocorra uma falha parcial em algum servidor ou nó do cluster
- Soft State
	O estado dos dados pode mudar ao longo do tempo sem a necessidade de uma interação direta do usuário
- Eventual Consistency
	É o cerne do modelo. O sistema garante que, se nenhuma nova atualização for feita, todos os nós distribuídos eventualmente se sincronizarão e alcançarão o mesmo estado idêntico e correto
- Teorema CAP
	Um sistema de banco de dados distribuído pode garantir, no máximo, duas de três propriedades desejadas: Consistência(C), Disponibilidade(D) e Tolerância a Partições(P)

	cassanda -> A, P
	mongo -> C, P
- Quando usar
	Modelo de dados muda com frequencia
	Volume de dados grande e heterogênio
	Necessidade escalar horizontalmente
	Mais escrita/leitura do que atualização/delete

# Módulo 8 - Aula 08 ------------------------------------------------------------------

Redis

https://github.com/unipds-projetos/modulo8-bancodedados-redis

- Inserir e recuperar dados rápido; e os dados são descartáveis com o tempo
- Pode ser usado com PUB/SUB e streams


# Módulo 8 - Aula 09 ------------------------------------------------------------------

Mongo

https://github.com/unipds-projetos/modulo8-bancodedados-mongodb

- Pode usar @Query para consultas no mongo

# Módulo 8 - Aula 10 ------------------------------------------------------------------

Cassanda

https://github.com/unipds-projetos/modulo8-bancodedados-cassandra

- Orientado para colunas
- processar grandes volumes de dados, baixa latência e alta disponibilidade
- Replicação horizontal
	Cada dado tem cópia em vários nós
- qualquer nó pode antender como leitura/escrita
	Mas não tem consistência imediata, mas com alguns ms segundos depois
- Modelos de Dados
	keyspace: agrupamento de tabelas (como um schema)
	table(column family): conjunto de dados organizados por chave
	partition: subconjunto de linhas para uma chave da partiçõa
	row: conjunto de colunas
	column: nome + valor + timestamp
- Não há joins
- CQL (Cassanda SQL)
- Prática
- Atenção consultas que filtram colunas com mais de 100k de registros
	A recomendação é criar outra
	Estudar melhor depois

# Módulo 8 - Aula 11 ------------------------------------------------------------------

NeoJ4

https://github.com/unipds-projetos/modulo8-bancodedados-neo4j

- Orientados a grafos. Banco vetorial
- Joins: são as relaões entre cada nó
- Suporte a transação ACID
- 


#####################################################################################################
# Infra and Clouding 

# Módulo 1 - Aula 01 ------------------------------------------------------------------

Apresentação

- Mostrando projeto exemplo

# Módulo 1 - Aula 02 ------------------------------------------------------------------

Docker

# Módulo 1 - Aula 03 ------------------------------------------------------------------

Docker Hub

# Módulo 1 - Aula 04 ------------------------------------------------------------------

Otimização docker file - multilayers

# Módulo 1 - Aula 05 ------------------------------------------------------------------

Podman

# Módulo 1 - Aula 06 ------------------------------------------------------------------

Podman Desktop

# Módulo 1 - Aula 07 ------------------------------------------------------------------

Kubernates

- Node master: instancia que coordena as outras
- Node worker: instancia com as aplicações
- Control Plane (Node Master)
-- API Server => redireciona as requisições para cada pod
-- Scheduler => agedador para iniciar pods
-- etcd => banco chave-valor com o estado 
-- Cloud Controller => integra com o provedor de nubem
- Pod
-- Contem o IP
-- É vinculado a um ReplicaSet
- ReplicaSet: controla a quantidade/inicialização/finalização dos pods
- Service:
-- Node Port
-- Balance
-- ???

# Módulo 1 - Aula 08 ------------------------------------------------------------------

Kubernate na prática

https://github.com/unipds-projetos/modulo7-cloud-infra-unipdi/tree/aula08

- Usando docker desktop com kubernate(kind)
- Criando pod mualmente
- Criando pods por arquivo
- Criando confimap, secret, pods, e service

# Módulo 1 - Aula 09 ------------------------------------------------------------------

Escalabilidade é Saúde

- readness
- liveness
- replica para quando recurso de cpu atingir o máximo configurado

# Módulo 1 - Aula 10 ------------------------------------------------------------------

AWS

- Apresentação de alguns serviços para desenvolvimento backend
- Instalação AWS CLI
	windows command: irm https://awscli.amazonaws.com/v2/install.ps1 | iex
- Apresentação criação da conta

# Módulo 1 - Aula 11, 12 ------------------------------------------------------------------

AWS S3

- Mostrando configurações
- Mostrando api

# Módulo 1 - Aula 13 ------------------------------------------------------------------

AWS Lambda

https://github.com/unipds-projetos/modulo7-cloud-infra-unipdi/tree/aula13

- Cria lib java para executar
- Cria a lambda
- Adicina o jar(fat jar)
- Adicina gatilho para o S3
- AWS Parameter Store: adiciona as configurações de email
	Pesquisar e estudar AWS Secret
- Conta google: adicionar um senha para aplicação
	Gerenciar conta google -> segurança -> senhas de app
- IAM: adicionar permissão para a função conseguir enviar email
	adicionar: AmazonSSMReadOnlyAccess

# Módulo 1 - Aula 14 ------------------------------------------------------------------

AWS Billing
Github Actions

https://github.com/jacqueline-oliveira/unipdi/tree/aula14

- adicionando avisos de custos
- git action: mostrando marketplace para configuração
	https://github.com/marketplace?type=actions
- git action: construindo script
- git actions: secret
	settings -> secrets and variables -> actions
- git actions: 

# Módulo 1 - Aula 15 ------------------------------------------------------------------

Jenkins

- Instalação e configuração
#####################################################################################################
# Concorrência e Multithreading em Java

# Módulo 1 - Aula 01 ------------------------------------------------------------------

- Apresentando todas as soluções de concorrência
- Falando sobre a importância de estudar multithreads

# Módulo 1 - Aula 02 ------------------------------------------------------------------

java 5 - ExecuterService

- Como iniciar
- Como parar
- Qual usar

# Módulo 1 - Aula 03 ------------------------------------------------------------------

Coleções para usar em multithread

# Módulo 1 - Aula 04 ------------------------------------------------------------------

sincronizadores

- CountDownLatch
- CycliBarrier
- Semaphore

# Módulo 1 - Aula 05 ------------------------------------------------------------------

Java 7 - Fork/join

- Usa o ExecuterService internamento
- Dividir para conquistar

# Módulo 1 - Aula 06 ------------------------------------------------------------------

Java 8 - Completable Future

# Módulo 1 - Aula 07 ------------------------------------------------------------------

Parallel Stream

- Rever!!

# Módulo 1 - Aula 08 ------------------------------------------------------------------

Virtual Threads

- Uma VT para cada tarefa que precisa ser feita
- Não use pool com virtual threads

# Módulo 1 - Aula 09 ------------------------------------------------------------------

Prática

#####################################################################################################
# Software Desgin And System Design

https://github.com/unipds-projetos/modulo5-software-design-system-design

# Módulo 1 - Aula 08 ------------------------------------------------------------------

Prática com alguns ideia de implementação

## Simplifique: não crie interface se não for necessário.

Comece com classes concretas e depois crie a interface, se necessário
Spring já recomenda fazer assim
Cria interface foi um necessidade na época do EJB e antes disso em smalltak

## Encapsulamento

- Retornar lista como cópias
- Não adicionar get e set sem necessidade. Evintar @Data do lombok


## Organização de pacotes

- package by layer

Cada pacote tem uma camada do software
Os pacotes são: config, service, domain, database etc

- package by feature

Primeiro temos as features e depois acrescentar as camadas
Ajuda na refatoração para micro serviço

Exemplo:
	br.com.app.user.controller
	br.com.app.user.service
	br.com.app.payment.controller
	br.com.app.payment.service

## Criação de tipos

- Crie tipos para representar o dominio
- Ler: https://www.martinfowler.com/ieeeSoftware/whenType.pdf
- Use enum com implementações abstradas
- Evite tipos primitidos quando os dados fazem sentido ficarem juntos
- Object Calistenics

# Módulo 1 - Aula 09 ------------------------------------------------------------------

- Padrões de projetos principais
- Dicas de livros
- Singleton, Factory, Strategy, Observer, Builder, Prototype(pouco utilizado),
	Adapter, Decorator, Proxy, 

# Módulo 1 - Aula 10 ------------------------------------------------------------------

- Exemplos práticos de implementação dos padrões de projetos
- Exemplo implementação observer com CDI. (Fazer na prática)
- Exemplo de decorator com CDI (Fazer na prática)

# Módulo 1 - Aula 11 ------------------------------------------------------------------

DDD

- Teoria
- Assistir novamente!
- É um framework(metodologia) para trazer o conhecimento de negócio para dentro da aplicação

# Módulo 1 - Aula 12 ------------------------------------------------------------------

JMolecule

- Assistir novamente!
- Framework para verificar se 'ideia' do ddd estão sendo respeitadas
- É necessário usar as anotações do jmolucule no dominio
- As anotações do spring são reconhecidas pelo jmolecule

# Módulo 2 - Aula 01 ------------------------------------------------------------------

Projeto prático

## Trello

https://trello.com/c/4DkRkFhG/1-01-cotuba-conhecendo-o-projeto-e-configurando-o-ambiente
https://trello.com/b/XamAmgsf/projeto-unipds-software-e-system-design

## Repo

https://github.com/alexandreaquiles/modulo5-projeto-cotuba
https://github.com/orgs/unipds-projetos/repositories?type=all

# Módulo 2 - Aula 02 ------------------------------------------------------------------

Single Responsability Principle

- Não é ter um método por classe
- É que a classe deve ser moficada para um escopo(razão)

# Módulo 2 - Aula 03 ------------------------------------------------------------------

DDD

- Implementando dominio segundo o DDD

# Módulo 2 - Aula 04 ------------------------------------------------------------------

Dependency Injection

- High-level business rules do not know about low-level details
	Classes de negócio não devem depender de classe de infraestrutura

# Módulo 2 - Aula 05 ------------------------------------------------------------------

# Módulo 2 - Aula 06 ------------------------------------------------------------------

Use objetos imutáveis (records)
Builder pattern

- Use o padrão builder quando é necessário criar um objeto que não dá para setar tudo de uma vezes
 as variáveis são obtidas de diferentes formas ou maneiras.

# Módulo 2 - Aula 07 ------------------------------------------------------------------

Entrevista simulada

- O entrevistado sempre deve puxar as perguntas, tente deduzir o mínimo possivel
- O entrevistador vai esperar que vc puxe a entrevista e não o contrário

## Exemplo

- O entrevistador vai começar explicando qual é o problema inicial e pede como vc criar uma solução 
- Antes de pegar no código, vc deve entender o que eles querem e precisam
- O entrevistado abre um arquivo texto(.md) e começa a escrever alguns tópicos importantes
- O entrevistado pode fazer um diagrama C4(mermaid local, mermaid live ou asciidoc)
- 

# Módulo 2 - Aula 08 ------------------------------------------------------------------

JMolecules

- Anotando serviços e domínio com o jmolecules
- Estudando pacotes do DDD. Divisão de pacotes estrita ou relaxada(UI pode acessar infraestrutura)

# Módulo 2 - Aula 09 ------------------------------------------------------------------

Adicionando plugin usando ServiceLouder

# Módulo 2 - Aula 11 ------------------------------------------------------------------

Interface Segregation Principle

- Não obrigar as classes a implementar métodos que não precisam
- Ação: criar interfaces separadas

# Módulo 2 - Aula 12 ------------------------------------------------------------------

Simulação de entrevista sobre escalabilidade

- Criar documentos(md) ADR(Architecture Decision Record ou Registro de Decisão de Arquitetura) para documentar
- Vincular estes documentos ao documento original
- No documento ADR colocar: context(descrição problema), opções, solução escolhida, consequências(positivas e negativas)

# Módulo 2 - Aula 13, 14-------------------------------------------------------------

Modularizacao

- nao permitir que 
- modulo é diferente de pacote
- pode usar o maven para modularizar
- usar JPMS
- JPMS não é tão usado, mas sempre use quando o seu jar for usado por alguma lib ou outra aplicação no classpath
- JPMS dá mais segurança para a aplicação e modularidade
- JPMS: atenção quando usar o 'open' para permitir reflactions
- JPMS: não funciona bem com jar contando todos as libs dentro dele, precisa usar o classpath passando todas as dependências
	ou usar o apache assembly

# Módulo 2 - Aula 12 ------------------------------------------------------------------

Entrevista: como resolver problema de flash sale(vendas relâmpago devido promoção ou celebridade fazendo pub) e quando
	a api de pagamento estiver lento

- Faça diagrama de sequência para cenários especificos(mermaid)
- Solução: enfileira as solicitaões de inscrições ou pagamentos 

#####################################################################################################
https://trello.com/b/KmZHipzR/projeto-pratico-unipds-fundamentos-java


https://github.com/unipds-projetos/modulo8-bancodedados-flyway
https://github.com/unipds-projetos/modulo2-backend-quarkus
https://github.com/unipds-projetos/modulo2-enterprise-ai-java-langchain4j
https://github.com/unipds-projetos/modulo1-fundamentos-java-desafio-1-encapsulamento