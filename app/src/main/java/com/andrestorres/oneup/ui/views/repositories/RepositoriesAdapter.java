package com.andrestorres.oneup.ui.views.repositories;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.andrestorres.oneup.R;
import com.andrestorres.oneup.app.Navigator;
import com.andrestorres.oneup.models.utils.Repository;
import com.andrestorres.oneup.ui.views.commits.CommitsActivity;
import com.andrestorres.oneup.utils.DateUtils;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by andrestorres on 12/15/16.
 */

public class RepositoriesAdapter extends RecyclerView.Adapter<RepositoriesAdapter.ViewHolder> {

    public static String EXTRA_REPOSITORY = "extra_repository";

    private Context mContext;
    private List<Repository> mRepositories = new ArrayList<>();

    public RepositoriesAdapter(Context context) {
        mContext = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(mContext).inflate(R.layout.item_repository, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final Repository repository = mRepositories.get(position);

        holder.title.setText(repository.getFullName());
        holder.url.setText(repository.getUrl());
        holder.created.setText(DateUtils.getDateString(DateUtils.parseDate(repository.getCreatedAt(), "yyyy-MM-dd'T'HH:mm:ss'Z'")));

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle bundle = new Bundle();
                bundle.putParcelable(EXTRA_REPOSITORY, repository);
                Navigator.startActivity(mContext, CommitsActivity.class, bundle);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mRepositories.size();
    }

    void addRepository(Repository repository){
        mRepositories.add(repository);
        notifyDataSetChanged();
    }

    void addAll(List<Repository> repositories){
        mRepositories.clear();
        mRepositories.addAll(repositories);
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        @Bind(R.id.item_repository_title)
        TextView title;

        @Bind(R.id.item_repository_url)
        TextView url;

        @Bind(R.id.item_repository_created)
        TextView created;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
