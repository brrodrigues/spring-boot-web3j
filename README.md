# Spring Boot Ethereum Blockchain - Java 

Este projeto e um exemplo de implementacao de web3j para a tecnologia Java.

Tecnologia envolvidas:

* Java 8
* Spring Boot 2.0 (http://start.spring.io/)
* Ganache (http://truffleframework.com/ganache/)
* Git
* Web3j (https://web3j.io/)
* Maven 

## Prerequisitos
É necessario possuir o git e maven instalado na maquina local. Os comando que utilizaremos abaixo, serao executado em um ambiente Linux.

## Baixando o projeto com Git
Digite no terminal git:

```
https://github.com/brrodrigues/spring-boot-ethereum-blockchain.git
cd spring-boot-ethereum-blockchain/
```

## Compilando/Executando o projeto com Maven
```
mvn spring-boot:run -DETHEREUM_RPC_SERVICE=http://127.0.0.1:7545
```
**Nota 1:** Para este teste inicial, utilizaremos o programa chamado ganache para criar um rede ethereum privada para fins de teste, pois nao e necessario mais nenhuma instalação. Caso o parametro ETHEREUM_RPC_SERVICE não for especificado, a URL a ser utilizado será http://localhost:8545/. Se estiver utilizando uma rede privado diferente da criada pela rede do ganache, é necessário substituir na variável ETHEREUM_RPC_SERVICE.

## Endpoint

### Accounts
Exemplo de listagem de contas existentes da rede.

GET http://localhost:8080/accounts
```
response
[
    {
        "address": "0x627306090abab3a6e1400e9345bc60c78a8bef57",
        "amount": "79.999999991463978806Ξ",
        "saldo": 79.999999991463978806
    },
   .....
    {
        "address": "0x5aeda56215b167893e80b4fe645ba6d5bab767de",
        "amount": "100Ξ",
        "saldo": 100
    }
]
```

### Send Transaction
Envio de valores, via api

POST http://localhost:8080/accounts
```
request
{
	"from":"0x627306090abab3a6e1400e9345bc60c78a8bef57",
	"to": "0xf17f52151ebef6c7334fad080c5704d77216b732",
	"amount": 10
}

response
{
  0xcd32944333782f56589bb45132bd92f6d40057b8bc36254a307e6a4a031b10c9
}
```

**Nota:** Os endpoints acima ilustram o exemplo das request e response existentes.
