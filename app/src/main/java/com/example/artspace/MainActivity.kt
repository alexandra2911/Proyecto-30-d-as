package com.example.artspace

import android.os.Bundle
import android.widget.Button
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.artspace.ui.theme.ArtSpaceTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ArtSpaceTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    ArtSpaceImages()
                }
            }
        }
    }
}

data class Celebrity(
    @DrawableRes val imageId: Int,
    val name: String,
    val age: Int
)

@Composable
fun ArtSpaceImages(modifier: Modifier = Modifier) {
    val celebrities = remember {
        listOf(
             Celebrity(R.drawable.cha_eun_woo, "Cha Eun Woo", 27),
             Celebrity(R.drawable.lee_min_hoo, "Lee Min Hoo", 36),
             Celebrity(R.drawable.kim_hyun_joong, "Kim Hyun Joong", 37),
             Celebrity(R.drawable.song_kang, "Song Kang", 30),
             Celebrity(R.drawable.kim_bum, "Kim Bum", 34),
             Celebrity(R.drawable.kim_soo_hyun, "Kim Soo Hyun", 36),
             Celebrity(R.drawable.lee_jong_suk, "Lee Jong Suk", 34),
             Celebrity(R.drawable.ji_chang_wook, "Ji Chang Wook", 36),
             Celebrity(R.drawable.kim_seon_ho, "Kim Seon Ho", 37),
             Celebrity(R.drawable.nam_joo_kyuk, "Nam Joo Hyuk", 30)
        )
    }

    var currentIndex by remember { mutableStateOf(0) }
    val currentCelebrity = celebrities[currentIndex]

    Column(
        modifier = modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(100.dp))
        CelebrityDisplay(currentCelebrity = currentCelebrity)
        Spacer(modifier = modifier.height(16.dp))
        CelebrityDetails(name = currentCelebrity.name, age = currentCelebrity.age)
        Spacer(modifier = modifier.height(25.dp))
        Row(
            modifier = modifier.padding(horizontal = 8.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp, Alignment.CenterHorizontally)
        ) {
            Button(
                onClick = {
                    currentIndex = (currentIndex - 1 + celebrities.size) % celebrities.size
                },
                colors = ButtonDefaults.buttonColors(
                    containerColor = colorResource(id = R.color.gray_900)
                ),
                elevation = ButtonDefaults.buttonElevation(
                    defaultElevation = 1.dp,
                    pressedElevation = 0.dp,
                    focusedElevation = 0.dp
                )
            ) {
                Text(
                    text = "Previous",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Medium,
                    color = colorResource(id = R.color.white)
                )
            }

            Button(
                onClick = {
                    currentIndex = (currentIndex + 1) % celebrities.size
                },
                colors = ButtonDefaults.buttonColors(
                    containerColor = colorResource(id = R.color.gray_900)
                ),
                elevation = ButtonDefaults.buttonElevation(
                    defaultElevation = 1.dp,
                    pressedElevation = 0.dp,
                    focusedElevation = 0.dp
                )
            ) {
                Text(
                    text = "Next",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Medium,
                    color = colorResource(id = R.color.white)
                )
            }
        }
    }
}

@Composable
fun CelebrityDisplay(
    modifier: Modifier = Modifier,
    currentCelebrity: Celebrity
) {
    Image(
        painter = painterResource(currentCelebrity.imageId),
        contentDescription = null,
        modifier = modifier.size(200.dp),
        contentScale = ContentScale.Crop
    )
}

@Composable
fun CelebrityDetails(
    name: String,
    age: Int
){
    Column (
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = name,
            fontWeight = FontWeight.Bold,
            color = colorResource(id = R.color.black),
            fontSize = 24.sp
        )

        Text(
            text = "$age años",
            fontSize = 16.sp,
            fontWeight = FontWeight.Medium,
            color = colorResource(id = R.color.gray_300)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun ArtSpaceImagesPreview() {
    ArtSpaceTheme {
        ArtSpaceImages()
    }
}
