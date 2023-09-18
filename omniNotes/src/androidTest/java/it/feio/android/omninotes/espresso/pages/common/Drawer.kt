package it.feio.android.omninotes.espresso.pages.common

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import it.feio.android.omninotes.espresso.locators.findViewById
import it.feio.android.omninotes.R
import it.feio.android.omninotes.espresso.actions.click
import it.feio.android.omninotes.espresso.actions.longClick
import it.feio.android.omninotes.espresso.locators.findViewByIdAndText
import it.feio.android.omninotes.espresso.pages.MainPage
import it.feio.android.omninotes.espresso.utils.TestLog
import org.hamcrest.CoreMatchers.allOf
import org.hamcrest.CoreMatchers.not

class Drawer {
    private val drawerContainer = findViewById(R.id.navigation_drawer)
    private val notesButton = findViewByIdAndText(R.id.title, R.string.notes)
    private val archiveButton = findViewByIdAndText(R.id.title, R.string.archive)
    private val trashButton = findViewByIdAndText(R.id.title, R.string.trash)
    private val settingsButton = findViewById(R.id.settings)

    fun clickOnArchiveButton(): MainPage {
        TestLog.step("Clicking on Archive button")
        archiveButton.click()
        return MainPage()
    }

    fun clickOnTrashButton(): MainPage {
        TestLog.step("Clicking on Trash button")
        trashButton.click()
        return MainPage()
    }

    fun longClickOnItemWithTitle(title: String): EditCategoryDialog {
        TestLog.step("Long clicking on item with title - $title")
        findViewByIdAndText(R.id.title, title).longClick()
        return EditCategoryDialog()
    }

    fun checkIfItemDisplayed(item: String, isDisplayed: Boolean): Drawer {
        TestLog.step("Checking if item is displayed. Item: $item, isDisplayed: $isDisplayed")
        if (isDisplayed) {
            onView(withId(R.id.left_drawer)).check(matches(allOf(
                hasDescendant(withText(item)),
                isDisplayed()
            )))
        } else {
            onView(withId(R.id.left_drawer)).check(matches(allOf(
                not(hasDescendant(withText(item)))
            )))
        }
        return this
    }
}
