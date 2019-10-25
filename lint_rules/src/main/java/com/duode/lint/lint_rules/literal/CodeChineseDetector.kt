package com.duode.lint.lint_rules.literal

import com.android.tools.lint.client.api.UElementHandler
import com.android.tools.lint.detector.api.*
import com.duode.lint.lint_rules.base.CommonConst
import com.duode.lint.lint_rules.base.CommonLogUtils.lintLog
import com.duode.lint.lint_rules.base.CommonStringUtils
import org.jetbrains.uast.UCallExpression
import org.jetbrains.uast.UElement
import org.jetbrains.uast.ULiteralExpression

/**
 * @Description: 检测代码中直接使用中文字符串的情况
 * 其实这里应该检测所有资源都应该不能直接使用文本，需要引用资源文件；
 * 但是由于不会有人直接使用英文，使用英文的时候一般都是引用资源文件了
 * @Author: hekang
 * @CreateDate: 2019-10-23 17:01
 */
class CodeChineseDetector : Detector(), Detector.UastScanner {

    companion object {

        private const val TAG = "CodeChineseDetector"

        @JvmStatic
        val ISSUE = Issue.create(
            CodeChineseConst.ISSUE_ID,
            CodeChineseConst.ISSUE_BRIEF_DESCRIPTION,
            CodeChineseConst.ISSUE_EXPLANATION,
            CommonConst.CATEGORY_CUSTOM,
            CommonConst.PRIORITY_SERIOUS,
            CommonConst.SEVERITY_CUSTOM,
            Implementation(
                CodeChineseDetector::class.java,
                Scope.JAVA_FILE_SCOPE
            )
        )
    }

    override fun getApplicableUastTypes(): List<Class<out UElement>>? {
        return listOf(ULiteralExpression::class.java)
    }

    override fun createUastHandler(context: JavaContext): UElementHandler? {
        return LiteralHandler(context)
    }

    /**
     * 检查code中的中文数据
     * */
    private class LiteralHandler(val ctx: JavaContext) : UElementHandler() {

        override fun visitLiteralExpression(node: ULiteralExpression) {
            val value = node.asSourceString()
            if (CommonStringUtils.hasChinese(value)) {
                lintLog(TAG, "visitLiteralExpression:$value")
                //需要过滤使用日志打印功能中产生的中文
                val parentElement = node.uastParent
                if (parentElement != null) {
                    if (parentElement is UCallExpression) {
                        val psiMethod = parentElement.resolve()
                        //由于日志方法我们限制死了，所以不会出现第一次调用不属于最终方法的情况
                        /*while (psiMethod == null) {//可能父类还不是最终调用的地方
                            parentElement = parentElement?.uastParent
                            if (parentElement != null) {
                                if (parentElement is UCallExpression) {
                                    parentElement.resolve()
                                } else {
                                    break
                                }
                            } else {
                                break
                            }
                        }*/
                        val isInLog = ctx.evaluator.isMemberInClass(
                            psiMethod,
                            CodeChineseConst.IGNORE_METHOD_CONTAINING_CLASS
                        )
                        if (isInLog) {
                            lintLog(TAG, "isInLog:$value")
                            return
                        }
                    }

                }

                ctx.report(
                    ISSUE,
                    node,
                    ctx.getLocation(node),
                    CodeChineseConst.ISSUE_EXPLANATION
                )

            }
        }

    }
}