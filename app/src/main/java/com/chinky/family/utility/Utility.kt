package com.chinky.family.utility

object Utility {
    private const val TAG: String = "ApplicationPackageLog"
    fun <T> printLogCat(klass: Class<T>, element: StackTraceElement?, message: String) {
        element?.let { e ->
            println("$TAG :: ${klass.simpleName} :: ${e.lineNumber} :: ${e.methodName} :: $message")
        }
    }
}