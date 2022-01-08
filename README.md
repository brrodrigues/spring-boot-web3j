# Connecting to Ethereum Blockchain on Spring boot - Java 

This project is a web3j implementation example written by Java

Tecnologies:

* Java 8
* Spring Boot 2.0 (http://start.spring.io/)
* Ganache as private ethereum network (http://truffleframework.com/ganache/)
* Git
* Web3j - API Dapps ethereum java (https://web3j.io/)
* Maven

## Requirements:
Must have maven and git installed on your PC. Do perform the commands above preferrable on linux or mac environment.

## Getting started
Type on terminal:

```
git clone - https://github.com/brrodrigues/spring-boot-ethereum-blockchain.git
cd spring-boot-ethereum-blockchain/
```

## Running
```
mvn spring-boot:run -DETHEREUM_RPC_SERVICE=http://127.0.0.1:7545
```
**Note:** For this initiative, we will use the Ganache App (https://trufflesuite.com/ganache/) to create our local ethereum network. 
**Note 1: Whether the ETHEREUM_RPC_SERVICE parameter is not settled, the application will use http://localhost:8545/ as default URL to connect the ethereum network. If you use another private network create from Ganache, it is necessary to set ETHEREUM_RPC_SERVICE variable.

## Endpoints

### Accounts
Example of account list.

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
Create a transaction to network

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
