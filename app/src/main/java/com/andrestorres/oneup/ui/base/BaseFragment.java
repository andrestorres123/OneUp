package com.andrestorres.oneup.ui.base;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.View;
import android.widget.Toast;

import com.andrestorres.oneup.utils.UIUtils;

import butterknife.ButterKnife;

/**
 * Created by andrestorres on 3/14/16.
 */
public class BaseFragment extends Fragment implements BaseViewContract {

    protected final String TAG = BaseFragment.class.getSimpleName();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    protected void completeView(View rootView){
        ButterKnife.bind(this, rootView);
    }

    @Override
    public void showLoader() {
        UIUtils.showProgressDialog(getActivity());
    }

    @Override
    public void hideLoader() {
        UIUtils.dismissProgressDialog();
    }

    @Override
    public void showMessage(String message) {
        Toast.makeText(getActivity(), message, Toast.LENGTH_SHORT).show();
    }
}
