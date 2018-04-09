package rio.brunorodrigues.ethereum.blockchain.api.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.http.HttpService;

@Configuration
public class EthereumConfig {

    @Value("${app.http.service.web3j.url:}")
    private String HTTP_SERVICE_WEB3J;

    @Bean
    Web3j web3j() {
        if (HTTP_SERVICE_WEB3J == null || HTTP_SERVICE_WEB3J.isEmpty()){
            return Web3j.build(new HttpService());
        }

        return Web3j.build(new HttpService(HTTP_SERVICE_WEB3J));
    }

}
