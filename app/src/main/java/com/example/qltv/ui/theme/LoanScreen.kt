package com.example.qltv.ui.theme

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
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
import com.example.qltv.viewmodel.Book
import com.example.qltv.viewmodel.LibraryViewModel

@Composable
fun LoanScreen(viewModel: LibraryViewModel) {
    // Sử dụng collectAsState để quan sát StateFlow
    val loans by viewModel.loans.collectAsState()
    val books by viewModel.books.collectAsState()
    val users by viewModel.users.collectAsState()

    // State để theo dõi sách và người dùng được chọn
    var selectedBook by remember { mutableStateOf<Book?>(null) }
    var selectedUser by remember { mutableStateOf<String?>(null) }

    // Dropdown state
    var showBookDropdown by remember { mutableStateOf(false) }
    var showUserDropdown by remember { mutableStateOf(false) }

    Column(modifier = Modifier.padding(16.dp)) {
        Text("Danh sách Mượn Sách", fontSize = 20.sp, fontWeight = FontWeight.Bold)

        LazyColumn(
            modifier = Modifier
                .weight(1f)
                .padding(vertical = 8.dp)
        ) {
            items(loans) { loan ->
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 4.dp)
                ) {
                    Text(
                        text = loan,
                        modifier = Modifier.padding(16.dp)
                    )
                }
            }
        }

        // Hiển thị sách được chọn
        Text(
            text = "Sách: ${selectedBook?.title ?: "Chưa chọn sách"}",
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 4.dp)
        )

        // Dropdown menu cho sách
        Button(
            onClick = { showBookDropdown = true },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Chọn Sách")
        }

        DropdownMenu(
            expanded = showBookDropdown,
            onDismissRequest = { showBookDropdown = false }
        ) {
            books.forEach { book ->
                DropdownMenuItem(
                    text = { Text("${book.title} - ${book.author}") },
                    onClick = {
                        selectedBook = book
                        showBookDropdown = false
                    }
                )
            }
        }

        // Hiển thị người dùng được chọn
        Text(
            text = "Người dùng: ${selectedUser ?: "Chưa chọn người dùng"}",
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 4.dp)
        )

        // Dropdown menu cho người dùng
        Button(
            onClick = { showUserDropdown = true },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Chọn Người Dùng")
        }

        DropdownMenu(
            expanded = showUserDropdown,
            onDismissRequest = { showUserDropdown = false }
        ) {
            users.forEach { user ->
                DropdownMenuItem(
                    text = { Text(user) },
                    onClick = {
                        selectedUser = user
                        showUserDropdown = false
                    }
                )
            }
        }

        // Nút mượn sách
        Button(
            onClick = {
                selectedBook?.let { book ->
                    selectedUser?.let { user ->
                        viewModel.loanBook(book, user)
                    }
                }
            },
            enabled = selectedBook != null && selectedUser != null,
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 8.dp)
        ) {
            Text("Mượn Sách")
        }
    }
}