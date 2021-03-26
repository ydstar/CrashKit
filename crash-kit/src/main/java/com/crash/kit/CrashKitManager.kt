package com.crash.kit

import com.crash.kit.util.ActivityManager
import com.crash.kit.util.AppGlobals
import java.io.File

/**
 * 捕获java层的crash,然后存到本地文件中
 */
object CrashKitManager {
    private const val CRASH_DIR_JAVA = "java_crash"

    fun init() {
        ActivityManager.instance.init(AppGlobals.get()!!)
        val javaCrashDir = getJavaCrashDir()
        CrashHandler.init(javaCrashDir.absolutePath)
    }

    private fun getJavaCrashDir(): File {
        val javaCrashFile = File(AppGlobals.get()!!.cacheDir, CRASH_DIR_JAVA)
        if (!javaCrashFile.exists()) {
            javaCrashFile.mkdirs()
        }
        return javaCrashFile
    }

    /**
     * 获取所有的崩溃日志
     */
    fun crashFiles(): Array<File> {
        return getJavaCrashDir().listFiles()
    }

    /**
     * 删除崩溃日志
     */
    fun deleteFiles() {
        //删除java层崩溃日志
        val javaCrashFile = File(AppGlobals.get()!!.cacheDir, CRASH_DIR_JAVA)
        if (javaCrashFile.exists()) {
            if (javaCrashFile.isDirectory) {
                val listFiles = javaCrashFile.listFiles()
                for (file in listFiles) {
                    file.delete()
                }
            } else {
                javaCrashFile.delete()
            }
        }
    }
}