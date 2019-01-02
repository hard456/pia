package cz.jpalcut.pia.config;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "google.recaptcha.key")
public class CaptchaSettings {

    @Value("${google.recaptcha.key.site}")
    private String site;

    @Value("${google.recaptcha.key.secret}")
    private String secret;

    public String getSite() {
        return site;
    }

    public String getSecret() {
        return secret;
    }

}
