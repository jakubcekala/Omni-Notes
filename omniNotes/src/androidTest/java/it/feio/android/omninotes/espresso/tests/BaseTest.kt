package it.feio.android.omninotes.espresso.tests

import android.graphics.Color
import androidx.test.rule.ActivityTestRule
import com.pixplicity.easyprefs.library.Prefs
import de.greenrobot.event.EventBus
import it.feio.android.omninotes.MainActivity
import it.feio.android.omninotes.async.bus.CategoriesUpdatedEvent
import it.feio.android.omninotes.async.bus.NotesUpdatedEvent
import it.feio.android.omninotes.db.DbHelper
import it.feio.android.omninotes.espresso.pages.MainPage
import it.feio.android.omninotes.espresso.utils.ScreenshotTestRule
import it.feio.android.omninotes.espresso.utils.TestLog
import it.feio.android.omninotes.models.Category
import it.feio.android.omninotes.models.Note
import org.junit.Before
import org.junit.Rule
import org.junit.rules.RuleChain
import java.util.*

abstract class BaseTest {

    private var dbHelper: DbHelper = DbHelper.getInstance()

    lateinit var mainPage: MainPage


    private val activityTestRule = ActivityTestRule(MainActivity::class.java, false, false)
    private val screenshotTestRule: ScreenshotTestRule = ScreenshotTestRule()

    @get:Rule
    val ruleChain: RuleChain = RuleChain
        .outerRule(activityTestRule)
        .around(screenshotTestRule)

    @Before
    open fun setUp() {
        TestLog.info("Setting up test")
        cleanAppData()
        activityTestRule.launchActivity(null)
        mainPage = MainPage()
    }

    private fun cleanAppData() {
        TestLog.info("Cleaning app data")
        Prefs.getPreferences().edit().clear().commit()
        dbHelper.getDatabase(true).delete(DbHelper.TABLE_NOTES, null, null)
        dbHelper.getDatabase(true).delete(DbHelper.TABLE_CATEGORY, null, null)
        dbHelper.getDatabase(true).delete(DbHelper.TABLE_ATTACHMENTS, null, null)
    }

    fun addTextNote(title: String, content: String): Note {
        TestLog.info("Adding text note to the database - Title: $title, Content: $content")
        val note = Note()
        note.title = title
        note.content = content
        val now = Calendar.getInstance().timeInMillis
        note.creation = now
        note.lastModification = now

        dbHelper.updateNote(note, false)

        EventBus.getDefault().post(NotesUpdatedEvent(listOf(note)))
        EventBus.getDefault().post(CategoriesUpdatedEvent())

        return note
    }

    fun Note.setCategoryToNote(category: Category) {
        TestLog.info("Setting category to note - Note: ${this.title}, Category: $category")
        createCategory(category)
        dbHelper.updateNote(dbHelper.getNote(this._id).apply { setCategory(category) }, true)
        EventBus.getDefault().post(CategoriesUpdatedEvent())
        EventBus.getDefault().post(NotesUpdatedEvent(listOf(this)))
    }

    private fun createCategory(category: Category) {
        TestLog.info("Creating cateogry in the database - Category: $category")
        category.name = "Cat"
        category.color = Color.BLUE.toString()
        category.description = "testing category"
        dbHelper.updateCategory(category)
    }

    fun Note.archiveNote() {
        TestLog.info("Archiving the note - Note: ${this.title}")
        dbHelper.archiveNote(this, true)
        EventBus.getDefault().post(NotesUpdatedEvent(listOf(this)))
        EventBus.getDefault().post(CategoriesUpdatedEvent())
    }
}
