package cz.jpalcut.pia.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

/**
 * Třída spravující konfigurace týkající se banky z application.properties
 */
@Configuration
public class BankConfig {

    //kód banky
    @Value("${appvar.bank_code}")
    private String bankCode;

    /**
     * Vrací kód banky
     *
     * @return kód banky
     */
    public String getBankCode() {
        return bankCode;
    }

}
