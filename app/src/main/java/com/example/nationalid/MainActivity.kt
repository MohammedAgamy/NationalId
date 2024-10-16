package com.example.nationalid

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.Cyan
import androidx.compose.ui.graphics.Color.Companion.LightGray
import androidx.compose.ui.graphics.Color.Companion.Magenta
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.substring

import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsControllerCompat

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        WindowCompat.setDecorFitsSystemWindows(window, false) // Make content fullscreen

        setContent {
            ManageStatusBar(window)

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(top = 20.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                val cont = NationalId()
                ContactApp(cont)
            }

        }

    }
}


@OptIn(ExperimentalMaterial3Api::class, ExperimentalFoundationApi::class)
@Composable
fun ContactApp(nationalId: NationalId) {
    var textInPut by remember { mutableStateOf(nationalId.id) }
    var year by remember { mutableStateOf(nationalId.id) }
    var month by remember { mutableStateOf(nationalId.id) }
    var day by remember { mutableStateOf(nationalId.id) }
    var governorate by remember { mutableStateOf(nationalId.id) }
    var gender by remember { mutableStateOf(nationalId.id) }
    var isError by remember { mutableStateOf(false) }
    var errorMessage by remember { mutableStateOf("") }




    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(15.dp)
            .wrapContentSize(Alignment.Center)

    ) {
        val gradientColors = listOf(Cyan, LightGray, Magenta /*...*/)

        Text(
            text = "National Id Information ",
            fontStyle = FontStyle.Italic,
            fontWeight = FontWeight.Bold,
            fontSize = 25.sp,
            style = TextStyle(
                brush = Brush.linearGradient(
                    colors = gradientColors
                )
            ),
            modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp),
            textAlign = TextAlign.Center
        )

        Spacer(modifier = Modifier.height(16.dp))

        // fun to validate in text == 14 digit like egyption id number


        if (isError) {
            Text(
                text = errorMessage,
                color = Color.Red,
                fontSize = 12.sp,
                modifier = Modifier.padding(top = 4.dp)
            )
        }
        OutlinedTextField(
            value = textInPut,
            onValueChange = { newText ->
                textInPut = newText;isError = newText.isBlank() || newText.length < 15
                errorMessage = if (newText.isBlank()) {
                    "This field cannot be empty"
                } else if (newText.length < 15) {
                    "Input must be at least 14 characters"
                } else {
                    ""
                }
            },
            label = {
                Text(text = "Enter Your National Id ")
            },
            isError = isError,
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            keyboardOptions = KeyboardOptions(
                imeAction = ImeAction.Next, keyboardType = KeyboardType.Number
            ),

            shape = RoundedCornerShape(8.dp)
        )

        //space
        Spacer(modifier = Modifier.height(16.dp))
        //show year
        Text(text = year, fontSize = 30.sp)
        //
        Spacer(modifier = Modifier.height(16.dp))
        //show month
        Text(text = month, fontSize = 30.sp)

        //
        Spacer(modifier = Modifier.height(16.dp))
        //show day
        Text(text = day, fontSize = 30.sp)
        //space
        Spacer(modifier = Modifier.height(16.dp))
        //governorate
        Text(text = governorate, fontSize = 30.sp)
        Spacer(modifier = Modifier.height(16.dp))
        //gender
        Text(text = gender, fontSize = 30.sp)
        Spacer(modifier = Modifier.height(16.dp))

        //click to asgin value from outline text to text view
        Button(
            onClick = {
                val numberId = textInPut.ifBlank { "Wait to inPout" }
                //get year
                val result = numberId.substring(2, 4)
                val indexOne = numberId.substring(1, 2)
                Log.d("TagInf", "index one $indexOne")
                // validate 2000 or 1900
                if (indexOne == "2") {
                    year = "Birth Year : 19$result"

                } else if (indexOne == "3") {
                    year = "Birth Year : 20$result"
                } else {
                    Log.d("TagInf", "notFound")
                }

                //get month
                val monthIndex = numberId.substring(4, 6)
                month = "Month : $monthIndex "

                ///get day
                val dayIndex = numberId.substring(6, 8)
                day = "Day : $dayIndex "

                //get governorate
                val governorateIndex = numberId.substring(8, 10)
                if (governorateIndex == "01") {
                    governorate = "Governorate of Birth :Cairo"
                } else if (governorateIndex == "23") {
                    governorate = "Government of Birth :Fayoum"
                } else {
                    Log.d("TagInf", "error")
                }


                //get Gander
                val ganderIndex = numberId.substring(11, 13).toInt()
                if (ganderIndex % 2 == 0) {
                    gender = "Gander : Man"
                } else {
                    gender = "Gander : Woman"
                }

                // year = result
                //Log.d("TagInf", year)al g
            },
            Modifier
                .fillMaxWidth()
                .padding(20.dp)
        ) {
            Text(text = "Add ID")
        }


    }


}


@Composable
fun ManageStatusBar(window: android.view.Window) {
    // Set the status bar color and icon behavior directly
    SideEffect {
        window.statusBarColor = android.graphics.Color.WHITE // Set status bar color
        WindowInsetsControllerCompat(window, window.decorView).isAppearanceLightStatusBars = false
    }

}


data class NationalId(val id: String = " ")






