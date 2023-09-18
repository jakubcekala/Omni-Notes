package it.feio.android.omninotes.espresso.assertions

import androidx.annotation.IdRes
import androidx.test.espresso.ViewInteraction
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import it.feio.android.omninotes.espresso.matchers.CustomMatchers.withBackgroundColor
import it.feio.android.omninotes.espresso.utils.ConditionWatchers
import it.feio.android.omninotes.espresso.utils.ConditionWatchers.DEFAULT_TIMEOUT
import org.hamcrest.CoreMatchers.not

fun ViewInteraction.checkIsDisplayed(isDisplayed: Boolean = true) {
    if (isDisplayed) {
        this.check(matches(isDisplayed()))
    } else {
        this.check(matches(not(isDisplayed())))
    }
}

fun ViewInteraction.checkText(text: String) {
    this.check(matches(withText(text)))
}

fun ViewInteraction.checkBackgroundColor(color: Int) {
    this.check(matches(withBackgroundColor(color)))
}

fun ViewInteraction.checkNotBackgroundColor(color: Int) {
    this.check(matches(not(withBackgroundColor(color))))
}

fun ViewInteraction.checkTextWithSubstring(substring: String) {
    this.check(matches(withSubstring(substring)))
}

fun ViewInteraction.checkLocalizedText(@IdRes id: Int) {
    this.check(matches(withText(id)))
}

fun ViewInteraction.checkLocalizedTextNot(@IdRes id: Int) {
    this.check(matches(not(withText(id))))
}

fun ViewInteraction.checkHint(hint: String) {
    this.check(matches(withHint(hint)))
}

fun ViewInteraction.checkLocalizedHint(@IdRes id: Int) {
    this.check(matches(withHint(id)))
}

fun ViewInteraction.waitForElementToDisappear(timeout: Int = DEFAULT_TIMEOUT) {
    ConditionWatchers.waitForElementToDisappear(this, timeout)
}