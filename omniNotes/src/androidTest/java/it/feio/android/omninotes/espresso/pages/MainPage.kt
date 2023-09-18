package it.feio.android.omninotes.espresso.pages

import androidx.recyclerview.widget.RecyclerView.ViewHolder
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.swipeLeft
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition
import androidx.test.espresso.matcher.ViewMatchers.*
import it.feio.android.omninotes.espresso.locators.findViewById
import it.feio.android.omninotes.R
import it.feio.android.omninotes.espresso.actions.click
import it.feio.android.omninotes.espresso.assertions.checkIsDisplayed
import it.feio.android.omninotes.espresso.assertions.checkText
import it.feio.android.omninotes.espresso.assertions.waitForElementToDisappear
import it.feio.android.omninotes.espresso.matchers.CustomMatchers.withBackgroundColor
import it.feio.android.omninotes.espresso.pages.common.Drawer
import it.feio.android.omninotes.espresso.pages.common.Toolbar
import it.feio.android.omninotes.espresso.utils.TestLog
import org.hamcrest.CoreMatchers.allOf
import org.hamcrest.CoreMatchers.not

class MainPage {
    private val fabMainButton = findViewById(R.id.fab_expand_menu_button)
    private val fabPhotoButton = findViewById(R.id.fab_camera)
    private val fabChecklistButton = findViewById(R.id.fab_checklist)
    private val fabNoteButton = findViewById(R.id.fab_note)

    private val emptyListInfo = findViewById(R.id.empty_list)
    private val recyclerViewList = findViewById(R.id.list)

    private val undoBarMessage = findViewById(R.id.undobar_message)
    private val undoBarButton = findViewById(R.id.undobar_button)
    private val topMessage = findViewById(0x101)

    val toolbar = Toolbar()
    val drawer = Drawer()

    fun clickOnFabButton(): MainPage {
        TestLog.step("Clicking on FAB button")
        fabMainButton.click()
        return this
    }

    fun clickOnChecklistButton(): ItemDetailsPage {
        TestLog.step("Clicking on checklist button")
        fabChecklistButton.click()
        return ItemDetailsPage()
    }

    fun clickOnNoteButton(): ItemDetailsPage {
        TestLog.step("Clicking on note button")
        fabNoteButton.click()
        return ItemDetailsPage()
    }

    fun selectNoteFromList(index: Int): ItemDetailsPage {
        TestLog.step("Selecting note from list - Index: $index")
        recyclerViewList.perform(actionOnItemAtPosition<ViewHolder>(index, click()))
        return ItemDetailsPage()
    }

    fun checkMarkerOnPosition(index: Int, isDisplayed: Boolean = true): ItemDetailsPage {
        TestLog.step("Checking marker on position - Position: $index, isDisplayed: $isDisplayed")
        if (isDisplayed) {
            recyclerViewList.check(
                matches(
                    allOf(
                        withParentIndex(index),
                        hasDescendant(allOf(
                                withId(R.id.category_marker),
                                not(withBackgroundColor(0))
                        ))
                    )
                )
            )
        } else {
            recyclerViewList.check(
                matches(
                    allOf(
                        withParentIndex(index),
                        hasDescendant(allOf(
                            withId(R.id.category_marker),
                            withBackgroundColor(0)
                        ))
                    )
                )
            )
        }
        return ItemDetailsPage()
    }

    fun swipeLeftOnTheItem(itemIndex: Int): MainPage {
        TestLog.step("Swiping left on the item - Item index: $itemIndex")
        recyclerViewList.perform(actionOnItemAtPosition<ViewHolder>(itemIndex, swipeLeft()))
        return this
    }

    fun checkFabMenuIsOpened(isOpened: Boolean): MainPage {
        TestLog.step("Verifying FAB menu is opened - is opened: $isOpened")
        fabMainButton.checkIsDisplayed()
        if (isOpened) {
            fabPhotoButton.checkIsDisplayed()
            fabChecklistButton.checkIsDisplayed()
            fabNoteButton.checkIsDisplayed()
        } else {
            fabPhotoButton.checkIsDisplayed(false)
            fabChecklistButton.checkIsDisplayed(false)
            fabNoteButton.checkIsDisplayed(false)
        }
        return this
    }

    fun checkPageTitle(title: String): MainPage {
        TestLog.step("Verifying page title - Title: $title")
        val toolbarTitle = onView(allOf(withId(R.id.toolbar), hasDescendant(withText(title))))
        toolbarTitle.checkIsDisplayed()
        return this
    }

    fun checkTopMessage(message: String): MainPage {
        TestLog.step("Verifying top message - Top message: $message")
        topMessage.checkText(message)
        return this
    }

    fun waitUntilTopMessageDisappear(): MainPage {
        TestLog.step("Waiting until top message disappears")
        topMessage.waitForElementToDisappear()
        return this
    }

    fun checkUndoMessageBar(text: String): MainPage {
        TestLog.step("Verifying undo message bar - Text: $text")
        undoBarMessage.checkIsDisplayed()
        undoBarMessage.checkText(text)
        undoBarButton.checkIsDisplayed()
        undoBarButton.checkText("UNDO")
        return this
    }

    fun checkEmptyListInfo(): MainPage {
        TestLog.step("Verifying empty list info")
        emptyListInfo.checkIsDisplayed()
        return this
    }

    fun checkListItem(title: String, content: String, isReminder: Boolean): MainPage {
        TestLog.step("Verifying checklist item - Title: $title, Content: $content, is reminder: $isReminder")
        val noteTitle = onView(allOf(
            withId(R.id.note_title),
            isDescendantOfA(withId(R.id.root))
        ))
        val noteContent = onView(allOf(
            withId(R.id.note_content),
            isDescendantOfA(withId(R.id.root))
        ))
        val noteDate = onView(allOf(
            withId(R.id.note_date),
            isDescendantOfA(withId(R.id.root))
        ))
        val alarmIcon = onView(allOf(
            withId(R.id.alarmIcon),
            isDescendantOfA(withId(R.id.root))
        ))

        noteTitle.checkIsDisplayed()
        noteContent.checkIsDisplayed()
        noteDate.checkIsDisplayed()
        if (isReminder) {
            alarmIcon.checkIsDisplayed()
        }
        noteTitle.checkText(title)
        noteContent.checkText(content)
        return this
    }
}
