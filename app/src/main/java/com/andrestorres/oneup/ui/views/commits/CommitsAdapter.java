package com.andrestorres.oneup.ui.views.commits;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.andrestorres.oneup.R;
import com.andrestorres.oneup.models.responses.CommitsResponse;
import com.andrestorres.oneup.models.utils.Commit;
import com.andrestorres.oneup.models.utils.Repository;
import com.andrestorres.oneup.ui.views.repositories.RepositoriesAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by andrestorres on 12/15/16.
 */

public class CommitsAdapter extends RecyclerView.Adapter<CommitsAdapter.ViewHolder> {

    private Context mContext;
    List<CommitsResponse> mCommitReponses = new ArrayList<>();

    public CommitsAdapter(Context context) {
        mContext = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(mContext).inflate(R.layout.item_commit, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        CommitsResponse commitReponse = mCommitReponses.get(position);

        holder.title.setText(commitReponse.getCommit().getMessage());
        holder.author.setText(commitReponse.getCommit().getAuthor().getName());
    }

    @Override
    public int getItemCount() {
        return mCommitReponses.size();
    }

    void addCommit(CommitsResponse commitsResponse) {
        mCommitReponses.add(commitsResponse);
        notifyDataSetChanged();
    }

    void addAll(List<CommitsResponse> commitReponse) {
        mCommitReponses.clear();
        mCommitReponses.addAll(commitReponse);
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        @Bind(R.id.item_commit_title)
        TextView title;
        @Bind(R.id.item_commit_author)
        TextView author;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
