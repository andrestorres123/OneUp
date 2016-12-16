package com.andrestorres.oneup.connection.helpers.interfaces;

import com.andrestorres.oneup.models.responses.OAuthResponse;
import com.andrestorres.oneup.models.request.OAuthRequest;

import retrofit2.http.Body;
import retrofit2.http.Header;
import retrofit2.http.POST;
import rx.Observable;

/**
 * Created by andrestorres on 12/15/16.
 */

public interface SessionServicesInterface {
    @POST("/login/oauth/access_token")
    Observable<OAuthResponse> getAccessToken(@Header("Accept") String responseType, @Body OAuthRequest oAuthRequest);

}

