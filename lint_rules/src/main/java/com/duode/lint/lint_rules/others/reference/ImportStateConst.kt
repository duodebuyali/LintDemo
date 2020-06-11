package com.duode.lint.lint_rules.others.reference

/**
 * @Description: 导包引用的常量
 * @Author: hekang
 * @CreateDate: 2020/5/13 15:33
 */
object ImportStateConst {
    /**
     * 检测中issue的id
     * */
    const val ISSUE_ID = "ImportStateLint"

    /**
     * 检测中issue的简单描述
     * */
    const val ISSUE_BRIEF_DESCRIPTION = "You need to remove unnecessary import"

    /**
     * 检测中issue的详细描述
     * */
    const val ISSUE_EXPLANATION = "需要移除没有必要的导包"

}