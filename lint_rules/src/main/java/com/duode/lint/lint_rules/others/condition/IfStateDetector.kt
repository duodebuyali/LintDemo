package com.duode.lint.lint_rules.others.condition

import com.android.tools.lint.client.api.UElementHandler
import com.android.tools.lint.detector.api.*
import com.duode.lint.lint_rules.base.CommonConst
import com.duode.lint.lint_rules.base.CommonLogUtils.lintLog
import com.duode.lint.lint_rules.others.reference.ImportStateDetector
import org.jetbrains.uast.UElement
import org.jetbrains.uast.UIfExpression

/**
 * @Description: 检测if 表达式的写法
 * @Author: hekang
 * @CreateDate: 2020/5/15 16:11
 */
@Suppress("UnstableApiUsage")
class IfStateDetector : Detector(), SourceCodeScanner {

    companion object {

        private const val TAG = "IfStateDetector"

        @JvmStatic
        val ISSUE = Issue.create(
            IfStateConst.ISSUE_ID,
            IfStateConst.ISSUE_BRIEF_DESCRIPTION,
            IfStateConst.ISSUE_EXPLANATION,
            CommonConst.CATEGORY_CUSTOM,
            CommonConst.PRIORITY_SERIOUS,
            CommonConst.SEVERITY_CUSTOM,
            Implementation(ImportStateDetector::class.java, Scope.JAVA_FILE_SCOPE)
        )
    }

    override fun getApplicableUastTypes(): List<Class<out UElement>>? {
        return listOf(UIfExpression::class.java)
    }

    override fun createUastHandler(context: JavaContext): UElementHandler? {
        return IfVisitor(context)
    }

    private class IfVisitor(private val context: JavaContext) : UElementHandler() {

        override fun visitIfExpression(node: UIfExpression) {
            lintLog(
                TAG,
                "visitIfExpression:${node.asRenderString()}"
            )
        }
    }
}