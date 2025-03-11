package com.example.qltv

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.List
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.qltv.ui.theme.BookListScreen
import com.example.qltv.ui.theme.LoanScreen
import com.example.qltv.ui.theme.UserListScreen
import com.example.qltv.viewmodel.LibraryViewModel
import androidx.lifecycle.viewmodel.compose.viewModel


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MaterialTheme(
                colorScheme = lightColorScheme()
            ) {
                LibraryManagementScreen()
            }
        }
    }
}

@Composable
fun LibraryManagementScreen() {
    val viewModel: LibraryViewModel = viewModel()
    var selectedScreen by remember { mutableStateOf("Quản lý") }

    Scaffold(
        bottomBar = {
            BottomNavigationBar { selectedScreen = it }
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            when (selectedScreen) {
                "Quản lý" -> BookListScreen(viewModel)
                "DS Sách" -> UserListScreen(viewModel)
                "Nhân viên" -> LoanScreen(viewModel)
            }
        }
    }
}

@Composable
fun BottomNavigationBar(onScreenSelected: (String) -> Unit) {
    var selectedScreen by remember { mutableStateOf("Quản lý") }

    NavigationBar {
        NavigationBarItem(
            icon = { Icon(Icons.Default.Home, contentDescription = "Quản lý") },
            label = { Text("Quản lý") },
            selected = selectedScreen == "Quản lý",
            onClick = {
                selectedScreen = "Quản lý"
                onScreenSelected(selectedScreen)
            }
        )
        NavigationBarItem(
            icon = { Icon(Icons.AutoMirrored.Filled.List, contentDescription = "DS Sách") },
            label = { Text("DS Sách") },
            selected = selectedScreen == "DS Sách",
            onClick = {
                selectedScreen = "DS Sách"
                onScreenSelected(selectedScreen)
            }
        )
        NavigationBarItem(
            icon = { Icon(Icons.Default.Person, contentDescription = "Nhân viên") },
            label = { Text("Nhân viên") },
            selected = selectedScreen == "Nhân viên",
            onClick = {
                selectedScreen = "Nhân viên"
                onScreenSelected(selectedScreen)
            }
        )
    }
}
