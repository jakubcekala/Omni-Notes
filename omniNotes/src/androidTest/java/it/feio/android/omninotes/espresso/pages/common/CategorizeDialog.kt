package it.feio.android.omninotes.espresso.pages.common

import it.feio.android.omninotes.espresso.locators.findViewById
import it.feio.android.omninotes.R
import it.feio.android.omninotes.espresso.actions.click
import it.feio.android.omninotes.espresso.assertions.checkIsDisplayed
import it.feio.android.omninotes.espresso.assertions.checkText
import it.feio.android.omninotes.espresso.data.TextDataProvider
import it.feio.android.omninotes.espresso.locators.findViewByIsDescendantId
import it.feio.android.omninotes.espresso.pages.ItemDetailsPage
import it.feio.android.omninotes.espresso.utils.TestLog

class CategorizeDialog {
    private val addCategoryButton = findViewById(R.id.md_buttonDefaultPositive)
    private val removeCategoryButton = findViewById(R.id.md_buttonDefaultNegative)
    private val categorizeTitle = findViewById(R.id.md_title)
    private val groupName = findViewByIsDescendantId(R.id.title, R.id.md_contentRecyclerView)

    fun clickOnAddCategoryButton(): EditCategoryDialog {
        TestLog.step("Clicking on Add category button")
        addCategoryButton.click()
        return EditCategoryDialog()
    }

    fun clickOnRemoveCategoryButton(): ItemDetailsPage {
        TestLog.step("Clicking on Remove category button")
        removeCategoryButton.click()
        return ItemDetailsPage()
    }

    fun checkDialogElements(): CategorizeDialog {
        TestLog.step("Checking group dialog elements")
        addCategoryButton.checkIsDisplayed()
        removeCategoryButton.checkIsDisplayed()
        categorizeTitle.checkIsDisplayed()
        addCategoryButton.checkText(TextDataProvider.addCategory)
        removeCategoryButton.checkText(TextDataProvider.removeCategory)
        categorizeTitle.checkText(TextDataProvider.categorizeAs)
        return this
    }

    fun checkExistingGroup(name: String): CategorizeDialog {
        TestLog.step("Checking existing group name: $name")
        groupName.checkText(name)
        return this
    }
}
