package org.printmyavatar.service.recaptcha;

public class RecaptchaResponse {

    private Boolean success;
    private String challenge_ts;
    private String hostname;


    public Boolean getSuccess() {
        return success;
    }

    public String getChallenge_ts() {
        return challenge_ts;
    }

    public String getHostname() {
        return hostname;
    }

    @Override
    public String toString() {
        return "RecaptchaResponse{" +
                "success=" + success +
                ", challenge_ts='" + challenge_ts + '\'' +
                ", hostname='" + hostname + '\'' +
                '}';
    }
}
