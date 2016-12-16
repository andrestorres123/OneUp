package com.andrestorres.oneup.connection.helpers.interfaces;

import com.andrestorres.oneup.models.utils.Repository;

import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.http.GET;
import retrofit2.http.Header;
import rx.Observable;

/**
 * Created by andrestorres on 12/15/16.
 */

public interface RepositoryServicesInterface {
    @GET("/user/repos")
    Observable<List<Repository>> getUserRepositories(@Header("Authorization") String authorization);
}
