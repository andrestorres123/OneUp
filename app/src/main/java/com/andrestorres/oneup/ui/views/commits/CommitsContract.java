package com.andrestorres.oneup.ui.views.commits;

import com.andrestorres.oneup.models.responses.CommitsResponse;
import com.andrestorres.oneup.models.utils.Commit;
import com.andrestorres.oneup.models.utils.Repository;
import com.andrestorres.oneup.ui.base.BaseViewContract;

import java.util.List;

/**
 * Created by andrestorres on 12/15/16.
 */

public interface CommitsContract {

    interface View extends BaseViewContract{
        void onCommitsFetched(List<CommitsResponse> commitReponses);
    }

    interface UserActionsListener{
        void get(Repository repository);
    }
}
