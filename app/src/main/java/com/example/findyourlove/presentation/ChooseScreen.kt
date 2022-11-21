package com.example.findyourlove.presentation

import android.widget.Toast
import androidx.compose.animation.*
import androidx.compose.animation.core.MutableTransitionState
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign

import androidx.compose.ui.unit.dp
import androidx.compose.ui.util.lerp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.findyourlove.LoveScreen
import com.example.findyourlove.R
import com.example.findyourlove.data.users
import com.google.accompanist.pager.*
import kotlinx.coroutines.launch
import kotlin.math.absoluteValue

@OptIn(ExperimentalPagerApi::class)
@Composable
fun ChooseScreen(navController: NavController) {
    val state = rememberPagerState()
    val scope = rememberCoroutineScope()
    val context = LocalContext.current
    Box(
        modifier = Modifier
            .fillMaxSize()
            .fillMaxHeight()
            .fillMaxWidth()
    ) {
        Image(
            painter = painterResource(id = R.drawable.backgraund),
            contentDescription = "background",
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight()
                .fillMaxSize(),
            contentScale = ContentScale.FillHeight
        )
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.Center)
        ) {
            Text(
                text = "RANDOM USER ID READY",
                fontSize = 30.sp,
                color = colorResource(id = R.color.red_project),
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center,
                modifier = Modifier.align(Alignment.CenterHorizontally)
            )
            Spacer(modifier = Modifier.padding(top = 10.dp))
            Text(
                text = "online: ${users[state.currentPage].id}",
                fontSize = 25.sp,
                color = colorResource(id = R.color.red_project),
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center,
                modifier = Modifier.align(Alignment.CenterHorizontally)
            )
            Spacer(modifier = Modifier.padding(top = 25.dp))
            HorizontalPagerWithOffsetTransition(state = state)
            Spacer(modifier = Modifier.padding(top = 15.dp))
            Row(
                modifier = Modifier
                    .padding(start = 20.dp, end = 20.dp)
                    .align(Alignment.CenterHorizontally)
            ) {

                Image(painter = painterResource(id = R.drawable.ic_next_button),
                    contentDescription = "next_button",
                    modifier = Modifier
                        .width(70.dp)
                        .height(70.dp)
                        .clickable {
                            scope.launch {
                                if (state.currentPage != users.size - 1) {
                                    state.scrollToPage(state.currentPage + 1)
                                }
                            }
                        })
                Spacer(modifier = Modifier.padding(start = 40.dp))
                Image(painter = painterResource(id = R.drawable.ic_message_button),
                    contentDescription = "message_button",
                    modifier = Modifier
                        .width(70.dp)
                        .height(70.dp)
                        .clickable {
                            navController.navigate(LoveScreen.Internet.screen_route)
                        })
            }
            Spacer(modifier = Modifier.padding(top = 15.dp))
            Button(
                onClick = {
                  navController.navigate(LoveScreen.Camera.screen_route)
                },
                shape = RoundedCornerShape(60),
                colors = ButtonDefaults.buttonColors(
                    backgroundColor = colorResource(R.color.red_project),
                    contentColor = Color.White),
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .padding(start = 30.dp, end = 30.dp)
                    .width(300.dp)
                    .height(70.dp)
            ) {
                Text(text = "TO START SEARCHING", fontSize = 18.sp)
            }
            Image(painter = painterResource(id = R.drawable.ic_settings_button),
                contentDescription = "settings_button",
                modifier = Modifier
                    .width(180.dp)
                    .height(80.dp)
                    .align(Alignment.CenterHorizontally)
                    .clickable {
                        navController.navigate(LoveScreen.Settings.screen_route)
                    })


        }

    }


}


@OptIn(ExperimentalPagerApi::class)
@Composable
fun HorizontalPagerWithOffsetTransition(modifier: Modifier = Modifier, state: PagerState) {

    HorizontalPager(
        count = users.size,
        state = state,
        // Add 32.dp horizontal padding to 'center' the pages
        contentPadding = PaddingValues(horizontal = 60.dp),

        ) { page ->


        Card(
            Modifier
                .graphicsLayer {
                    // Calculate the absolute offset for the current page from the
                    // scroll position. We use the absolute value which allows us to mirror
                    // any effects for both directions
                    val pageOffset = calculateCurrentOffsetForPage(page).absoluteValue

                    // We animate the scaleX + scaleY, between 85% and 100%
                    lerp(
                        start = 0.90f,
                        stop = 1f,
                        fraction = 1f - pageOffset.coerceIn(0f, 1f)
                    ).also { scale ->
                        scaleX = scale
                        scaleY = scale
                    }

                    // We animate the alpha, between 50% and 100%
                    alpha = lerp(
                        start = 0.5f,
                        stop = 1f,
                        fraction = 1f - pageOffset.coerceIn(0f, 1f)
                    )
                }
                .fillMaxWidth()
                .aspectRatio(1f),
            shape = CircleShape
        ) {
            Box {
                Image(
                    painter = painterResource(id = users[page].imageResourceId),
                    contentDescription = null,
                    modifier = Modifier.fillMaxSize(),
                )

            }
        }

    }
}


@Composable
fun <T> T.AnimationBox(
    enter: EnterTransition = expandVertically() + fadeIn(),
    exit: ExitTransition = fadeOut() + shrinkVertically(),
    content: @Composable T.() -> Unit
) {
    val state = remember {
        MutableTransitionState(false).apply {
            // Start the animation immediately.
            targetState = true
        }
    }

    AnimatedVisibility(
        visibleState = state,
        enter = enter,
        exit = exit
    ) { content() }
}

