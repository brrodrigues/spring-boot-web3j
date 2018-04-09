package rio.brunorodrigues.ethereum.blockchain.api.domain;

import java.math.BigDecimal;


public class Account {

    private String address;
    private BigDecimal balance;

    public Account() {
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public BigDecimal getSaldo() {
        return balance;
    }

    public void setSaldo(BigDecimal saldo) {
        this.balance = saldo;
    }


    public String getAmount() {
        return getSaldo().toString() + " ETH";
    }
}
