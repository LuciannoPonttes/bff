# BFF - Conta Digital Gerenciar

## Descrição:
Aplicação responsável por disponibilizar serviços de consulda de dados da conta PortoBank no IAAS (Itaú as a Service)

### Rotas

#### GET - v1/conta-digital/gerenciar/dados-conta

##### Descrição
Retorna os dados da conta digital Porto no IAAS.

##### Response Status
- 200
```json 
  {
  "dados": {
    "id": "9986a51e-bb13-442b-b9a0-9d0d5b63f9ef",
    "contaBancaria": {
      "nomeBanco": "Porto Bank - Banco Itaucard S/A",
      "codigoBanco": "341",
      "agencia": "4287",
      "numeroConta": "000149656",
      "digitoConta": "7"
    }
  }
}
```
- 4.X.X, 5.X.X

```json
  {
    "erros": [
      {
        "campo": "string",
        "mensagens": [
          "string"
        ]
      }
    ]
  }
  ```

#### Exemplo de Curl

````shell
curl --request GET \
  --url http://localhost:8080/portobank/v3/conta-digital/gerenciar/dados-conta \
  --header 'Authorization: Bearer eyJraWQiOiJjY3RcL2I1K0twZ2V4Qk5sNGFEcUxFU0JhbVRSRkJ3TWJhcng3dlpVMFwvVU09IiwiYWxnIjoiUlMyNTYifQ.eyJzdWIiOiI5MTMxNDI1ZC0yYTQ3LTQwZTQtYWVhMy05NzExNDU1YWY4YTQiLCJldmVudF9pZCI6IjY1NTQyY2UzLTI4NGUtNDQ2Zi04ODM1LTAxM2FiYjE5NDEyZSIsInRva2VuX3VzZSI6ImFjY2VzcyIsInNjb3BlIjoiYXdzLmNvZ25pdG8uc2lnbmluLnVzZXIuYWRtaW4iLCJhdXRoX3RpbWUiOjE3MDMyNDY2NjMsImlzcyI6Imh0dHBzOlwvXC9jb2duaXRvLWlkcC51cy1lYXN0LTEuYW1hem9uYXdzLmNvbVwvdXMtZWFzdC0xX1RacldWUzhUWiIsImV4cCI6MTcwMzI1MDI2MiwiaWF0IjoxNzAzMjQ2NjYzLCJqdGkiOiI1YzAwZjAxNS1kMTY5LTQwM2YtYWM4My1kYWJkYzc4YmYyNDkiLCJjbGllbnRfaWQiOiIzMDhzMWdhbmZpMWwzYW5kYzkwdnIxMWE3cyIsInVzZXJuYW1lIjoiODU5NDc5NDYxNDkifQ.foS_UQ3DKHwQY18TSDkg0wP0HA822RdYfbsYISaW0S8J8UHpmDdsEBJTyo5y92jIflb8Xrq7QS3jjg5oPxzegY6bWKgwRf5nLMiQMg1ivp8e26Dm2aPLqXWi3NW9eNWrOtsiEpAFYZpQQ4Wk6s5UInapDI3xPDDyy1FbyTyw8InvREb8aa6jIOLyFCQG3dBLE38oS80IPI6z7Uescg-bsc6jXNOBRnbxm8PvJ7gC9ra_PSNW2mRIQRcR9ZLoYOoVkV6JwSh-TqjwIu5u_qjPnZ1wXALtE0WGNK5WIzg1FB5DhwjWjffwmEY_1KDa9pr0iFLZbLTnvW1usK-h94HyDw' \
  --header 'Content-Type: application/json' \
  --header 'User-Agent: Insomnia/2023.5.6' \
  --header 'x-account-id: 11d2a352-4eeb-47df-a49d-e739a63e1be7' \
  --header 'x-itau-auth: ZTM1MGMwODgtMmM0NS00MWYzLTk1NmUtNDk0Yjk1YjNjYTFhOm=='
````

#### GET - v1/conta-digital/gerenciar/sumario

##### Descrição
Retorna um resumo da conta digital Porto no IAAS com informações da conta saldo e quantidade de chaves.

##### Response Status
- 200
```json 
  {
  "dados": {
    "nomeBanco": "Porto Bank - Banco Itaucard S/A",
    "codigoBanco": "341",
    "agencia": "4287",
    "numeroConta": "000150464",
    "digitoConta": "2",
    "saldo": "R$ 8.857,53",
    "statusConta": null,
    "documento": "13767300818",
    "possuiCartao": false,
    "quantidadeChavePix": "5 Chaves"
  }
}
```
- 4.X.X, 5.X.X

```json
  {
    "erros": [
      {
        "campo": "string",
        "mensagens": [
          "string"
        ]
      }
    ]
  }
  ```

#### Exemplo de Curl

````shell
curl --request GET \
  --url http://localhost:8080/portobank/v3/conta-digital/gerenciar/sumario \
  --header 'Authorization: Bearer eyJraWQiOiJjY3RcL2I1K0twZ2V4Qk5sNGFEcUxFU0JhbVRSRkJ3TWJhcng3dlpVMFwvVU09IiwiYWxnIjoiUlMyNTYifQ.eyJzdWIiOiI5MTMxNDI1ZC0yYTQ3LTQwZTQtYWVhMy05NzExNDU1YWY4YTQiLCJldmVudF9pZCI6IjY1NTQyY2UzLTI4NGUtNDQ2Zi04ODM1LTAxM2FiYjE5NDEyZSIsInRva2VuX3VzZSI6ImFjY2VzcyIsInNjb3BlIjoiYXdzLmNvZ25pdG8uc2lnbmluLnVzZXIuYWRtaW4iLCJhdXRoX3RpbWUiOjE3MDMyNDY2NjMsImlzcyI6Imh0dHBzOlwvXC9jb2duaXRvLWlkcC51cy1lYXN0LTEuYW1hem9uYXdzLmNvbVwvdXMtZWFzdC0xX1RacldWUzhUWiIsImV4cCI6MTcwMzI1MDI2MiwiaWF0IjoxNzAzMjQ2NjYzLCJqdGkiOiI1YzAwZjAxNS1kMTY5LTQwM2YtYWM4My1kYWJkYzc4YmYyNDkiLCJjbGllbnRfaWQiOiIzMDhzMWdhbmZpMWwzYW5kYzkwdnIxMWE3cyIsInVzZXJuYW1lIjoiODU5NDc5NDYxNDkifQ.foS_UQ3DKHwQY18TSDkg0wP0HA822RdYfbsYISaW0S8J8UHpmDdsEBJTyo5y92jIflb8Xrq7QS3jjg5oPxzegY6bWKgwRf5nLMiQMg1ivp8e26Dm2aPLqXWi3NW9eNWrOtsiEpAFYZpQQ4Wk6s5UInapDI3xPDDyy1FbyTyw8InvREb8aa6jIOLyFCQG3dBLE38oS80IPI6z7Uescg-bsc6jXNOBRnbxm8PvJ7gC9ra_PSNW2mRIQRcR9ZLoYOoVkV6JwSh-TqjwIu5u_qjPnZ1wXALtE0WGNK5WIzg1FB5DhwjWjffwmEY_1KDa9pr0iFLZbLTnvW1usK-h94HyDw' \
  --header 'Content-Type: application/json' \
  --header 'User-Agent: Insomnia/2023.5.6' \
  --header 'x-account-id: 11d2a352-4eeb-47df-a49d-e739a63e1be7' \
  --header 'x-itau-auth: ZTM1MGMwODgtMmM0NS00MWYzLTk1NmUtNDk0Yjk1YjNjYTFhOm=='
````

#### GET - v1/conta-digital/gerenciar/saldo

##### Descrição
Retorna informações de saldo da conta digital Porto no IAAS.

##### Response Status
- 200
```json 
{
  "dados": {
    "contaId": "Porto Bank - Banco Itaucard S/A",
    "disponivel": "341",
    "reservada": "4287",
    "bloqueado": "000150464"
  }
}
```
- 4.X.X, 5.X.X

```json
  {
    "erros": [
      {
        "campo": "string",
        "mensagens": [
          "string"
        ]
      }
    ]
  }
  ```

#### Exemplo de Curl

````shell
curl --request GET \
  --url http://localhost:8080/portobank/v3/conta-digital/gerenciar/saldo \
  --header 'Authorization: Bearer eyJraWQiOiJjY3RcL2I1K0twZ2V4Qk5sNGFEcUxFU0JhbVRSRkJ3TWJhcng3dlpVMFwvVU09IiwiYWxnIjoiUlMyNTYifQ.eyJzdWIiOiI5MTMxNDI1ZC0yYTQ3LTQwZTQtYWVhMy05NzExNDU1YWY4YTQiLCJldmVudF9pZCI6IjY1NTQyY2UzLTI4NGUtNDQ2Zi04ODM1LTAxM2FiYjE5NDEyZSIsInRva2VuX3VzZSI6ImFjY2VzcyIsInNjb3BlIjoiYXdzLmNvZ25pdG8uc2lnbmluLnVzZXIuYWRtaW4iLCJhdXRoX3RpbWUiOjE3MDMyNDY2NjMsImlzcyI6Imh0dHBzOlwvXC9jb2duaXRvLWlkcC51cy1lYXN0LTEuYW1hem9uYXdzLmNvbVwvdXMtZWFzdC0xX1RacldWUzhUWiIsImV4cCI6MTcwMzI1MDI2MiwiaWF0IjoxNzAzMjQ2NjYzLCJqdGkiOiI1YzAwZjAxNS1kMTY5LTQwM2YtYWM4My1kYWJkYzc4YmYyNDkiLCJjbGllbnRfaWQiOiIzMDhzMWdhbmZpMWwzYW5kYzkwdnIxMWE3cyIsInVzZXJuYW1lIjoiODU5NDc5NDYxNDkifQ.foS_UQ3DKHwQY18TSDkg0wP0HA822RdYfbsYISaW0S8J8UHpmDdsEBJTyo5y92jIflb8Xrq7QS3jjg5oPxzegY6bWKgwRf5nLMiQMg1ivp8e26Dm2aPLqXWi3NW9eNWrOtsiEpAFYZpQQ4Wk6s5UInapDI3xPDDyy1FbyTyw8InvREb8aa6jIOLyFCQG3dBLE38oS80IPI6z7Uescg-bsc6jXNOBRnbxm8PvJ7gC9ra_PSNW2mRIQRcR9ZLoYOoVkV6JwSh-TqjwIu5u_qjPnZ1wXALtE0WGNK5WIzg1FB5DhwjWjffwmEY_1KDa9pr0iFLZbLTnvW1usK-h94HyDw' \
  --header 'Content-Type: application/json' \
  --header 'User-Agent: Insomnia/2023.5.6' \
  --header 'x-account-id: 11d2a352-4eeb-47df-a49d-e739a63e1be7' \
  --header 'x-itau-auth: ZTM1MGMwODgtMmM0NS00MWYzLTk1NmUtNDk0Yjk1YjNjYTFhOm=='
````

### Links importantes

- [Ficha Greg](https://greg/publisher/assets/kubernetes/details/f3dcd603-6143-4b7f-95fa-317d950a9f43)
- [Esteira no Jenkins](https://jenkinsciportoprd/job/ExperienciaCliente/job/BFF/job/bff-conta-digital-gerenciar/)


