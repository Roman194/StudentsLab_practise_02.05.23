package com.example.students_camp_practise

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
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.WindowInsetsSides
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.only
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.systemBars
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.view.WindowCompat
import com.example.students_camp_practise.ui.theme.Students_camp_practiseTheme
import me.onebone.toolbar.CollapsingToolbarScaffold
import me.onebone.toolbar.ScrollStrategy
import me.onebone.toolbar.rememberCollapsingToolbarScaffoldState

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        WindowCompat.setDecorFitsSystemWindows(window,false)

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

data class TagMessage(val name: String, val category: String)
data class Tag(val tagName:String, val onClickMessage: TagMessage)

@Composable
fun MainScreen_elements(messages: List<Message>) { //receive list of objects with Message data class type
    val context=LocalContext.current
    val state = rememberCollapsingToolbarScaffoldState()
    val tagsList = listOf(
        Tag(tagName = "MOBA", onClickMessage = TagMessage(name="MOBA",category="similar")),
        Tag(tagName = "MULTIPLAYER", onClickMessage = TagMessage(name="multiplayer gaming", category = "multiplayer")),
        Tag(tagName = "STRATEGY", onClickMessage = TagMessage(name = "strategies", category = "strategy"))
    )
    val messageClickText = stringResource(R.string.button_clck_msg)

    CollapsingToolbarScaffold(
        modifier = Modifier
            .fillMaxWidth()
            .padding(
                WindowInsets.systemBars
                    .only(WindowInsetsSides.Top)
                    .asPaddingValues()
            )
        ,
        state = state,
        scrollStrategy = ScrollStrategy.ExitUntilCollapsed,
        toolbar = {//elements of collapsing toolbar scope described there
            val textSize = (18 + (30 - 18) * state.toolbarState.progress).sp
            val miniTextSize =(18 + (20 - 18) * state.toolbarState.progress).sp

            Box(
                modifier = Modifier
                    .background(MaterialTheme.colors.primary) //toolbar will have yellow background color while it will be collapsed
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
                painter = painterResource(id = R.drawable.app_logo),
                contentDescription = "logo picture",
                alignment = Alignment.BottomEnd,
                modifier = Modifier.size(143.dp,390.dp)
            )
            Text(text= stringResource(R.string.installs_digit),
                modifier= Modifier
                    .road(whenCollapsed = Alignment.BottomStart, whenExpanded = Alignment.TopEnd)
                    .padding(vertical = 16.dp, horizontal = 85.dp),
                color = if(miniTextSize.value==18f) MaterialTheme.colors.onBackground else MaterialTheme.colors.secondary, //became brighter in collapsed state
                style = MaterialTheme.typography.body2,
                textAlign = TextAlign.Center,
                fontSize = if(miniTextSize.value<=19f)18.sp else 0.sp //invisible while have bigger font than 19f (i really like how it looks like)
            )
            Text(
                text = stringResource(R.string.market_app_name),
                modifier = Modifier
                    .road(Alignment.CenterStart, Alignment.BottomCenter)
                    .padding(all = 16.dp),
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
                Row (modifier = Modifier.padding(top = 2.dp)){
                    Spacer(modifier = Modifier.width(129.dp))

                    StarsRow( //function of star drawing (5 similar Images)
                        rating = 5.0,
                        painterFull = R.drawable.star_new,
                        painterSemi = R.drawable.star_semi_new,
                        painterBold = R.drawable.star_bold_new
                    )

                    Text(text= stringResource(R.string.installs_digit),
                        modifier=Modifier.padding(horizontal=4.dp),
                        color = MaterialTheme.colors.secondary,
                        style = MaterialTheme.typography.body2,
                        textAlign = TextAlign.End,
                        fontSize = 16.sp
                    )

                }

            }

            item {
                ScrollTag(tags = tagsList, context = context)


            }
            item {
                var isExpanded by remember { mutableStateOf(false) }
                val surfaceColor by animateColorAsState(
                    if (isExpanded)  MaterialTheme.colors.primary else MaterialTheme.colors.surface,//Color(244, 209, 68, 240)
                )
                Surface(shape = MaterialTheme.shapes.medium,
                    elevation = 0.5.dp,
                    color = surfaceColor, //this surface will change color to yellow after user click on it
                    modifier = Modifier
                        .clickable { isExpanded = !isExpanded }
                        .animateContentSize() //this surface is clickable with animation
                        .padding(
                            start = 1.dp,
                            end = 1.dp,
                            bottom = 18.dp
                        )
                ) {
                    Text(
                        text = stringResource(R.string.market_app_description),
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
                ScrollPictures(context)
            }
            item {
                Column {//Reviews head ellements
                    Text(
                        text =  stringResource(R.string.review_label) +" & " + stringResource(R.string.rating_label),
                        modifier = Modifier.padding(horizontal = 12.dp, vertical = 12.dp),
                        color = MaterialTheme.colors.onBackground,
                        style = MaterialTheme.typography.subtitle2,
                        fontSize = 20.sp
                    )
                    Row {

                        Text(
                            text = stringResource(R.string.rating_digit),
                            modifier = Modifier.padding(horizontal = 12.dp),
                            color = MaterialTheme.colors.onBackground,
                            style = MaterialTheme.typography.subtitle2,
                            fontSize = 50.sp
                        )
                        Column {
                            Spacer(modifier = Modifier.height(16.dp))

                            StarsRow( //function of full star drawing (5 similar Images)
                                rating = 4.9,
                                painterFull = R.drawable.star_new,
                                painterSemi = R.drawable.star_semi_new,
                                painterBold = R.drawable.star_bold_new
                            )

                            Spacer(modifier = Modifier.height(2.dp))
                            Text(
                                text = stringResource(R.string.installs_digit) + " " + stringResource(R.string.reviews_label),
                                modifier = Modifier.padding(horizontal = 4.dp),
                                color = MaterialTheme.colors.onPrimary,
                                style = MaterialTheme.typography.body2
                            )

                        }
                    }
                    Spacer(modifier = Modifier.height(12.dp))
                }

            }

            itemsIndexed(messages) { index,message -> //this construction optimize creation of similar reviews elements

                Every_user_review(message) //all Message data class type objects transmits in turn

                if(index < messages.lastIndex){
                    Divider(
                        color = Color.Gray,
                        thickness = 1.dp,
                        modifier = Modifier.padding(
                            top=12.dp,
                            bottom = 10.dp,
                            start = 24.dp,
                            end = 24.dp)

                    )
                }
            }
            item {
                Spacer(modifier = Modifier.height(12.dp))
                Button( //yellow clickable button
                    onClick = {
                        Toast.makeText(
                            context,
                             messageClickText,
                            Toast.LENGTH_LONG
                        ).show()
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .size(width = 327.dp, height = 80.dp)
                        .padding(horizontal = 18.dp, vertical = 18.dp),
                    colors = ButtonDefaults.buttonColors(backgroundColor = MaterialTheme.colors.primary)
                ) {
                    Text(
                        text = stringResource(R.string.button_text),
                        style = MaterialTheme.typography.button,
                        color = MaterialTheme.colors.background//Color(7, 7, 7, 255)
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
fun Every_user_review(msg:Message){//receive msg object which contain information about user logo, name, time and review
    Students_camp_practiseTheme {
        var isExpanded by remember { mutableStateOf(false) }
        val surfaceColor by animateColorAsState(
            if (isExpanded) MaterialTheme.colors.primary else MaterialTheme.colors.surface,
        )
        Column (modifier = Modifier.padding(horizontal = 12.dp, vertical =8.dp )){
            Row(modifier = Modifier.padding(all = 8.dp)) {
                Image(
                    painter = painterResource(msg.u_logo),
                    contentDescription = "user profile logo",
                    modifier = Modifier
                        .size(50.dp)
                        .clip(CircleShape)
                        .border(1.dp, color = MaterialTheme.colors.primary, CircleShape)
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
                        color = MaterialTheme.colors.secondary,//Color(220, 220, 220, 128),
                        style = MaterialTheme.typography.body2
                    )
                }

            }
            Surface(shape = MaterialTheme.shapes.medium,
                elevation =0.5.dp,
                color= surfaceColor, //this surface will change color to yellow after user click on it
                modifier= Modifier
                    .animateContentSize()
                    .clickable {
                        isExpanded = !isExpanded
                    } //this surface is clickable with animation
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
            R.drawable.user_logo
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
            R.drawable.user_logo_2
        ),
        Message(
            "Lisa Ajax",
            "February 5, 2016",
            "I think Kotlin will be my favorite programming language.\n" +
                    "It's so much fun!",
            R.drawable.user_logo_3
        ),
    )
}