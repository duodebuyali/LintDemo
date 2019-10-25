package com.duode.lint.lint_rules.literal

/**
 * @Description: 检测字符串的常量数据
 * @Author: hekang
 * @CreateDate: 2019-10-23 17:02
 */
object CodeChineseConst {

    /**
     * 检测中issue的id
     * */
    const val ISSUE_ID = "CodeLiteralChineseLint"

    /**
     * 检测中issue的简单描述
     * */
    const val ISSUE_BRIEF_DESCRIPTION = "You cannot use Chinese in your code"

    /**
     * 检测中issue的详细描述
     * */
    const val ISSUE_EXPLANATION = "除打印日志功能外，不能在代码中直接使用中文字符串"


    /**
     * 即使有中文也不报错的方法所在的类名
     * TODO: 2019年10月24日09:55:07 这里需要替换为 日志库中封装好的类
     * */
    const val IGNORE_METHOD_CONTAINING_CLASS = "android.util.Log"
}