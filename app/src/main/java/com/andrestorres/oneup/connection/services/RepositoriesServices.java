package com.andrestorres.oneup.connection.services;

import com.andrestorres.oneup.app.OneUpApplication;
import com.andrestorres.oneup.connection.helpers.ServiceHelper;
import com.andrestorres.oneup.models.utils.Repository;
import com.andrestorres.oneup.utils.Constants;

import java.util.List;

import okhttp3.ResponseBody;
import rx.Observable;

/**
 * Created by andrestorres on 12/15/16.
 */

public class RepositoriesServices {
    public static Observable<List<Repository>> getUserRepositories(){
        return ServiceHelper.INSTANCE.getRepositoryServicesInterface()
                .getUserRepositories("token " + OneUpApplication.Settings.getPreferences().getString(Constants.KEYS.SECURITY.ACCESS_TOKEN));
    }
}
