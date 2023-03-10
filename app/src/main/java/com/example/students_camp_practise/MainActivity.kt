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
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import com.example.students_camp_practise.ui.theme.Students_camp_practiseTheme
import com.google.android.exoplayer2.SimpleExoPlayer
import com.google.android.exoplayer2.source.ProgressiveMediaSource
import com.google.android.exoplayer2.ui.PlayerView
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory
import com.google.android.exoplayer2.util.Util

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Students_camp_practiseTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    Greeting(SampleData.feedbackSample)
                }
            }
        }
    }
}
data class Message(val author: String,val time: String, val body: String, val u_logo:Int)

@Composable
fun Greeting(messages: List<Message>) {
    val context=LocalContext.current
    LazyColumn{
        item {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight()
            ) {
                Image(
                    painter = painterResource(R.drawable.mask_group_cutted),
                    contentDescription = "Background picture",
                    modifier = Modifier.size(392.dp, 309.dp),
                    alignment = Alignment.TopCenter
                )
                Row {

                    Image(
                        painter = painterResource(R.drawable.mask_logo),
                        contentDescription = "logo picture",
                        modifier = Modifier.size(149.dp, 395.dp),
                        alignment = Alignment.BottomEnd
                    )
                    Column(modifier = Modifier.size(width =180.dp, height = 395.dp)){
                        Text(text = ".",
                            modifier = Modifier.padding(horizontal = 12.dp, vertical = 12.dp),
                            color= MaterialTheme.colors.onBackground,
                            style = MaterialTheme.typography.subtitle2)
                        Spacer(modifier = Modifier.height(235.dp))
                        Text(text = "DoTA 2",
                            modifier = Modifier.padding(horizontal = 4.dp, vertical = 3.dp),
                            color= MaterialTheme.colors.onBackground,
                            style = MaterialTheme.typography.subtitle2,
                            fontSize = 30.sp)
                        Row{
                            for (i in 0..4)
                                Star_full(i)

                            Spacer(modifier = Modifier.width(6.dp))
                            Text(
                                text="70M",
                                modifier=Modifier.padding(horizontal=4.dp),
                                color = Color(198, 195, 181, 128), //???????????? ???? ????????
                                style = MaterialTheme.typography.body2,
                                fontSize = 12.sp,
                                textAlign = TextAlign.End
                            )
                        }
                    }
                }

            }
        }
        item{
            var isExpanded by remember { mutableStateOf(false) }
            Box(modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight()
                .padding(vertical = 8.dp)){
                Row(modifier=Modifier.padding(horizontal=12.dp)){
                    Image(
                        painter = painterResource(R.drawable.tag),
                        contentDescription = "logo tag",
                        modifier = Modifier
                            .size(53.dp, 22.dp)
                            .clickable {
                                isExpanded = !isExpanded
                                Toast
                                    .makeText(
                                        context,
                                        "Like MOBA? \nYou will have ability to install it in next versions of our app ;)",
                                        Toast.LENGTH_LONG
                                    )
                                    .show()
                            },
                        alignment = Alignment.BottomEnd
                    )
                    Spacer(modifier = Modifier.width(6.dp))
                    Image(
                        painter = painterResource(R.drawable.tag_1),
                        contentDescription = "logo tag 1",
                        modifier = Modifier
                            .size(93.dp, 22.dp)
                            .clickable {
                                isExpanded = !isExpanded
                                Toast
                                    .makeText(
                                        context,
                                        "Like multiplayer gaming? \nYou will have ability to install this and other multiplayer games in next versions of our app ;)",
                                        Toast.LENGTH_LONG
                                    )
                                    .show()
                            },
                        alignment = Alignment.BottomEnd
                    )
                    Spacer(modifier = Modifier.width(6.dp))
                    Image(
                        painter = painterResource(R.drawable.tag_2),
                        contentDescription = "logo tag 2",
                        modifier = Modifier
                            .size(73.dp, 22.dp)
                            .clickable {
                                isExpanded = !isExpanded
                                Toast
                                    .makeText(
                                        context,
                                        "Like strategies? \nYou will have ability to install this and other strategy games in next versions of our app ;)",
                                        Toast.LENGTH_LONG
                                    )
                                    .show()
                            },
                        alignment = Alignment.BottomEnd
                    )

                }
                Row(modifier=Modifier.padding(horizontal=12.dp)) {
                    Text(
                        text = "MOBA",
                        modifier = Modifier.padding(horizontal = 8.dp, vertical = 3.dp),
                        color = Color(68, 169, 244, 255),
                        style = MaterialTheme.typography.body2,
                        fontSize = 12.sp,
                        textAlign = TextAlign.End
                    )
                    Spacer(modifier = Modifier.width(6.dp))
                    Text(
                        text = "MULTIPLAYER",
                        modifier = Modifier.padding(horizontal = 5.dp, vertical = 3.dp),
                        color = Color(68, 169, 244, 255),
                        style = MaterialTheme.typography.body2,
                        fontSize = 12.sp,
                        textAlign = TextAlign.End
                    )
                    Spacer(modifier = Modifier.width(6.dp))
                    Text(
                        text = "STRATEGY",
                        modifier = Modifier.padding(horizontal = 6.dp, vertical = 3.dp),
                        color = Color(68, 169, 244, 255),
                        style = MaterialTheme.typography.body2,
                        fontSize = 12.sp,
                        textAlign = TextAlign.End
                    )
                }
            }

        }
        item {
            var isExpanded by remember { mutableStateOf(false) }
            val surfaceColor by animateColorAsState(
                if (isExpanded) Color(244, 209, 68, 240) else MaterialTheme.colors.surface,
            )
            Surface(shape = MaterialTheme.shapes.medium,
                    elevation = 0.5.dp,
                    color= surfaceColor,
                    modifier = Modifier
                        .clickable { isExpanded = !isExpanded }
                        .animateContentSize()
                        .padding(1.dp)) {
                Text(
                    text = "Dota 2 is a multiplayer online battle arena (MOBA) game which has two teams of five players compete to collectively destroy a large structure defended by the opposing team known as the \"Ancient\", whilst defending their own.",
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 12.dp, vertical = 12.dp),
                    color = MaterialTheme.colors.onBackground,
                    style = MaterialTheme.typography.body2,
                    maxLines = if (isExpanded) Int.MAX_VALUE else 2
                )
            }
        }
        item{
            var isExpanded by remember { mutableStateOf(false) }
            Box {
                Row {
                    Image(
                        painter = painterResource(R.drawable.description_picture),
                        contentDescription = "app screen",
                        modifier = Modifier
                            .size(250.dp, 128.dp)
                            .clickable {
                                isExpanded = !isExpanded
                                Toast
                                    .makeText(
                                        context,
                                        "There will be MOBA video snippet in next versions :)\n Now you already can enjoy a ralli!!!",
                                        Toast.LENGTH_LONG
                                    )
                                    .show()
                                //Play_video(context)
                                context.startActivity(Intent(context,VideoActivity::class.java))
                            },
                        alignment = Alignment.BottomEnd
                    )
                    Image(
                        painter = painterResource(R.drawable.description_picture_1),
                        contentDescription = "app screen 1",
                        modifier = Modifier.size(240.dp, 128.dp),
                        alignment = Alignment.BottomEnd
                    )
                }
                Row {
                    Text(text = "",
                        color= MaterialTheme.colors.onBackground,
                        style = MaterialTheme.typography.subtitle2)
                    Spacer(modifier = Modifier.width(88.dp))

                    Image(
                        painter = painterResource(R.drawable.video_symbol),
                        contentDescription = "video symbol",
                        modifier = Modifier.size(80.dp,128.dp),
                        alignment = Alignment.Center
                    )
                }
            }
        }
        item{
            Column{
                Text(text = "Review & Ratings",
                    modifier = Modifier.padding(horizontal = 12.dp, vertical = 12.dp),
                    color= MaterialTheme.colors.onBackground,
                    style = MaterialTheme.typography.subtitle2,
                    fontSize = 20.sp)
                Row {

                    Text(
                        text = "4.9",
                        modifier = Modifier.padding(horizontal = 12.dp),
                        color = MaterialTheme.colors.onBackground,
                        style = MaterialTheme.typography.subtitle2,
                        fontSize = 50.sp
                    )
                    Column {
                        Spacer(modifier = Modifier.height(18.dp))
                        Row{
                            for (i in 0..3)
                                Star_full(i)

                            Image(
                                painter = painterResource(R.drawable.star_semi1),
                                contentDescription = "fifth star",
                                modifier = Modifier
                                    .padding(horizontal = 4.dp)
                                    .size(16.dp, 16.dp),
                                alignment = Alignment.BottomEnd
                            )
                        }
                        Spacer(modifier = Modifier.height(2.dp))
                        Text(
                            text="70M Reviews",
                            modifier=Modifier.padding(horizontal=4.dp),
                            color = Color(198, 195, 181, 255), //???????????? ???? ????????
                            style = MaterialTheme.typography.body2
                        )

                    }
                }
                Spacer(modifier = Modifier.height(12.dp))
            }

        }

        items(messages){message ->
            Every_user_review(message)
        }
        item{
            Spacer(modifier = Modifier.height(12.dp))
            Button(onClick = { Toast.makeText(context,"Sorry, can't install it now \nYou will have ability to install it in next versions of app",Toast.LENGTH_LONG).show() },
                modifier= Modifier
                    .fillMaxWidth()
                    .size(width = 327.dp, height = 80.dp)
                    .padding(horizontal = 18.dp, vertical = 18.dp),
                colors = ButtonDefaults.buttonColors(backgroundColor = Color(244, 209, 68, 255))
            ) {
                Text(text = "Install",
                style = MaterialTheme.typography.button,
                color = Color(7, 7, 7, 255)
                )
            }
        }
    }

}

@Preview(uiMode=Configuration.UI_MODE_NIGHT_NO,
    name="Light mode",
    showBackground = true)
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES,
    showBackground = true,
    name="Darkest Mode")

@Composable
fun DefaultPreview() {
    Students_camp_practiseTheme {
        Greeting(SampleData.feedbackSample)
    }
}
@Composable
fun Star_full(iteration:Int){
    Students_camp_practiseTheme {
        Image(
            painter = painterResource(R.drawable.star_full),
            contentDescription = "$iteration star",
            modifier = Modifier
                .padding(horizontal = 4.dp)
                .size(16.dp, 16.dp),
            alignment = Alignment.BottomEnd
        )
    }
}


@Composable
fun Every_user_review(msg:Message){
    Students_camp_practiseTheme {
        var isExpanded by remember { mutableStateOf(false) }
        val surfaceColor by animateColorAsState(
            if (isExpanded) Color(244, 209, 68, 240) else MaterialTheme.colors.surface,
        )
        Column (modifier = Modifier.padding(horizontal = 12.dp, vertical =8.dp )){
            Row(modifier = Modifier.padding(all = 8.dp)) {
                Image(
                    painter = painterResource(msg.u_logo),
                    contentDescription = "user profile logo",
                    modifier = Modifier
                        .size(50.dp)
                        .clip(CircleShape)
                        .border(1.dp, color = Color(244, 209, 68, 255), CircleShape)
                )

                Column (modifier = Modifier.padding(all = 6.dp)) {
                    Text(
                        msg.author,
                        color = MaterialTheme.colors.onBackground,
                        style = MaterialTheme.typography.body2,
                        fontSize = 16.sp
                    )

                    Text(
                        msg.time,
                        color = Color(220, 220, 220, 128), //???????????? ???? ????????
                        style = MaterialTheme.typography.body2
                    )
                }

            }
            Surface(shape = MaterialTheme.shapes.medium,
                elevation =0.5.dp,
                color= surfaceColor,
                modifier= Modifier
                    .animateContentSize()
                    .clickable { isExpanded = !isExpanded }
                    .padding(1.dp)) {
                Text(
                    msg.body,
                    color = MaterialTheme.colors.onBackground,
                    style = MaterialTheme.typography.body2,
                    modifier = Modifier.padding(horizontal = 12.dp),
                    maxLines = if (isExpanded) Int.MAX_VALUE else 2
                )
            }
        }
    }
}
object SampleData {
    val feedbackSample = listOf(
        Message(
            "Marius Conte",
            "February 14, 2019",
            "Once you start to learn its secrets, there???s a wild and exciting variety of play here that???s unmatched, even by its peers.",
            R.drawable.user_logo_3
        ),
        Message(
            "Maria Marcelino",
            "November 12, 2017",
            "List of Android versions:\n" +
                    "Android KitKat (API 19)\n" +
                    "Android Lollipop (API 21)\n" +
                    "Android Marshmallow (API 23)\n" +
                    "Android Nougat (API 24)\n" +
                    "Android Oreo (API 26)\n" +
                    "Android Pie (API 28)\n" +
                    "Android 10 (API 29)\n" +
                    "Android 11 (API 30)\n" +
                    "Android 12 (API 31)\n",
            R.drawable.user_logo_5
        ),
        Message(
            "Lisa Ajax",
            "February 5, 2016",
            "I think Kotlin will be my favorite programming language.\n" +
                    "It's so much fun!",
            R.drawable.user_logo_2
        ),
    )
}