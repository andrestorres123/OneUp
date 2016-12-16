package com.andrestorres.oneup.ui.views.repositories;

import com.andrestorres.oneup.app.OneUpApplication;
import com.andrestorres.oneup.connection.helpers.callbacks.SubscriberCallback;
import com.andrestorres.oneup.connection.services.RepositoriesServices;
import com.andrestorres.oneup.connection.services.SessionServices;
import com.andrestorres.oneup.models.responses.OAuthResponse;
import com.andrestorres.oneup.models.utils.Repository;
import com.andrestorres.oneup.ui.base.BasePresenter;
import com.andrestorres.oneup.utils.Constants;

import java.util.List;

/**
 * Created by andrestorres on 12/15/16.
 */

public class RepositoriesPresenter extends BasePresenter<RepositoriesContract.View> implements RepositoriesContract.UserActionsListener {

    protected RepositoriesPresenter(RepositoriesContract.View view) {
        super(view);
    }

    @Override
    public void get() {
        SessionServices.getAccessToken()
                .compose(this.<OAuthResponse>safelyLoading())
                .subscribe(new SubscriberCallback<OAuthResponse>(view.getActivity()){
                    @Override
                    public void onNext(OAuthResponse response) {
                        super.onNext(response);
                        OneUpApplication.Settings.getPreferences().putString(Constants.KEYS.SECURITY.ACCESS_TOKEN, response.getAccessToken());
                        getAllRepositories();
                    }

                    @Override
                    public void onError(Throwable e) {
                        super.onError(e);
                    }
                });
    }

    public void getAllRepositories() {
        RepositoriesServices.getUserRepositories()
                .compose(this.<List<Repository>>safelyLoading())
                .subscribe(new SubscriberCallback<List<Repository>>(view.getActivity()){
                    @Override
                    public void onNext(List<Repository> repositories) {
                        super.onNext(repositories);
                        view.onRepositoriesFetched(repositories);
                    }

                    @Override
                    public void onError(Throwable e) {
                        super.onError(e);
                    }
                });
    }
}
