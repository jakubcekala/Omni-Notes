package it.feio.android.omninotes.espresso.suites

import it.feio.android.omninotes.espresso.tests.*
import org.junit.runner.RunWith
import org.junit.runners.Suite

@RunWith(Suite::class)
@Suite.SuiteClasses(
    NotesArchivingTest::class,
    NotesCreatingTest::class,
    NotesViewingTest::class,
    NotesRemovingTest::class
)
class RegressionSuite