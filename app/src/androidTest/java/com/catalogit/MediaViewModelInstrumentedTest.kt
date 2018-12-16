package com.catalogit

import android.os.Handler
import android.os.Looper
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import androidx.test.rule.ActivityTestRule
import com.catalogit.view.MainActivity
import com.catalogit.viewmodel.MediaViewModel
import org.hamcrest.Matchers
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.MockitoAnnotations

@LargeTest
@RunWith(AndroidJUnit4::class)
class MediaViewModelInstrumentedTest {

    @Rule
    @JvmField
    var mActivityTestRule = ActivityTestRule(MainActivity::class.java)

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)
    }

    @Test
    fun mediaViewModelTest() {
        Handler(Looper.getMainLooper()).post {

            val mLiveDataTimerViewModel =
                ViewModelProviders.of(mActivityTestRule.activity).get(MediaViewModel::class.java)

            mLiveDataTimerViewModel.mediaList.observe(mActivityTestRule.activity, Observer { data ->
                data.forEach {
                    Assert.assertNotNull(it)
                    Assert.assertThat(it.items.size.toString(), Matchers.greaterThanOrEqualTo("1"))
                }
            })

        }
    }
}