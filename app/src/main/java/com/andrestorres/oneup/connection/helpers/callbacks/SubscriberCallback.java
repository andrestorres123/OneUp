package com.andrestorres.oneup.connection.helpers.callbacks;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import java.io.IOException;

import retrofit2.adapter.rxjava.HttpException;
import rx.Subscriber;

/**
 * Created by andrestorres on 12/15/16.
 */

public class SubscriberCallback<T> extends Subscriber<T> {

    private static final String TAG = SubscriberCallback.class.getSimpleName();

    private T response;
    private Context context;

    public SubscriberCallback(Context context) {
        this.context = context;
    }

    @Override
    public void onCompleted() {
    }

    @Override
    public void onError(Throwable e) {
        if(e != null && e.getCause() != null){
            Log.e(TAG, "onFailure: " + e.getCause().getMessage(), e);
        }

        if (e instanceof HttpException) {
            // We had non-2XX http error
            Log.d(TAG,((HttpException) e).code() + " "  + ((HttpException) e).message() );
        }
        if (e instanceof IOException) {
            // A network or conversion error happened
            Toast.makeText(context, "Network connection problem", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onNext(T response) {
        setResponse(response);
    }

    public T getResponse() {
        return response;
    }

    public void setResponse(T response) {
        this.response = response;
    }
}
