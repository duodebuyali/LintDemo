package com.duode.lint.lint_rules.name.code_file

/**
 * @Description:
 * @Author: hekang
 * @CreateDate: 2020/4/22 18:31
 */
object FileHeadConst {
    /**
     * 检测中issue的id
     * */
    const val ISSUE_ID = "FileHeadLint"

    /**
     * 检测中issue的简单描述
     * */
    const val ISSUE_BRIEF_DESCRIPTION = "You must add a uniform header for each file'"

    /**
     * 检测中issue的详细描述
     * */
    const val ISSUE_EXPLANATION = "必须给每一个文件添加统一的头部说明"
}