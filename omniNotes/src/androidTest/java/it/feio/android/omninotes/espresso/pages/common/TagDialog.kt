package it.feio.android.omninotes.espresso.pages.common

import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import it.feio.android.omninotes.espresso.locators.findViewByIdAndText
import it.feio.android.omninotes.R
import it.feio.android.omninotes.espresso.actions.click
import it.feio.android.omninotes.espresso.locators.findViewById
import it.feio.android.omninotes.espresso.pages.ItemDetailsPage
import it.feio.android.omninotes.espresso.utils.TestLog
import org.hamcrest.CoreMatchers.allOf

class TagDialog {
    private val okButton = findViewByIdAndText(R.id.md_buttonDefaultPositive, R.string.ok)
    private val tagsList = findViewById(R.id.md_contentRecyclerView)

    fun checkTagVisible(tagName: String, amount: Int): TagDialog {
        TestLog.step("Checking if tag is visible. Tag: $tagName, amount: $amount")
        tagsList.check(matches(hasDescendant(allOf(
            withId(R.id.md_title),
            withText("$tagName ($amount)")
        ))))
        return this
    }

    fun clickOkButton(): ItemDetailsPage {
        TestLog.step("Clicking on Ok button")
        okButton.click()
        return ItemDetailsPage()
    }
}