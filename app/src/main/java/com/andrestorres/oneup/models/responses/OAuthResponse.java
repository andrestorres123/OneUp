package com.andrestorres.oneup.models.responses;

import com.google.gson.annotations.SerializedName;

/**
 * Created by andrestorres on 12/15/16.
 */

public class OAuthResponse {
    @SerializedName("access_token")
    private String accessToken;
    private String scope;
    @SerializedName("token_type")
    private String tokenType;

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public String getScope() {
        return scope;
    }

    public void setScope(String scope) {
        this.scope = scope;
    }

    public String getTokenType() {
        return tokenType;
    }

    public void setTokenType(String tokenType) {
        this.tokenType = tokenType;
    }
}
