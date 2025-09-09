package com.ibi.networkapp.uiLayer.uiElement

import android.widget.Toast
import android.widget.Toast.makeText
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.ibi.networkapp.uiLayer.data.LoginUiState

@Composable
fun LoginScreen(navController: NavHostController) {
    var loginUiState: LoginUiState by remember { mutableStateOf( LoginUiState()) }

    val gradient = Brush.verticalGradient(
        colors = listOf(Color(0xFF42A5F5), Color(0xFF7E57C2)) // same as AddFriend
    )

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(gradient)
            .padding(16.dp),
        contentAlignment = Alignment.Center
    ) {
        Card(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(20.dp),
            elevation = CardDefaults.cardElevation(12.dp),
            colors = CardDefaults.cardColors(containerColor = Color.White)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(20.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "Welcome Back",
                    style = MaterialTheme.typography.headlineLarge,
                    color = Color(0xFF673AB7) // purple accent
                )

                // Email input
                StyledTextField(
                    value = loginUiState.email,
                    onValueChange = { loginUiState = loginUiState.copy(email = it) },
                    label = ("Email"),
                    leadingIcon = (Icons.Default.Email),
                    keyboardType = KeyboardType.Email
                )

                // Password input
                StyledTextField(
                    value = loginUiState.password,
                    onValueChange = { loginUiState = loginUiState.copy(password = it) },
                    label = ("Password"),
                    leadingIcon = (Icons.Default.Lock),
                    keyboardType = KeyboardType.Password,
                )

                Spacer(modifier = Modifier.height(8.dp))

                // Login Button
                Button(
                    onClick = {
                        // TODO: handle login
                        makeText(navController.context, "Login successful! \n welcome back!", Toast.LENGTH_SHORT).show()
                        navController.navigate("Friends")
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(56.dp),
                    shape = RoundedCornerShape(12.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xFF673AB7),
                        contentColor = Color.White
                    )
                ) {
                    Text("Login")
                }

                TextButton(
                    onClick = {
                        // TODO: navigate to register screen
                    }
                ) {
                    Text("Don't have an account? Sign Up", color = Color(0xFF673AB7))
                }
            }
        }
    }
}
