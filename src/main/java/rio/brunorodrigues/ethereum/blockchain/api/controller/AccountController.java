package rio.brunorodrigues.ethereum.blockchain.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import rio.brunorodrigues.ethereum.blockchain.api.service.AccountService;
import rio.brunorodrigues.ethereum.blockchain.api.domain.Account;

import java.util.concurrent.ExecutionException;
import java.util.stream.Stream;

@RestController
@RequestMapping("/accounts")
public class AccountController {

    @Autowired
    private AccountService accountService;

    @GetMapping(produces = {MediaType.APPLICATION_STREAM_JSON_VALUE})
    public Stream<Account> getAccounts() {

        try {
            return accountService.getAccounts();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        return Stream.empty();

    }
}
