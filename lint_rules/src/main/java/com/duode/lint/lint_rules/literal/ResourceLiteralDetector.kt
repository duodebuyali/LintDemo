package com.duode.lint.lint_rules.literal

import com.android.SdkConstants
import com.android.resources.ResourceFolderType
import com.android.tools.lint.detector.api.*
import com.duode.lint.lint_rules.base.CommonConst
import org.apache.http.util.TextUtils
import org.w3c.dom.Attr

/**
 * @Description: 查询资源文件中的text,color 等资源的使用情况，是否是引用数据，而不是直接写死
 * @Author: hekang
 * @CreateDate: 2019-10-24 09:57
 */
class ResourceLiteralDetector : Detector(), XmlScanner {

    companion object {

        private const val TAG = "ResourceLiteralDetector"

        @JvmStatic
        val ISSUE = Issue.create(
            ResourceLiteralConst.ISSUE_ID,
            ResourceLiteralConst.ISSUE_BRIEF_DESCRIPTION,
            ResourceLiteralConst.ISSUE_EXPLANATION,
            CommonConst.CATEGORY_CUSTOM,
            CommonConst.PRIORITY_SERIOUS,
            CommonConst.SEVERITY_CUSTOM,
            Implementation(
                ResourceLiteralDetector::class.java,
                Scope.RESOURCE_FILE_SCOPE
            )
        )
    }

    override fun getApplicableAttributes(): Collection<String>? {
        //指定扫描所有的资源文件
        return XmlScannerConstants.ALL
    }

    override fun appliesTo(folderType: ResourceFolderType): Boolean {
        return (folderType == ResourceFolderType.LAYOUT ||
                folderType == ResourceFolderType.MENU ||

                folderType == ResourceFolderType.DRAWABLE ||
                folderType == ResourceFolderType.MIPMAP ||

                folderType == ResourceFolderType.XML)
    }

    /**
     * 这里暂时只处理
     * width,height，margin，padding，color,textColor，background,text,hint,tag,size,radius,angle等的校验
     * */
    override fun visitAttribute(context: XmlContext, attribute: Attr) {
        val name = attribute.name
        val value = attribute.value

        /**
         * 确认这个资源是否是Android规定的attributes
         * 是否需要将tools的也加入检测
         *
         * 获取当前标签的tag
         * val tagName = attribute.ownerElement.tagName
         *
         * 获取封闭标签中的值
         * val attributeValue = attribute.ownerElement.firstChild.nodeValue
         * 如<string name="app_name">LintDemo</string>，获取到的是LintDemo
         * */
        if (SdkConstants.ANDROID_URI != attribute.namespaceURI) {
            return
        }

        //检验size,radius,angle
        if (name.endsWith(SdkConstants.ATTR_TEXT_SIZE) ||
            name.endsWith(SdkConstants.ATTR_FAB_SIZE) ||
            name.endsWith(SdkConstants.ATTR_FAB_CUSTOM_SIZE) ||
            name.endsWith(SdkConstants.ATTR_MAX_IMAGE_SIZE) ||
            //radius
            name.endsWith(SdkConstants.ATTR_CORNER_RADIUS) ||
            name.endsWith(SdkConstants.ATTR_GRADIENT_RADIUS) ||

            name.endsWith(ResourceLiteralConst.RADIUS) ||
            name.endsWith(ResourceLiteralConst.BOTTOM_LEFT_RADIUS) ||
            name.endsWith(ResourceLiteralConst.BOTTOM_RIGHT_RADIUS) ||
            name.endsWith(ResourceLiteralConst.TOP_LEFT_RADIUS) ||
            name.endsWith(ResourceLiteralConst.TOP_RIGHT_RADIUS) ||

            name.endsWith(ResourceLiteralConst.SHADOW_RADIUS) ||
            //angle
            name.endsWith(ResourceLiteralConst.ANGLE)
        ) {
            if (!isReferenceResource(value)) {
                context.report(
                    ISSUE,
                    attribute,
                    context.getLocation(attribute),
                    "${ResourceLiteralConst.ISSUE_EXPLANATION},如:${ResourceLiteralConst.DIMEN_RESOURCE_REFERENCE}"
                )
            }
            return
        }


        //检验text,hint,tag
        if (name.endsWith(SdkConstants.ATTR_TEXT) ||
            name.endsWith(SdkConstants.ATTR_HINT) ||
            name.endsWith(SdkConstants.ATTR_TAG)
        ) {
            if (!isReferenceResource(value)) {
                context.report(
                    ISSUE,
                    attribute,
                    context.getLocation(attribute),
                    "${ResourceLiteralConst.ISSUE_EXPLANATION},如:${ResourceLiteralConst.STRING_RESOURCE_REFERENCE}"
                )
            }
            return
        }

        //检验color
        if (name.endsWith(SdkConstants.ATTR_COLOR) ||
            name.endsWith(SdkConstants.ATTR_TEXT_COLOR) || name.endsWith(SdkConstants.ATTR_TEXT_COLOR_HIGHLIGHT) ||
            name.endsWith(SdkConstants.ATTR_TEXT_COLOR_HINT) || name.endsWith(SdkConstants.ATTR_TEXT_COLOR_LINK) ||

            name.endsWith(ResourceLiteralConst.COLOR_START_COLOR) ||
            name.endsWith(ResourceLiteralConst.COLOR_END_COLOR) ||
            name.endsWith(ResourceLiteralConst.COLOR_CENTER_COLOR)
        ) {
            if (!isReferenceResource(value)) {
                context.report(
                    ISSUE,
                    attribute,
                    context.getLocation(attribute),
                    "${ResourceLiteralConst.ISSUE_EXPLANATION},如:${ResourceLiteralConst.COLOR_RESOURCE_REFERENCE}"
                )
            }
            return
        }

        //检验background
        if (name.endsWith(SdkConstants.ATTR_BACKGROUND) ||
            name.endsWith(SdkConstants.ATTR_BACKGROUND_TINT)
        ) {
            if (!isReferenceResource(value)) {
                context.report(
                    ISSUE,
                    attribute,
                    context.getLocation(attribute),
                    "${ResourceLiteralConst.ISSUE_EXPLANATION},如:" +
                            "${ResourceLiteralConst.COLOR_RESOURCE_REFERENCE}或者${ResourceLiteralConst.DRAWABLE_RESOURCE_REFERENCE}"
                )
            }
            return
        }

        //检验margin,padding
        if (name.endsWith(SdkConstants.ATTR_LAYOUT_MARGIN) ||
            name.endsWith(SdkConstants.ATTR_LAYOUT_MARGIN_LEFT) || name.endsWith(SdkConstants.ATTR_LAYOUT_MARGIN_RIGHT) ||
            name.endsWith(SdkConstants.ATTR_LAYOUT_MARGIN_BOTTOM) || name.endsWith(SdkConstants.ATTR_LAYOUT_MARGIN_TOP) ||
            name.endsWith(SdkConstants.ATTR_LAYOUT_MARGIN_START) || name.endsWith(SdkConstants.ATTR_LAYOUT_MARGIN_END) ||
            //padding
            name.endsWith(SdkConstants.ATTR_PADDING) ||
            name.endsWith(SdkConstants.ATTR_PADDING_LEFT) || name.endsWith(SdkConstants.ATTR_PADDING_RIGHT) ||
            name.endsWith(SdkConstants.ATTR_PADDING_BOTTOM) || name.endsWith(SdkConstants.ATTR_PADDING_TOP) ||
            name.endsWith(SdkConstants.ATTR_PADDING_START) || name.endsWith(SdkConstants.ATTR_PADDING_END) ||
            //drawable_padding
            name.endsWith(SdkConstants.ATTR_DRAWABLE_PADDING)
        ) {
            if (!isReferenceResource(value)) {
                context.report(
                    ISSUE,
                    attribute,
                    context.getLocation(attribute),
                    "${ResourceLiteralConst.ISSUE_EXPLANATION},如:${ResourceLiteralConst.DIMEN_RESOURCE_REFERENCE}"
                )
            }
            return
        }

        //检验width，height
        if (name.endsWith(SdkConstants.ATTR_WIDTH) || name.endsWith(SdkConstants.ATTR_HEIGHT) ||
            name.endsWith(SdkConstants.ATTR_LAYOUT_WIDTH) || name.endsWith(SdkConstants.ATTR_LAYOUT_HEIGHT)
        ) {
            //将fill_parent也当做错误处理
            if (value == ResourceLiteralConst.LITERAL_WRAP_CONTENT ||
                value == ResourceLiteralConst.LITERAL_MATCH_PARENT
            ) {
                return
            }
            if (!isReferenceResource(value)) {
                context.report(
                    ISSUE,
                    attribute,
                    context.getLocation(attribute),
                    "${ResourceLiteralConst.ISSUE_EXPLANATION},如:${ResourceLiteralConst.DIMEN_RESOURCE_REFERENCE}"
                )
            }
            return
        }

    }

    /**
     * 是否是引用资源
     * 如 @string/xxx,@color/colorAccent
     * ?android:maxButtonHeight
     * */
    private fun isReferenceResource(attributeValue: String): Boolean {
        if (TextUtils.isEmpty(attributeValue)) {
            return false
        }
        if (attributeValue.first() == '@' || attributeValue.first() == '?') {
            return true
        }
        return false
    }
}