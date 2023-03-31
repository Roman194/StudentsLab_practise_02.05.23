package com.example.students_camp_practise

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.content.res.Configuration
import android.net.Uri
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import com.example.students_camp_practise.ui.theme.Students_camp_practiseTheme
import com.google.android.exoplayer2.SimpleExoPlayer
import com.google.android.exoplayer2.source.ProgressiveMediaSource
import com.google.android.exoplayer2.ui.PlayerView
import com.google.android.exoplayer2.upstream.DataSource
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory
import com.google.android.exoplayer2.util.Util

class VideoActivity: ComponentActivity(){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Students_camp_practiseTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {

                    VideoAct()

                    }

                }
            }
        }
    }
@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun VideoAct() {
    val context = LocalContext.current
    Scaffold(topBar = { TopAppBar {
        Box( //head bar box with description of what in this activity user can watch and how he can move back from it
            modifier = Modifier
                .fillMaxWidth()
                .background(Color(244, 209, 68, 240))
                .height(56.dp)
        ) {


            IconButton(onClick = {
                context.startActivity(
                    Intent(
                        context,
                        MainActivity::class.java
                    )
                )
            }) {
                Icon(
                    painter = painterResource(id = android.R.drawable.ic_menu_close_clear_cancel),
                    contentDescription = "BackIcon",
                    modifier = Modifier.padding(vertical=11.dp)
                )
            }
            Text(
                "DoTA 2 Video preview",
                color = Color.White,
                style = MaterialTheme.typography.subtitle2,
                fontSize = 18.sp,
                modifier = Modifier.padding(horizontal = 50.dp,vertical=14.dp)
            )
        }
    } },)
    {

        Play_video(context)
    }
    

}

    @SuppressLint("RememberReturnType")
    @Composable
    fun Play_video(context: Context){ //function of playing to user video (use one of the google mp.4 public videos)
        val videoUrl="https://commondatastorage.googleapis.com/gtv-videos-bucket/sample/WeAreGoingOnBullrun.mp4"

        val exoPlayer = remember(context){
            SimpleExoPlayer.Builder(context).build().apply {
                val dataSourceFactory: DataSource.Factory=
                    DefaultDataSourceFactory(context, Util.getUserAgent(context,context.packageName))

                val source= ProgressiveMediaSource.Factory(dataSourceFactory)
                    .createMediaSource(Uri.parse(videoUrl))

                this.prepare(source)
            }
        }

        AndroidView(factory = {context ->
            PlayerView(context).apply{
                player=exoPlayer
            }
        })
    }

    @Preview(uiMode= Configuration.UI_MODE_NIGHT_NO,
        name="Light mode",
        showBackground = true)
    @Preview(uiMode = Configuration.UI_MODE_NIGHT_YES,
        showBackground = true,
        name="Darkest Mode")

    @Composable
    fun DefaultPreview_2() {
        Students_camp_practiseTheme {
            VideoAct()
        }
    }
