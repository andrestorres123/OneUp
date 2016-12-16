package com.andrestorres.oneup.models.request;

import com.google.gson.annotations.SerializedName;

/**
 * Created by andrestorres on 12/15/16.
 */

public class OAuthRequest {
    @SerializedName("client_id")
    private String clientId;
    @SerializedName("client_secret")
    private String clientSecret;
    private String code;

    public OAuthRequest(String clientId, String clientSecret, String code) {
        this.clientId = clientId;
        this.clientSecret = clientSecret;
        this.code = code;
    }

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public String getClientSecret() {
        return clientSecret;
    }

    public void setClientSecret(String clientSecret) {
        this.clientSecret = clientSecret;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
