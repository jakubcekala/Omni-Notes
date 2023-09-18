package it.feio.android.omninotes.espresso.tests

import androidx.test.filters.LargeTest
import it.feio.android.omninotes.espresso.data.MessagesDataProvider
import it.feio.android.omninotes.espresso.data.NoteDataProvider
import it.feio.android.omninotes.espresso.data.TextDataProvider
import org.junit.Before
import org.junit.Test

@LargeTest
class NotesArchivingTest : BaseTest() {

    private val noteData = NoteDataProvider.simpleTextNote

    @Before
    override fun setUp() {
        super.setUp()
        addTextNote(noteData.title, noteData.content)
    }

    @Test
    fun archiveExistingNote() {
        mainPage
            .swipeLeftOnTheItem(0)
            .checkTopMessage(MessagesDataProvider.noteArchived)
            .checkUndoMessageBar(MessagesDataProvider.oneArchived)
            .waitUntilTopMessageDisappear()
            .checkEmptyListInfo()
            .toolbar.openDrawer().clickOnArchiveButton()
        mainPage
            .checkPageTitle(TextDataProvider.archiveTitle)
            .checkListItem(noteData.title, noteData.content, false)
    }
}