package cz.jpalcut.pia.service;

import cz.jpalcut.pia.config.CaptchaSettings;
import cz.jpalcut.pia.utils.CaptchaResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestOperations;
import org.springframework.web.client.RestTemplate;

import java.net.URI;

/**
 * Služba pro kontrolu Google Captcha
 */
@Service
public class CaptchaService {

    @Autowired
    private CaptchaSettings captchaSettings;

    @Autowired
    private RestOperations restTemplate;

    /**
     * Pro ověření Google Captcha
     *
     * @param response        google.recaptcha.key.site
     * @param remoteIpAddress ip adresa uživatele
     * @return true - ověření v pořádku, false - ověření selhalo
     */
    public boolean processResponse(String response, String remoteIpAddress) {
        URI verifyUri = URI.create(String.format(
                "https://www.google.com/recaptcha/api/siteverify?secret=%s&response=%s&remoteip=%s",
                captchaSettings.getSecret(), response, remoteIpAddress));

        CaptchaResponse captchaResponse = restTemplate.getForObject(verifyUri, CaptchaResponse.class);

        return captchaResponse.isSuccess();
    }

    /**
     * RestTemplate pro Google Captcha
     *
     * @return vratí nový RestTemplate
     */
    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

}
