package com.example.students_camp_practise

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun reviewHead(){
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

@Preview
@Composable
fun HeadPreview(){
    reviewHead()
}