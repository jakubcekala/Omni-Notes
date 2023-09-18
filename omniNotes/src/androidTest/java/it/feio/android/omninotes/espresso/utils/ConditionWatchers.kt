package it.feio.android.omninotes.espresso.utils

import android.view.View
import androidx.test.espresso.NoMatchingViewException
import androidx.test.espresso.ViewInteraction
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import com.azimolabs.conditionwatcher.ConditionWatcher
import com.azimolabs.conditionwatcher.Instruction
import junit.framework.AssertionFailedError

/**
 * Single place for all the [ConditionWatcher] functions used in project.
 */
object ConditionWatchers {

    private const val TIMEOUT_LIMIT = 10000
    const val DEFAULT_TIMEOUT = 5000
    private const val WATCH_INTERVAL = 500

    /**
     * Waits for a [View], located by [ViewInteraction], to be gone.
     *
     * @param viewInteraction that locates a view.
     * @param timeout - amount of time in milliseconds to wait for condition.
     * @return [ViewInteraction] for located view.
     */
    @Throws(Exception::class)
    @JvmStatic
    fun waitForElementToDisappear(
        viewInteraction: ViewInteraction,
        timeout: Int = DEFAULT_TIMEOUT
    ): ViewInteraction {
        ConditionWatcher.setTimeoutLimit(timeout)
        ConditionWatcher.setWatchInterval(WATCH_INTERVAL)
        ConditionWatcher.waitForCondition(object : Instruction() {

            override fun getDescription(): String {
                return "waitForElementIsGone"
            }

            override fun checkCondition(): Boolean {
                return try {
                    viewInteraction.check(matches(isDisplayed()))
                    false
                } catch (ex: NoMatchingViewException) {
                    true
                } catch (ex: AssertionFailedError) {
                    true
                }
            }
        })
        return viewInteraction
    }
}
