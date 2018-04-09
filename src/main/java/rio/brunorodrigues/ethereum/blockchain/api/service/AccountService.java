package rio.brunorodrigues.ethereum.blockchain.api.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.DefaultBlockParameterName;
import org.web3j.protocol.core.Request;
import org.web3j.protocol.core.methods.request.Transaction;
import org.web3j.protocol.core.methods.response.*;
import org.web3j.utils.Convert;
import rio.brunorodrigues.ethereum.blockchain.api.domain.Account;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.concurrent.ExecutionException;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Component
public class AccountService {

    private final Logger LOGGER = LoggerFactory.getLogger(AccountService.class);

    @Autowired
    private Web3j web3j;


    public Stream<Account> getAccounts() throws InterruptedException, ExecutionException {
        return
        web3j.ethAccounts().
                sendAsync().
                handleAsync((ethAccounts, throwable) -> handleReturn(ethAccounts, throwable)).
                get();
    }

    private Stream<Account> handleReturn(EthAccounts ethAccounts, Throwable throwable) {

        if (throwable != null) {
            LOGGER.info("Nao foi possivel consultar as contas. Tente novamente, mais tarde.");
        }

        return
                ethAccounts.getAccounts().stream().map(this::loadAccountInfo).collect(Collectors.toList()).stream();

    }

    public String sendTransaction(String addressFrom, String addressTo, String amount) throws IOException {

        BigInteger value = Convert.toWei(amount, Convert.Unit.ETHER).toBigInteger();

        Request<?, EthGetTransactionCount> ethGetTransactionCountRequest = web3j.ethGetTransactionCount(addressFrom, DefaultBlockParameterName.LATEST);

        BigInteger nounce = ethGetTransactionCountRequest.send().getTransactionCount();

        EthGasPrice gasPrice = web3j.ethGasPrice().send();

        Transaction etherTransaction = Transaction.createEtherTransaction(addressFrom, nounce , gasPrice.getGasPrice(), BigInteger.valueOf(6721975), addressTo, value);

        Request<?, EthSendTransaction> ethSendTransactionRequest = web3j.ethSendTransaction(etherTransaction);

        String transactionHash = ethSendTransactionRequest.send().getTransactionHash();
        return transactionHash;

    }

   /* private EthAccounts getAccount(String address) throws IOException {
        EthGetBalance ethGetBalance = web3j.ethGetBalance(address, DefaultBlockParameterName.LATEST).send();

        Convert.

        ethGetBalance.getBalance();
    }*/

    private Account loadAccountInfo(String address) {
        Account account = new Account();
        try {
            Request<?, EthGetBalance> ether = web3j.ethGetBalance(address, DefaultBlockParameterName.LATEST);
            account.setAddress(address);
            EthGetBalance ethGetBalance = ether.sendAsync().get();

            BigDecimal amount = Convert.fromWei(ethGetBalance.getBalance().toString(), Convert.Unit.ETHER);

            account.setSaldo(amount);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        return account;
    }
}
