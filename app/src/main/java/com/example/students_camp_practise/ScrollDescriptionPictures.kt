package com.example.students_camp_practise

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp

@Composable
fun ScrollPictures(context: Context){
    var isExpanded by remember { mutableStateOf(false) }
    LazyRow (
        horizontalArrangement = Arrangement.spacedBy(10.dp),
        contentPadding = PaddingValues(end = 10.dp)
    ){
        item {
            Box {//this box is used to make video symbol over image. Image is still clickable under it
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
                                    "There will be MOBA video snippet in next versions :)",
                                    Toast.LENGTH_LONG
                                )
                                .show()
                        },
                    alignment = Alignment.BottomEnd
                )
                Row (modifier = Modifier.padding(start = 90.dp)){
                    Icon(
                        bitmap = ImageBitmap.imageResource(R.drawable.video_symbol),
                        contentDescription = "video symbol",
                        modifier = Modifier
                            .size(80.dp, 128.dp)
                    )
                }

            }
            Image(
                painter = painterResource(R.drawable.description_picture_1),
                contentDescription = "app screen 1",
                modifier = Modifier.size(250.dp, 128.dp),
                alignment = Alignment.BottomEnd
            )
        }
    }
}