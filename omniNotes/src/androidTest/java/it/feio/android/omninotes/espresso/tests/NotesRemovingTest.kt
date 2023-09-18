package it.feio.android.omninotes.espresso.tests

import androidx.test.filters.LargeTest
import it.feio.android.omninotes.espresso.data.MessagesDataProvider
import it.feio.android.omninotes.espresso.data.NoteDataProvider
import it.feio.android.omninotes.espresso.data.TextDataProvider
import it.feio.android.omninotes.models.Note
import org.junit.Before
import org.junit.Test

@LargeTest
class NotesRemovingTest : BaseTest() {

    lateinit var note: Note
    private val noteData = NoteDataProvider.simpleTextNote

    @Before
    override fun setUp() {
        super.setUp()
        note = addTextNote(noteData.title, noteData.content)
        note.archiveNote()
    }

    @Test
    fun removeNoteFromArchive() {
        mainPage
            .checkPageTitle(TextDataProvider.notesTitle)
            .toolbar.openDrawer()
            .clickOnArchiveButton()
            .checkListItem(note.title, note.content, false)
            .swipeLeftOnTheItem(0)
            .checkTopMessage(MessagesDataProvider.notesTrashed)
            .checkUndoMessageBar(MessagesDataProvider.oneTrashed)
            .waitUntilTopMessageDisappear()
            .toolbar.openDrawer()
            .clickOnTrashButton()
            .checkPageTitle(TextDataProvider.trashTitle)
            .checkListItem(note.title, note.content, false)
    }
}