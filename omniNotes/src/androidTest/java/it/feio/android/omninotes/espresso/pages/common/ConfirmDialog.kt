package it.feio.android.omninotes.espresso.pages.common

import it.feio.android.omninotes.espresso.locators.findViewByIdAndText
import it.feio.android.omninotes.R
import it.feio.android.omninotes.espresso.actions.click
import it.feio.android.omninotes.espresso.assertions.checkText
import it.feio.android.omninotes.espresso.locators.findViewById
import it.feio.android.omninotes.espresso.utils.TestLog

class ConfirmDialog {
    private val confirmButton = findViewByIdAndText(R.id.md_buttonDefaultPositive, R.string.confirm)
    private val content = findViewById(R.id.md_content)

    private val contentString =
        "This category is used by some notes.\nConfirm un-categorization of the notes and deletion of the category?"


    fun clickOnConfirmButton(): Drawer {
        TestLog.step("Clicking on Confirm button")
        confirmButton.click()
        return Drawer()
    }

    fun checkContent(): ConfirmDialog {
        TestLog.step("Checking dialog content")
        content.checkText(contentString)
        return this
    }
}
