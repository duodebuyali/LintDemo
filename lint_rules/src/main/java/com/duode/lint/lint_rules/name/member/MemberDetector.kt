package com.duode.lint.lint_rules.name.member

import com.android.tools.lint.client.api.UElementHandler
import com.android.tools.lint.detector.api.*
import com.duode.lint.lint_rules.base.CommonConst
import com.duode.lint.lint_rules.base.CommonLogUtils.lintLog
import com.duode.lint.lint_rules.base.CommonStringUtils
import org.jetbrains.uast.UElement
import org.jetbrains.uast.UField

/**
 * @Description: 对成员变量的检测
 * 这里暂时只对命名进行检测，后续考虑加上访问权限(private,default,protect,public)的检测
 * @Author: hekang
 * @CreateDate: 2019-10-22 16:09
 */
class MemberDetector : Detector(), Detector.UastScanner {

    companion object {

        private const val TAG = "MemberDetector"

        @JvmStatic
        val ISSUE = Issue.create(
            MemberConst.ISSUE_ID,
            MemberConst.ISSUE_BRIEF_DESCRIPTION,
            MemberConst.ISSUE_EXPLANATION,
            CommonConst.CATEGORY_CUSTOM,
            CommonConst.PRIORITY_SERIOUS,
            CommonConst.SEVERITY_CUSTOM,
            Implementation(MemberDetector::class.java, Scope.JAVA_FILE_SCOPE)
        )
    }


    /**
     * 检查每个类的信息
     * */
    override fun getApplicableUastTypes(): List<Class<out UElement>>? {
        return listOf(UField::class.java)
    }

    override fun createUastHandler(context: JavaContext): UElementHandler? {
        return CheckMemberHandler(context)
    }

    private class CheckMemberHandler(val context: JavaContext) : UElementHandler() {

        override fun visitField(node: UField) {
            //是否是常量
            val isConst = node.isFinal && node.isStatic
            val isTAG = CommonConst.TAG_NAME == node.name
            //FIXME: 2019年10月23日14:36:58 由于kt中object类中，所有filed都会含有final和static,暂时不校验这个
            val memberName = node.name

            if (isConst || isTAG) {
                val constName = memberName.replace("_", "")
                if (CommonStringUtils.hasLowerCase(constName)) {
                    lintLog(TAG, "isConst:${node.asSourceString()};memberName:$memberName")
                    context.report(
                        ISSUE,
                        node,
                        context.getLocation(node),
                        MemberConst.CONST_NAME_RULE
                    )
                }
                return
            }

            if (!CommonStringUtils.checkLowerCamelCase(memberName)) {
                lintLog(TAG, "checkLowerCamelCase:${node.asSourceString()};memberName:$memberName")
                context.report(
                    ISSUE,
                    node,
                    context.getLocation(node),
                    MemberConst.FILED_NAME_RULE
                )
            }
        }
    }
}