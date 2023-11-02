package com.example.students_camp_practise

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Chip
import androidx.compose.material.ChipDefaults
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun ScrollTag(tags:List<Tag>,context: Context){
    val chipClickMessage = stringResource(R.string.tag_clck_msg)
    val chipClickMessageSecond = stringResource(R.string.tag_clck_msg_2)
    val chipClckMsgThrd = stringResource(R.string.tag_clck_msg_3)

    LazyRow(
        horizontalArrangement = Arrangement.spacedBy(10.dp),
        contentPadding = PaddingValues(start = 24.dp, end = 24.dp),
        modifier = Modifier.padding(top = 16.dp, bottom = 16.dp)
    ){
        items(tags){tag->

            Chip(
                onClick = {
                    Toast
                        .makeText(
                            context,
                            chipClickMessage + " ${tag.onClickMessage.name}? \n" +
                                    chipClickMessageSecond + " ${tag.onClickMessage.category} " + chipClckMsgThrd,
                            Toast.LENGTH_LONG
                        )
                        .show()
                },
                colors = ChipDefaults.chipColors(backgroundColor = MaterialTheme.colors.secondaryVariant),
                content = {
                    Text(
                        text = tag.tagName,
                        modifier = Modifier.padding(horizontal = 8.dp, vertical = 3.dp),
                        color = MaterialTheme.colors.primaryVariant,
                        style = MaterialTheme.typography.body2,
                        fontSize = 12.sp,
                        textAlign = TextAlign.End
                    )
                }
            )
        }
    }
}