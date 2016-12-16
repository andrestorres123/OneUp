package com.andrestorres.oneup.connection.services;


import com.andrestorres.oneup.BuildConfig;
import com.andrestorres.oneup.app.OneUpApplication;
import com.andrestorres.oneup.connection.helpers.ServiceHelper;
import com.andrestorres.oneup.models.responses.OAuthResponse;
import com.andrestorres.oneup.models.request.OAuthRequest;
import com.andrestorres.oneup.utils.Constants;

import rx.Observable;

/**
 * Created by andrestorres on 12/15/16.
 */

public class SessionServices {

    public static Observable<OAuthResponse> getAccessToken(){
        return ServiceHelper.INSTANCE.getSessionInterface().getAccessToken("application/json", new OAuthRequest(BuildConfig.CLIEND_ID, BuildConfig.CLIEND_SECRET,
                OneUpApplication.Settings.getPreferences().getString(Constants.KEYS.SECURITY.ACCESS_CODE)));
    }
}
