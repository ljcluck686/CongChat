package com.software.mywechat.data

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.Label
import androidx.compose.material.icons.filled.*
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector

data class AddBookItem(
    val avatar: String,
    val name: String,
)

data class HeaderItem(
    val icon: ImageVector,
    val title: String,
    val background: Color
)

val contactsList = listOf(
    AddBookItem(
        "https://img.duoziwang.com/2018/24/12130927112411.jpg",
        "小美",
    ),
    AddBookItem(
        "https://img.duoziwang.com/2019/07/12080849913594.jpg",
        "大牛",
    ),
    AddBookItem(
        "https://img.duoziwang.com/2018/17/05272146705707.jpg",
        "小姑",
    ),
    AddBookItem(
        "https://img.duoziwang.com/2019/07/12080849900677.jpg",
        "小花",
    ),
    AddBookItem(
        "https://img.duoziwang.com/2019/07/12080849900682.jpg",
        "好妹仔",
    ),
    AddBookItem(
        "https://img.duoziwang.com/2018/21/09030701215429.jpg",
        "梓潼",
    ),
    AddBookItem(
        "https://img.duoziwang.com/2018/24/12130927112411.jpg",
        "小美",
    ),
    AddBookItem(
        "https://img.duoziwang.com/2021/04/07291624704661.jpg",
        "小明",
    ),
    AddBookItem(
        "https://img.duoziwang.com/2021/04/08260845108757.jpg",
        "小爱",
    ),
    AddBookItem(
        "https://img.duoziwang.com/2018/18/06032028705302.jpg",
        "小阳",
    ),
    AddBookItem(
        "https://img.duoziwang.com/2019/07/12080849913594.jpg",
        "大牛",
    ),
    AddBookItem(
        "https://img.duoziwang.com/2018/17/05272146705707.jpg",
        "小姑",
    ),
    AddBookItem(
        "https://img.duoziwang.com/2019/07/12080849900677.jpg",
        "小花",
    ),
    AddBookItem(
        "https://img.duoziwang.com/2019/07/12080849900682.jpg",
        "好妹仔",
    ),
    AddBookItem(
        "https://img.duoziwang.com/2018/21/09030701215429.jpg",
        "梓潼",
    ),

    AddBookItem(
        "https://img.duoziwang.com/2018/24/12130927112411.jpg",
        "爱美",
    ),
    AddBookItem(
        "https://img.duoziwang.com/2019/07/12080849913594.jpg",
        "比大牛",
    ),
    AddBookItem(
        "https://img.duoziwang.com/2018/17/05272146705707.jpg",
        "差小姑",
    ),
    AddBookItem(
        "https://img.duoziwang.com/2019/07/12080849900677.jpg",
        "东小花",
    ),
    AddBookItem(
        "https://img.duoziwang.com/2019/07/12080849900682.jpg",
        "鹅好妹仔",
    ),
    AddBookItem(
        "https://img.duoziwang.com/2018/21/09030701215429.jpg",
        "房梓潼",
    ),
    AddBookItem(
        "https://img.duoziwang.com/2018/24/12130927112411.jpg",
        "高小美",
    ),
    AddBookItem(
        "https://img.duoziwang.com/2021/04/07291624704661.jpg",
        "华小明",
    ),
    AddBookItem(
        "https://img.duoziwang.com/2021/04/08260845108757.jpg",
        "将小爱",
    ),
    AddBookItem(
        "https://img.duoziwang.com/2018/18/06032028705302.jpg",
        "可小阳",
    ),
    AddBookItem(
        "https://img.duoziwang.com/2019/07/12080849913594.jpg",
        "刘大牛",
    ),
    AddBookItem(
        "https://img.duoziwang.com/2018/17/05272146705707.jpg",
        "米小姑",
    ),
    AddBookItem(
        "https://img.duoziwang.com/2019/07/12080849900677.jpg",
        "刘小花",
    ),
    AddBookItem(
        "https://img.duoziwang.com/2019/07/12080849900682.jpg",
        "让好妹仔",
    ),
    AddBookItem(
        "https://img.duoziwang.com/2018/21/09030701215429.jpg",
        "家梓潼",
    ),
    AddBookItem(
        "https://img.duoziwang.com/2019/07/12080849900682.jpg",
        "月好妹仔",
    ),
    AddBookItem(
        "https://img.duoziwang.com/2018/21/09030701215429.jpg",
        "行家梓潼",
    ),
    AddBookItem(
        "https://img.duoziwang.com/2019/07/12080849900682.jpg",
        "自好妹仔",
    ),
    AddBookItem(
        "https://img.duoziwang.com/2018/21/09030701215429.jpg",
        "家2梓潼",
    ),
)

val headList = listOf(
    HeaderItem(
        Icons.Filled.GroupAdd,
        "新的朋友",
        Color(0xffe79f56)
    ),
    HeaderItem(
        Icons.Filled._3p,
        "仅聊天的朋友",
        Color(0xffe79f56)
    ),
    HeaderItem(
        Icons.Filled.PeopleAlt,
        "群聊",
        Color(0xff6cbe6d)
    ),
    HeaderItem(
        Icons.AutoMirrored.Filled.Label,
        "标签",
        Color(0xff4f81d3)
    ),
    HeaderItem(
        Icons.Filled.Person,
        "公众号",
        Color(0xff4f81d3)
    ),
)