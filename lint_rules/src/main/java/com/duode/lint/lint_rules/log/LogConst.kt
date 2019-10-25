package com.duode.lint.lint_rules.log

/**
 * @Description: 存储有关log检测相关的常量数据
 * @Author: hekang
 * @CreateDate: 2019-10-15 14:17
 */
object LogConst {

    /**
     * log检测中issue的id
     * */
    const val ISSUE_ID = "LogLint"

    /**
     * log检测中issue的简单描述
     * */
    const val ISSUE_BRIEF_DESCRIPTION = "Must use unity of 'LogUtils'"

    /**
     * log检测中issue的详细描述
     * */
    const val ISSUE_EXPLANATION = "请使用统一的LogUtils处理日志事件"


    /**
     * 以下是有关使用android系统Log工具打印日志时的方法名
     * "v", "d", "i", "w", "e", "wtf"
     * 以及一个可以自定义日志级别的方法"println"
     * */
    const val LOG_LEVEL_V_METHOD_NAME: String = "v"
    const val LOG_LEVEL_D_METHOD_NAME: String = "d"
    const val LOG_LEVEL_I_METHOD_NAME: String = "i"
    const val LOG_LEVEL_W_METHOD_NAME: String = "w"
    const val LOG_LEVEL_WTF_METHOD_NAME: String = "wtf"

    const val LOG_LEVEL_CUSTOM_METHOD_NAME: String = "println"

    /**
     * Android 日志打印方法的完整类名
     * */
    const val LOG_ANDROID_CLASS_NAME = "android.util.Log"

    /**
     * 以下是有关java打印日志的方法
     * System.out.print;System.out.println
     * System.err.print;System.err.println
     * System.err.flush;System.err.checkError
     * */
    const val SYSTEM_PRINT_METHOD_NAME = "print"
    const val SYSTEM_PRINTLN_METHOD_NAME = "println"
    const val SYSTEM_FLUSH_METHOD_NAME = "flush"
    const val SYSTEM_CHECKERROR_METHOD_NAME = "checkError"

    /**
     * JAVA 日志打印方法的完整类名
     * */
    const val LOG_JAVA_CLASS_NAME = "java.io.PrintStream"

}