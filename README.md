# BFF - Conta Timeline

## Fluxo da movimentação da conta docliente.
Aqui você vai saber como usar a API para ter acesso a toda a movimentação da conta para exibição ao cliente..

As seguintes funcionalidade estão disponíveis:
-  IaaS - Timeline (GET /contas/{accountId})

## Contexto
**Objetivo:**

O objetivo desta API é exibição de toda movimentação da conta do cliente.


## Pre-requisitos
Para rodar o projeto é necessário:
- Java17
- Docker

Passos para executar "//contas/{accountId}" em ambiente local:
1) Execute o comando `docker-compose up -d` na pasta `docker-compose` que está na raiz do projeto.
    1) Irá subir um WireMock para simular as requisições feitas ao Backend da Porto
2) Inicie a aplicação
3) Realize as chamadas da próxima seção
    1) Importante: os mocks para o Backend da Porto levam em consideração um campo no Body das requisições. Para encontrar o mock, é necessário enviar o `backtrackId` ou o `authCod` com o valor `9f76e291-3a22-43f0-a7d5-8794904f2a9f` (valor que está nos mocks /par e /token)


## Dados de chamadas - Requests
A seguir tem exemplos das requisições e caso prefira, acesse o Swagger: `GET /swagger-ui/index.html`

**Endpoint:**
`GET - /portobank/v1/conta-timeline/contas/{accountId}`
- Headers
    - Authorization: Bearer {token}
    - x-account-id: Identificador da conta.
    - x-itau-auth:  Auth do itau.
- Path
  - accountId: identificador da conta (informado no processo de onboarding
- Query
  - perPage: quantidade de resultados por página (Default value : 10)
  - afterId: Identificador da página posterior à última requisição.Este parâmetro é recebido ao requisitar o avanço de paginação.
  - beginDate: Filtro opcional de data e hora iniciais
  - endDate: Filtro opcional de data e hora finais


- Responses
- Cenário de sucesso:
    - 200 Retorno de sucesso
```json
{
  "idIntegracao": "a81ff07a-a0c1-428e-9908-0e8199b228e1",
  "fornecedor": "account-pf",
  "status": "InProgress"
}
```
- Cenário de erro:
    - 500:
```json
{
  "errors": [
    {
      "code": "string",
      "message": "string"
    }
  ]
}
```
- Cenário de erro:
    - 407:
```json
{
  "errors": [
    {
      "code": "string",
      "message": "string"
    }
  ]
}
```

- Curl exemplo:
```json
curl --request POST \
  --url http://localhost:8080/portobank/v3/conta-digital/timeline/contas/1\
  --header 'Authorization: Bearer sadasdsad' \
  --header 'Content-Type: application/json' \
  --header 'x-account-id: 123' \
  --header 'x-product-id: 2' \
  --data '{}'
```
