package com.example.qltv.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

// Tạo data class cho Book
data class Book(val title: String, val author: String)

class LibraryViewModel : ViewModel() {
    // Cập nhật _books để sử dụng kiểu Book
    private val _books = MutableStateFlow(listOf(
        Book("Sách 01", "Tác giả 01"),
        Book("Sách 02", "Tác giả 02")
    ))
    val books: StateFlow<List<Book>> = _books

    private val _users = MutableStateFlow(listOf("Người dùng A", "Người dùng B"))
    val users: StateFlow<List<String>> = _users

    private val _loans = MutableStateFlow(listOf<String>())
    val loans: StateFlow<List<String>> = _loans

    // Cập nhật hàm addBook để nhận title và author
    fun addBook(title: String, author: String) {
        viewModelScope.launch {
            _books.value += Book(title, author)
        }
    }

    fun addUser(user: String) {
        viewModelScope.launch {
            _users.value += user
        }
    }

    // Cập nhật hàm loanBook để nhận Book thay vì String
    fun loanBook(book: Book, user: String) {
        viewModelScope.launch {
            _loans.value += "$user đã mượn ${book.title} (${book.author})"
        }
    }
}