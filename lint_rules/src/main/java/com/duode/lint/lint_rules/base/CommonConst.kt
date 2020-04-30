package com.duode.lint.lint_rules.base

import com.android.tools.lint.detector.api.Category
import com.android.tools.lint.detector.api.Severity

/**
 * @Description: 用来放置项目中需要共同使用的数据
 * @Author: hekang
 * @CreateDate: 2019-10-15 11:21
 */
object CommonConst {

    /**
     * 自定义Issue的种类，方便查看
     * */
    @JvmStatic
    val CATEGORY_CUSTOM = Category.create("Custom Category", 105)

    /**
     * 自定义权重，方便排序
     * 暂时分为三个等级：轻微、普通和严重
     */
    const val PRIORITY_SLIGHT = 8
    const val PRIORITY_NORMAL = 9
    const val PRIORITY_SERIOUS = 10

    /**
     * 暂时设置所有自定义的lint检测的严重程度都是崩溃级别
     * */
    @JvmStatic
    val SEVERITY_CUSTOM = Severity.FATAL


    /**
     * 一般用这个来最为日志的tag，虽然不是常量但是以其命名方式命名
     * */
    const val TAG_NAME = "TAG"

    /**
     * kotlin语言的id
     * */
    const val LANGUAGE_KOTLIN_ID = "kotlin"

    /**
     * 常量的前缀描述和注解
     * */
    const val CONSTANT_PREFIX = "const"
    const val CONSTANT_ANNOTATION = "@JvmStatic"
}