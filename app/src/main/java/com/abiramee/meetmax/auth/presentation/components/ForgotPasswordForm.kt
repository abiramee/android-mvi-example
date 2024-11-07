package com.abiramee.meetmax.auth.presentation.components

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
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBackIosNew
import androidx.compose.material.icons.filled.ArrowLeft
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxColors
import androidx.compose.material3.CheckboxDefaults
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
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.abiramee.meetmax.R
import com.abiramee.meetmax.auth.domain.ForgotPasswordModel
import com.abiramee.meetmax.auth.presentation.AuthAction
import com.abiramee.meetmax.auth.presentation.DateDefaults.DATE_LENGTH
import com.abiramee.meetmax.auth.presentation.DateDefaults.DATE_MASK
import com.abiramee.meetmax.core.domain.util.MaskVisualTransformation
import com.abiramee.meetmax.ui.theme.Blue80
import com.abiramee.meetmax.ui.theme.MeetMaxTheme

@Composable
fun ForgotPasswordForm(
    modifier: Modifier = Modifier,
    onNavigateSignIn: (AuthAction) -> Unit,
    onForgotPasswordSendClick: (ForgotPasswordModel) -> Unit
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
            var email by remember { mutableStateOf("") }
            var password by remember { mutableStateOf("") }
            var rememberMe by remember { mutableStateOf(false) }

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


            Spacer(modifier = modifier.size(15.dp))

            Button(
                onClick = {
                    onForgotPasswordSendClick(ForgotPasswordModel(email = email))
                },
                colors = ButtonDefaults.buttonColors(
                    containerColor = Blue80,
                ),
                shape = RoundedCornerShape(5.dp),
                modifier = modifier.fillMaxWidth()
            ) {
                Text(
                    text = "Send",
                    color = Color.White,

                    )
            }

            Spacer(modifier = modifier.size(15.dp))

            Row(
                modifier = modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center,
            ) {
                Button(
                    onClick = {onNavigateSignIn(AuthAction.OnNavigateSignInClick)},
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xFFfFFFFFF)
                    ),
                    contentPadding = PaddingValues(5.dp)
                ) {
                    Icon(Icons.Filled.ArrowBackIosNew, "", modifier = modifier.size(10.dp), tint = Blue80)
                    Spacer(modifier = modifier.size(8.dp))
                    Text(
                        text = "Back to Sign In",
                        color = Color(0xFF377DFF)
                    )
                }
            }
        }
    }
}
