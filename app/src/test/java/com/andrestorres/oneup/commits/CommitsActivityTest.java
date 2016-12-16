package com.andrestorres.oneup.commits;

import com.andrestorres.oneup.BuildConfig;
import com.andrestorres.oneup.R;
import com.andrestorres.oneup.models.responses.CommitsResponse;
import com.andrestorres.oneup.models.utils.Commit;
import com.andrestorres.oneup.ui.views.commits.CommitsActivity;
import com.andrestorres.oneup.ui.views.commits.CommitsContract;
import com.andrestorres.oneup.ui.views.commits.CommitsPresenter;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricGradleTestRunner;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;

import java.util.ArrayList;
import java.util.List;

import static junit.framework.Assert.assertNotNull;
import static org.mockito.Mockito.verify;

/**
 * Created by andrestorres on 12/16/16.
 */

@RunWith(RobolectricGradleTestRunner.class)
@Config(constants = BuildConfig.class)
public class CommitsActivityTest {

    private CommitsActivity activity;

    @Before
    public void setUp() throws Exception {
        activity = Robolectric.buildActivity( CommitsActivity.class )
                .create()
                .resume()
                .get();
    }

    @Test
    public void shouldNotBeNull() throws Exception {
        assertNotNull(activity);
    }

    @Test
    public void shouldHavePlaceholder() throws Exception {
        assertNotNull(activity.findViewById(R.id.activity_commits_placeholder));
    }
}
