# Sistema de Registro de Exames Laboratoriais 

## Descrição do Projeto

Este projeto consiste no desenvolvimento de um sistema em Java para registro e consulta de exames laboratoriais solicitados para pacientes de um laboratório de análises clínicas. 

O sistema foi desenvolvido utilizando conceitos de Programação Orientada a Objetos (POO), aplicando princípios como:

> Encapsulamento

> Associação entre classes

>Organização em múltiplas classes

> Leitura e gravação de arquivos texto (.TXT)

> Implementação manual de listas lineares

---

## Objetivo

Permitir que o laboratório consiga:

* Carregar os exames disponíveis a partir de um arquivo '.TXT'
* Cadastrar pacientes e seus exames solicitados
* Calcular automaticamente a data de liberação dos resultados
* Consultar os dados de um paciente pelo CPF
* Finalizar os atendimentos do dia gravando os registros em arquivo
* Exibir estatísticas sobre os atendimentos realizados

---

## Estrutura do Sistema

O sistema é composto pelas seguintes classes:

### 'Laboratorio'
> Classe principal de gerenciamento e controle do sistema:

* Carrega os exames disponíveis do arquivo '.TXT'
* Exibe e controla o menu principal 
* Cadastra novos pacientes e seus pedidos de exames
* Realiza buscas sequenciais por exame e por paciente
* Grava o arquivo de finalização dos atendimentos do dia

### 'Exame'
> Representa um exame disponivel no laboratório:

1. Abreviação 
2. Nome
3. Quantidade de dias para liberação do resultado

### 'PedidoExame'
> Representa o pedido de exames de um paciente:

1. CPF
2. Nome
3. Data de realização
4. Data de liberação dos resultados
5. Lista de exames solicitados ('ArrayList<Exame>')

---

## Regras de Negócio

* Os exames disponíveis são carregados de um arquivo '.TXT' e armazenados em um vetor de objetos 'Exame' com no máximo 200 ocorrências
* As opções 2 a 5 do menu só ficam disponíveis após o carregamento do arquivo de exames (opção 1)
* A data de liberação é calculada com base no **maior prazo** entre os exames solicitados pelo paciente
* A busca por exames e pacientes é feita por **busca sequencial**
* Após a finalização dos atendimentos (opção 4), apenas as opções de estatísticas e saída permanecem acessíveis
* O arquivo de saída é nomeado automaticamente com a data atual no formato 'aaaammdd.txt'

---

## Funcionalidade 

### Carregar Dados de Exames
- Leitura do arquivo '.TXT' com os exames disponíveis
- Armazenamento em vetor de objetos 'Exame'

### Novo Paciente
- CPF do paciente
- Nome do paciente
- Abreviações dos exames solicitados (encerrado com 'XXX')
- Exibição do nome e prazo de cada exame encontrado
- Cálculo e exibição da data de liberação dos resultados

### Consultar Paciente
- Busca por CPF no vetor de pedidos do dia
- Exibição dos dados do paciente, exames solicitados e data de liberação
- Mensagem de erro caso o paciente não seja encontrado


### Finalização dos Atendimentos 
- Gravação de todos os pedidos do dia em arquivo '.TXT'
- Formato de cada linha:
  '''
  CPF; NOME; DATA_REALIZACAO; DATA_LIBERACAO; ABREVI1; ABREV2;....
  '''

  ### Estatísticas
  - Exibição de estatísticas gerais sobre os atendimentos e exames do dia
 
  ### Sair
  - Exibe os nomes dos integrantes e encerra a aplicação

  ## Formato do Arquivo de Entrada
  


