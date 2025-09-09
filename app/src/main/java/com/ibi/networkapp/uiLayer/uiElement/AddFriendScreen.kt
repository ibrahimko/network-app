package com.ibi.networkapp.uiLayer.uiElement

import android.widget.Toast
import android.widget.Toast.makeText
import androidx.compose.foundation.background
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import com.ibi.networkapp.uiLayer.data.FriendUiState
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material3.*
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp

@Composable
fun AddFriend(navController: NavHostController) {
    var friendUiState: FriendUiState by remember { mutableStateOf(FriendUiState()) }
    val gradient = Brush.verticalGradient(
        colors = listOf(Color(0xFF42A5F5), Color(0xFF7E57C2))
    )

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(gradient)
            .padding(16.dp)
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
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                Text(
                    text = "Add New Friend",
                    style = MaterialTheme.typography.headlineLarge,
                    color = Color(0xFF673AB7) // Purple title
                )

                StyledTextField(
                    value = friendUiState.firstName,
                    onValueChange = { friendUiState = friendUiState.copy(firstName = it) },
                    label = ("First Name"),
                    leadingIcon = Icons.Default.Person,
                )

                StyledTextField(
                    value = friendUiState.lastName,
                    onValueChange = { friendUiState = friendUiState.copy(lastName = it) },
                    label = ("Last Name"),
                    leadingIcon = Icons.Default.Person,
                )

                StyledTextField(
                    value = friendUiState.email,
                    onValueChange = { friendUiState = friendUiState.copy(email = it) },
                    label = ("Email"),
                    leadingIcon = Icons.Default.Email,
                    keyboardType = KeyboardType.Email
                )

                StyledTextField(
                    value = friendUiState.phoneNumber,
                    onValueChange = { friendUiState = friendUiState.copy(phoneNumber = it) },
                    label = ("Phone Number"),
                    leadingIcon = Icons.Default.Phone,
                    keyboardType = KeyboardType.Phone
                )
                Button(
                    onClick = {
                        makeText(navController.context, "Friend added", Toast.LENGTH_SHORT).show()
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
                    Icon(Icons.Default.Check, contentDescription = null)
                    Spacer(modifier = Modifier.width(8.dp))
                    Text("Submit")
                }
            }
        }
    }
}



