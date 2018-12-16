package com.catalogit

import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import androidx.test.platform.app.InstrumentationRegistry
import com.catalogit.view.MainActivity
import org.hamcrest.Matchers.not
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@LargeTest
@RunWith(AndroidJUnit4::class)
class MainActivityInstrumentedTest {

    @Before
    fun launchActivity() {
        ActivityScenario.launch(MainActivity::class.java)
    }

    @Test
    fun mainComponentsAreDisplayed() {
        InstrumentationRegistry.getInstrumentation().waitForIdleSync()

        onView(withId(R.id.swipeRefreshLayout))
            .check(matches(isDisplayed()))

        onView(withId(R.id.categoryList))
            .check(matches(isDisplayed()))

        onView(withId(R.id.emptyList))
            .check(matches(not(isDisplayed())))
    }
}