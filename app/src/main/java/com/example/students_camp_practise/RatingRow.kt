package com.example.students_camp_practise

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp

@Composable
fun StarsRow(rating: Double, painterFull: Int, painterSemi: Int, painterBold: Int){
    var rate = rating
    Row {
        for (iteration in 0..4) {
            when (rate) {
                in 0.1..0.95 -> Star(iteration, painterSemi)
                0.0 -> Star(iteration, painterBold)
                else -> Star(iteration, painterFull)
            }
            if(rate>=1.0){
                rate-=1.0
            }else{
                if(rate>0.0){
                    rate=0.0
                }
            }
        }
    }

}

@Composable
fun Star(iteration: Int, painter: Int){
    Image( //making of similar image elements. They differ only by contentDescription
        painter = painterResource(painter),
        contentDescription = "${iteration + 1} star", //depends from for cycle iteration
        modifier = Modifier.size(16.dp, 20.dp),
        alignment = Alignment.CenterEnd,
    )
    Spacer(modifier = Modifier.width(4.dp))
}