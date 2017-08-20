package org.printmyavatar.service.recaptcha;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

@Service
public class RecaptchaServiceImpl implements RecaptchaService {

    @Value("${google.recaptcha.url}")
    private String url;

    @Value("${google.recaptcha.secret}")
    private String secret;

    @Override
    public boolean verify(String gRecaptchaResponse) {
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
        map.add("secret", secret);
        map.add("response", gRecaptchaResponse);

        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(map, headers);

        ResponseEntity<RecaptchaResponse> responseEntity = restTemplate.postForEntity(url, request, RecaptchaResponse.class);
        RecaptchaResponse response = responseEntity.getBody();
        return response.getSuccess();
    }
}
