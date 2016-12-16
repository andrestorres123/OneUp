package com.andrestorres.oneup.ui.views.repositories;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.andrestorres.oneup.R;
import com.andrestorres.oneup.models.utils.Repository;
import com.andrestorres.oneup.ui.base.BaseActivity;
import com.andrestorres.oneup.ui.views.utils.list_divider.SimpleDividerItemDecoration;

import java.util.List;

import butterknife.Bind;


public class RepositoriesActivity extends BaseActivity implements RepositoriesContract.View {

    @Bind(R.id.activity_main_repositories_rv)
    RecyclerView mRecyclerView;
    private List<Repository> mMockData;

    RepositoriesContract.UserActionsListener mUserActionsListener;
    private RepositoriesAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        completeView();

        setUpView("OneUp", false);

        mUserActionsListener = new RepositoriesPresenter(this);
        mUserActionsListener.get();

        setUpRepositoriesRecyclerView();
    }

    void setUpRepositoriesRecyclerView() {
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.addItemDecoration(new SimpleDividerItemDecoration(this));
        mAdapter = new RepositoriesAdapter(this);
        mRecyclerView.setAdapter(mAdapter);
    }

    @Override
    public void onRepositoriesFetched(List<Repository> repositories) {
        mAdapter.addAll(repositories);
    }
}
