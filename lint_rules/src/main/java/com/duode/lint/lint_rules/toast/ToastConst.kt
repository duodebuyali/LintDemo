package com.duode.lint.lint_rules.toast

/**
 * @Description: 储存有关toast检测的数据
 * @Author: hekang
 * @CreateDate: 2019-10-18 18:39
 */
object ToastConst {
    /**
     * log检测中issue的id
     * */
    const val ISSUE_ID = "ToastLint"

    /**
     * log检测中issue的简单描述
     * */
    const val ISSUE_BRIEF_DESCRIPTION = "Must use unity of 'ToastUtils'"

    /**
     * log检测中issue的详细描述
     * */
    const val ISSUE_EXPLANATION = "请使用统一的ToastUtils处理toast事件"


    const val METHOD_NAME = "makeText"

    const val CLASS_NAME = "android.widget.Toast"


}