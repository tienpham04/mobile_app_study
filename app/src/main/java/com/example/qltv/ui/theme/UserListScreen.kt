package com.example.qltv.ui.theme

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.qltv.viewmodel.LibraryViewModel

@Composable
fun UserListScreen(viewModel: LibraryViewModel) {
    // Sử dụng collectAsState để quan sát StateFlow
    val users by viewModel.users.collectAsState()

    // State cho việc thêm người dùng mới
    var newUser by remember { mutableStateOf("") }

    Column(modifier = Modifier.padding(16.dp)) {
        Text("Danh sách Người dùng", fontSize = 20.sp, fontWeight = FontWeight.Bold)

        LazyColumn(
            modifier = Modifier
                .weight(1f)
                .padding(vertical = 8.dp)
        ) {
            items(users) { user ->
                UserItem(user)
            }
        }

        // TextField để nhập tên người dùng mới
        OutlinedTextField(
            value = newUser,
            onValueChange = { newUser = it },
            label = { Text("Tên người dùng mới") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp)
        )

        Button(
            onClick = {
                if (newUser.isNotBlank()) {
                    viewModel.addUser(newUser)
                    newUser = ""  // Xóa nội dung sau khi thêm
                }
            },
            enabled = newUser.isNotBlank(),
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Thêm Người dùng")
        }
    }
}

@Composable
fun UserItem(user: String) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp)
    ) {
        Text(
            text = user,
            modifier = Modifier.padding(16.dp),
            fontWeight = FontWeight.Medium
        )
    }
}