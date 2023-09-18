package it.feio.android.omninotes.espresso.pages

import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.hasChildCount
import it.feio.android.omninotes.espresso.locators.findViewById
import it.feio.android.omninotes.R
import it.feio.android.omninotes.espresso.actions.click
import it.feio.android.omninotes.espresso.actions.replaceText
import it.feio.android.omninotes.espresso.assertions.*
import it.feio.android.omninotes.espresso.locators.findViewByHint
import it.feio.android.omninotes.espresso.locators.findViewByIdAndParentId
import it.feio.android.omninotes.espresso.pages.common.Toolbar
import it.feio.android.omninotes.espresso.utils.TestLog

class ItemDetailsPage {

    private val titleEditText = findViewById(R.id.detail_title)
    private val contentEditText = findViewById(R.id.detail_content)
    private val reminderLayout = findViewById(R.id.reminder_layout)
    private val reminderIcon = findViewById(R.id.reminder_icon)
    private val reminderDateTime = findViewById(R.id.datetime)
    private val timePickerOkButton = findViewByIdAndParentId(R.id.buttonPositive, R.id.button_layout)
    private val newItemEditText = findViewByHint(R.string.checklist_item_hint)
    private val titleTagMarker = findViewById(R.id.tag_marker)
    private val topMessage = findViewById(0x101)

    val toolbar = Toolbar()

    fun enterTitle(title: String): ItemDetailsPage {
        TestLog.step("Entering title - $title")
        titleEditText.replaceText(title)
        return this
    }

    fun enterContentNote(content: String): ItemDetailsPage {
        TestLog.step("Entering content note - $content")
        contentEditText.replaceText(content)
        return this
    }

    fun enterChecklistItem(itemText: String): ItemDetailsPage {
        TestLog.step("Entering checklist item - $itemText")
        newItemEditText.replaceText(itemText)
        return this
    }

    fun clickOnReminder(): ItemDetailsPage {
        TestLog.step("Clicking on reminder button")
        reminderLayout.click()
        return this
    }

    fun checkTopMessage(message: String): ItemDetailsPage {
        TestLog.step("Verifying top message - $message")
        topMessage.checkText(message)
        return this
    }

    fun clickOkButtonTimePicker(): ItemDetailsPage {
        TestLog.step("Clicking on time picker button")
        timePickerOkButton.click()
        return ItemDetailsPage()
    }

    fun checkTitleTagMarker(isDisplayed: Boolean = true): ItemDetailsPage {
        TestLog.step("Verifying if tag marker is displayed - isDisplayed: $isDisplayed")
        if (!isDisplayed) {
            titleTagMarker.checkBackgroundColor(0)
        } else {
            titleTagMarker.checkNotBackgroundColor(0)
        }
        return this
    }

    fun checkTitleHint(): ItemDetailsPage {
        TestLog.step("Checking title hint")
        titleEditText.checkLocalizedHint(R.string.title)
        return this
    }

    fun checkContentHint(): ItemDetailsPage {
        TestLog.step("Checking content hint")
        contentEditText.checkLocalizedHint(R.string.content)
        return this
    }

    fun checkNewItemHint(): ItemDetailsPage {
        TestLog.step("Checking new item hint")
        newItemEditText.checkLocalizedHint(R.string.checklist_item_hint)
        return this
    }

    fun checkIfReminderIsAdded(isAdded: Boolean): ItemDetailsPage {
        TestLog.step("Checking if reminder is added - is added: $isAdded")
        reminderIcon.checkIsDisplayed()
        if (isAdded) {
            reminderDateTime.checkLocalizedHint(R.string.add_reminder)
        } else {
            reminderDateTime.checkLocalizedHint(R.string.add_reminder)
        }
        return this
    }

    fun checkEnteredDataTextNote(title: String, content: String, isReminder: Boolean): ItemDetailsPage {
        TestLog.step("Checking note data - Title: $title, Content: $content, is reminder: $isReminder")
        titleEditText.checkText(title)
        contentEditText.checkText(content)
        if (isReminder) {
            reminderDateTime.checkTextWithSubstring("Reminder set for")
        } else {
            reminderDateTime.checkHint("Add reminder")
        }
        return this
    }

    fun checkEnteredDataChecklist(title: String, numberOfItems: Int, isReminder: Boolean): ItemDetailsPage {
        TestLog.step("Checking checklist data - Title: $title, Nr of items: $numberOfItems, is reminder: $isReminder")
        titleEditText.checkText(title)
        contentEditText.check(matches(hasChildCount(numberOfItems.plus(1))))

        if (isReminder) {
            reminderDateTime.checkTextWithSubstring("Reminder set for")
        } else {
            reminderDateTime.checkText("Add reminder")
        }
        return this
    }

    fun waitUntilTopMessageDisappear(): ItemDetailsPage {
        TestLog.step("Waiting until top message disappears")
        topMessage.waitForElementToDisappear()
        return this
    }

    fun verifyDetailNoteScreen(): ItemDetailsPage {
        TestLog.step("Verifying detail note screen")
        titleEditText.checkIsDisplayed()
        contentEditText.checkIsDisplayed()
        reminderLayout.checkIsDisplayed()
        reminderIcon.checkIsDisplayed()
        reminderDateTime.checkIsDisplayed()
        toolbar.verifyToolbarButtonsForTextNote()
        return this
    }
}
