package com.software.mywechat.utils

import android.util.Log
import com.software.mywechat.data.AddBookItem
import com.software.mywechat.data.contactsList
import net.sourceforge.pinyin4j.PinyinHelper

object AddBookUtils {
    /*
    * c护理通讯录按字母排序
    *
    * */

    fun getContactMap(): Map<String, MutableList<AddBookItem>>{
        val originalHashMap = HashMap<String,MutableList<AddBookItem>>()
        contactsList.forEach{ row ->
            val char = PinyinHelper.toHanyuPinyinStringArray(row.name.first())
            val key = char[0][0].uppercase()
            originalHashMap.getOrPut(key){ArrayList()}.add(row)
            //进行添加到Map中，如果存在，存储到当前key对应的value集合中。否则新建key进行存储
            //使用 getOrPut 函数简化添加到 Map 中的逻辑
        }
        val sortedLinkedHashMap = originalHashMap
            .toSortedMap(compareBy { it })
            .toMap()
        // 打印结果
        sortedLinkedHashMap.forEach { (key, value) ->
            Log.d("AddrBookUtils", "$key: $value")
        }
        return sortedLinkedHashMap
    }
}