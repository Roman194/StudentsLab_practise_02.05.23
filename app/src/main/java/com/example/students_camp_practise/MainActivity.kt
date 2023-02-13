package com.example.students_camp_practise

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
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
                    Greeting("Android")
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String) {
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
                //Spacer(modifier = Modifier.padding(vertical = 16.dp))
                Image(
                    painter = painterResource(R.drawable.mask_logo),
                    contentDescription = "logo picture",
                    modifier = Modifier.size(149.dp, 395.dp),
                    alignment = Alignment.BottomEnd
                )
            }
        }
        item {
            Text(text = "Dota 2 is a multiplayer online battle arena (MOBA) game which has two teams of five players compete to collectively destroy a large structure defended by the opposing team known as the \"Ancient\", whilst defending their own.",
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 12.dp, vertical = 12.dp),
                style = MaterialTheme.typography.body2)
        }
        item{
            Row{
                Image(
                    painter = painterResource(R.drawable.description_picture),
                    contentDescription = "logo picture",
                    modifier = Modifier.size(250.dp, 128.dp),
                    alignment = Alignment.BottomEnd
                )
                Image(
                    painter = painterResource(R.drawable.description_picture_1),
                    contentDescription = "logo picture",
                    modifier = Modifier.size(240.dp, 128.dp),
                    alignment = Alignment.BottomEnd
                )
            }
        }
        item(){
            Spacer(modifier = Modifier.height(12.dp))
            Button(onClick = { Toast.makeText(context,"Sorry, can't install it now \nYou will have ability to install it in next versions of app",Toast.LENGTH_LONG).show() },
                modifier= Modifier
                    .fillMaxWidth()
                    .size(width = 327.dp, height=80.dp)
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

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    Students_camp_practiseTheme {
        Greeting("Android")
    }
}