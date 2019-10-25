package com.duode.lint.lint_rules.name.method

import com.android.tools.lint.client.api.UElementHandler
import com.android.tools.lint.detector.api.*
import com.duode.lint.lint_rules.base.CommonConst
import com.duode.lint.lint_rules.base.CommonLogUtils.lintLog
import com.duode.lint.lint_rules.base.CommonStringUtils
import org.jetbrains.uast.UElement
import org.jetbrains.uast.UMethod

/**
 * @Description: 对类中申明的方法名的检测
 * @Author: hekang
 * @CreateDate: 2019-10-23 15:58
 */
class MethodDetector : Detector(), Detector.UastScanner {
    companion object {

        private const val TAG = "MemberDetector"

        @JvmStatic
        val ISSUE = Issue.create(
            MethodConst.ISSUE_ID,
            MethodConst.ISSUE_BRIEF_DESCRIPTION,
            MethodConst.ISSUE_EXPLANATION,
            CommonConst.CATEGORY_CUSTOM,
            CommonConst.PRIORITY_SERIOUS,
            CommonConst.SEVERITY_CUSTOM,
            Implementation(MethodDetector::class.java, Scope.JAVA_FILE_SCOPE)
        )
    }

    override fun getApplicableUastTypes(): List<Class<out UElement>>? {
        return listOf(UMethod::class.java)
    }

    override fun createUastHandler(context: JavaContext): UElementHandler? {
        return MethodUastHandler(context)
    }

    private class MethodUastHandler(val ctx: JavaContext) : UElementHandler() {

        override fun visitMethod(node: UMethod) {
            val name = node.name
            //判断是否是方法调用,是否是构造方法
            if (node.isConstructor) {
                lintLog(TAG, "visitMethod--isConstructorCall:$name")
                return
            }

            if (!CommonStringUtils.checkLowerCamelCase(name)) {
                ctx.report(
                    ISSUE,
                    node,
                    ctx.getLocation(node),
                    MethodConst.ISSUE_EXPLANATION
                )
            }
        }
    }
}