package com.duode.lint.lint_rules.others.condition

/**
 * @Description:
 * @Author: hekang
 * @CreateDate: 2020/5/15 16:11
 */
object IfStateConst {
    /**
     * 检测中issue的id
     * */
    const val ISSUE_ID = "IfStateLint"

    /**
     * 检测中issue的简单描述
     * */
    const val ISSUE_BRIEF_DESCRIPTION = "'if' expression cannot be used to remove '{}'"

    /**
     * 检测中issue的详细描述
     * */
    const val ISSUE_EXPLANATION = "`if`表达式使用时不能移除`{}`"
}