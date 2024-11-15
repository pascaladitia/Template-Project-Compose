package com.pascal.templateprojectcompose.data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.pascal.templateprojectcompose.data.local.dao.HistoryDao
import com.pascal.templateprojectcompose.data.local.model.HistoryEntity

@Database(
    entities = [HistoryEntity::class],
    version = 1,
    exportSchema = true
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun historyDao(): HistoryDao
}
