package com.example.qltv.data

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [Book::class, User::class, Loan::class], version = 1)
abstract class LibraryDatabase : RoomDatabase() {
    abstract fun libraryDao(): LibraryDao

    companion object {
        @Volatile
        private var INSTANCE: LibraryDatabase? = null

    }
}