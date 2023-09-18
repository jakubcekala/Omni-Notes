package it.feio.android.omninotes.espresso.tests

import androidx.test.filters.LargeTest
import it.feio.android.omninotes.espresso.data.MessagesDataProvider
import it.feio.android.omninotes.espresso.data.NoteDataProvider
import it.feio.android.omninotes.espresso.data.NoteDataProvider.simpleTextNoteWithGroup
import it.feio.android.omninotes.espresso.data.TextDataProvider
import it.feio.android.omninotes.models.Category
import it.feio.android.omninotes.models.Note
import org.junit.Before
import org.junit.Test

@LargeTest
class NotesViewingTest : BaseTest() {

    private val noteData = NoteDataProvider.simpleTextNote
    private val hashtagNoteData = NoteDataProvider.hashtagNote
    private val noteDataUpdated = NoteDataProvider.simpleTextNoteUpdated
    private val categoryData = NoteDataProvider.category
    lateinit var noteWithGroup: Note
    lateinit var category: Category

    @Before
    override fun setUp() {
        super.setUp()
        addTextNote(noteData.title, noteData.content)
        category = Category()
    }

    @Test
    fun addNewGroup() {
        mainPage
            .selectNoteFromList(0)
            .toolbar.clickOnCategoryButton()
            .clickOnAddCategoryButton()
            .enterCategoryTitle(categoryData.name)
            .clickOnOkButton()
            .checkTopMessage(MessagesDataProvider.categorySaved)
            .waitUntilTopMessageDisappear()
            .checkTitleTagMarker()
            .toolbar.clickOnBackButton()
        mainPage
            .checkMarkerOnPosition(0, isDisplayed = true)
    }

    @Test
    fun removeExistingGroup() {
        noteWithGroup = addTextNote(simpleTextNoteWithGroup.title, simpleTextNoteWithGroup.content)
        noteWithGroup.setCategoryToNote(category)
        mainPage
            .selectNoteFromList(0)
            .checkTitleTagMarker(isDisplayed = true)
            .toolbar.clickOnCategoryButton()
            .clickOnRemoveCategoryButton()
            .checkTitleTagMarker(isDisplayed = false)
            .toolbar.clickOnBackButton()
        mainPage
            .checkMarkerOnPosition(0, isDisplayed = false)
    }

    @Test
    fun viewGroupManagementDialog() {
        noteWithGroup = addTextNote(simpleTextNoteWithGroup.title, simpleTextNoteWithGroup.content)
        noteWithGroup.setCategoryToNote(category)
        mainPage
            .selectNoteFromList(0)
            .checkTitleTagMarker(isDisplayed = true)
            .toolbar.clickOnCategoryButton()
            .checkDialogElements()
            .checkExistingGroup(category.name)
    }

    @Test
    fun editExistingNoteContentAddTagToNote() {
        mainPage
            .selectNoteFromList(0)
            .enterTitle(hashtagNoteData.title)
            .enterContentNote(hashtagNoteData.content)
            .toolbar.clickOnBackButton()
        mainPage
            .selectNoteFromList(0)
            .toolbar.clickOnTagButton()
            .checkTagVisible(hashtagNoteData.content.substringAfter("#"), 1)
            .clickOkButton()
    }

    @Test
    fun editExistingNote() {
        mainPage
            .checkPageTitle(TextDataProvider.notesTitle)
            .selectNoteFromList(0)
            .enterTitle(noteDataUpdated.title)
            .enterContentNote(noteDataUpdated.content)
            .clickOnReminder()
            .clickOkButtonTimePicker()
            .checkEnteredDataTextNote(noteDataUpdated.title, noteDataUpdated.content, true)
            .toolbar.clickOnBackButton()
        mainPage
            .checkTopMessage(MessagesDataProvider.noteUpdated)
            .checkListItem(noteDataUpdated.title, noteDataUpdated.content, true)
    }

    @Test
    fun viewExistingNote() {
        mainPage
            .checkPageTitle(TextDataProvider.notesTitle)
            .selectNoteFromList(0)
            .verifyDetailNoteScreen()
            .checkEnteredDataTextNote(noteData.title, noteData.content, false)
    }
}
