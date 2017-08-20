package org.printmyavatar.service.recaptcha;

public interface RecaptchaService {

    boolean verify(String gRecaptchaResponse);

}
