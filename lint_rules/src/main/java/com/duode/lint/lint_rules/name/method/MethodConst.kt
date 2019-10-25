package com.duode.lint.lint_rules.name.method

/**
 * @Description:对类中申明的方法名的资源申明
 * @Author: hekang
 * @CreateDate: 2019-10-23 15:57
 */
object MethodConst {
    /**
     * 检测中issue的id
     * */
    const val ISSUE_ID = "MethodNameLint"

    /**
     * 检测中issue的简单描述
     * */
    const val ISSUE_BRIEF_DESCRIPTION = "You must use a uniform naming convention for method names'"

    /**
     * 检测中issue的详细描述
     * */
    const val ISSUE_EXPLANATION = "必须使用统一的命名规则命名申明的方法"

}