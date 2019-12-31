package com.duode.lint.lint_rules.name.widget

/**
 * @Description: 存储有关控件的数据
 * @Author: hekang
 * @CreateDate: 2019-10-18 19:39
 */
object WidgetIdConst {

    /**
     * log检测中issue的id
     * */
    const val ISSUE_ID = "WidgetIdLint"

    /**
     * log检测中issue的简单描述
     * */
    const val ISSUE_BRIEF_DESCRIPTION = "Use uniform naming rules'"

    /**
     * log检测中issue的详细描述
     * */
    const val ISSUE_EXPLANATION = "请使用统一的控件缩写，来作为命名资源id时的后缀"

    /**
     * 命名id时的包名
     * */
    const val ANDROID_ID = "android:id"

    /**
     * LinearLayout的简称以及在xml布局中的根标签的名字
     * */
    const val LINEARLAYOUT_ID = "LinearLayout"
    const val LINEARLAYOUT_SHORT_NAME = "ll"

    /**
     * RelativeLayout的简称以及在xml布局中的根标签的名字
     * */
    const val RELATIVELAYOUT_ID = "RelativeLayout"
    const val RELATIVELAYOUT_SHORT_NAME = "rl"

    /**
     * FrameLayout的简称以及在xml布局中的根标签的名字
     * */
    const val FRAMELAYOUT_ID = "FrameLayout"
    const val FRAMELAYOUT_SHORT_NAME = "fl"

    /**
     * TableLayout的简称以及在xml布局中的根标签的名字
     * */
    const val TABLAYOUT_ID = "TableLayout"
    const val TABLAYOUT_SHORT_NAME = "tl"


    /**
     * BUTTON的简称以及在xml布局中的根标签的名字
     * */
    const val BUTTON_ID = "Button"
    const val BUTTON_SHORT_NAME = "btn"

    /**
     * RadioButton的简称以及在xml布局中的根标签的名字
     * */
    const val RADIOBUTTON_ID = "RadioButton"
    const val RADIOBUTTON_SHORT_NAME = "rbtn"

    /**
     * ImageButton的简称以及在xml布局中的根标签的名字
     * */
    const val IMAGEBUTTON_ID = "ImageButton"
    const val IMAGEBUTTON_SHORT_NAME = "ibtn"

    /**
     * TextView的简称以及在xml布局中的根标签的名字
     * */
    const val TEXTVIEW_ID = "TextView"
    const val TEXTVIEW_SHORT_NAME = "tv"

    /**
     * EditText的简称以及在xml布局中的根标签的名字
     * */
    const val EDITTEXT_ID = "EditText"
    const val EDITTEXT_SHORT_NAME = "et"

    /**
     * ImageView的简称以及在xml布局中的根标签的名字
     * */
    const val IMAGEVIEW_ID = "ImageView"
    const val IMAGEVIEW_SHORT_NAME = "iv"

    /**
     * WebView的简称以及在xml布局中的根标签的名字
     * */
    const val WEBVIEW_ID = "WebView"
    const val WEBVIEW_SHORT_NAME = "wv"

    /**
     * CheckBox的简称以及在xml布局中的根标签的名字
     * */
    const val CHECKBOX_ID = "CheckBox"
    const val CHECKBOX_SHORT_NAME = "cb"

    /**
     * ProgressBar的简称以及在xml布局中的根标签的名字
     * */
    const val PROGRESSBAR_ID = "ProgressBar"
    const val PROGRESSBAR_SHORT_NAME = "pb"

    /**
     * ProgressBar的简称以及在xml布局中的根标签的名字
     * */
    const val SEEKBAR_ID = "SeekBar"
    const val SEEKBAR_SHORT_NAME = "sb"

    /**
     * VIEW的简称以及在xml布局中的根标签的名字
     * */
    const val VIEW_ID = "View"
    const val VIEW_SHORT_NAME = "view"

    /**
     * Space的简称以及在xml布局中的根标签的名字
     * */
    const val SPACE_ID = "Space"
    const val SPACE_SHORT_NAME = "space"

    /**
     * ScrollView的简称以及在xml布局中的根标签的名字
     * */
    const val SCROLLVIEW_ID = "ScrollView"
    const val SCROLLVIEW_SHORT_NAME = "sv"

    /**
     * ScrollView的简称以及在xml布局中的根标签的名字
     * */
    const val HORIZONTALSCROLLVIEW = "HorizontalScrollView"
    const val HORIZONTALSCROLLVIEW_SHORT_NAME = "hsv"

    /**
     * RecyclerView的简称以及在xml布局中的根标签的名字
     * */
    const val RECYCLERVIEW_ID = "RecyclerView"
    const val RECYCLERVIEW_SHORT_NAME = "rv"

    /**
     * GridView的简称以及在xml布局中的根标签的名字
     * TODO：2019年10月18日19:57:38 这里应该检测不让使用GridView和ListView
     * */
    const val GRIDVIEW_ID = "GridView"
    const val GRIDVIEW_SHORT_NAME = "gv"

    /**
     * ListView的简称以及在xml布局中的根标签的名字
     * TODO：2019年10月18日19:57:38 这里应该检测不让使用GridView和ListView以及ExpandableListView
     * */
    const val LISTVIEW_ID = "ListView"
    const val LISTVIEW_SHORT_NAME = "lv"

    /**
     * ExpandableListView的简称以及在xml布局中的根标签的名字
     * TODO：2019年10月18日19:57:38 这里应该检测不让使用GridView和ListView以及ExpandableListView
     * */
    const val EXPANDABLELISTVIEW_ID = "ExpandableListView"
    const val EXPANDABLELISTVIEW_SHORT_NAME = "elv"


}