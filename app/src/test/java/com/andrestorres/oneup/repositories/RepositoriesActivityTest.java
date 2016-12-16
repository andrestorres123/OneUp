package com.andrestorres.oneup.repositories;

import android.support.test.espresso.action.ViewActions;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.andrestorres.oneup.R;
import com.andrestorres.oneup.RecyclerViewActions;
import com.andrestorres.oneup.ui.views.repositories.RepositoriesActivity;
import com.andrestorres.oneup.ui.views.repositories.RepositoriesPresenter;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static junit.framework.Assert.assertNotNull;

/**
 * Created by andrestorres on 12/16/16.
 */
@RunWith(AndroidJUnit4.class)
public class RepositoriesActivityTest {

    @Mock
    private RepositoriesPresenter mPresenter;

    @Rule
    public ActivityTestRule<RepositoriesActivity> activityTestRule = new ActivityTestRule<>(RepositoriesActivity.class);

    @Test
    public void shouldNotBeNull() throws Exception {
        assertNotNull(activityTestRule);
    }

    @Test
    public void clickOnRepository_OpenCommitsActivity(){
        onView(withId(R.id.activity_main_repositories_rv)).perform(RecyclerViewActions.actionOnItemAtPosition(0, ViewActions.click()));
        //should wait until next screen appears
        assertNotNull(activityTestRule.getActivity().findViewById(R.id.activity_commits_rv)); //next screen has this view
    }
}
