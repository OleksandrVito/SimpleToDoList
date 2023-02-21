package ua.vitolex.to_dolist.ui

import android.view.animation.OvershootInterpolator
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import kotlinx.coroutines.delay
import ua.vitolex.to_dolist.R
import ua.vitolex.to_dolist.util.Routes

@Composable
fun SplashScreen(
    navController: NavController
) {
    val scale = remember {
        Animatable(1f)
    }
    val alpha = remember {
        Animatable(0f)
    }

    LaunchedEffect(key1 = true) {
        scale.animateTo(
            targetValue = 3f,
            animationSpec = tween(
                durationMillis = 2000,
                easing = {
                    OvershootInterpolator(4f).getInterpolation(it)
                }
            )
        )
        delay(0L)
        alpha.animateTo(
            targetValue = 1f,
            animationSpec = tween(
                durationMillis = 1000,
                easing = {
                    OvershootInterpolator(1f).getInterpolation(it)
                }
            )
        )
        delay(0L)
        navController.navigate(Routes.TODO_LIST)
    }
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colors.primary),
        contentAlignment = Alignment.Center,
    ) {
        Column( modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center) {
            Image(
                painter = painterResource(id = R.drawable.ic_todo_96),
                contentDescription = stringResource(R.string.list),
                modifier = Modifier
                    .scale(scale.value)
                    .clip(RoundedCornerShape(3.dp))
            )
            Spacer(modifier = Modifier.height(48.dp))
            Text(
                text = stringResource(R.string.todo_list),
                style = MaterialTheme.typography.body1,
                color = MaterialTheme.colors.secondary,
                fontSize = 24.sp,
                fontWeight= FontWeight.Bold,
                modifier = Modifier.alpha(alpha.value)
            )
        }
    }
}
