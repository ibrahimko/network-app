package com.ibi.networkapp.uiLayer.uiElement

import android.widget.Toast
import android.widget.Toast.makeText
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController

@Composable
fun LogoutScreen(navController: NavHostController){
    makeText( navController.context , "Logged out was successful!", Toast.LENGTH_SHORT).show()
    navController.navigate("login")
}