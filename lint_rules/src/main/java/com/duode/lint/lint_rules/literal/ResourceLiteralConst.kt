package com.duode.lint.lint_rules.literal

/**
 * @Description:
 * @Author: hekang
 * @CreateDate: 2019-10-24 09:59
 */
object ResourceLiteralConst {
    /**
     * 检测中issue的id
     * */
    const val ISSUE_ID = "ResourceLiteralLint"

    /**
     * 检测中issue的简单描述
     * */
    const val ISSUE_BRIEF_DESCRIPTION = "You need to use reference resources"

    /**
     * 检测中issue的详细描述
     * */
    const val ISSUE_EXPLANATION = "不能直接在资源文件中直接写死数据，需要使用引用资源"


    const val LITERAL_WRAP_CONTENT = "wrap_content"
    const val LITERAL_MATCH_PARENT = "match_parent"
    //这个已经不再推荐使用，是否也直接报错
    const val LITERAL_FILL_PARENT = "fill_parent"


    const val DIMEN_RESOURCE_REFERENCE = "'@dimen/xx'"
    const val DRAWABLE_RESOURCE_REFERENCE = "'@drawable/xx'"
    const val COLOR_RESOURCE_REFERENCE = "'@color/xx'"
    const val STRING_RESOURCE_REFERENCE = "'@string/xx'"

    const val COLOR_START_COLOR = "startColor"
    const val COLOR_CENTER_COLOR = "centerColor"
    const val COLOR_END_COLOR = "endColor"


    /**
     * android:radius="25dp"
     * android:bottomLeftRadius="50dp"
     * android:bottomRightRadius="50dp"
     * android:topLeftRadius="50dp"
     * android:topRightRadius="50dp"
     * */
    const val RADIUS = "radius"
    const val BOTTOM_LEFT_RADIUS = "bottomLeftRadius"
    const val BOTTOM_RIGHT_RADIUS = "bottomRightRadius"
    const val TOP_LEFT_RADIUS = "topLeftRadius"
    const val TOP_RIGHT_RADIUS = "topRightRadius"

    const val SHADOW_RADIUS = "shadowRadius"

    /**
     *  <gradient
     *  android:angle="45"
     *  android:endColor="#958674"
     *  android:gradientRadius="25dp"
     *  android:startColor="@color/colorPrimary" />
     * */
    const val ANGLE = "angle"


}