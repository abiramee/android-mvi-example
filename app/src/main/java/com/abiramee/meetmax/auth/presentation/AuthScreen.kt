package com.abiramee.meetmax.auth.presentation

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.windowInsetsEndWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.abiramee.meetmax.R
import com.abiramee.meetmax.auth.presentation.components.ForgotPasswordForm
import com.abiramee.meetmax.auth.presentation.components.SignInForm
import com.abiramee.meetmax.auth.presentation.models.AuthUI
import com.abiramee.meetmax.ui.theme.MeetMaxTheme

@Composable
fun AuthScreen(
    state: AuthState, modifier: Modifier = Modifier, onAction: (AuthAction) -> Unit
) {
    if (state.isLoading) {
        Box(
            modifier = modifier
                .fillMaxSize()
                .background(Color(0xFFF9F9F9)),
            contentAlignment = Alignment.Center
        ) {
            CircularProgressIndicator()
        }
    } else {
        val scrollState = rememberScrollState()
        Column (
            modifier = modifier
                .fillMaxHeight()
                .background(Color(0xFFF9F9F9))
                .padding(start = 24.dp, end = 24.dp)
                .verticalScroll(state = scrollState)
        ) {
            Row(
                modifier = modifier
                    .fillMaxWidth()
                    .height(60.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Image(
                    modifier = modifier.size(80.dp),
                    imageVector = ImageVector.vectorResource(id = R.drawable.logo),
                    contentDescription = "logo"
                )

                var expanded by remember { mutableStateOf(false) }
                var selectedIndex: Int by remember { mutableIntStateOf(0) }
                val items = listOf("English(UK)")

                Row(
                    modifier = modifier
                        .height(30.dp)
                        .background(color = Color(0xFFF9F9F9))
                        .shadow(
                            elevation = 2.dp,
                            shape = RectangleShape,
                            ambientColor = Color.LightGray,
                            spotColor = Color.LightGray
                        )
                        .clip(RoundedCornerShape(5.dp))
                ) {
                    IconButton(
                        onClick = { expanded = true },
                        modifier = modifier
                            .background(Color.White)
                            .width(100.dp)
                    ) {
                        Row {
                            Text(
                                text = items[selectedIndex],
                                textAlign = TextAlign.Center,
                                color = Color.Gray,
                                fontSize = 10.sp,
                                fontWeight = FontWeight.Bold,
                                modifier = modifier.background(color = Color(0xFFFFFFFF))
                            )
                            Icon(Icons.Filled.ArrowDropDown, "print", tint = Color.Gray)
                        }
                    }
                    DropdownMenu(
                        expanded = expanded,
                        onDismissRequest = { expanded = false },
                        modifier = Modifier.width(100.dp)
                    ) {
                        items.forEachIndexed { index, s ->
                            DropdownMenuItem(text = { Text(s) }, onClick = {
                                selectedIndex = index
                                expanded = false
                            })
                        }
                    }
                }
            }

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 60.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column(
                    modifier = modifier.fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = when (state.screen) {
                            is AuthUI.SignIn -> (state.screen as AuthUI.SignIn).title1
                            is AuthUI.SignUp -> (state.screen as AuthUI.SignUp).title1
                            is AuthUI.ForgotPassword -> (state.screen as AuthUI.ForgotPassword).title1
                        },
                        color = Color(0xFF4E5D78),
                        fontSize = 22.sp,
                        fontWeight = FontWeight.Bold,
                    )
                    Text(
                        modifier = modifier
                            .padding(top = 20.dp)
                            .width(300.dp),
                        text = when (state.screen) {
                            is AuthUI.SignIn -> (state.screen as AuthUI.SignIn).title2
                            is AuthUI.SignUp -> (state.screen as AuthUI.SignUp).title2
                            is AuthUI.ForgotPassword -> (state.screen as AuthUI.ForgotPassword).title2
                        },
                        textAlign = TextAlign.Center,
                        color = Color(0xFF4E5D78),
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Bold,
                    )
                }
            }
            Spacer(modifier = modifier.size(30.dp))
            Box(modifier = modifier.animateContentSize()) {
                when (state.screen) {
                    is AuthUI.SignIn -> SignInForm(onNavigateSignUp = {
                        onAction(AuthAction.OnNavigateSignUpClick)
                    }, onNavigateForgotPassword = {
                        onAction(AuthAction.OnNavigateForgotPasswordClick)
                    }, onSignInClick = {
                        onAction(AuthAction.OnSignInClick(it))
                    })

                    is AuthUI.SignUp -> SignUpForm(onNavigateSignInButtonClicked = {
                        onAction(AuthAction.OnNavigateSignInClick)
                    }, onSignUpButtonClicked = {
                        onAction(AuthAction.OnSignUpClick(it))
                    })

                    is AuthUI.ForgotPassword -> ForgotPasswordForm(onNavigateSignIn = {
                        onAction(it)
                    }, onForgotPasswordSendClick = {
                        onAction(AuthAction.OnForgotPasswordSentClick(it))
                    })
                }
            }
            Spacer(modifier= modifier.size(30.dp))
        }
    }
}

@Preview(device = Devices.PIXEL_7A)
@Composable
fun AuthScreenPreview() {
    MeetMaxTheme {

    }
}

internal val previewState = AuthState(
    screen = AuthUI.SignIn()
)
