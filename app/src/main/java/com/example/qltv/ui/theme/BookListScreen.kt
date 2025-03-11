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
import com.example.qltv.viewmodel.Book
import com.example.qltv.viewmodel.LibraryViewModel

@Composable
fun BookListScreen(viewModel: LibraryViewModel) {
    // Sử dụng collectAsState để quan sát StateFlow
    val books by viewModel.books.collectAsState()

    // State cho việc thêm sách mới
    var newBookTitle by remember { mutableStateOf("") }
    var newBookAuthor by remember { mutableStateOf("") }

    Column(modifier = Modifier.padding(16.dp)) {
        Text("Danh sách Sách", fontSize = 20.sp, fontWeight = FontWeight.Bold)

        LazyColumn(modifier = Modifier.weight(1f)) {
            items(books) { book ->
                BookItem(book)
            }
        }

        // TextField để nhập tiêu đề sách mới
        OutlinedTextField(
            value = newBookTitle,
            onValueChange = { newBookTitle = it },
            label = { Text("Tiêu đề sách") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 4.dp)
        )

        // TextField để nhập tên tác giả
        OutlinedTextField(
            value = newBookAuthor,
            onValueChange = { newBookAuthor = it },
            label = { Text("Tác giả") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 4.dp)
        )

        Button(
            onClick = {
                if (newBookTitle.isNotBlank() && newBookAuthor.isNotBlank()) {
                    viewModel.addBook(newBookTitle, newBookAuthor)
                    // Xóa nội dung sau khi thêm
                    newBookTitle = ""
                    newBookAuthor = ""
                }
            },
            enabled = newBookTitle.isNotBlank() && newBookAuthor.isNotBlank(),
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Thêm Sách")
        }
    }
}

@Composable
fun BookItem(book: Book) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(text = book.title, fontWeight = FontWeight.Bold)
            Text(text = "Tác giả: ${book.author}")
        }
    }
}