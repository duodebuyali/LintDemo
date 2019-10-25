package com.duode.lint.lint_rules.base


/**
 * @Description: 在lint检测中用来打印检测日志的方法
 * @Author: hekang
 * @CreateDate: 2019-10-23 18:43
 */
object CommonLogUtils {

    private val canLog = true


    public fun Any.lintLog(tag: String, msg: Any) {
        if (canLog) {
            println("$tag--$msg")
        }
    }

}