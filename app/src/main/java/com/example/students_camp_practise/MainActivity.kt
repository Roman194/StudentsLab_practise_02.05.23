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
import com.example.students_camp_practise.assets.SampleData
import com.example.students_camp_practise.data.Message
import com.example.students_camp_practise.data.Tag
import com.example.students_camp_practise.data.TagMessage
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
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    MainScreenElements(SampleData.feedbackSample)
                }
            }
        }
    }
}

@Composable
fun MainScreenElements(messages: List<Message>) { //receive list of objects with Message data class type
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
                    .fillMaxWidth()
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
                color = MaterialTheme.colors.onBackground,
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
                    Spacer(modifier = Modifier.width(129.dp)) //How to make it universal?

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
                textDescription()
            }
            item {
                ScrollPictures(context)
            }
            item {
                reviewHead()
            }

            itemsIndexed(messages) { index,message -> //this construction optimize creation of similar reviews elements

                EveryUserReview(message) //all Message data class type objects transmits in turn

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
        MainScreenElements(SampleData.feedbackSample)
    }
}