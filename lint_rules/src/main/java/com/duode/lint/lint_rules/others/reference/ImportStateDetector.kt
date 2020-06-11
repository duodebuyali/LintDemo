package com.duode.lint.lint_rules.others.reference

import com.android.tools.lint.client.api.UElementHandler
import com.android.tools.lint.detector.api.*
import com.duode.lint.lint_rules.base.CommonConst
import com.duode.lint.lint_rules.base.CommonLogUtils.lintLog
import com.intellij.psi.PsiClass
import org.jetbrains.uast.UElement
import org.jetbrains.uast.UImportStatement
import org.jetbrains.uast.UPrefixExpression

/**
 * @Description: 对每个引用包的状态检测
 * @Author: hekang
 * @CreateDate: 2020/5/13 15:24
 */
@Suppress("UnstableApiUsage")
class ImportStateDetector : Detector(), SourceCodeScanner {

    companion object {

        private const val TAG = "ImportStateDetector"

        @JvmStatic
        val ISSUE = Issue.create(
            ImportStateConst.ISSUE_ID,
            ImportStateConst.ISSUE_BRIEF_DESCRIPTION,
            ImportStateConst.ISSUE_EXPLANATION,
            CommonConst.CATEGORY_CUSTOM,
            CommonConst.PRIORITY_SERIOUS,
            CommonConst.SEVERITY_CUSTOM,
            Implementation(ImportStateDetector::class.java, Scope.JAVA_FILE_SCOPE)
        )
    }

    override fun getApplicableUastTypes(): List<Class<out UElement>>? {
        return listOf(
            UImportStatement::class.java,
            UPrefixExpression::class.java
        )
    }

    override fun createUastHandler(context: JavaContext): UElementHandler? {
        return ImportVisitor(context)
    }

    private class ImportVisitor(private val context: JavaContext) : UElementHandler() {

        override fun visitPrefixExpression(node: UPrefixExpression) {
            lintLog(
                TAG,
                "visitPrefixExpression:${node.asRenderString()}"
            )
        }

        override fun visitImportStatement(node: UImportStatement) {
            val resolved = node.resolve()

            context.evaluator.getPackage(node)

            if (resolved is PsiClass) {

                val qualifiedName = resolved.qualifiedName
                lintLog(
                    TAG,
                    "qualifiedName:$qualifiedName"
                )
                if ("android.R" == qualifiedName) {
                    val location =
                        context.getLocation(node)
                    context.report(
                        ISSUE,
                        node,
                        location, ImportStateConst.ISSUE_BRIEF_DESCRIPTION
                    )
                }
            }
        }

    }
}