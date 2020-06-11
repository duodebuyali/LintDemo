package com.duode.lint.lint_rules.toast

import com.android.tools.lint.detector.api.*
import com.duode.lint.lint_rules.base.CommonConst
import com.duode.lint.lint_rules.base.CommonLogUtils.lintLog
import com.intellij.psi.PsiMethod
import org.jetbrains.uast.UCallExpression

/**
 * @Description: 检测项目中的 toast用法
 * @Author: hekang
 * @CreateDate: 2019-10-18 18:37
 */
@Suppress("UnstableApiUsage")
class ToastDetector : Detector(), Detector.UastScanner {

    companion object {

        private const val TAG = "ToastDetector"

        @JvmStatic
        val ISSUE = Issue.create(
            ToastConst.ISSUE_ID,
            ToastConst.ISSUE_BRIEF_DESCRIPTION,
            ToastConst.ISSUE_EXPLANATION,
            CommonConst.CATEGORY_CUSTOM,
            CommonConst.PRIORITY_SERIOUS,
            CommonConst.SEVERITY_CUSTOM,
            Implementation(ToastDetector::class.java, Scope.JAVA_FILE_SCOPE)
        )
    }

    override fun getApplicableMethodNames(): List<String>? {
        return listOf(ToastConst.METHOD_NAME)
    }

    override fun visitMethodCall(context: JavaContext, node: UCallExpression, method: PsiMethod) {
        lintLog(TAG, "method.name:${method.name}")

        if (method.name == ToastConst.METHOD_NAME) {
            if (context.evaluator.isMemberInClass(
                    node.resolve(),
                    ToastConst.CLASS_NAME
                )
            ) {
                context.report(
                    ISSUE,
                    node,
                    context.getLocation(node),
                    ToastConst.ISSUE_EXPLANATION
                )
            }
        }

    }
}