package com.example.students_camp_practise

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.content.res.Configuration
import android.net.Uri
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.SnackbarDefaults.backgroundColor
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
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
                    Column{
                        val context = LocalContext.current

                        Button(onClick = { context.startActivity(Intent(context,MainActivity::class.java)) },
                            modifier= Modifier
                                .size(width = 120.dp, height = 100.dp)
                                .padding(horizontal = 18.dp, vertical = 18.dp),
                            colors = ButtonDefaults.buttonColors(backgroundColor = Color(244, 209, 68, 240))
                        ) {
                            Text(text = "  <- \nBack",
                                style = MaterialTheme.typography.button,
                                color = MaterialTheme.colors.onBackground
                            )
                        }
                        Spacer(modifier = Modifier.height(175.dp))
                        Play_video(context)
                    }

                }
            }
        }
    }

    @SuppressLint("RememberReturnType")
    @Composable
    fun Play_video(context: Context){
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
    fun DefaultPreview() {
        Students_camp_practiseTheme {
            val context= LocalContext.current
            Play_video(context)
        }
    }
}