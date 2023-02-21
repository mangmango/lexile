package com.example.myapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.myapplication.ui.theme.MyApplicationTheme
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyApplicationTheme {
                MyAppNavHost()
            }
        }
    }
}

@Composable
fun Main(onNavigateToSearch: () -> Unit) {
    val scaffoldState = rememberScaffoldState(rememberDrawerState(DrawerValue.Closed))
    val scope = rememberCoroutineScope()

    Column(
        verticalArrangement = Arrangement.Top,
        modifier = Modifier
            .fillMaxSize()
    ) {
        Scaffold(
            scaffoldState = scaffoldState,
            topBar = {
                TopAppBar(
                    title = { Text("") },
                    backgroundColor = Color.White,
                    navigationIcon = {
                        IconButton(onClick = {
                            scope.launch {
                                scaffoldState.drawerState.open()
                            }
                        }) {
                            Icon(Icons.Default.Menu, null)
                        }
                    },
                )
            },
            drawerContent = { Menu().DrawerView() },
        ) {
            SearchButton(padding = it, onNavigateToSearch)
        }
    }
}

@Composable
fun SearchButton(padding: PaddingValues, onNavigateToSearch: () -> Unit) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
            .padding(top = padding.calculateTopPadding() + 100.dp)
    ) {
        Image(
            painter = painterResource(id = R.drawable.search_logo),
            contentDescription = null,
            contentScale = ContentScale.FillWidth,
            modifier = Modifier
                .size(150.dp)
                .border(BorderStroke(0.dp, Color.White))
                .padding(20.dp)
        )

        ExtendedFloatingActionButton(
            text = { Text(text = "검색하기") },
            icon = {
                   Icon(Icons.Default.Search, contentDescription = null)
            },
            onClick = { onNavigateToSearch() },
            backgroundColor = Color.White,
            modifier = Modifier
                .width(250.dp)
        )
    }
}

@Composable
fun MyAppNavHost(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
    startDestination: String = "main"
) {
    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = startDestination
    ) {
        composable("main") {
            Main(onNavigateToSearch = { navController.navigate("search") })
        }

        composable("search") {
            Search().SearchScreen(onNavigateToMain = { navController.navigate("main") })
        }
    }
}