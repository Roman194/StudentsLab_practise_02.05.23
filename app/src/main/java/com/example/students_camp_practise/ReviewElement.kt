package com.example.students_camp_practise

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.students_camp_practise.data.Message
import com.example.students_camp_practise.ui.theme.Students_camp_practiseTheme

@Composable
fun EveryUserReview(msg: Message){//receive msg object which contain information about user logo, name, time and review
    Students_camp_practiseTheme {
        var isExpanded by remember { mutableStateOf(false) }
        val surfaceColor by animateColorAsState(
            if (isExpanded) MaterialTheme.colors.primary else MaterialTheme.colors.surface,
        )
        Column (modifier = Modifier.padding(horizontal = 12.dp, vertical =8.dp )){
            Row(modifier = Modifier.padding(all = 8.dp)) {
                Image(
                    painter = painterResource(msg.uLogo),
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