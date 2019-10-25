package com.duode.lint.lint_rules.name.code_file

import com.android.tools.lint.client.api.UElementHandler
import com.android.tools.lint.detector.api.*
import com.duode.lint.lint_rules.base.CommonConst
import com.duode.lint.lint_rules.base.CommonStringUtils
import org.apache.http.util.TextUtils
import org.jetbrains.uast.UClass
import org.jetbrains.uast.UElement

/**
 * @Description: 对kt和java文件的所属包名和文件名以及类名的命名的检测
 * kt文件，文件名和类名可以不一致,但是我们的规则不容许这种情况存在
 * 以及java文件和kt文件都存在一个文件包含多个类的情况
 * @Author: hekang
 * @CreateDate: 2019-10-21 11:33
 */

class CodeFileDetector : Detector(), Detector.UastScanner {

    companion object {

        private const val TAG = "CodeFileDetector"

        @JvmStatic
        val ISSUE = Issue.create(
            CodeFileConst.ISSUE_ID,
            CodeFileConst.ISSUE_BRIEF_DESCRIPTION,
            CodeFileConst.ISSUE_EXPLANATION,
            CommonConst.CATEGORY_CUSTOM,
            CommonConst.PRIORITY_SERIOUS,
            CommonConst.SEVERITY_CUSTOM,
            Implementation(CodeFileDetector::class.java, Scope.JAVA_FILE_SCOPE)
        )
    }

    /**
     * 检查每个类的信息
     * */
    override fun getApplicableUastTypes(): List<Class<out UElement>>? {
        return listOf(UClass::class.java)
    }

    override fun createUastHandler(context: JavaContext): UElementHandler? {
        return CheckClassHandler(context)
    }

    private class CheckClassHandler(val context: JavaContext) : UElementHandler() {

        override fun visitClass(node: UClass) {
            //获取文件名
            var fileName = ""
            context.psiFile?.let {
                fileName = context.uastParser.getFile(it)?.name ?: ""
            }
            val classFullName = context.evaluator.getQualifiedName(node) ?: ""
            val packageName = context.evaluator.getPackage(node.javaPsi)?.qualifiedName ?: ""

            //确认每一个包名不含有大写
            val isRightPackageName = checkPackageName(packageName, node)

            //确认文件名使用大驼峰式命名
            val isRightCodeFileName =
                if (isRightPackageName) checkCodeFileName(fileName, node) else false

            //确认类名是否符合规范
            if (isRightCodeFileName) {
                checkClassName(classFullName, fileName, node)
            }

        }

        /**
         * 对类名进行校验
         * @return 返回true表示通过校验
         * */
        private fun checkClassName(
            classFullName: String,
            codeFileName: String,
            node: UClass
        ): Boolean {
            if (TextUtils.isEmpty(classFullName)) {
                //如果没有名字，暂时默认正常处理
                return true
            }
            val name = codeFileName.substring(0, codeFileName.indexOf("."))
            val className = classFullName.substring(classFullName.lastIndexOf(".") + 1)
            if (name == className) {
                return true
            }
            //先判断是否是内部类
            val superClassName = node.containingClass?.name ?: ""
            if (superClassName == name) {
                return true
            }
            context.report(
                ISSUE,
                node,
                context.getLocation(node.javaPsi),
                CodeFileConst.CLASS_NAME_RULE
            )
            return false
        }


        /**
         * 对kt和java文件名进行校验
         * @return 返回true表示通过校验
         * */
        private fun checkCodeFileName(codeFileName: String, node: UClass): Boolean {
            if (TextUtils.isEmpty(codeFileName)) {
                //如果没有名字，暂时默认正常处理
                return true
            }

            val name = codeFileName.substring(0, codeFileName.indexOf("."))
            if (!CommonStringUtils.checkUpperCamelCase(name)) {//大驼峰命名
                context.report(
                    ISSUE,
                    node,
                    context.getLocation(node.javaPsi),
                    CodeFileConst.CODEFILE_NAME_RULE
                )
                return false
            }
            return true
        }

        /**
         * 对包名进行校验
         * @return 返回true表示通过校验
         * */
        private fun checkPackageName(packageName: String, node: UClass): Boolean {
            var isRight = true
            if (TextUtils.isEmpty(packageName)) {
                //如果没有名字，暂时默认正常处理
                return isRight
            }
            packageName.split(".").map {
                if (CommonStringUtils.hasUpperCase(it)
                ) {
                    context.report(
                        ISSUE,
                        node,
                        context.getLocation(node.javaPsi),
                        CodeFileConst.PACKAGE_NAME_RULE
                    )
                    isRight = false
                    return@map
                }
            }

            return isRight
        }

    }
}