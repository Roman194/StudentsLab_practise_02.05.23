package com.example.students_camp_practise

import android.content.Intent
import android.content.res.Configuration
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.students_camp_practise.ui.theme.Students_camp_practiseTheme
import me.onebone.toolbar.CollapsingToolbarScaffold
import me.onebone.toolbar.ScrollStrategy
import me.onebone.toolbar.rememberCollapsingToolbarScaffoldState

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
                    MainScreen_elements(SampleData.feedbackSample)
                }
            }
        }
    }
}
data class Message(val author: String,val time: String, val body: String, val u_logo:Int)

@Composable
fun MainScreen_elements(messages: List<Message>) { //receive list of objects with Message data class type
    val context=LocalContext.current
    val state = rememberCollapsingToolbarScaffoldState()

    CollapsingToolbarScaffold(
        modifier = Modifier
            .fillMaxSize(),
        state = state,
        scrollStrategy = ScrollStrategy.ExitUntilCollapsed,
        toolbar = {//elements of collapsing toolbar scope described there
            val textSize = (18 + (30 - 18) * state.toolbarState.progress).sp
            val miniTextSize =(18 + (20 - 18) * state.toolbarState.progress).sp

            Box(
                modifier = Modifier
                    .background(color = Color(244, 209, 68, 255)) //toolbar will have yellow background color while it will be collapsed
                    .fillMaxWidth()
                    .height(200.dp)
                    .pin()
            )


            Image(
                modifier = Modifier
                    .pin()
                    .size(392.dp, 309.dp),
                painter = painterResource(id = R.drawable.back_picture_2),
                contentDescription = "background picture",
                alignment = Alignment.TopCenter,
                contentScale = ContentScale.Crop,
                alpha=if(textSize.value==18f) 0f else 1f //became invisible in collapsed state
            )

            Image(
                painter = painterResource(id = R.drawable.mask_logo_2),
                contentDescription = "logo picture",
                alignment = Alignment.BottomEnd,
                modifier = Modifier.size(143.dp,390.dp)
            )
            Text(text="70M",
                modifier= Modifier
                    .road(whenCollapsed = Alignment.BottomStart, whenExpanded = Alignment.TopEnd)
                    .padding(vertical = 16.dp, horizontal = 85.dp),
                color = if(miniTextSize.value==18f) Color(255, 255, 255, 255) else Color(198, 195, 181, 128), //became brighter in collapsed state
                style = MaterialTheme.typography.body2,
                textAlign = TextAlign.Center,
                fontSize = if(miniTextSize.value<=19f)18.sp else 0.sp //invisible while have bigger font than 19f (i really like how it looks like)
            )
            Text(
                text = "DoTA 2",
                modifier = Modifier
                    .road(Alignment.CenterStart, Alignment.BottomCenter)
                    .padding(16.dp, 16.dp, 16.dp, 16.dp),
                color = Color.White,
                style=MaterialTheme.typography.subtitle2,
                fontSize = textSize
            )



        }
    ) {//main screen ellements described there
        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            item{
                Row{
                    Text(
                        text = ""
                    )
                    Spacer(modifier = Modifier.width(129.dp))

                    for (i in 0..4) //function of full star drawing (5 similar Images)
                        Star_full(i)

                    Text(text="70M",
                        modifier=Modifier.padding(horizontal=4.dp),
                        color = Color(198, 195, 181, 128),
                        style = MaterialTheme.typography.body2,
                        textAlign = TextAlign.End,
                        fontSize = 16.sp
                    )

                }

            }

            item {
                var isExpanded by remember { mutableStateOf(false) } //images in this item are clickable
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .fillMaxHeight()
                        .padding(vertical = 8.dp) //Box of Image and Text elements
                ) {
                    Row(modifier = Modifier.padding(horizontal = 12.dp)) {
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
                    Row(modifier = Modifier.padding(horizontal = 12.dp)) {
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
                    color = surfaceColor, //this surface will change color to yellow after user click on it
                    modifier = Modifier
                        .clickable { isExpanded = !isExpanded }
                        .animateContentSize() //this surface is clickable with animation
                        .padding(1.dp)) {
                    Text(
                        text = "Dota 2 is a multiplayer online battle arena (MOBA) game which has two teams of five players compete to collectively destroy a large structure defended by the opposing team known as the \"Ancient\", whilst defending their own.",
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 12.dp, vertical = 12.dp),
                        color = MaterialTheme.colors.onBackground,
                        style = MaterialTheme.typography.body2,
                        maxLines = if (isExpanded) Int.MAX_VALUE else 2 //this Text show all his lines only after surface will receive click of user
                    )
                }
            }
            item {
                var isExpanded by remember { mutableStateOf(false) }
                Box {//this box is used to make video symbol over image. Image is still clickable under it
                    Row {
                        Image(
                            painter = painterResource(R.drawable.description_picture),
                            contentDescription = "app screen",
                            modifier = Modifier
                                .size(250.dp, 128.dp)
                                .clickable { //on user click on it he will move to special VideoActivity to show him a video snippet
                                    isExpanded = !isExpanded
                                    Toast
                                        .makeText(
                                            context,
                                            "There will be MOBA video snippet in next versions :)\n Now you already can enjoy a ralli!!!",
                                            Toast.LENGTH_LONG
                                        )
                                        .show()

                                    context.startActivity(
                                        Intent(
                                            context,
                                            VideoActivity::class.java
                                        )
                                    )
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
                        Text(
                            text = "",
                            color = MaterialTheme.colors.onBackground,
                            style = MaterialTheme.typography.subtitle2
                        )
                        Spacer(modifier = Modifier.width(88.dp))

                        Image(
                            painter = painterResource(R.drawable.video_symbol),
                            contentDescription = "video symbol",
                            modifier = Modifier.size(80.dp, 128.dp),
                            alignment = Alignment.Center
                        )
                    }
                }
            }
            item {
                Column {//Reviews head ellements
                    Text(
                        text = "Review & Ratings",
                        modifier = Modifier.padding(horizontal = 12.dp, vertical = 12.dp),
                        color = MaterialTheme.colors.onBackground,
                        style = MaterialTheme.typography.subtitle2,
                        fontSize = 20.sp
                    )
                    Row {

                        Text(
                            text = "4.9",
                            modifier = Modifier.padding(horizontal = 12.dp),
                            color = MaterialTheme.colors.onBackground,
                            style = MaterialTheme.typography.subtitle2,
                            fontSize = 50.sp
                        )
                        Column {//drawing fifth stars but the last one is semi-painted one
                            Spacer(modifier = Modifier.height(18.dp))
                            Row {
                                for (i in 0..3)
                                    Star_full(i)

                                Image(
                                    painter = painterResource(R.drawable.star_semi1),
                                    contentDescription = "fifth star",
                                    modifier = Modifier
                                        .padding(vertical = 1.5.dp)
                                        .size(16.dp, 16.dp),
                                    alignment = Alignment.BottomEnd
                                )
                            }
                            Spacer(modifier = Modifier.height(2.dp))
                            Text(
                                text = "70M Reviews",
                                modifier = Modifier.padding(horizontal = 4.dp),
                                color = Color(198, 195, 181, 255),
                                style = MaterialTheme.typography.body2
                            )

                        }
                    }
                    Spacer(modifier = Modifier.height(12.dp))
                }

            }

            items(messages) { message -> //this construction optimize creation of similar reviews elements
                Every_user_review(message) //all Message data class type objects transmits in turn
            }
            item {
                Spacer(modifier = Modifier.height(12.dp))
                Button( //yellow clickable button
                    onClick = {
                        Toast.makeText(
                            context,
                            "Sorry, can't install it now \nYou will have ability to install it in next versions of app",
                            Toast.LENGTH_LONG
                        ).show()
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .size(width = 327.dp, height = 80.dp)
                        .padding(horizontal = 18.dp, vertical = 18.dp),
                    colors = ButtonDefaults.buttonColors(backgroundColor = Color(244, 209, 68, 255))
                ) {
                    Text(
                        text = "Install",
                        style = MaterialTheme.typography.button,
                        color = Color(7, 7, 7, 255)
                    )
                }
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
        MainScreen_elements(SampleData.feedbackSample)
    }
}
@Composable
fun Star_full(iteration:Int){
    Students_camp_practiseTheme {
        Image( //making of similar image elements. They differ only by contentDescription
            painter = painterResource(R.drawable.star_full),
            contentDescription = "$iteration star", //depends from for cycle iteration
            modifier = Modifier.size(16.dp, 20.dp),
            alignment = Alignment.CenterEnd,
        )
        Spacer(modifier = Modifier.width(4.dp))
    }
}


@Composable
fun Every_user_review(msg:Message){//receive msg object which contain information about user logo, name, time and review
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
                        color = Color(220, 220, 220, 128), //белому не идёт
                        style = MaterialTheme.typography.body2
                    )
                }

            }
            Surface(shape = MaterialTheme.shapes.medium,
                elevation =0.5.dp,
                color= surfaceColor, //this surface will change color to yellow after user click on it
                modifier= Modifier
                    .animateContentSize()
                    .clickable { isExpanded = !isExpanded } //this surface is clickable with animation
                    .padding(1.dp)) {
                Text(
                    msg.body,
                    color = MaterialTheme.colors.onBackground,
                    style = MaterialTheme.typography.body2,
                    modifier = Modifier.padding(horizontal = 12.dp),
                    maxLines = if (isExpanded) Int.MAX_VALUE else 2 //this Text show all his lines only after surface will receive click of user
                )
            }
        }
    }
}
object SampleData { //object with list of user reviews information (Message data class)
    val feedbackSample = listOf(
        Message(
            "Marius Conte",
            "February 14, 2019",
            "Once you start to learn its secrets, there’s a wild and exciting variety of play here that’s unmatched, even by its peers.",
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