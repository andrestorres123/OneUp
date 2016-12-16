package com.andrestorres.oneup.models.responses;

import com.andrestorres.oneup.models.utils.Commit;
import com.andrestorres.oneup.models.utils.Owner;

/**
 * Created by andrestorres on 12/15/16.
 */

public class CommitsResponse {
    private String sha;
    private Commit commit;

    public String getSha() {
        return sha;
    }

    public void setSha(String sha) {
        this.sha = sha;
    }

    public Commit getCommit() {
        return commit;
    }

    public void setCommit(Commit commit) {
        this.commit = commit;
    }
}
