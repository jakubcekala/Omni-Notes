package it.feio.android.omninotes.espresso.pages.common

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.withClassName
import androidx.test.espresso.matcher.ViewMatchers.withId
import it.feio.android.omninotes.R
import it.feio.android.omninotes.espresso.actions.click
import it.feio.android.omninotes.espresso.assertions.checkIsDisplayed
import it.feio.android.omninotes.espresso.locators.findViewById
import it.feio.android.omninotes.espresso.matchers.CustomMatchers.childAtPosition
import it.feio.android.omninotes.espresso.pages.ItemDetailsPage
import it.feio.android.omninotes.espresso.utils.TestLog
import org.hamcrest.CoreMatchers.allOf
import org.hamcrest.Matchers

class Toolbar {

    private val attachmentButton = findViewById(R.id.menu_attachment)
    private val categoryButton = findViewById(R.id.menu_category)
    private val tagButton = findViewById(R.id.menu_tag)
    private val shareButton = findViewById(R.id.menu_share)
    private val backButton = onView(allOf(childAtPosition(allOf(
        withId(R.id.toolbar),
        childAtPosition(
            withClassName(Matchers.`is`("android.widget.RelativeLayout")),
            0
        )
    ), 0), ViewMatchers.isDisplayed()))
    private val drawerButton = onView(allOf(childAtPosition(allOf(
        withId(R.id.toolbar),
        childAtPosition(
            withClassName(Matchers.`is`("android.widget.RelativeLayout")),
            0
        )
    ), 1), ViewMatchers.isDisplayed()))

    fun verifyToolbarButtonsForTextNote(): ItemDetailsPage {
        TestLog.step("Verifying toolbar buttons for text note")
        attachmentButton.checkIsDisplayed()
        categoryButton.checkIsDisplayed()
        tagButton.checkIsDisplayed()
        shareButton.checkIsDisplayed()
        return ItemDetailsPage()
    }

    fun clickOnCategoryButton(): CategorizeDialog {
        TestLog.step("Clicking on Category button")
        categoryButton.click()
        return CategorizeDialog()
    }

    fun clickOnTagButton(): TagDialog {
        TestLog.step("Clicking on Tag button")
        tagButton.click()
        return TagDialog()
    }

    fun clickOnBackButton() {
        TestLog.step("Clicking on Back button")
        backButton.click()
    }

    fun openDrawer(): Drawer {
        TestLog.step("Opening side drawer")
        drawerButton.click()
        return Drawer()
    }
}
