package com.abiramee.meetmax.auth.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.abiramee.meetmax.R
import com.abiramee.meetmax.auth.domain.SignUpModel
import com.abiramee.meetmax.auth.presentation.DateDefaults.DATE_LENGTH
import com.abiramee.meetmax.auth.presentation.DateDefaults.DATE_MASK
import com.abiramee.meetmax.core.domain.util.MaskVisualTransformation
import com.abiramee.meetmax.ui.theme.Blue80

object DateDefaults {
    const val DATE_MASK = "##/##/####"
    const val DATE_LENGTH = 8 // Equals to "##/##/####".count { it == '#' }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SignUpForm(
    modifier: Modifier = Modifier,
    onNavigateSignInButtonClicked: () -> Unit,
    onSignUpButtonClicked: (SignUpModel) -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(5.dp))
            .background(Color.White)
    ) {
        Column(
            modifier = Modifier
                .padding(20.dp)
                .background(Color.White)
        ) {
            Row(
                modifier = modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Button(
                    onClick = {},
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xFFf6f7f8),
                    ),
                    shape = RoundedCornerShape(5.dp),
                    contentPadding = PaddingValues(
                        start = 10.dp,
                        end = 10.dp,
                        top = 10.dp,
                        bottom = 10.dp
                    ),
                    modifier = modifier
                        .clip(RectangleShape)
                        .background(Color.White)
                        .weight(1f)
                ) {
                    Icon(
                        imageVector = ImageVector.vectorResource(R.drawable.google),
                        contentDescription = "google",
                        tint = Color(0XFF4E5D78)
                    )
                    Spacer(modifier = modifier.size(5.dp))
                    Text(
                        text = "Log in with Google",
                        color = Color(0XFF4E5D78),
                        fontSize = 12.sp
                    )
                }
                Spacer(modifier = modifier.size(10.dp))
                Button(
                    onClick = {},
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xFFf6f7f8),
                    ),
                    contentPadding = PaddingValues(
                        start = 10.dp,
                        end = 10.dp,
                        top = 10.dp,
                        bottom = 10.dp
                    ),
                    shape = RoundedCornerShape(5.dp),
                    modifier = modifier
                        .background(Color.White)
                        .weight(1f)
                ) {
                    Icon(
                        imageVector = ImageVector.vectorResource(R.drawable.apple),
                        contentDescription = "google",
                        tint = Color(0XFF4E5D78)
                    )
                    Spacer(modifier = modifier.size(5.dp))
                    Text(
                        text = "Log in with Apple",
                        color = Color(0XFF4E5D78),
                        fontSize = 12.sp
                    )
                }
            }
            Spacer(modifier = modifier.size(10.dp))
            Row(
                modifier = modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Box(
                    modifier = modifier
                        .weight(1f)
                        .fillMaxWidth()
                        .height(1.dp)
                        .background(Color(0xFFe6e8ec))
                )
                Spacer(modifier = modifier.size(10.dp))
                Text(
                    text = "OR",
                    color = Color(0xFF4E5D78),
                    fontSize = 10.sp,
                    fontWeight = FontWeight.Bold
                )
                Spacer(modifier = modifier.size(10.dp))
                Box(
                    modifier = modifier
                        .weight(1f)
                        .fillMaxWidth()
                        .height(1.dp)
                        .background(Color(0xFFe6e8ec))
                )
            }

            Spacer(modifier = modifier.size(10.dp))
            var email by remember { mutableStateOf("") }
            var name by remember { mutableStateOf("") }
            var password by remember { mutableStateOf("") }
            var date by remember { mutableStateOf("") }
            var gender by remember { mutableStateOf("male") }

            val colorConfigTextfield = TextFieldDefaults.colors(
                focusedContainerColor = Color.White,
                unfocusedContainerColor = Color.White,
                unfocusedIndicatorColor = Color.White,
                focusedIndicatorColor = Color.White,
                focusedLeadingIconColor = Color(0xFF4E5D78),
                unfocusedLeadingIconColor = Color(0xFF4E5D78),
                focusedTextColor = Color(0xFF4E5D78),
                unfocusedTextColor = Color(0xFF4E5D78),
                unfocusedTrailingIconColor = Color(0xFF4E5D78),
            )

            TextField(
                value = email,
                onValueChange = { email = it },
                modifier = modifier
                    .fillMaxWidth()
                    .border(
                        width = 1.dp,
                        color = Color(0xFFe7e9ec), // Set the border color to gray
                        shape = RoundedCornerShape(4.dp)
                    ),
                colors = colorConfigTextfield,
                textStyle = TextStyle(
                    fontWeight = FontWeight.Bold
                ),
                placeholder = {
                    Text(
                        text = "Your Email",
                        color = Color(0xFF9ca4b3),
                        fontWeight = FontWeight.Bold,
                        fontSize = 14.sp
                    )
                },
                leadingIcon = {
                    Icon(
                        imageVector = ImageVector.vectorResource(R.drawable.main),
                        contentDescription = "mail"
                    )
                }
            )
            Spacer(modifier = modifier.size(10.dp))
            TextField(
                value = name,
                onValueChange = { name = it },
                modifier = modifier
                    .fillMaxWidth()
                    .border(
                        width = 1.dp,
                        color = Color(0xFFe7e9ec), // Set the border color to gray
                        shape = RoundedCornerShape(4.dp)
                    ),
                colors = colorConfigTextfield,
                textStyle = TextStyle(
                    fontWeight = FontWeight.Bold
                ),
                placeholder = {
                    Text(
                        text = "Your Name",
                        color = Color(0xFF9ca4b3),
                        fontWeight = FontWeight.Bold,
                        fontSize = 14.sp
                    )
                },
                leadingIcon = {
                    Icon(
                        imageVector = ImageVector.vectorResource(R.drawable.smile),
                        contentDescription = "mail",
                    )
                }
            )

            Spacer(modifier = modifier.size(10.dp))
            var passwordVisible by rememberSaveable { mutableStateOf(false) }
            TextField(
                value = password,
                onValueChange = { password = it },
                modifier = modifier
                    .fillMaxWidth()
                    .border(
                        width = 1.dp,
                        color = Color(0xFFe7e9ec), // Set the border color to gray
                        shape = RoundedCornerShape(4.dp)
                    ),
                colors = colorConfigTextfield,
                textStyle = TextStyle(
                    fontWeight = FontWeight.Bold
                ),
                placeholder = {
                    Text(
                        text = "Create Password",
                        color = Color(0xFF9ca4b3),
                        fontWeight = FontWeight.Bold,
                        fontSize = 14.sp
                    )
                },
                leadingIcon = {
                    Icon(
                        imageVector = ImageVector.vectorResource((R.drawable.lock)),
                        contentDescription = "mail",
                    )
                },
                trailingIcon = {
                    IconButton(
                        onClick = {
                            passwordVisible = !passwordVisible
                        }
                    ) {
                        Icon(
                            imageVector = ImageVector.vectorResource((R.drawable.visible)),
                            contentDescription = "mail",
                            tint = if (passwordVisible) Color(0xFF4E5D78) else Color(0xFF9CA4B3)
                        )
                    }
                },
                visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
            )

            Spacer(modifier = modifier.size(10.dp))

            TextField(
                value = date,
                onValueChange = { if (it.length <= DATE_LENGTH) {
                    date = it
                } },
                modifier = modifier
                    .fillMaxWidth()
                    .border(
                        width = 1.dp,
                        color = Color(0xFFe7e9ec), // Set the border color to gray
                        shape = RoundedCornerShape(4.dp)
                    ),
                colors = colorConfigTextfield,
                textStyle = TextStyle(
                    fontWeight = FontWeight.Bold
                ),
                placeholder = {
                    Text(
                        text = "Date Of Birth",
                        color = Color(0xFF9ca4b3),
                        fontWeight = FontWeight.Bold,
                        fontSize = 14.sp
                    )
                },
                leadingIcon = {
                    Icon(
                        imageVector = ImageVector.vectorResource((R.drawable.calendar)),
                        contentDescription = "mail",
                    )
                },
                visualTransformation = MaskVisualTransformation(DATE_MASK)
            )

            Spacer(modifier = modifier.size(10.dp))

            Row(
                modifier = modifier
                    .fillMaxWidth()
                    .height(60.dp)
                    .border(
                        width = 1.dp,
                        color = Color(0xFFe7e9ec), // Set the border color to gray
                        shape = RoundedCornerShape(4.dp)
                    )
                    .padding(18.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    imageVector = ImageVector.vectorResource((R.drawable.male)),
                    contentDescription = "mail",
                    tint =  Color(0xFF4E5D78),
                )
                Spacer(modifier = modifier.size(20.dp))
                RadioButton(
                    selected = gender == "Male",
                    onClick = { gender = "Male" }
                )
                Text(
                    text = "Male",
                    color = if (gender == "Male") Color(0xFF4E5D78) else Color(0xFF9ca4b3),
                    fontWeight = FontWeight.Bold,
                    fontSize = 14.sp
                )

                RadioButton(
                    selected = gender == "Female",
                    onClick = { gender = "Female" }
                )
                Text(
                    text = "Female",
                    color = if (gender == "Female") Color(0xFF4E5D78) else Color(0xFF9ca4b3),
                    fontWeight = FontWeight.Bold,
                    fontSize = 14.sp
                )
            }

            Spacer(modifier = modifier.size(15.dp))

            Button(
                onClick = {
                    onSignUpButtonClicked(
                        SignUpModel(
                            name = name,
                            email = email,
                            password = password,
                            dateOfBirth = date,
                            gender = gender
                        )
                    )
                },
                colors = ButtonDefaults.buttonColors(
                    containerColor = Blue80,
                ),
                shape = RoundedCornerShape(5.dp),
                modifier = modifier.fillMaxWidth()
            ) {
                Text(
                    text = "Sign Up",
                    color = Color.White,
                )
            }

            Spacer(modifier = modifier.size(15.dp))

            Row(
                modifier = modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center,
            ) {
                Text(
                    text = "Already have an account?",
                    color = Color(0xFF4E5D78),
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold
                )
                Button(
                    onClick = {
                        onNavigateSignInButtonClicked()
                    },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xFFfFFFFFF)
                    ),
                    contentPadding = PaddingValues(0.dp)
                ) {
                    Text(
                        text = "Sign in",
                        color = Color(0xFF377DFF)
                    )
                }
            }
        }
    }
}

