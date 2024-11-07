package com.abiramee.meetmax

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.abiramee.meetmax.auth.presentation.AuthAction
import com.abiramee.meetmax.auth.presentation.AuthScreen
import com.abiramee.meetmax.auth.presentation.AuthShare
import com.abiramee.meetmax.auth.presentation.AuthViewmodel
import com.abiramee.meetmax.auth.presentation.models.AuthUI
import com.abiramee.meetmax.feed.FeedScreen
import com.abiramee.meetmax.ui.theme.MeetMaxTheme
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import kotlinx.coroutines.launch
import kotlinx.serialization.Serializable
import org.koin.androidx.compose.koinViewModel

@Suppress("UNREACHABLE_CODE")
class MainActivity : ComponentActivity() {
    @SuppressLint("CoroutineCreationDuringComposition")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        enableEdgeToEdge()
        setContent {
            val systemUiController = rememberSystemUiController()
            val useDarkIcons = !isSystemInDarkTheme()

            val viewModel = koinViewModel<AuthViewmodel>()
            val state by viewModel.state.collectAsStateWithLifecycle()// Determine if dark icons should be used


            LaunchedEffect(key1 = systemUiController, key2 = useDarkIcons) {
                systemUiController.setStatusBarColor(
                    color = Color(0xFFF9F9F9),
                )
            }
            MeetMaxTheme {
                val navController = rememberNavController()
                lifecycleScope.launch {
                    repeatOnLifecycle(Lifecycle.State.STARTED) {
                        viewModel.share.collect {
                            when (it) {
                                is AuthShare.Navigation -> {
                                    navController.navigate(ScreenB)
                                }
                                is AuthShare.ToastError -> {
                                    Toast.makeText(this@MainActivity, it.message, Toast.LENGTH_SHORT).show()
                                }
                            }
                        }
                    }
                }
                Scaffold(
                    modifier = Modifier
                        .statusBarsPadding()
                        .fillMaxSize()
                ) { innerPadding ->
                    NavHost(
                        navController = navController,
                        startDestination = ScreenA
                    ) {
                        composable<ScreenA> {
                            AuthScreen(
                                modifier = Modifier.padding(top = innerPadding.calculateTopPadding()),
                                state = state
                            ) {
                                viewModel.onAction(it)
                            }
                        }
                        composable<ScreenB> {
                            FeedScreen()
                        }
                    }
                }

//                lifecycleScope.launch {
//                    delay(2000L)
//                    navController.navigate(ScreenB)
//                }
            }
        }
    }
}

@Serializable
object ScreenA

@Serializable
object ScreenB

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    MeetMaxTheme {
        Greeting("Android")
    }
}