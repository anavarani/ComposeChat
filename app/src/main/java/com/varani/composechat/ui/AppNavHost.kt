package com.varani.composechat.ui

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.varani.composechat.ui.channel.ChannelScreen
import com.varani.composechat.ui.conversation.ConversationScreen

@Composable
fun AppNavHost(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
    startDestination: String = "channelList"
) {
    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = startDestination
    ) {
        composable("channelList") {
            ChannelScreen(
                navigateToConversation = { channelId ->
                    navController.navigate("conversation/$channelId")
                }
            )
        }
        composable(
            route = "conversation/{channelId}",
            arguments = listOf(navArgument("channelId") { type = NavType.StringType })
        ) {
            ConversationScreen()
        }
    }
}