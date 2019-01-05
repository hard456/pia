package cz.jpalcut.pia.config;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * Třída sloužící pro konfiguraci Google Captcha
 */
@Component
@ConfigurationProperties(prefix = "google.recaptcha.key")
public class CaptchaSettings {

    //google.recaptcha.key.site
    @Value("${google.recaptcha.key.site}")
    private String site;

    //google.recaptcha.key.secret
    @Value("${google.recaptcha.key.secret}")
    private String secret;

    /**
     * Vrátí google.recaptcha.key.site
     *
     * @return google.recaptcha.key.site
     */
    public String getSite() {
        return site;
    }

    /**
     * Vrátí google.recaptcha.key.secret
     *
     * @return google.recaptcha.key.secret
     */
    public String getSecret() {
        return secret;
    }

}
