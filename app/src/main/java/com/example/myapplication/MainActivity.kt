package com.example.myapplication

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.myapplication.ui.theme.MyApplicationTheme
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyApplicationTheme {
                // A surface container using the 'background' color from the theme
                Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colors.background) {
                    Screen()
                }
            }
        }
    }
}

//@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun Screen() {
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
            drawerContent = { DrawerView() },
            content = {
                Search()
            }
        )
    }
//    Search()
}

@Preview
@Composable
fun DrawerView() {
    val navItems = listOf(
        NavItem(
            name = "Home",
            route = "home",
            icon = Icons.Default.Info,
            index = 0
        ),
        NavItem(
            name = "Search",
            route = "search",
            icon = Icons.Default.Search,
            index = 1
        )
    )

    Column(
        modifier = Modifier.fillMaxHeight()
    ) {
//        items(navItems.size) { index ->
//            val item = navItems[index]
        navItems.forEach { item ->

            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp)
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .padding(horizontal = 15.dp)
                ) {
                    Icon(imageVector = item.icon, contentDescription = null)
                    Text(item.name, modifier = Modifier.padding(horizontal = 5.dp))
                }
            }
        }
    }

}

@Composable
fun Search() {
    val (search, setSearch) = remember { mutableStateOf("") }
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier
            .fillMaxSize()
    ) {
        Image(
            painter = painterResource(id = R.drawable.ic_launcher),
            contentDescription = null,
            contentScale = ContentScale.FillWidth,
            modifier = Modifier
                .size(150.dp)
                .border(BorderStroke(0.dp, Color.White))
                .padding(20.dp)
        )
        OutlinedTextField(
            value = search,
            onValueChange = { setSearch(it) },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
            placeholder = { Text("검색") },
            leadingIcon = @Composable {
                Icon(Icons.Default.Search, contentDescription = null)
            },
        )
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    MyApplicationTheme {
        Screen()
    }
}

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Preview(showBackground = true)
@Composable
fun Test() {
    val scaffoldState = rememberScaffoldState()

    MyApplicationTheme {
        val navItems = listOf(
            NavItem(
                name = "Home",
                route = "home",
                icon = Icons.Default.Info,
                index = 0
            ),
            NavItem(
                name = "Search",
                route = "search",
                icon = Icons.Default.Search,
                index = 1
            )
        )

        Scaffold(
            scaffoldState = scaffoldState,
            topBar = {},
            drawerContent = {
                Column(
                    modifier = Modifier
                        .fillMaxSize(),
//                        .padding(start = 8.dp, top = 32.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                ) {
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(4.dp)
                    ) {
                        navItems.forEach { item ->
                            Row(
                                modifier = Modifier
                                    .padding(vertical = (item.index * 30).dp),
                            ) {
                                Icon(imageVector = item.icon, contentDescription = null)
                                Text(item.name)
                            }
                        }
                    }
                }
            },
//            drawerGesturesEnabled = true,
        ) {}
    }
}