package com.andrestorres.oneup.ui.views.oauth;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.andrestorres.oneup.BuildConfig;
import com.andrestorres.oneup.R;
import com.andrestorres.oneup.app.Navigator;
import com.andrestorres.oneup.app.OneUpApplication;
import com.andrestorres.oneup.ui.base.BaseActivity;
import com.andrestorres.oneup.ui.views.repositories.RepositoriesActivity;
import com.andrestorres.oneup.utils.Constants;

import butterknife.Bind;

/**
 * Created by andrestorres on 12/15/16.
 */

public class OAuthActivity extends BaseActivity {

    public static String OAUTH_URL = "https://github.com/login/oauth/authorize";
    public static String OAUTH_ACCESS_TOKEN_URL = "https://github.com/login/oauth/access_token";

    @Bind(R.id.activity_oauth_webview)
    WebView mOAuthWebView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_oauth);
        completeView();

        String url = OAUTH_URL + "?client_id=" + BuildConfig.CLIEND_ID + "&scope=repo";

        mOAuthWebView.getSettings().setJavaScriptEnabled(true);
        mOAuthWebView.setWebViewClient(new WebViewClient() {
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                String accessTokenFragment = "access_token=";
                String accessCodeFragment = "code=";

                // We hijack the GET request to extract the OAuth parameters

                if (url.contains(accessTokenFragment)) {
                    // the GET request contains directly the token
                    String accessToken = url.substring(url.indexOf(accessTokenFragment));
                    //  TokenStorer.setAccessToken(accessToken);


                } else if (url.contains(accessCodeFragment)) {
                    // the GET request contains an authorization code
                    String accessCode = url.substring(url.indexOf(accessCodeFragment) + accessCodeFragment.length());
                    OneUpApplication.Settings.getPreferences().putString(Constants.KEYS.SECURITY.ACCESS_CODE, "" + accessCode);
                    Navigator.startActivity(OAuthActivity.this, RepositoriesActivity.class);
//                    String query = "client_id=" + BuildConfig.CLIEND_ID + "&client_secret=" + BuildConfig.CLIEND_SECRET + "&code=" + accessCode;
//                    view.postUrl(OAUTH_ACCESS_TOKEN_URL, query.getBytes());
                }
            }
        });

        mOAuthWebView.loadUrl(url);
    }
}
