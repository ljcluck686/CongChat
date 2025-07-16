package com.software.mywechat.avtivity

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.runtime.Composable
import androidx.core.view.WindowCompat
import com.google.accompanist.insets.ProvideWindowInsets
import com.software.mywechat.ui.page.moment.FriendsMomentPage
import com.software.mywechat.ui.theme.MyWeChatTheme

class FriendsMomentActivity : AppCompatActivity() {

    companion object{
        fun navigate(context:Context){
            val intent = Intent(context,FriendsMomentActivity::class.java)
            context.startActivity(intent)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        WindowCompat.setDecorFitsSystemWindows(window,false)
        setContent{
            MomentPage()
        }
    }

}



@Composable
fun MomentPage() {
    MyWeChatTheme() {
        ProvideWindowInsets {
            FriendsMomentPage()
        }
    }
}