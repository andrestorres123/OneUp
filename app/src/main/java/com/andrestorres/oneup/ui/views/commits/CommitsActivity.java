package com.andrestorres.oneup.ui.views.commits;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.andrestorres.oneup.R;
import com.andrestorres.oneup.models.responses.CommitsResponse;
import com.andrestorres.oneup.models.utils.Commit;
import com.andrestorres.oneup.models.utils.Repository;
import com.andrestorres.oneup.ui.base.BaseActivity;
import com.andrestorres.oneup.ui.views.repositories.RepositoriesActivity;
import com.andrestorres.oneup.ui.views.repositories.RepositoriesAdapter;
import com.andrestorres.oneup.ui.views.utils.list_divider.SimpleDividerItemDecoration;

import java.util.List;

import butterknife.Bind;

public class CommitsActivity extends BaseActivity implements CommitsContract.View {

    @Bind(R.id.activity_commits_rv)
    RecyclerView mRecyclerView;

    CommitsContract.UserActionsListener mUserActionsListener;
    private CommitsAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_commits);
        completeView();

        mUserActionsListener = new CommitsPresenter(this);

        if(getIntent().getExtras().getParcelable(RepositoriesAdapter.EXTRA_REPOSITORY) != null){
            Repository repository = getIntent().getExtras().getParcelable(RepositoriesAdapter.EXTRA_REPOSITORY);
            setUpView(repository.getName(), true);
            mUserActionsListener.get(repository);
            setUpRepositoriesRecyclerView();
        }
    }

    void setUpRepositoriesRecyclerView() {
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mAdapter = new CommitsAdapter(this);
        mRecyclerView.setAdapter(mAdapter);
    }

    @Override
    public void onCommitsFetched(List<CommitsResponse> commitsReponses) {
        mAdapter.addAll(commitsReponses);
        checkIfDataIsEmpty(commitsReponses);
    }

    void checkIfDataIsEmpty(List<CommitsResponse> commitsResponses){
        if(!commitsResponses.isEmpty())
            mRecyclerView.setVisibility(View.VISIBLE);
    }
}
