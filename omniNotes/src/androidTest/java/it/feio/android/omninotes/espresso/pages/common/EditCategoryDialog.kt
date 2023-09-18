package it.feio.android.omninotes.espresso.pages.common

import it.feio.android.omninotes.espresso.locators.findViewById
import it.feio.android.omninotes.R
import it.feio.android.omninotes.espresso.actions.click
import it.feio.android.omninotes.espresso.actions.typeText
import it.feio.android.omninotes.espresso.pages.ItemDetailsPage
import it.feio.android.omninotes.espresso.utils.TestLog

class EditCategoryDialog {
    private val titleEditText = findViewById(R.id.category_title)
    private val colorChooser = findViewById(R.id.color_chooser)
    private val okButton = findViewById(R.id.save)
    private val deleteButton = findViewById(R.id.delete)

    fun enterCategoryTitle(title: String): EditCategoryDialog {
        TestLog.step("Entering category title")
        titleEditText.typeText(title)
        return this
    }

    fun clickOnOkButton(): ItemDetailsPage {
        TestLog.step("Clicking on Ok button")
        okButton.click()
        return ItemDetailsPage()
    }

    fun clickOnDeleteButton(): ConfirmDialog {
        TestLog.step("Clicking on Delete button")
        deleteButton.click()
        return ConfirmDialog()
    }
}
