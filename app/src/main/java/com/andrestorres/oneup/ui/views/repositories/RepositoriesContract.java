package com.andrestorres.oneup.ui.views.repositories;

import com.andrestorres.oneup.models.utils.Repository;
import com.andrestorres.oneup.ui.base.BaseViewContract;

import java.util.List;

/**
 * Created by andrestorres on 12/15/16.
 */

public interface RepositoriesContract {

    interface View extends BaseViewContract{
        void onRepositoriesFetched(List<Repository> repositories);
    }

    interface UserActionsListener{
        void get();
    }
}
