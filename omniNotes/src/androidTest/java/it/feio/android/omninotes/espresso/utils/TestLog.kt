package it.feio.android.omninotes.espresso.utils

import android.util.Log
const val TAG = "AUTOMATION"

object TestLog {

    fun step(message: String) {
        Log.i(TAG, "[STEP] $message")
    }

    fun info(message: String) {
        Log.i(TAG, "[INFO] $message")
    }

    private fun debug(message: String) {
        Log.d(TAG, "[DEBUG] $message")
    }

    private fun error(message: String) {
        Log.e(TAG, "[ERROR] $message")
    }

    fun errorStack(ex: Throwable) {
        var cause: Throwable? = null
        var result: Throwable? = ex
        error(parseExceptionMessage(ex))
        for (trace in ex.stackTrace) {
            debug(trace.toString())
        }
        while (null != result!!.cause.also { cause = it } && result !== cause) {
            result = cause
            Log.e(TAG, "[CAUSED BY] " + cause?.let { parseExceptionMessage(it) })
            for (trace in cause!!.stackTrace) {
                debug("[stacktrace_cause] $trace")
            }
        }
    }

    private fun parseExceptionMessage(ex: Throwable): String {
        val message = ex.stackTraceToString();
        if (message.contains("View Hierarchy")) {
            return ex.message?.split("View Hierarchy")!![0]
        }
        return message;
    }
}
