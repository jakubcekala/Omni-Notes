package it.feio.android.omninotes.espresso.tests

import androidx.test.filters.LargeTest
import it.feio.android.omninotes.espresso.data.MessagesDataProvider
import it.feio.android.omninotes.espresso.data.NoteDataProvider
import it.feio.android.omninotes.espresso.data.TextDataProvider
import org.junit.Test

@LargeTest
class NotesCreatingTest : BaseTest() {

    private val textNote = NoteDataProvider.simpleTextNote
    private val checklistNote = NoteDataProvider.simpleChecklistNote

    @Test
    fun createNewTextNote() {
        mainPage
            .checkPageTitle(TextDataProvider.notesTitle)
            .clickOnFabButton()
            .checkFabMenuIsOpened(true)
            .clickOnNoteButton()
            .verifyDetailNoteScreen()
            .checkTitleHint()
            .checkContentHint()
            .checkIfReminderIsAdded(false)
            .enterTitle(textNote.title)
            .enterContentNote(textNote.content)
            .clickOnReminder()
            .clickOkButtonTimePicker()
            .checkEnteredDataTextNote(textNote.title, textNote.content, true)
            .toolbar.clickOnBackButton()
        mainPage
            .checkTopMessage(MessagesDataProvider.noteUpdated)
            .checkListItem(textNote.title, textNote.content, true)
    }

    @Test
    fun createNewChecklistNote() {
        mainPage
            .checkPageTitle(TextDataProvider.notesTitle)
            .clickOnFabButton()
            .checkFabMenuIsOpened(true)
            .clickOnChecklistButton()
            .verifyDetailNoteScreen()
            .checkTitleHint()
            .checkNewItemHint()
            .enterTitle(checklistNote.title)
            .enterChecklistItem(checklistNote.points[0])
            .enterChecklistItem(checklistNote.points[1])
            .enterChecklistItem(checklistNote.points[2])
            .clickOnReminder()
            .clickOkButtonTimePicker()
            .checkEnteredDataChecklist(
                checklistNote.title,
                checklistNote.points.size,
                true
            )
            .toolbar.clickOnBackButton()
        mainPage
            .checkTopMessage(MessagesDataProvider.noteUpdated)
            .checkListItem(
                checklistNote.title,
                "◻ ${checklistNote.points[0]}\n◻ ${checklistNote.points[1]}…",
                true
            )
    }

    @Test
    fun createEmptyTextNote() {
        mainPage
            .checkPageTitle(TextDataProvider.notesTitle)
            .clickOnFabButton()
            .checkFabMenuIsOpened(true)
            .clickOnNoteButton()
            .verifyDetailNoteScreen()
            .toolbar.clickOnBackButton()
        mainPage
            .checkTopMessage(MessagesDataProvider.cantSaveEmptyNote)
    }

    @Test
    fun createEmptyChecklistNote() {
        mainPage
            .checkPageTitle(TextDataProvider.notesTitle)
            .clickOnFabButton()
            .checkFabMenuIsOpened(true)
            .clickOnChecklistButton()
            .verifyDetailNoteScreen()
            .toolbar.clickOnBackButton()
        mainPage
            .checkTopMessage(MessagesDataProvider.cantSaveEmptyNote)
    }
}
