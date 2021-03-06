package com.duode.lint.lint_rules

import com.android.tools.lint.client.api.IssueRegistry
import com.android.tools.lint.detector.api.CURRENT_API
import com.android.tools.lint.detector.api.Issue
import com.duode.lint.lint_rules.literal.CodeChineseDetector
import com.duode.lint.lint_rules.literal.ResourceLiteralDetector
import com.duode.lint.lint_rules.log.LogDetector
import com.duode.lint.lint_rules.name.code_file.CodeFileDetector
import com.duode.lint.lint_rules.name.code_file.FileHeadDetector
import com.duode.lint.lint_rules.name.member.MemberDetector
import com.duode.lint.lint_rules.name.method.MethodDetector
import com.duode.lint.lint_rules.name.widget.WidgetIdDetector
import com.duode.lint.lint_rules.others.condition.IfStateDetector
import com.duode.lint.lint_rules.toast.ToastDetector

/**
 * @Description:用来注册自定义的lint规则，提供给lint仓库使用
 * @Author: hekang
 * @CreateDate: 2019-10-15 11:13
 */
@Suppress("UnstableApiUsage")
class CustomIssueRegistry : IssueRegistry() {

    /**
     * 需要检测的lint规则
     * */
    override val issues: List<Issue>
        get() = listOf(
            LogDetector.ISSUE,
            ToastDetector.ISSUE,
            WidgetIdDetector.ISSUE,
            CodeFileDetector.ISSUE,
            MemberDetector.ISSUE,
            MethodDetector.ISSUE,
            CodeChineseDetector.ISSUE,
            ResourceLiteralDetector.ISSUE,
//            ImportStateDetector.ISSUE,
            IfStateDetector.ISSUE,
            FileHeadDetector.ISSUE
        )

    override val api: Int
        get() = CURRENT_API
}