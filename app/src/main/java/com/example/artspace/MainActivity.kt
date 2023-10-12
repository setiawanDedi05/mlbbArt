package com.example.artspace

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.artspace.ui.theme.ArtSpaceTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ArtSpaceTheme {
                ArtSpaceApp()
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Preview(showBackground = true)
@Composable
fun ArtSpaceApp() {
    var currentHero by remember{
        mutableStateOf(2)
    }
    Scaffold(
        topBar = {
        CenterAlignedTopAppBar(
            title = {
                Text(
                    text = "MLBB Art", fontWeight = FontWeight.Bold
                )
            },
            colors = TopAppBarDefaults.smallTopAppBarColors(
                containerColor = MaterialTheme.colorScheme.primaryContainer,
                titleContentColor = MaterialTheme.colorScheme.onPrimaryContainer,
                navigationIconContentColor = MaterialTheme.colorScheme.onPrimaryContainer,
                actionIconContentColor = MaterialTheme.colorScheme.onPrimaryContainer
            )
        )
        }
    ) { innerPadding ->
        Surface(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .background(MaterialTheme.colorScheme.tertiaryContainer),
            color = MaterialTheme.colorScheme.background
        ){
            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                when(currentHero){
                    1 -> HeroImageAndText(image = R.drawable.chou, title = R.string.chou)
                    2 -> HeroImageAndText(image = R.drawable.gs, title = R.string.gusion)
                    3 -> HeroImageAndText(image = R.drawable.angela, title = R.string.angela)
                    4 -> HeroImageAndText(image = R.drawable.claude, title = R.string.claude)
                    5 -> HeroImageAndText(image = R.drawable.floryn, title = R.string.floryn)
                    6 -> HeroImageAndText(image = R.drawable.clint, title = R.string.clint)
                    7 -> HeroImageAndText(image = R.drawable.johnson, title = R.string.johnson)
                    8 -> HeroImageAndText(image = R.drawable.khufra, title = R.string.khufra)
                    9 -> HeroImageAndText(image = R.drawable.lancelot, title = R.string.lancelot)
                    10 -> HeroImageAndText(image = R.drawable.leomord, title = R.string.leomord)
                    else -> HeroImageAndText(image = R.drawable.xavier, title = R.string.xavier)
                }
                Spacer(modifier = Modifier.height(10 .dp))
                ButtonControll(
                    onPrev = {
                            currentHero--
                    },
                    onNext = {
                            currentHero++
                    },
                    isPrevEnable = currentHero != 1,
                    isNextEnable = currentHero != 11
                )
            }
        }
    }
}

@Composable
fun ButtonControll(
    isPrevEnable : Boolean,
    isNextEnable : Boolean,
    onPrev : () -> Unit,
    onNext : () -> Unit
){
    Row() {
        Button(onClick = onPrev, enabled = isPrevEnable) {
            Text(text = stringResource(R.string.previous))
        }
        Spacer(modifier = Modifier.width(10 .dp))
        Button(onClick = onNext, enabled = isNextEnable) {
            Text(text = stringResource(R.string.next))
        }
    }
}

@Composable
fun HeroImageAndText(
    @StringRes title : Int,
    @DrawableRes image: Int
    ){
    val configuration = LocalConfiguration.current
    val screenWidth = configuration.screenWidthDp.dp
    val screenHeight = configuration.screenHeightDp.dp
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Image(
            painter = painterResource(image),
            contentDescription = stringResource(id = title),
            modifier = Modifier
                .width(screenWidth.times(0.8f))
                .height(screenHeight.times(0.5f)),
            contentScale = ContentScale.Crop
        )
        Spacer(modifier = Modifier.height(10 .dp))
        Text(text = stringResource(id = title))
    }
}

