package it.feio.android.omninotes.espresso.utils

import android.util.Log
import androidx.test.platform.app.InstrumentationRegistry.getInstrumentation
import androidx.test.uiautomator.UiDevice
import org.junit.rules.TestWatcher
import org.junit.runner.Description
import java.io.File
import java.io.IOException

class ScreenshotTestRule : TestWatcher() {

    override fun starting(description: Description) {
        TestLog.info("Test is starting - ${description.displayName}")
        super.starting(description)
    }

    override fun finished(description: Description) {
        super.finished(description)
        TestLog.info("Test is finished - ${description.displayName}")
    }

    override fun failed(e: Throwable?, description: Description) {
        val parentFolderPath = "/sdcard/Pictures/omniNotes/screenshots/failures/${description.className}"
        takeScreenshot(parentFolderPath = parentFolderPath, screenshotName = description.methodName + ".jpg")
        e?.let { TestLog.errorStack(it) }
    }

    private fun takeScreenshot(parentFolderPath: String = "", screenshotName: String) {
        Log.d("Screenshots", "Taking screenshot of '$screenshotName'")
        val device = UiDevice.getInstance(getInstrumentation())
        val file = File(parentFolderPath, screenshotName)
        file.parentFile!!.mkdirs()
        try {
            device.takeScreenshot(file)
            Log.d("Screenshots", "Screenshot taken")
        } catch (ex: IOException) {
            Log.e("Screenshots", "Could not take the screenshot", ex)
        }
    }
}
