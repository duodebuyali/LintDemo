package com.duode.lint.lint_rules.name.member

/**
 * @Description: 关于成员变量的资源
 * @Author: hekang
 * @CreateDate: 2019-10-21 19:26
 */
object MemberConst {
    /**
     * log检测中issue的id
     * */
    const val ISSUE_ID = "MemberNameLint"

    /**
     * log检测中issue的简单描述
     * */
    const val ISSUE_BRIEF_DESCRIPTION = "You must use a uniform naming convention for member names'"

    /**
     * log检测中issue的详细描述
     * */
    const val ISSUE_EXPLANATION = "必须使用统一的命名规则命名成员变量"

    const val CONST_NAME_RULE = "常量必须全部使用大写"

    const val FILED_NAME_RULE = "成员变量必须使用小驼峰命名"
}