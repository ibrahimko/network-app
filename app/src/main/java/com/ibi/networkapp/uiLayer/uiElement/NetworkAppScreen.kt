package com.ibi.networkapp.uiLayer.uiElement

import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState

sealed class BottomNavItem(val route: String, val label: String, val icon: ImageVector) {
    object FriendsList : BottomNavItem("Friends", "Friends", Icons.Default.Person)
    object AddNewFriend : BottomNavItem("Add", "Add new friend", Icons.Default.Add)
    object Profile : BottomNavItem("profile", "Profile", Icons.Default.AccountCircle)
    object Login : BottomNavItem("login", "Login", Icons.Default.Lock)
    object Logout : BottomNavItem("logout", "Logout", Icons.Default.Lock)
}

@Composable
fun BottomNavigationBar(
    navController: NavHostController,
    items: List<BottomNavItem>
) {
    NavigationBar(
        containerColor = Color(0xFF673AB7), // purple background
        contentColor = Color.White
    ) {
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute = navBackStackEntry?.destination?.route

        items.forEach { item ->
            NavigationBarItem(
                icon = { Icon(item.icon, contentDescription = item.label) },
                label = { Text(item.label) },
                selected = currentRoute == item.route,
                colors = NavigationBarItemDefaults.colors(
                    selectedIconColor = Color.Yellow,
                    unselectedIconColor = Color.White,
                    selectedTextColor = Color.Yellow,
                    unselectedTextColor = Color.White,
                    indicatorColor = Color(0xFF9575CD) // lighter purple indicator
                ),
                onClick = {
                    navController.navigate(item.route) {
                        popUpTo(navController.graph.startDestinationId) { saveState = true }
                        launchSingleTop = true
                        restoreState = true
                    }
                }
            )
        }
    }
}


@Composable
fun MainScreen(navController: NavHostController) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route
    val items = listOf(
        BottomNavItem.FriendsList,
        BottomNavItem.AddNewFriend,
        BottomNavItem.Profile,
        BottomNavItem.Logout
    )

    Scaffold(
        bottomBar = {
                if (currentRoute != BottomNavItem.Login.route)
                    BottomNavigationBar(navController, items) }
    ) { innerPadding ->
        NavHost(
            navController,
            startDestination = BottomNavItem.Login.route,
            modifier = Modifier.padding(innerPadding)
        ) {
            composable(BottomNavItem.Login.route) { LoginScreen( navController ) }
            composable(BottomNavItem.FriendsList.route) { FriendsScreen(navController) }
            composable(BottomNavItem.AddNewFriend.route) { AddFriend(navController)  }
            composable(BottomNavItem.Logout.route) {LogoutScreen(navController)}
            composable(BottomNavItem.Profile.route) { ProfileScreen() }
        }
    }
}
