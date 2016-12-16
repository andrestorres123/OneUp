package com.andrestorres.oneup.ui.base;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.TextView;


import com.andrestorres.oneup.R;
import com.andrestorres.oneup.utils.UIUtils;

import butterknife.ButterKnife;


/**
 * Created by andrestorres on 3/14/16.
 */
public class BaseActivity extends AppCompatActivity implements BaseViewContract {

    protected final String TAG = BaseActivity.class.getSimpleName();

    public Toolbar mToolbar;
    public TextView mTitleTextView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    protected void completeView(){
        ButterKnife.bind(this);

        mToolbar = (Toolbar)findViewById(R.id.toolbar);
        if(mToolbar != null){
            setSupportActionBar(mToolbar);
        }

    }

    protected void setUpView(String activityLabel, boolean backButtonEnabled){
        setSupportActionBar(mToolbar);
        getSupportActionBar().setHomeButtonEnabled(backButtonEnabled);
        getSupportActionBar().setDisplayHomeAsUpEnabled(backButtonEnabled);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.btn_back_normal);
        getSupportActionBar().setTitle(null);
        mTitleTextView = (TextView) mToolbar.findViewById(R.id.toolbar_title);
        mTitleTextView.setText(activityLabel);
    }

    public void loadFragment(FragmentActivity activity, Fragment fragment, int containerID) {
        FragmentTransaction transaction = activity.getSupportFragmentManager().beginTransaction();
        transaction.replace(containerID, fragment);
        transaction.commitAllowingStateLoss();

    }

    protected void hideKeyboard() {
        View view = this.getCurrentFocus();
        if (view != null) {
            InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        if(id == android.R.id.home){
            finish();
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public Activity getActivity() {
        return this;
    }

    @Override
    public void showLoader() {
        UIUtils.showProgressDialog(this);
    }

    @Override
    public void hideLoader() {
        UIUtils.dismissProgressDialog();

    }

    @Override
    public void showMessage(String message) {
        hideKeyboard();
        Snackbar.make(findViewById(android.R.id.content), message, Snackbar.LENGTH_SHORT).show();
    }
}
