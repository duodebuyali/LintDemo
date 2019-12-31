package com.duode.lint.lint_rules.name.widget

import com.android.SdkConstants
import com.android.resources.ResourceFolderType
import com.android.tools.lint.detector.api.*
import com.duode.lint.lint_rules.base.CommonConst
import org.w3c.dom.Attr

/**
 * @Description: 对控件id的命名检测
 * @Author: hekang
 * @CreateDate: 2019-10-18 19:38
 */
class WidgetIdDetector : Detector(), XmlScanner {

    companion object {

        private const val TAG = "WidgetIdDetector"

        @JvmStatic
        val ISSUE = Issue.create(
            WidgetIdConst.ISSUE_ID,
            WidgetIdConst.ISSUE_BRIEF_DESCRIPTION,
            WidgetIdConst.ISSUE_EXPLANATION,
            CommonConst.CATEGORY_CUSTOM,
            CommonConst.PRIORITY_SERIOUS,
            CommonConst.SEVERITY_CUSTOM,
            /**
             * 检测的实现以及检测的范围
             * Scope.RESOURCE_FILE_SCOPE 检测资源文件的每一个文件
             * Scope.RESOURCE_FOLDER_SCOPE 检测资源文件夹
             * ALL_RESOURCES_SCOPE 所有资源类文件以及文件夹
             * */
            Implementation(WidgetIdDetector::class.java, Scope.RESOURCE_FILE_SCOPE)
        )
    }


    /**
     * 申明要检测的资源文件的类型
     * 这里只检测 layout目录下的
     * */
    override fun appliesTo(folderType: ResourceFolderType): Boolean {
        return ResourceFolderType.LAYOUT == folderType
    }

    /**
     * 申明要检测的资源文件中的类型
     * @see SdkConstants.ATTR_ID
     * @see SdkConstants.ATTR_TEXT
     *
     * 这里只检测控件的命名
     * */
    override fun getApplicableAttributes(): Collection<String>? {
        return listOf(SdkConstants.ATTR_ID)
    }

    override fun visitAttribute(context: XmlContext, attribute: Attr) {
        super.visitAttribute(context, attribute)
        val prnMain = context.mainProject.dir.path
        val prnCur = context.project.dir.path

        /**
         * 只关心有关id的数据
         * 过滤掉build文件目录下的数据干扰
         * */
        if (attribute.name.startsWith(WidgetIdConst.ANDROID_ID) && prnMain == prnCur) {
            checkNameSpace(context, attribute)
        }
    }

    private fun checkNameSpace(context: XmlContext, attribute: Attr) {
        //获取当前控价的类型
        val tagName = attribute.ownerElement.tagName
        var startIndex = 0
        //移除命名时@+id/test_title的前缀
        val idName = attribute.value.substring(5)
        var isEndWith = true
        when (tagName) {
            WidgetIdConst.LINEARLAYOUT_ID -> {
                isEndWith = idName.endsWith(WidgetIdConst.LINEARLAYOUT_SHORT_NAME)
            }
            WidgetIdConst.RELATIVELAYOUT_ID -> {
                isEndWith = idName.endsWith(WidgetIdConst.RELATIVELAYOUT_SHORT_NAME)
            }
            WidgetIdConst.FRAMELAYOUT_ID -> {
                isEndWith = idName.endsWith(WidgetIdConst.FRAMELAYOUT_SHORT_NAME)
            }
            WidgetIdConst.TABLAYOUT_ID -> {
                isEndWith = idName.endsWith(WidgetIdConst.TABLAYOUT_SHORT_NAME)
            }

            WidgetIdConst.BUTTON_ID -> {
                isEndWith = idName.endsWith(WidgetIdConst.BUTTON_SHORT_NAME)
            }
            WidgetIdConst.IMAGEBUTTON_ID -> {
                isEndWith = idName.endsWith(WidgetIdConst.IMAGEBUTTON_SHORT_NAME)
            }
            WidgetIdConst.RADIOBUTTON_ID -> {
                isEndWith = idName.endsWith(WidgetIdConst.RADIOBUTTON_SHORT_NAME)
            }

            WidgetIdConst.TEXTVIEW_ID -> {
                isEndWith = idName.endsWith(WidgetIdConst.TEXTVIEW_SHORT_NAME)
            }
            WidgetIdConst.EDITTEXT_ID -> {
                isEndWith = idName.endsWith(WidgetIdConst.EDITTEXT_SHORT_NAME)
            }

            WidgetIdConst.IMAGEVIEW_ID -> {
                isEndWith = idName.endsWith(WidgetIdConst.IMAGEVIEW_SHORT_NAME)
            }

            WidgetIdConst.WEBVIEW_ID -> {
                isEndWith = idName.endsWith(WidgetIdConst.WEBVIEW_SHORT_NAME)
            }

            WidgetIdConst.CHECKBOX_ID -> {
                isEndWith = idName.endsWith(WidgetIdConst.CHECKBOX_SHORT_NAME)
            }

            WidgetIdConst.PROGRESSBAR_ID -> {
                isEndWith = idName.endsWith(WidgetIdConst.PROGRESSBAR_SHORT_NAME)
            }
            WidgetIdConst.SEEKBAR_ID -> {
                isEndWith = idName.endsWith(WidgetIdConst.SEEKBAR_SHORT_NAME)
            }

            WidgetIdConst.VIEW_ID -> {
                isEndWith = idName.endsWith(WidgetIdConst.VIEW_SHORT_NAME)
            }

            WidgetIdConst.SPACE_ID -> {
                isEndWith = idName.endsWith(WidgetIdConst.SPACE_SHORT_NAME)
            }

            WidgetIdConst.SCROLLVIEW_ID -> {
                isEndWith = idName.endsWith(WidgetIdConst.SCROLLVIEW_SHORT_NAME)
            }
            WidgetIdConst.HORIZONTALSCROLLVIEW -> {
                isEndWith = idName.endsWith(WidgetIdConst.HORIZONTALSCROLLVIEW_SHORT_NAME)
            }

            WidgetIdConst.RECYCLERVIEW_ID -> {
                isEndWith = idName.endsWith(WidgetIdConst.RECYCLERVIEW_SHORT_NAME)
            }

            /**
             * 这里是否考虑，限制不让使用以下的滑动控件
             * */
            WidgetIdConst.LISTVIEW_ID -> {
                isEndWith = idName.endsWith(WidgetIdConst.LISTVIEW_SHORT_NAME)
            }
            WidgetIdConst.GRIDVIEW_ID -> {
                isEndWith = idName.endsWith(WidgetIdConst.GRIDVIEW_SHORT_NAME)
            }
            WidgetIdConst.EXPANDABLELISTVIEW_ID -> {
                isEndWith = idName.endsWith(WidgetIdConst.EXPANDABLELISTVIEW_SHORT_NAME)
            }

            else -> {//存在一些使用较少的控件，没有规范的控件，默认一直符合规范
                isEndWith = true
            }
        }

        if (!isEndWith) {
            context.report(
                ISSUE,
                attribute,
                context.getLocation(attribute),
                WidgetIdConst.ISSUE_EXPLANATION
            )
        }
    }

}