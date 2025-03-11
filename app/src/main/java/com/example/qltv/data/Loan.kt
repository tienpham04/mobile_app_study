// entity muon

package com.example.qltv.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "loans")
data class Loan(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val userId: Int,
    val bookId: Int,
    val date: String
)