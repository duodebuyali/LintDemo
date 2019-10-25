package com.duode.lint.lint_rules.base

import org.apache.http.util.TextUtils
import java.util.regex.Pattern

/**
 * @Description: 关于字符串处理的工具
 * @Author: hekang
 * @CreateDate: 2019-10-21 18:53
 */
object CommonStringUtils {

    /**
     * 判断字符串中是否含有大写
     * @param str 需要判断的字符串
     */
    fun hasUpperCase(str: String): Boolean {
        for (element in str) {
            if (Character.isUpperCase(element)) {
                return true
            }
        }
        return false
    }

    /**
     * 判断字符串中是否含有小写
     * @param str 需要判断的字符串
     */
    fun hasLowerCase(str: String): Boolean {
        for (element in str) {
            if (Character.isLowerCase(element)) {
                return true
            }
        }
        return false
    }


    /**
     * 判断字符串是否符合大驼峰命名
     * 这里的判断只是简单的判断，没有判断单词的正确以及是否用首字母命名分割单词
     * */
    fun checkUpperCamelCase(str: String): Boolean {
        if (TextUtils.isEmpty(str)) {
            return false
        }
        val isSingle = str.length == 1
        if (Character.isUpperCase(str.first())) {
            if (isSingle) {
                return true
            }
            if (Character.isLowerCase(str.last())) {
                return true
            }
        }

        return false
    }

    /**
     * 判断字符串是否符合小驼峰命名
     * 这里的判断只是简单的判断，没有判断单词的正确以及是否用首字母命名分割单词
     * */
    fun checkLowerCamelCase(str: String): Boolean {
        if (TextUtils.isEmpty(str)) {
            return false
        }
        //只要第一位是小写就是对的
        if (Character.isLowerCase(str.first())) {
            return true
        }
        return false
    }

    /**
     * 检查是否含有中文
     * */
    fun hasChinese(str: String): Boolean {
        //中文编码的ASCII码范围
        val patternStr = "[\\u4e00-\\u9fa5]"
        val pattern = Pattern.compile(patternStr)
        val matcher = pattern.matcher(str)

        return matcher.find()
    }
}