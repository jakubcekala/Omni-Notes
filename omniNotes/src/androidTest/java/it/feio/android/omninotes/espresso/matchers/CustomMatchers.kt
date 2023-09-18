package it.feio.android.omninotes.espresso.matchers

import android.graphics.drawable.ColorDrawable
import android.view.View
import android.view.ViewGroup
import androidx.test.espresso.intent.Checks
import androidx.test.espresso.matcher.BoundedMatcher
import org.hamcrest.Description
import org.hamcrest.Matcher
import org.hamcrest.TypeSafeMatcher


object CustomMatchers {
    fun childAtPosition(
        parentMatcher: Matcher<View?>, position: Int
    ): Matcher<View?>? {
        return object : TypeSafeMatcher<View>() {
            override fun describeTo(description: Description) {
                description.appendText("Child at position $position in parent ")
                parentMatcher.describeTo(description)
            }

            override fun matchesSafely(view: View): Boolean {
                val parent = view.parent
                return (parent is ViewGroup && parentMatcher.matches(parent)
                        && view == parent.getChildAt(position))
            }
        }
    }

    fun withBackgroundColor(color: Int): Matcher<View?> {
        Checks.checkNotNull(color)
        return object : BoundedMatcher<View?, View>(View::class.java) {
            override fun matchesSafely(row: View): Boolean {
                return color == (row.background as ColorDrawable).color
            }

            override fun describeTo(description: Description) {
                description.appendText("with text color: ")
            }
        }
    }
}