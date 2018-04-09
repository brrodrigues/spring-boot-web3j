package rio.brunorodrigues.ethereum.blockchain.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import rio.brunorodrigues.ethereum.blockchain.api.service.AccountService;
import rio.brunorodrigues.ethereum.blockchain.api.controller.request.TransactionRequest;

import java.io.IOException;

@RestController
@RequestMapping("/transactions")
public class TransactionController {

    @Autowired
    private AccountService accountService;

    @PostMapping
    public String sendTransaction(@RequestBody TransactionRequest requestBody) {
        try {
            return accountService.sendTransaction(requestBody.getAddressFrom(), requestBody.getAddressTo(), requestBody.getAmount());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "NonTx";
    }

}
