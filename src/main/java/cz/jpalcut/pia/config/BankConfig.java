package cz.jpalcut.pia.config;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BankConfig {

    @Value("appvar.bank_code")
    String bankCode;

    public String getBankCode() {
        return bankCode;
    }

}
