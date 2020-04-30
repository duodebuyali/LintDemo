package com.duode.lint.lint_rules.name.code_file

import com.android.tools.lint.client.api.UElementHandler
import com.android.tools.lint.detector.api.*
import com.duode.lint.lint_rules.base.CommonConst
import org.apache.http.util.TextUtils
import org.jetbrains.uast.UClass
import org.jetbrains.uast.UElement

/**
 * @Description: 文件头部的说明检测
 * @Author: hekang
 * @CreateDate: 2020/4/22 18:25
 */
@Suppress("UnstableApiUsage")
class FileHeadDetector : Detector(), Detector.UastScanner {

    companion object {

        private const val TAG = "FileHeadDetector"

        @JvmStatic
        val ISSUE = Issue.create(
            FileHeadConst.ISSUE_ID,
            FileHeadConst.ISSUE_BRIEF_DESCRIPTION,
            FileHeadConst.ISSUE_EXPLANATION,
            CommonConst.CATEGORY_CUSTOM,
            CommonConst.PRIORITY_SERIOUS,
            CommonConst.SEVERITY_CUSTOM,
            Implementation(FileHeadDetector::class.java, Scope.JAVA_FILE_SCOPE)
        )
    }

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
//            val packageName = context.evaluator.getPackage(node.javaPsi)?.qualifiedName ?: ""

            if (TextUtils.isEmpty(classFullName)) {
                //如果没有名字，暂时默认正常处理
                return
            }
            val name = fileName.substring(0, fileName.indexOf("."))
            val className = classFullName.substring(classFullName.lastIndexOf(".") + 1)
            if (name == className) {//确认是顶层类
                //顶层类的数据
                val superClassText = node.text

                val isStartWithDescriptionHead = superClassText.startsWith(
                    "/**\n" +
                            " * @Description:"
                )
                val isContainAuthorHead = superClassText.contains(" * @Author:")
                val isContainDateHead = superClassText.contains(" * @CreateDate:")
                if (isStartWithDescriptionHead && isContainAuthorHead && isContainDateHead) {
                    return
                }

                context.report(
                    FileHeadDetector.ISSUE,
                    node,
                    context.getLocation(node.javaPsi),
                    FileHeadConst.ISSUE_EXPLANATION
                )
            }
            //TODO: 2020年04月22日19:31:38 是否对内部类也进行文件头检测

        }
    }

}