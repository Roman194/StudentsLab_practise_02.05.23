package com.example.students_camp_practise

import android.content.res.Configuration
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
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
import com.example.students_camp_practise.ui.theme.Students_camp_practiseTheme

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
                                color = Color(198, 195, 181, 128), //белому не идёт
                                style = MaterialTheme.typography.body2,
                                fontSize = 12.sp,
                                textAlign = TextAlign.End
                            )
                        }
                    }
                }

            }
        }
        item {
            Text(text = "Dota 2 is a multiplayer online battle arena (MOBA) game which has two teams of five players compete to collectively destroy a large structure defended by the opposing team known as the \"Ancient\", whilst defending their own.",
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 12.dp, vertical = 12.dp),
                color= MaterialTheme.colors.onBackground,
                style = MaterialTheme.typography.body2)
        }
        item{
            Row{
                Image(
                    painter = painterResource(R.drawable.description_picture),
                    contentDescription = "app screen",
                    modifier = Modifier.size(250.dp, 128.dp),
                    alignment = Alignment.BottomEnd
                )
                Image(
                    painter = painterResource(R.drawable.description_picture_1),
                    contentDescription = "app screen 1",
                    modifier = Modifier.size(240.dp, 128.dp),
                    alignment = Alignment.BottomEnd
                )
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
                            color = Color(198, 195, 181, 255), //белому не идёт
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
        Surface {
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
                Text(msg.body,
                    color= MaterialTheme.colors.onBackground,
                    style = MaterialTheme.typography.body2,
                    modifier = Modifier.padding(horizontal=12.dp))
            }
        }
    }
}
object SampleData {
    val feedbackSample = listOf(
        Message(
            "Auguste Conte",
            "February 14, 2019",
            "Once you start to learn its secrets, there’s a wild and exciting variety of play here that’s unmatched, even by its peers.",
            R.drawable.user_logo
        ),
        Message(
            "Jang Marcelino",
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
            R.drawable.user_logo_1
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