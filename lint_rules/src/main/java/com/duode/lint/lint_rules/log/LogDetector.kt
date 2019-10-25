package com.duode.lint.lint_rules.log

import com.android.tools.lint.client.api.UElementHandler
import com.android.tools.lint.detector.api.*
import com.duode.lint.lint_rules.base.CommonConst
import com.duode.lint.lint_rules.base.CommonLogUtils.lintLog
import org.jetbrains.uast.UCallExpression
import org.jetbrains.uast.UElement
import org.jetbrains.uast.util.isMethodCall
import java.util.*

/**
 * @Description: 实现对打印日志的检测
 * @Author: hekang
 * @CreateDate: 2019-10-15 14:19
 */
class LogDetector : Detector(), Detector.UastScanner {

    companion object {

        private const val TAG = "LogDetector"

        @JvmStatic
        val ISSUE = Issue.create(
            LogConst.ISSUE_ID,//用来确定检测ISSUE的唯一标识
            LogConst.ISSUE_BRIEF_DESCRIPTION,//问题简单描述
            LogConst.ISSUE_EXPLANATION,//问题的详细描述
            CommonConst.CATEGORY_CUSTOM,//问题的类型，一般为问题种类（正确性、安全性等），这里使用自定义的
            CommonConst.PRIORITY_SERIOUS,//问题权重,范围[1,10],这个可以在执行Lint Task之后生成的报告中用于排序。
            CommonConst.SEVERITY_CUSTOM,//问题严重程度（崩溃、忽略、警告、错误）
            //实现，参数为处理实例和作用域
            Implementation(LogDetector::class.java, Scope.JAVA_FILE_SCOPE)
        )
    }

    /**
     * 决定了什么样的类型能够被检测到
     * 检测类型包括文本表达式、调用相关表达式以及类资源等
     *
     * 这里的检测调用的方法表达式
     * */
    override fun getApplicableUastTypes(): List<Class<out UElement>>? {
        return Collections.singletonList(UCallExpression::class.java)//返回所有可能是被调用的方法
    }


    /**
     * 创建UElementHandler
     * 这里的这个方法的实现，是由getApplicableUastTypes确定
     * */
    override fun createUastHandler(context: JavaContext): UElementHandler? {
        return LogHandler(context)
    }


    /**
     * 具体检测代码的实现
     * */
    private class LogHandler(val context: JavaContext) : UElementHandler() {

        /**
         * 这里重写的方法，是根据上面getApplicableUastTypes和createUastHandler指定的类型来重写的
         *
         * */
        override fun visitCallExpression(node: UCallExpression) {
            //判断是否是方法调用
            if (!node.isMethodCall()) {
                return
            }
            //TODO:2019年10月18日18:36:00 这里还存在引入其他的第三方日志工具输出日志的情况，暂时不处理
            @Suppress("DUPLICATE_LABEL_IN_WHEN")
            when (node.methodName) {
                //检测是否是Android方法输出日志
                LogConst.LOG_LEVEL_V_METHOD_NAME, LogConst.LOG_LEVEL_D_METHOD_NAME,
                LogConst.LOG_LEVEL_I_METHOD_NAME, LogConst.LOG_LEVEL_W_METHOD_NAME,
                LogConst.LOG_LEVEL_WTF_METHOD_NAME -> {
                    //对比类名，确认是Android的Log输出的日志
                    if (context.evaluator.isMemberInClass(
                            node.resolve(),
                            LogConst.LOG_ANDROID_CLASS_NAME
                        )
                    ) {
                        context.report(
                            ISSUE,
                            node,
                            context.getLocation(node),
                            LogConst.ISSUE_EXPLANATION
                        )
                    }
                }
                //检测是否是java的方法输出日志
                LogConst.SYSTEM_PRINTLN_METHOD_NAME,//这里报警告是因为它和LOG_LEVEL_CUSTOM_METHOD_NAME的值一样
                LogConst.SYSTEM_FLUSH_METHOD_NAME,
                LogConst.SYSTEM_CHECKERROR_METHOD_NAME -> {
                    lintLog(TAG, "visitCallExpression:${node.methodName}")
                    if (context.evaluator.isMemberInClass(
                            node.resolve(),
                            LogConst.LOG_JAVA_CLASS_NAME
                        )
                    ) {
                        context.report(
                            ISSUE,
                            node,
                            context.getLocation(node),
                            LogConst.ISSUE_EXPLANATION
                        )
                    }
                }
                //检测是否是方法名相同的情况下的，任意一种调用
                LogConst.SYSTEM_PRINT_METHOD_NAME,
                LogConst.LOG_LEVEL_CUSTOM_METHOD_NAME -> {
                    if (context.evaluator.isMemberInClass(
                            node.resolve(),
                            LogConst.LOG_JAVA_CLASS_NAME
                        ) || context.evaluator.isMemberInClass(
                            node.resolve(),
                            LogConst.LOG_ANDROID_CLASS_NAME
                        )
                    ) {
                        context.report(
                            ISSUE,
                            node,
                            context.getLocation(node),
                            LogConst.ISSUE_EXPLANATION
                        )
                    }
                }

            }

        }
    }
}
