package it.feio.android.omninotes.espresso.locators

import androidx.annotation.IdRes
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.ViewInteraction
import androidx.test.espresso.matcher.ViewMatchers.*
import org.hamcrest.CoreMatchers.allOf

fun findViewById(@IdRes id: Int): ViewInteraction = onView(withId(id))
fun findViewByIdAndText(@IdRes id: Int, @IdRes text: Int): ViewInteraction = onView(allOf(withId(id), withText(text)))
fun findViewByIdAndText(@IdRes id: Int, text: String): ViewInteraction = onView(allOf(withId(id), withText(text)))
fun findViewByIdAndParentId(@IdRes id: Int, @IdRes parentId: Int): ViewInteraction = onView(allOf(withId(id), withParent(withId(parentId))))
fun findViewByIsDescendantId(@IdRes id: Int, @IdRes descendantId: Int): ViewInteraction = onView(allOf(withId(id), isDescendantOfA(withId(descendantId))))
fun findViewByHint(@IdRes hint: Int): ViewInteraction = onView(withHint(hint))