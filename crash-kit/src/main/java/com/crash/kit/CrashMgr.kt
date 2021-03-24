package com.crash.kit

import com.crash.kit.util.ActivityManager
import com.crash.kit.util.AppGlobals
import org.devio.`as`.proj.libbreakpad.NativeCrashHandler
import java.io.File

/**
 * 捕获java层和native层的crash,然后存到本地文件中
 */
object CrashMgr {
    private const val CRASH_DIR_JAVA = "java_crash"
    private const val CRASH_DIR_NATIVE = "native_crash"

    fun init() {
        ActivityManager.instance.init(AppGlobals.get()!!)

        val javaCrashDir = getJavaCrashDir()
        val nativeCrashDir = getNativeCrashDir()

        CrashHandler.init(javaCrashDir.absolutePath)
        NativeCrashHandler.init(nativeCrashDir.absolutePath)
    }

    private fun getJavaCrashDir(): File {
        val javaCrashFile = File(AppGlobals.get()!!.cacheDir, CRASH_DIR_JAVA)
        if (!javaCrashFile.exists()) {
            javaCrashFile.mkdirs()
        }
        return javaCrashFile
    }

    private fun getNativeCrashDir(): File {
        val nativeCrashFile = File(AppGlobals.get()!!.cacheDir, CRASH_DIR_NATIVE)
        if (!nativeCrashFile.exists()) {
            nativeCrashFile.mkdirs()
        }
        return nativeCrashFile
    }


    /**
     * 获取所有的崩溃日志
     */
    fun crashFiles(): Array<File> {
        return getJavaCrashDir().listFiles() + getNativeCrashDir().listFiles()
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
        //删除native层崩溃日志
        val nativeCrashFile = File(AppGlobals.get()!!.cacheDir, CRASH_DIR_NATIVE)
        if (nativeCrashFile.exists()) {
            if (nativeCrashFile.isDirectory) {
                val listFiles = nativeCrashFile.listFiles()
                for (file in listFiles) {
                    file.delete()
                }
            } else {
                nativeCrashFile.delete()
            }
        }
    }
}