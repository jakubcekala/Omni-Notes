package it.feio.android.omninotes.espresso.actions

import androidx.test.espresso.ViewInteraction
import androidx.test.espresso.action.ViewActions

fun ViewInteraction.click(): ViewInteraction = this.perform(ViewActions.click())
fun ViewInteraction.longClick(): ViewInteraction = this.perform(ViewActions.longClick())
fun ViewInteraction.typeText(text: String): ViewInteraction = this.perform(ViewActions.typeText(text))
fun ViewInteraction.replaceText(text: String): ViewInteraction = this.perform(ViewActions.replaceText(text))
