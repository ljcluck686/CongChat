package com.software.mywechat.util

import net.sourceforge.pinyin4j.PinyinHelper

object PinyinUtils {
    // 获取拼音首字母(大写)，非字母返回"#"
    fun getFirstLetter(nickname: String?): String {
        if (nickname.isNullOrEmpty()) return "#"

        val c = nickname[0]
        // 处理英文
        if (c in 'A'..'Z') return c.toString()
        if (c in 'a'..'z') return c.uppercase()

        // 处理中文：获取拼音首字母
        val pinyinArray = PinyinHelper.toHanyuPinyinStringArray(c)
        if (pinyinArray != null && pinyinArray.isNotEmpty()) {
            val firstChar = pinyinArray[0][0]
            return if (firstChar in 'A'..'Z') firstChar.toString() else "#"
        }

        // 其他字符(数字、符号等)
        return "#"
    }
}