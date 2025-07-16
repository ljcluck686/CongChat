package com.software.mywechat.ui.book

import androidx.compose.foundation.lazy.LazyListState
import com.software.mywechat.data.AddBookItem

class StickyHeaderState(
    val state: LazyListState = LazyListState(),
    private var hashMap:Map<String,MutableList<AddBookItem>>
) {
    fun setData(data:Map<String,MutableList<AddBookItem>>){
        this.hashMap = data
    }


    suspend fun scrollToItem(initial: String) {
        val (sum, indexOfSelf) = getLeftHeaderIndexByChar(hashMap, initial)
        state.scrollToItem(sum + indexOfSelf)
    }

    private fun getLeftHeaderIndexByChar(
        data:Map<String,MutableList<AddBookItem>>,
        initial : String
    ):Pair<Int,Int>{
        val keysBeforeList =
            data.keys.takeWhile { it!=initial } //获取输入字母之前的值
        val sum = keysBeforeList.sumOf { data[it]?.size?:0 }
        val indexOfSelf = data.keys.indexOf(initial)
        return Pair(sum,indexOfSelf)
    }
}