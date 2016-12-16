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

                if (url.contains(accessTokenFragment)) {

                } else if (url.contains(accessCodeFragment)) {
                    String accessCode = url.substring(url.indexOf(accessCodeFragment) + accessCodeFragment.length());
                    OneUpApplication.Settings.getPreferences().putString(Constants.KEYS.SECURITY.ACCESS_CODE, "" + accessCode);
                    Navigator.startActivity(OAuthActivity.this, RepositoriesActivity.class);
                }
            }
        });

        mOAuthWebView.loadUrl(url);
    }
}
