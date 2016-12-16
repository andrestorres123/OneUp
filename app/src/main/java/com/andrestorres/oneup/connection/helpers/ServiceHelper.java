package com.andrestorres.oneup.connection.helpers;

import android.content.Context;

import com.andrestorres.oneup.BuildConfig;
import com.andrestorres.oneup.connection.helpers.interfaces.CommitsServicesInterface;
import com.andrestorres.oneup.connection.helpers.interfaces.RepositoryServicesInterface;
import com.andrestorres.oneup.connection.helpers.interfaces.SessionServicesInterface;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;
import java.net.CookieHandler;
import java.net.CookieManager;
import java.net.CookiePolicy;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by andrestorres on 12/15/16.
 */

public class ServiceHelper {

    public static final String TAG = ServiceHelper.class.getSimpleName();

    public static final ServiceHelper INSTANCE = new ServiceHelper(null);

    protected Retrofit mRetrofit;
    protected Retrofit mRetrofitApi;
    protected CookieManager cookieManager;

    protected Context mContext;

    private SessionServicesInterface mSessionInterface;
    private RepositoryServicesInterface mRepositoryServicesInterface;
    private CommitsServicesInterface mCommitsServicesInterface;

    private Map<String, String> headers = new HashMap<String, String>() {
        {
            put("Content-Type", "application/json");
            put("Cache-Control", String.format("max-age=%d", 50000));
        }
    };

    public ServiceHelper() {
    }

    public ServiceHelper(Context context) {
        if (mContext == null) {
            mContext = context;
        }

        if (cookieManager == null) {
            cookieManager = new CookieManager();
            cookieManager.setCookiePolicy(CookiePolicy.ACCEPT_ALL);
            CookieHandler.setDefault(cookieManager);
        }


        if (mRetrofit == null) {
            GsonBuilder gsonBuilder = new GsonBuilder()
                    .setDateFormat("yyyy-MM-dd")
                    .setLenient();
            Gson gson = gsonBuilder.create();

            OkHttpClient.Builder client = new OkHttpClient.Builder();
            // set your desired log level
            HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
            interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
            client.interceptors().add(interceptor);

            OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
            httpClient.networkInterceptors().add(new Interceptor() {
                @Override
                public Response intercept(Chain chain) throws IOException {
                    Request.Builder request = chain.request().newBuilder();
                    for (Map.Entry<String, String> entry : headers.entrySet()) {
                        request.addHeader(entry.getKey(), entry.getValue());
                    }
                    return chain.proceed(request.build());
                }
            });


            final OkHttpClient.Builder okHttpClient = new OkHttpClient.Builder()
                    .readTimeout(120, TimeUnit.SECONDS)
                    .connectTimeout(120, TimeUnit.SECONDS);

            mRetrofit = new Retrofit.Builder()
                    .baseUrl(BuildConfig.BASEURL)
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                    .client(httpClient.build())
                    .client(okHttpClient.build())
                    .client(client.build())
                    .build();

            if (mSessionInterface == null) {
                mSessionInterface = mRetrofit.create(SessionServicesInterface.class);
            }
        }

        if (mRetrofitApi == null) {
            GsonBuilder gsonBuilder = new GsonBuilder()
                    .setDateFormat("yyyy-MM-dd")
                    .setLenient();
            Gson gson = gsonBuilder.create();

            OkHttpClient.Builder client = new OkHttpClient.Builder();
            // set your desired log level
            HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
            interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
            client.interceptors().add(interceptor);

            OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
            httpClient.networkInterceptors().add(new Interceptor() {
                @Override
                public Response intercept(Chain chain) throws IOException {
                    Request.Builder request = chain.request().newBuilder();
                    for (Map.Entry<String, String> entry : headers.entrySet()) {
                        request.addHeader(entry.getKey(), entry.getValue());
                    }
                    return chain.proceed(request.build());
                }
            });


            final OkHttpClient.Builder okHttpClient = new OkHttpClient.Builder()
                    .readTimeout(120, TimeUnit.SECONDS)
                    .connectTimeout(120, TimeUnit.SECONDS);

            mRetrofitApi = new Retrofit.Builder()
                    .baseUrl(BuildConfig.BASEAPIURL)
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                    .client(httpClient.build())
                    .client(okHttpClient.build())
                    .client(client.build())
                    .build();

            if (mRepositoryServicesInterface == null) {
                mRepositoryServicesInterface = mRetrofitApi.create(RepositoryServicesInterface.class);
            }

            if (mCommitsServicesInterface == null) {
                mCommitsServicesInterface = mRetrofitApi.create(CommitsServicesInterface.class);
            }
        }
    }

    public SessionServicesInterface getSessionInterface() {
        return mSessionInterface;
    }

    public RepositoryServicesInterface getRepositoryServicesInterface() {
        return mRepositoryServicesInterface;
    }

    public CommitsServicesInterface getCommitsServicesInterface() {
        return mCommitsServicesInterface;
    }
}
