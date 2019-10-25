package com.duode.lint.lint_rules.name.code_file

/**
 * @Description:有关类名检测的数据
 * @Author: hekang
 * @CreateDate: 2019-10-21 11:38
 */
object CodeFileConst {
    /**
     * 检测中issue的id
     * */
    const val ISSUE_ID = "CodeFileLint"

    /**
     * 检测中issue的简单描述
     * */
    const val ISSUE_BRIEF_DESCRIPTION = "You must use a uniform naming convention for class names'"

    /**
     * 检测中issue的详细描述
     * */
    const val ISSUE_EXPLANATION = "必须使用统一的命名规则命名类名"

    const val PACKAGE_NAME_RULE = "包名不能包含大写"
    const val CODEFILE_NAME_RULE = "文件名必须使用大驼峰式命名"
    const val CLASS_NAME_RULE = "非内部类，必须单独使用一个文件且命名和文件名一致"
}