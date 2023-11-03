package com.example.students_camp_practise

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun textDescription(){
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
