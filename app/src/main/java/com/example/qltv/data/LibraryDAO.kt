package com.example.qltv.data

import androidx.room.*

@Dao
interface LibraryDao {
    // Sách
    @Insert
    suspend fun insertBook(book: Book)

    // Người dùng
    @Insert
    suspend fun insertUser(user: User)

    // Mượn sách
    @Insert
    suspend fun insertLoan(loan: Loan)

}

// Data class để hiển thị thông tin mượn sách
data class LoanDetails(
    val title: String,
    val name: String,
    val date: String
)
