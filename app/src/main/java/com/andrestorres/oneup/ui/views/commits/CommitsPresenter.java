package com.andrestorres.oneup.ui.views.commits;

import com.andrestorres.oneup.connection.helpers.callbacks.SubscriberCallback;
import com.andrestorres.oneup.connection.services.CommitsServices;
import com.andrestorres.oneup.models.responses.CommitsResponse;
import com.andrestorres.oneup.models.utils.Commit;
import com.andrestorres.oneup.models.utils.Repository;
import com.andrestorres.oneup.ui.base.BasePresenter;

import java.util.List;

import okhttp3.ResponseBody;

/**
 * Created by andrestorres on 12/15/16.
 */

public class CommitsPresenter extends BasePresenter<CommitsContract.View> implements CommitsContract.UserActionsListener {

    protected CommitsPresenter(CommitsContract.View view) {
        super(view);
    }

    @Override
    public void get(Repository repository) {

        String owner = repository.getOwner().getLogin();
        String repo = repository.getName();

        CommitsServices.getCommits(owner, repo)
                .compose(this.<List<CommitsResponse>>safelyLoading())
                .subscribe(new SubscriberCallback<List<CommitsResponse>>(view.getActivity()){
                    @Override
                    public void onNext(List<CommitsResponse> response) {
                        super.onNext(response);
                        view.onCommitsFetched(response);
                    }

                    @Override
                    public void onError(Throwable e) {
                        super.onError(e);
                    }
                });
    }
}
