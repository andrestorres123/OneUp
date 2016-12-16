package com.andrestorres.oneup.connection.helpers.interfaces;


import com.andrestorres.oneup.models.responses.CommitsResponse;
import com.andrestorres.oneup.models.utils.Commit;

import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.http.GET;
import retrofit2.http.Path;
import rx.Observable;

/**
 * Created by andrestorres on 12/15/16.
 */

public interface CommitsServicesInterface {

    @GET("/repos/{owner}/{repo}/commits")
    Observable<List<CommitsResponse>> getCommits(@Path("owner") String owner, @Path("repo") String repo);
}
